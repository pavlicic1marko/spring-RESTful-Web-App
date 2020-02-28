package com.webApp.springRESTfulWebApp.repositories;


import com.webApp.springRESTfulWebApp.entities.AddressEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    private AddressEntity addressEntity;
    private String addressId="2334556";
    private String city="Boston";
    private String streetName="First Avenue";
    private String streetNumber="12";
    private String addressType="work";

    @BeforeEach
    void SetUp(){
        //Create a address entity and save it
        addressEntity = new AddressEntity();
        addressEntity.setAddressId(addressId);
        addressEntity.setCity(city);
        addressEntity.setStreetName(streetName);
        addressEntity.setStreetNumber(streetNumber);
        addressEntity.setAddressType(addressType);
        addressRepository.save(addressEntity);

    }

    @Test
    final void findAddressEntityByAddressId(){
        AddressEntity entity = addressRepository.findByAddressId(addressId);
        assertNotNull(entity);
        assertEquals(city,entity.getCity());
        assertEquals(streetName,entity.getStreetName());
        assertEquals(streetNumber,entity.getStreetNumber());
        assertEquals(addressType,entity.getAddressType());
    }


}
