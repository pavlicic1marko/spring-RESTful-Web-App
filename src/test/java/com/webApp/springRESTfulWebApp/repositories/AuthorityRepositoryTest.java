package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entities.AuthorityEntity;
import com.webApp.springRESTfulWebApp.security.Authorities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
 class AuthorityRepositoryTest {

    @Autowired
    private AuthorityRepository authorityRepository;



    @Test
    final void findByName(){
        AuthorityEntity readAuthorityEntity= authorityRepository.findByName(Authorities.READ_AUTHORITY.name());
        assertNotNull(readAuthorityEntity);
        assertEquals(Authorities.READ_AUTHORITY.name(), readAuthorityEntity.getName());

        AuthorityEntity writAuthorityEntity= authorityRepository.findByName(Authorities.WRITE_AUTHORITY.name());
        assertNotNull(writAuthorityEntity);
        assertEquals(Authorities.WRITE_AUTHORITY.name(), writAuthorityEntity.getName());

        AuthorityEntity deleteAuthorityEntity= authorityRepository.findByName(Authorities.DELETE_AUTHORITY.name());
        assertNotNull(deleteAuthorityEntity);
        assertEquals(Authorities.DELETE_AUTHORITY.name(), deleteAuthorityEntity.getName());








    }
}
