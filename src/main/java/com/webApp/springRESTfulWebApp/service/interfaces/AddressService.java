package com.webApp.springRESTfulWebApp.service.interfaces;


import com.webApp.springRESTfulWebApp.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAddresses();

    List<AddressDto> getAddressesByUserId(String userId);

    AddressDto getAddressByAddressId(String addressId);
}
