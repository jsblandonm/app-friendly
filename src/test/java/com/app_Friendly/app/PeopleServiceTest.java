package com.app_Friendly.app;

import com.app_Friendly.app.model.People;
import com.app_Friendly.app.repository.PeopleRepository;
import com.app_Friendly.app.service.PeopleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeopleServiceTest {

    @Mock
    private PeopleRepository peopleRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private PeopleService peopleService;

    private People people;

    @BeforeEach
    void setUp() {
        people = new People("John Doe", "john@example.com", "password");
    }

    @Test
    void testRegisterPeople() {
        when(peopleRepository.findByEmail(any())).thenReturn(null);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("hashedPassword");
        when(peopleRepository.save(any())).thenReturn(people);

        People savedPeople = peopleService.registerPeople("John Doe", "john@example.com", "password");

        assertThat(savedPeople).isEqualTo(people);
        assertThat(savedPeople.getPassword()).isEqualTo("hashedPassword");
    }

    // Agrega más pruebas unitarias para otros métodos del servicio
}