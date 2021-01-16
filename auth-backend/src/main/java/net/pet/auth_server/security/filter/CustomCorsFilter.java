package net.pet.auth_server.security.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class CustomCorsFilter extends OncePerRequestFilter {

    @Value("${custom.cors.allowOrigin}")
    private String allowOrigin;

    @Value("${custom.cors.allowMethods}")
    private String allowMethods;

    @Value("${custom.cors.allowHeaders}")
    private String allowHeaders;

    @Value("${custom.cors.allowCredentials}")
    private String allowCredentials;

    @Value("${custom.cors.maxAge}")
    private String maxAge;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", allowOrigin);

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", allowMethods);
            response.addHeader("Access-Control-Allow-Credentials", allowCredentials);
            response.addHeader("Access-Control-Max-Age", maxAge);
            response.addHeader("Access-Control-Allow-Headers", allowHeaders);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
