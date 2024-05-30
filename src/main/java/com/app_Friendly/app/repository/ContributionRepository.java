package com.app_Friendly.app.repository;

import com.app_Friendly.app.model.Contribution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface ContributionRepository extends MongoRepository<Contribution, String> {
    }
