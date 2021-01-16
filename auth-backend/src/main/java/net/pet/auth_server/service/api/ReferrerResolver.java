package net.pet.auth_server.service.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ReferrerResolver {
    void resolve(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
