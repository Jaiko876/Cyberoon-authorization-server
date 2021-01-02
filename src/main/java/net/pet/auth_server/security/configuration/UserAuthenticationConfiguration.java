package net.pet.auth_server.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class UserAuthenticationConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private Oauth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .mvcMatchers("/login/**", "/oauth/**", "/oauth2/**")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .and()
                .oauth2Login()
                .successHandler(oauth2AuthenticationSuccessHandler);
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


}
