package com.webApp.springRESTfulWebApp.ui.controllers;

import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.model.response.AddressInformationResponseModel;
import com.webApp.springRESTfulWebApp.service.interfaces.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Secured("ROLE_ADMIN")
@RequestMapping("address")
@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    private ModelMapper modelMapper = new ModelMapper();


    @GetMapping
    public List<AddressInformationResponseModel> getAddresses(@RequestParam(value="type",required = false)String type){
        List<AddressDto>  addressDtoList =addressService.getAddresses(Optional.ofNullable(type));
        List<AddressInformationResponseModel> addressInformationResponseModelList = new ArrayList<>();
        addressDtoList.forEach(addressDto -> addressInformationResponseModelList.add(modelMapper.map(addressDto,AddressInformationResponseModel.class)));
        return addressInformationResponseModelList;
    }


    @GetMapping(path = "/{addressId}")
    public AddressInformationResponseModel getAddress(@PathVariable String addressId){
        AddressDto addressDto= addressService.getAddressByAddressId(addressId);
        return modelMapper.map(addressDto,AddressInformationResponseModel.class);

    }
}
