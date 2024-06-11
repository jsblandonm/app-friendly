package com.app_Friendly.app.service;

import com.app_Friendly.app.model.Contribution;
import com.app_Friendly.app.model.Group;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.repository.ContributionRepository;
import com.app_Friendly.app.repository.GroupRepository;
import com.app_Friendly.app.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private GroupRepository groupRepository;

    public Contribution createContribution(People people, Group group, double amount) {
        Contribution contribution = new Contribution(people, group, amount);
        contribution = contributionRepository.save(contribution);

        //Actualizar el monto total de contribucion del grupo
        double currentContribution = people.getContribution(group);
        people.addContribution(group, currentContribution + amount);
        peopleRepository.save(people);

        //Actualizar el monto total de contribuciones del grupo
        group.setTotalAmount(group.getTotalAmount() + amount);
        groupRepository.save(group);

        return contribution;
    }


    public List<Contribution> getContributions(){
        return contributionRepository.findAll();
    }
}
