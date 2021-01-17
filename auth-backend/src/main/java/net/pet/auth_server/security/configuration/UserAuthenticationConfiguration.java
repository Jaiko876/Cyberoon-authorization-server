package net.pet.auth_server.security.configuration;

import net.pet.auth_server.security.handler.Oauth2AuthenticationSuccessHandler;
import net.pet.auth_server.security.handler.UsernamePasswordAuthenticationSuccessHandler;
import net.pet.auth_server.service.AuthOidcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletResponse;

@Configuration
@Order(1)
@EnableWebSecurity
public class UserAuthenticationConfiguration extends WebSecurityConfigurerAdapter {


    private final Oauth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler;
    private final UsernamePasswordAuthenticationSuccessHandler usernamePasswordAuthenticationSuccessHandler;


    @Autowired
    public UserAuthenticationConfiguration(Oauth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler, UsernamePasswordAuthenticationSuccessHandler usernamePasswordAuthenticationSuccessHandler) {
        this.oauth2AuthenticationSuccessHandler = oauth2AuthenticationSuccessHandler;
        this.usernamePasswordAuthenticationSuccessHandler = usernamePasswordAuthenticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .requestMatchers()
                .mvcMatchers("/login/**", "/registration", "/exception", "/oauth/**", "/oauth2/**")
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/registration", "/exception").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").clearAuthentication(true).logoutSuccessHandler((request, response, authentication) -> {
                     response.setStatus(HttpServletResponse.SC_OK);})
                .and()
                .oauth2Login().loginPage("/login").successHandler(oauth2AuthenticationSuccessHandler)
                .userInfoEndpoint().oidcUserService(new AuthOidcUserService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**", "/*.js", "/*.*.map", "/*.css", "/*.jpg",
                        "/*.woff", "/*.ico", "/*.eot", "/*.ttf", "/*.svg", "/*.png");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        return new CookieCsrfTokenRepository();
    }

    @Bean
    public RedirectStrategy redirectStrategy() {
        return new DefaultRedirectStrategy();
    }

    @Bean
    public HttpSessionRequestCache httpSessionRequestCache() {
        return new HttpSessionRequestCache();
    }
}
