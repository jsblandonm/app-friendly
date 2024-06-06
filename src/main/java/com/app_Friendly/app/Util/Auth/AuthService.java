package com.app_Friendly.app.Util.Auth;

import com.app_Friendly.app.Util.Component.JwtUtil;
import com.app_Friendly.app.DTO.LoginDto;
import com.app_Friendly.app.DTO.PeopleDTO;
import com.app_Friendly.app.model.People;
import com.app_Friendly.app.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private final PeopleRepository peopleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(PeopleRepository peopleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.peopleRepository = peopleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }



    public void register(PeopleDTO peopleDTO){
        People people = new People(peopleDTO.getName(), peopleDTO.getEmail(), passwordEncoder.encode(peopleDTO.getPassword()));
        peopleRepository.save(people);
    }

    public String login(LoginDto loginDto){
        People people = peopleRepository.findByEmail(loginDto.getEmail());
        if (people != null && bCryptPasswordEncoder
                .matches(loginDto.getPassword(), people.getPassword())) {
            return jwtUtil.generateToken(people.getEmail());
        }
        throw new RuntimeException("Invalid credentials");
    }

    public String refreshToken(String token) {
        if (jwtUtil.validateToken(token)) {
            String username = jwtUtil.extractUsername(token);
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Token inválido");
    }
}
