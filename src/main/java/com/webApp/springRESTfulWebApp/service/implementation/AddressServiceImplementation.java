package com.webApp.springRESTfulWebApp.service.implementation;

import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.entities.AddressEntity;
import com.webApp.springRESTfulWebApp.entities.UserEntity;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import com.webApp.springRESTfulWebApp.service.interfaces.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<AddressDto> getAddressesByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        List<AddressEntity> addressEntitiesList = userEntity.getAddresses();
        List<AddressDto> addressDtoList = new ArrayList<>();
        addressEntitiesList.forEach(address -> addressDtoList.add(modelMapper.map(address, AddressDto.class)));
        return addressDtoList;
    }
}
