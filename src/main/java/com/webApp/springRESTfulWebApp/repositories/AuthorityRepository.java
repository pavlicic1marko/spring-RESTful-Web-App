package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entities.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Long> {
    AuthorityEntity findByName(String name);
}
