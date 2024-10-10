package net.enginnerringdigest.journalapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.enginnerringdigest.journalapp.entity.ConfigJournalAppEntity;

public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId>{
    
}
