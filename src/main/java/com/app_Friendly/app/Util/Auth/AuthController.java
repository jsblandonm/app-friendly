package com.app_Friendly.app.Util.Auth;


import com.app_Friendly.app.DTO.LoginDto;
import com.app_Friendly.app.DTO.PeopleDTO;
import com.app_Friendly.app.Util.JWT.JwtResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody PeopleDTO peopleDTO) {
        authService.register(peopleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginDto loginDto) {
        String token = authService.login(loginDto);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody JwtResponse jwtResponse) {
        String token = authService.refreshToken(jwtResponse.getToken());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
