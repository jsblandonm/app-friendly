package com.app_Friendly.app.controller;

import com.app_Friendly.app.DTO.PeopleDTO;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @PostMapping("/register")
    public People registerPeople(@RequestBody PeopleDTO peopleDTO) {
        return peopleService.registerPeople(peopleDTO.getName(), peopleDTO.getEmail(), peopleDTO.getPassword());
    }

    @PutMapping("/{id}")
    public People updatePeople(@PathVariable String id, @RequestBody PeopleDTO peopleDTO) {
        return peopleService.updatePeople(id, peopleDTO.getName(), peopleDTO.getEmail(), peopleDTO.getPassword(), peopleDTO.getImageUrl());
    }

    @PostMapping("/login")
    public People login(@RequestBody PeopleDTO loginRequest) {
        return peopleService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @GetMapping
    public List<People> getPeoples() {
        return peopleService.getPeoples();
    }

    @PostMapping("/{id}/upload-image")
    public People uploadImage(@PathVariable String id, @RequestParam("file") MultipartFile file){
        String imageUrl = peopleService.uploadImage(file);
        return peopleService.updatePeople(id,null,null,null,imageUrl);
    }
}

