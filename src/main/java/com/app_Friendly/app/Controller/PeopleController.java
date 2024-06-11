package com.app_Friendly.app.Controller;

import com.app_Friendly.app.DTO.PeopleDTO;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.service.PeopleService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

//    @PostMapping("/addPeople")
//    public People addPeople(@RequestBody @Valid PeopleDTO peopleDTO) {
//        return peopleService.addPeople(peopleDTO.getName(), peopleDTO.getEmail(), peopleDTO.getBankAccount(), peopleDTO.getPassword());
//    }

    @PutMapping("/{id}")
    public People updatePeople(@PathVariable String id, @RequestBody PeopleDTO peopleDTO) {
        return peopleService.updatePeople(id, peopleDTO.getName(), peopleDTO.getEmail(), peopleDTO.getBankAccount(), peopleDTO.getPassword(), peopleDTO.getImageUrl());
    }

//    @PostMapping("/login")
//    public People login(@RequestBody PeopleDTO loginRequest) {
//        return peopleService.login(loginRequest.getEmail(), loginRequest.getPassword());
//    }

    @GetMapping
    public List<People> getPeoples() {
        return peopleService.getPeoples();
    }

    @PostMapping("/{id}/upload-image")
    public People uploadImage(@PathVariable String id, @RequestParam("file") MultipartFile file){
        String imageUrl = peopleService.uploadImage(file);
        return peopleService.updatePeople(id,null,null,null,null,imageUrl);
    }
}

