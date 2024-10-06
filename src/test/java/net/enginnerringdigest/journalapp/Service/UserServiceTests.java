package net.enginnerringdigest.journalapp.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.enginnerringdigest.journalapp.entity.User;
//import net.enginnerringdigest.journalapp.repository.UserRepository;
import net.enginnerringdigest.journalapp.service.UserService;


@SpringBootTest
public class UserServiceTests {

    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private UserService userService;

    
    @ParameterizedTest
    // @ValueSource(strings = {
    //     "ram",
    //     "abhi",
    //     "shyam"
    // })
    @Disabled
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){  
        assertTrue(userService.saveNewUser(user));
        // User user = userRepository.findByUserName("ram");
        // assertTrue(!user.getJournalEntries().isEmpty());
        // assertTrue(5 > 3);
    }
    

    @Disabled
    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "2,10,12",
        "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }


}
