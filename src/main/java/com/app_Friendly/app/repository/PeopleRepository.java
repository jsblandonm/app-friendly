package com.app_Friendly.app.repository;

import com.app_Friendly.app.model.People;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends MongoRepository<People, String> {
    People findByEmail(String email);
}
