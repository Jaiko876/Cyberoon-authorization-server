package net.pet.auth_server.service;

import net.pet.auth_server.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String credentials) throws UsernameNotFoundException {
        return userRepository.findByUsername(credentials)
                .orElseGet(() -> userRepository.findByEmail(credentials)
                        .orElseThrow(() -> new UsernameNotFoundException(credentials)));
    }
}
