package net.enginnerringdigest.journalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

//import net.enginnerringdigest.journalapp.entity.User;
import net.enginnerringdigest.journalapp.repository.UserRepository;


@Component
public class CustomUserDetailsMpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        net.enginnerringdigest.journalapp.entity.User user = userRepository.findByUserName(username);  // Replace with your user entity
        if (user != null) {
            return User.builder()  // Spring Security's User builder
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
    
}
