package com.app_Friendly.app.Controller;

import com.app_Friendly.app.DTO.GroupDTO;
import com.app_Friendly.app.model.Group;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.service.ContributionAdjustmentService;
import com.app_Friendly.app.service.GroupService;
import com.app_Friendly.app.service.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ContributionAdjustmentService contributionAdjustmentService;

    @PostMapping
    public Group createGroup(@RequestBody @Valid GroupDTO groupDTO) {
        People owner = peopleService.findById(groupDTO.getOwnerId());
        return groupService.createGroup(groupDTO.getName(), owner);
    }

    @PostMapping("/{groupId}/members")
    public Group addMember(@PathVariable String groupId, @RequestParam String peopleId) {
        People people = peopleService.findById(peopleId);
        return groupService.addMember(groupId, people);
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable String id, @RequestBody GroupDTO groupDTO) {
        return groupService.updateGroup(id, groupDTO.getName());
    }

    @DeleteMapping("/{groupId}/members")
    public void removeMember(@PathVariable String groupId, @RequestParam String peopleId) {
        People people = peopleService.findById(peopleId);
        groupService.removeMember(groupId, people);
    }

    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @PostMapping("/{groupId}/adjust-contributions")
    public void adjustContributions(@PathVariable String groupId) {
        contributionAdjustmentService.adjustContributions(groupId);
    }
}
