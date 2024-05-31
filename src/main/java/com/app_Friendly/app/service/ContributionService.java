package com.app_Friendly.app.service;

import com.app_Friendly.app.model.Contribution;
import com.app_Friendly.app.model.Group;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.repository.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

    public Contribution createContribution(People people, Group group, double amount) {
        Contribution contribution = new Contribution(people, group, amount);
        contribution = contributionRepository.save(contribution);
        people.addContribution(group, amount);
        return contribution;
    }



    public List<Contribution> getContributions(){
        return contributionRepository.findAll();
    }
}
