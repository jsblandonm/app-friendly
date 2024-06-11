package com.app_Friendly.app.service;

import com.app_Friendly.app.Util.Component.JwtFilter;
import com.app_Friendly.app.Util.Component.JwtUtil;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.repository.GroupRepository;
import com.app_Friendly.app.repository.PeopleRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class PeopleService {

    @Autowired
    private final PeopleRepository peopleRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private GridFsOperations gridFsOperations;


    public PeopleService(PeopleRepository peopleRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder,
                         GridFsOperations gridFsOperations){
        this.peopleRepository = peopleRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.gridFsOperations = gridFsOperations;
    }

//    public People addPeople(String name, String email,Long bankAccount, String password){
//
//        // Validar los datos de etrada
//        if (name == null || name.isEmpty()){
//            throw new IllegalArgumentException("El nombre no puede estar vacio");
//        }
//        if (email == null || email.isEmpty()){
//            throw new IllegalArgumentException("El email no puede estar vacio");
//        }
//        if (password == null || password.isEmpty()){
//            throw new IllegalArgumentException("La cotraseña no puede estar vacio");
//        }
//
//        //Verificar si el email ya esta registrado
//        if (peopleRepository.findByEmail(email) != null){
//            throw  new IllegalArgumentException("El correo electronico ya esta registrado");
//        }
//
//        //Hash la contraseña bcrypt
//        String hashedPassword = bCryptPasswordEncoder.encode(password);
//        People people= new People(name,email,hashedPassword);
//
//        people.setBankAccount(bankAccount);
//
//        return peopleRepository.save(people);
//
//    }

    public People updatePeople(String id, String name, String email,Long bankAccount, String password, String imageUrl) {
        People people = peopleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La persona no existe"));

        if (name != null && !name.isEmpty()) {
            people.setName(name);
        }
        if (email != null && !email.isEmpty()) {
            // Verificar si el correo electrónico ya está registrado para otra persona
            if (!email.equals(people.getEmail()) && peopleRepository.findByEmail(email) != null) {
                throw new IllegalArgumentException("El correo electrónico ya está registrado");
            }
            people.setEmail(email);
        }
        if (bankAccount != null) {
            people.setBankAccount(bankAccount);
        }
        if (password != null && !password.isEmpty()) {
            people.setPassword(password);
        }
        if (imageUrl != null && !imageUrl.isEmpty()){
            people.setImageUrl(imageUrl);
        }

        return peopleRepository.save(people);
    }

    public People findById(String id){
        return peopleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La persona no existe"));
    }

//    public People login(String email, String password){
//        People people = peopleRepository.findByEmail(email);
//        if (people != null && people.authenticate(email,password)){
//            return people;
//        }
//        return null;
//    }
    public List<People> getPeoples(){
        return peopleRepository.findAll();
    }

    public String uploadImage(MultipartFile file) {
//        try {
//            ObjectId objectId = gridFsOperations.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
//            return objectId.toString();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return "https://example.com/images/" + file.getOriginalFilename();
    }

//    public ResponseEntity<Resource> getImage(String imageUrl){
//        GridFSFile file = gridFsOperations.findOne(new Query(Criteria.where("_id").is(imageUrl)));
//
//        if (file != null) {
//            GridFsResource resource = gridFsOperations.getResource(file);
//            HttpHeaders headers = new HttpHeaders();
//            try {
//                headers.setContentType(MediaType.valueOf(resource.getContentType()));
//                headers.setContentLength(resource.contentLength());
//
//                return ResponseEntity.ok()
//                        .headers(headers)
//                        .body(new InputStreamResource(resource.getInputStream()));
//            } catch (IOException e) {
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }
}


