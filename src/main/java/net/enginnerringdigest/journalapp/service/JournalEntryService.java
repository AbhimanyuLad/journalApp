package net.enginnerringdigest.journalapp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.enginnerringdigest.journalapp.entity.JournalEntry;
import net.enginnerringdigest.journalapp.entity.User;
import net.enginnerringdigest.journalapp.repository.JournalEntryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);// user is extracted
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry); // we saved journalEntry
            user.getJournalEntries().add(saved); // the found user, we are adding into journalEntries
            userService.saveUser(user); // saving user into database
        } catch(Exception e){
            throw new RuntimeException("An error occured while saving the entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry); // saving user into database

    }


    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        } catch (Exception e) {
            log.error("ERROR", e);
            throw new RuntimeException("An error occured while deleting the entry", e);
        }
        return removed;
    }

    public List<JournalEntry> findByUserName(String userName){
        return journalEntryRepository.findAll();
    }

}





// controller ---> service ---> repository











