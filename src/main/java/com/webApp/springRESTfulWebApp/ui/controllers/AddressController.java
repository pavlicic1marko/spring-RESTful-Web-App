package com.webApp.springRESTfulWebApp.ui.controllers;

import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.exceptions.customexceptions.ErrorMessageObject;
import com.webApp.springRESTfulWebApp.exceptions.messages.ErrorMessages;
import com.webApp.springRESTfulWebApp.model.response.AddressInformationResponseModel;
import com.webApp.springRESTfulWebApp.service.interfaces.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
    public ResponseEntity<?> getAddresses(@RequestParam(value="type",required = false)String type){
        List<AddressDto>  addressDtoList =addressService.getAddresses(Optional.ofNullable(type));
        if(addressDtoList.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageObject(new Date(), ErrorMessages.ADDRESS_RECORD_NOT_FOUND.getErrorMessage()));
        }
        List<AddressInformationResponseModel> addressInformationResponseModelList = new ArrayList<>();
        addressDtoList.forEach(addressDto -> addressInformationResponseModelList.add(modelMapper.map(addressDto,AddressInformationResponseModel.class)));
        return ResponseEntity.ok((addressInformationResponseModelList));
    }


    @GetMapping(path = "/{addressId}")
    public AddressInformationResponseModel getAddress(@PathVariable String addressId){
        AddressDto addressDto= addressService.getAddressByAddressId(addressId);
        return modelMapper.map(addressDto,AddressInformationResponseModel.class);

    }
}
