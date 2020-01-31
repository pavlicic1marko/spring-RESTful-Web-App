package com.webApp.springRESTfulWebApp.service.implementation;

import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.entities.AddressEntity;
import com.webApp.springRESTfulWebApp.entities.UserEntity;
import com.webApp.springRESTfulWebApp.repositories.AddressRepository;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import com.webApp.springRESTfulWebApp.service.interfaces.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplementation implements AddressService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<AddressDto> getAddresses(Optional<String> type) {
        if(!type.isPresent()) {
            Iterable<AddressEntity> addressEntityIterable = addressRepository.findAll();
            List<AddressDto> addressDtoList = new ArrayList<>();
            addressEntityIterable.forEach(addressEntity -> addressDtoList.add(modelMapper.map(addressEntity, AddressDto.class)));
            return addressDtoList;
        }
        Iterable<AddressEntity> addressEntityIterable = addressRepository.findAllByAddressType(type.get());
        List<AddressDto> addressDtoList = new ArrayList<>();
        addressEntityIterable.forEach(addressEntity -> addressDtoList.add(modelMapper.map(addressEntity, AddressDto.class)));
        return addressDtoList;
    }

    @Override
    public List<AddressDto> getAddressesByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        List<AddressEntity> addressEntitiesList = userEntity.getAddresses();
        List<AddressDto> addressDtoList = new ArrayList<>();
        addressEntitiesList.forEach(address -> addressDtoList.add(modelMapper.map(address, AddressDto.class)));
        return addressDtoList;
    }

    @Override
    public AddressDto getAddressByAddressId(String addressId) {
        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
        return modelMapper.map(addressEntity,AddressDto.class);
    }
}
