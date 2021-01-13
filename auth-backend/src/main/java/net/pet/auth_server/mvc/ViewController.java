package net.pet.auth_server.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String view() {
        return "forward:/index.html";
    }
}
