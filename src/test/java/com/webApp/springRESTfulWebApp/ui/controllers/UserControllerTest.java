package com.webApp.springRESTfulWebApp.ui.controllers;


import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.dto.UpdateUserDto;
import com.webApp.springRESTfulWebApp.dto.UserDto;
import com.webApp.springRESTfulWebApp.exceptions.customexceptions.UserControllerException;
import com.webApp.springRESTfulWebApp.model.request.AddressInformationRequestModel;
import com.webApp.springRESTfulWebApp.model.request.UserInformationRequestModel;
import com.webApp.springRESTfulWebApp.model.response.AddressInformationResponseModel;
import com.webApp.springRESTfulWebApp.model.response.UserInformationResponseModel;
import com.webApp.springRESTfulWebApp.service.implementation.AddressServiceImplementation;
import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
import com.webApp.springRESTfulWebApp.shared.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImplementation userServiceImplementation;

    @Mock
    Utils utils;

    @Mock
    AddressServiceImplementation addressServiceImplementation;


    private String email = "testemail@test.com";
    private String firstName = "Marko";
    private String lastName = "Pavlicic";
    private String userId = "testID";

    private String streetNumber = "12";
    private String streetName = "1st";
    private String city = "NY";
    private String addressType = "work";
    private String addressId = "testID";

    private AddressDto addressDto;
    private UserDto userdto;
    private UpdateUserDto updateUserDto;
    private UserInformationRequestModel userInformationRequestModel;
    private AddressInformationRequestModel addressInformationRequestModel = new AddressInformationRequestModel();
    private List<AddressInformationRequestModel> addressInformationRequestModels = new ArrayList<>();

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
        userdto.setUserId(userId);

        updateUserDto =  new UpdateUserDto();
        updateUserDto.setAddresses(addressDtoList);
        updateUserDto.setFirstName(firstName);
        updateUserDto.setEmail(email);
        updateUserDto.setLastName(lastName);
        updateUserDto.setUserId(userId);

        userInformationRequestModel = new UserInformationRequestModel();
        userInformationRequestModel.setFirstName(firstName);
        userInformationRequestModel.setEmail(email);
        userInformationRequestModel.setLastName(lastName);


        addressInformationRequestModel.setStreetNumber(streetNumber);
        addressInformationRequestModel.setStreetName(streetName);
        addressInformationRequestModel.setCity(city);
        addressInformationRequestModel.setAddressType(addressType);
        addressInformationRequestModel.setAddressId(addressId);
        addressInformationRequestModels.add(0, addressInformationRequestModel);
        userInformationRequestModel.setAddresses(addressInformationRequestModels);



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
        assertEquals(streetName, userInformation.getAddresses().get(0).getStreetName());
        assertEquals(streetNumber, userInformation.getAddresses().get(0).getStreetNumber());
        assertEquals(addressType, userInformation.getAddresses().get(0).getAddressType());
        assertEquals(addressId, userInformation.getAddresses().get(0).getAddressId());

    }

    @Test
    final void deleteUser() {
        when(userServiceImplementation.deleteUser(anyString())).thenReturn(userdto);
        UserInformationResponseModel userInformation = userController.deleteUser(userId);
        assertNotNull(userInformation);
        assertEquals(email, userInformation.getEmail());
        assertEquals(firstName, userInformation.getFirstName());
        assertEquals(lastName, userInformation.getLastName());
        assertEquals(userdto.getAddresses().size(), userInformation.getAddresses().size());
        assertEquals(city, userInformation.getAddresses().get(0).getCity());
        assertEquals(streetName, userInformation.getAddresses().get(0).getStreetName());
        assertEquals(streetNumber, userInformation.getAddresses().get(0).getStreetNumber());
        assertEquals(addressType, userInformation.getAddresses().get(0).getAddressType());
        assertEquals(addressId, userInformation.getAddresses().get(0).getAddressId());
    }

    @Test
    final void updateUser() {
        when(userServiceImplementation.updateUser(anyString(), any(UserDto.class))).thenReturn(updateUserDto);
        UpdateUserDto userInformation = userController.updateUser(userId, userInformationRequestModel);
        assertNotNull(userInformation);
        assertEquals(email, userInformation.getEmail());
        assertEquals(firstName, userInformation.getFirstName());
        assertEquals(lastName, userInformation.getLastName());
        assertEquals(userInformationRequestModel.getAddresses().size(), userInformation.getAddresses().size());
        assertEquals(city, userInformation.getAddresses().get(0).getCity());
        assertEquals(streetName, userInformation.getAddresses().get(0).getStreetName());
        assertEquals(streetNumber, userInformation.getAddresses().get(0).getStreetNumber());
        assertEquals(addressType, userInformation.getAddresses().get(0).getAddressType());
        assertEquals(addressId, userInformation.getAddresses().get(0).getAddressId());
    }

    @Test
    final void createUser() {
        when(userServiceImplementation.createUser(any(UserDto.class))).thenReturn(userdto);
        UserInformationResponseModel userInformation = userController.createUser(userInformationRequestModel);
        assertNotNull(userInformation);
        assertEquals(email, userInformation.getEmail());
        assertEquals(firstName, userInformation.getFirstName());
        assertEquals(lastName, userInformation.getLastName());
        assertEquals(userInformationRequestModel.getAddresses().size(), userInformation.getAddresses().size());
        assertEquals(city, userInformation.getAddresses().get(0).getCity());
        assertEquals(streetName, userInformation.getAddresses().get(0).getStreetName());
        assertEquals(streetNumber, userInformation.getAddresses().get(0).getStreetNumber());
        assertEquals(addressType, userInformation.getAddresses().get(0).getAddressType());
        assertEquals(addressId, userInformation.getAddresses().get(0).getAddressId());
    }

    @Test
    final void createUser_testUserControllerException() {
        userInformationRequestModel.setAddresses(null);
        assertThrows(UserControllerException.class,
                () -> userController.createUser(userInformationRequestModel)
        );

    }

    @Test
    final void getUserAddresses() {
        when(addressServiceImplementation.getAddressesByUserId(anyString())).thenReturn(addressDtoList);
        List<AddressInformationResponseModel> addressList = userController.getUserAddresses(addressId);
        assertNotNull(addressList);
        assertEquals(addressDtoList.size(), addressList.size());
        assertEquals(city, addressList.get(0).getCity());
        assertEquals(streetName, addressList.get(0).getStreetName());
        assertEquals(streetNumber, addressList.get(0).getStreetNumber());
        assertEquals(addressType, addressList.get(0).getAddressType());
        assertEquals(addressId, addressList.get(0).getAddressId());


    }


}
