package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import server.entities.User;
import server.exceptions.ResourceNotFoundException;
import server.repositories.UserRepository;
import server.security.UserPrincipal;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either login or email
        User user = userRepository.findByLoginOrEmail(loginOrEmail, loginOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with login or email : " + loginOrEmail)
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}
