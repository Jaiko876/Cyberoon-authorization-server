package net.pet.auth_server.rest;


import net.pet.auth_server.service.model.User;
import net.pet.auth_server.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {

    private final UserRepository userRepository;

    @Autowired
    public UserInfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/userinfo")
    public User userInfo(Principal principal) {
        return userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User with username " + principal.getName() + " not found"));
    }
}
