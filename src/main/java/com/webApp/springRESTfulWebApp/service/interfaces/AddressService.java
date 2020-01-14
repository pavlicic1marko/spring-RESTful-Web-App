package com.webApp.springRESTfulWebApp.service.interfaces;


import data.transfer.objects.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAddressesByUserId(String userId);
}
