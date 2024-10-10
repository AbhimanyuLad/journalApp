package net.enginnerringdigest.journalapp.service;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

//import lombok.extern.slf4j.Slf4j;

import java.util.*;
import net.enginnerringdigest.journalapp.entity.User;
import net.enginnerringdigest.journalapp.repository.UserRepository;

@Service
@Slf4j //--- log.
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public void saveUser(User user){
        userRepository.save(user); 

    }

    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("ERROR occured for {} :",user.getUserName(), e);
            return false;
        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN"));
        userRepository.save(user);
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

}
