package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entityes.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
