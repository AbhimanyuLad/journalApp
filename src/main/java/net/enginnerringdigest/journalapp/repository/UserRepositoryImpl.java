package net.enginnerringdigest.journalapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import net.enginnerringdigest.journalapp.entity.User;

public class UserRepositoryImpl {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUsersForSA(){
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.orOperator(
            Criteria.where("email").exists(true), 
            Criteria.where("sentimentAnalysis").is(true))
        );
        // query.addCriteria(Criteria.where("userName").is("ram"));
        // query.addCriteria(Criteria.where("age").gte(20));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }


}
