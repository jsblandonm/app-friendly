package com.app_Friendly.app.controller;

import com.app_Friendly.app.model.Contribution;
import com.app_Friendly.app.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {
    @Autowired
    private ContributionService contributionService;

    @PostMapping
    public Contribution createContribution(@RequestBody Contribution contribution) {
        return contributionService.createContribution(contribution.getPeople(), contribution.getGroup(), contribution.getAmount());
    }

    @GetMapping
    public List<Contribution> getContributions() {
        return contributionService.getContributions();
    }
}
