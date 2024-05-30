package com.app_Friendly.app.controller;

import com.app_Friendly.app.model.Group;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping
    public Group createGroup(@RequestBody Group group){
        return groupService.createGroup(group.getName());
    }
    @PostMapping("/{groupId}/members")
    public Group addMember(@PathVariable String groupId, @RequestBody People people) {
        return groupService.addMember(groupId, people);
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable String id, @RequestBody Group group) {
        return groupService.updateGroup(id, group.getName());
    }

    @DeleteMapping("/{groupId}/members")
    public void removeMember(@PathVariable String groupId, @RequestBody People people) {
        groupService.removeMember(groupId, people);
    }

    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @PostMapping("/{groupId}/adjust-contributions")
    public void adjustContributions(@PathVariable String groupId) {
        groupService.adjustContributions(groupId);
    }
}