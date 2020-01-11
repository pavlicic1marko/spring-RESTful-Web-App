package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entityes.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
