package net.pet.auth_server.service.utils;

import net.pet.auth_server.service.api.ReferrerResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ReferrerResolverImpl implements ReferrerResolver {

    @Value("${server-url}")
    private String serverUrl;

    @Value("${client-url}")
    private String clientUrl;

    private final RedirectStrategy redirectStrategy;

    @Autowired
    public ReferrerResolverImpl(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void resolve(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!isReferrerAllowed(request))
            redirectStrategy.sendRedirect(request, response, clientUrl);
    }

    private boolean isReferrerAllowed(HttpServletRequest request) {
        if (request != null && request.getHeader("Referer") != null) {
            String referer = request.getHeader("Referer");
            return referer.contains(serverUrl) || referer.contains(clientUrl);
        }
        return false;
    }
}
