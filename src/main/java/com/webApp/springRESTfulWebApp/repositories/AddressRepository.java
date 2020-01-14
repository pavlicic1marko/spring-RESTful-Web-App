package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entityes.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
