package net.pet.auth_server.mvc;

import net.pet.auth_server.service.api.ReferrerResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ViewController {

    private final ReferrerResolver referrerResolver;

    @Autowired
    public ViewController(ReferrerResolver referrerResolver) {
        this.referrerResolver = referrerResolver;
    }

    @GetMapping(value = {"/login", "/registration", "/exception"})
    public String forward(HttpServletRequest request, HttpServletResponse response) throws IOException {
        referrerResolver.resolve(request, response);
        return "forward:/";
    }
}
