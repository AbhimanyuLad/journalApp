package net.enginnerringdigest.journalapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mongodb.assertions.Assertions;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testSaveNewUser(){
        Assertions.assertNotNull(userRepository.getUsersForSA());
    }
    
}
