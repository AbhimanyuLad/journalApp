package net.enginnerringdigest.journalapp.Service;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import net.enginnerringdigest.journalapp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import com.mongodb.assertions.Assertions;

import net.enginnerringdigest.journalapp.repository.UserRepository;
import net.enginnerringdigest.journalapp.service.CustomUserDetailsMpl;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;


@ActiveProfiles("dev")
public class UserDetailsServiceMpl {

    @InjectMocks
    private CustomUserDetailsMpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserNByUserNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("inindi").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
    
}
