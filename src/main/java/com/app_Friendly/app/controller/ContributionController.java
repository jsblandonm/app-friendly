package com.app_Friendly.app.controller;

import com.app_Friendly.app.DTO.ContributionDTO;
import com.app_Friendly.app.model.Contribution;
import com.app_Friendly.app.model.Group;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.repository.GroupRepository;
import com.app_Friendly.app.repository.PeopleRepository;
import com.app_Friendly.app.service.ContributionService;
import com.app_Friendly.app.service.GroupService;
import com.app_Friendly.app.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {
    @Autowired
    private ContributionService contributionService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private GroupService groupService;

    @PostMapping
    public Contribution createContribution(@RequestBody ContributionDTO contributionDTO) {
        People people = peopleService.findById(contributionDTO.getPeopleId());
        Group group = groupService.findById(contributionDTO.getGroupId());
        return contributionService.createContribution(people, group, contributionDTO.getAmount());
    }

    @GetMapping
    public List<Contribution> getContributions() {
        return contributionService.getContributions();
    }
}
