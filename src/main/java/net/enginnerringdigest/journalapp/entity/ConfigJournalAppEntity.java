package net.enginnerringdigest.journalapp.entity;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "config_journal_api")
@Data
@NoArgsConstructor
public class ConfigJournalAppEntity {

    private String key;
    private String value;
    
}

//https://cloud.mongodb.com/v2/66f044ef41851577f550af56#/metrics/replicaSet/66f0d98542c5a66b940e8cd6/explorer/journaldb/config_journal_api/find
