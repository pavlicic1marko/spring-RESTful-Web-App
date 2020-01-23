package com.webApp.springRESTfulWebApp.ui.controllers;


import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.dto.UserDto;
import com.webApp.springRESTfulWebApp.model.response.UserInformationResponseModel;
import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImplementation userServiceImplementation;

    private String email = "testemail@test.com";
    private String firstName = "Marko";
    private String lastName = "Pavlicic";
    private String userId = "testID";
    private String password = "1234";

    private String streetNumber = "12";
    private String streetName = "1st";
    private String city = "NY";
    private String addressType = "work";
    private String addressId = "testID";

    private AddressDto addressDto;
    private UserDto userdto;
    private List<AddressDto> addressDtoList = new ArrayList<>();

    @BeforeEach
    final void setUp() {
        MockitoAnnotations.initMocks(this);
        addressDto = new AddressDto();
        addressDto.setStreetNumber(streetNumber);
        addressDto.setStreetName(streetName);
        addressDto.setCity(city);
        addressDto.setAddressType(addressType);
        addressDto.setAddressId(addressId);
        addressDtoList.add(0, addressDto);


        userdto = new UserDto();
        userdto.setAddresses(addressDtoList);
        userdto.setFirstName(firstName);
        userdto.setEmail(email);
        userdto.setLastName(lastName);
        userdto.setPassword(password);
        userdto.setUserId(userId);

    }


    @Test
    final void getUser() {
        when(userServiceImplementation.getUserByUserId(anyString())).thenReturn(userdto);
        UserInformationResponseModel userInformation = userController.getUser("testId");
        assertNotNull(userInformation);
        assertEquals(email, userInformation.getEmail());
        assertEquals(firstName, userInformation.getFirstName());
        assertEquals(lastName, userInformation.getLastName());
        assertEquals(userdto.getAddresses().size(), userInformation.getAddresses().size());
        assertEquals(city, userInformation.getAddresses().get(0).getCity());

    }


}
