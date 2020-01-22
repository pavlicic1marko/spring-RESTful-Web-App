package com.webApp.springRESTfulWebApp.ui.controllers;

import com.webApp.springRESTfulWebApp.dto.UserDto;
import com.webApp.springRESTfulWebApp.entityes.UserEntity;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class UserControllerTest {


    @InjectMocks
    UserServiceImplementation userService;

    @Mock
    UserRepository userRepository;

    private UserEntity userEntity;

    private String email = "testemail@test.com";
    private String firstName = "Marko";
    private String lastName = "Pavlicic";

    @BeforeEach
    final void setUp() {
        MockitoAnnotations.initMocks(this);
        userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
    }

    @Test
    final void getUser() {
        when(userRepository.findByUserId(anyString())).thenReturn(userEntity);
        UserDto userDto = userService.getUserByUserId("testId");
        assertNotNull(userDto);
        assertEquals(email, userDto.getEmail());
        assertEquals(firstName, userDto.getFirstName());
        assertEquals(lastName, userDto.getLastName());
    }
}
