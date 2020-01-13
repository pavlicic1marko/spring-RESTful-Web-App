package com.webApp.springRESTfulWebApp.ui.controllers;

import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
import data.transfer.objects.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class UserControllerTest {


    @Mock
    private UserServiceImplementation userService;

    private UserDto userDto;

    private String email = "junit@test.com";

    @BeforeEach
    final void setUp() {
        MockitoAnnotations.initMocks(this);
        userDto = new UserDto();
        userDto.setEmail(email);
    }

    @Test
    final void getUser() {
        when(userService.getUserByUserId(anyString())).thenReturn(userDto);
        assertEquals(userDto.getEmail(), email);
    }
}
