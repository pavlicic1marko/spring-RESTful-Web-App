package com.webApp.springRESTfulWebApp.ui.controllers;

import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.model.response.AddressInformationResponseModel;
import com.webApp.springRESTfulWebApp.service.interfaces.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("address")
@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    private ModelMapper modelMapper = new ModelMapper();


    @GetMapping(path = "/{addressId}")
    public AddressInformationResponseModel getAddress(@PathVariable String addressId){
        AddressDto addressDto= addressService.getAddressByAddressId(addressId);
        return modelMapper.map(addressDto,AddressInformationResponseModel.class);

    }
}
