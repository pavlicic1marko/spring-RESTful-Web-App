package com.webApp.springRESTfulWebApp.service.interfaces;


import com.webApp.springRESTfulWebApp.dto.AddressDto;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<AddressDto> getAddresses(Optional<String> type);

    List<AddressDto> getAddressesByUserId(String userId);

    AddressDto getAddressByAddressId(String addressId);
}
