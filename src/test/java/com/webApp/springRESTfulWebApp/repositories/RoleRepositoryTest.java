package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entities.RoleEntity;
import com.webApp.springRESTfulWebApp.security.Roles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    final void findByName(){
        RoleEntity userRoleEntity = roleRepository.findByName(Roles.ROLE_USER.name());
        assertNotNull(userRoleEntity);
        assertEquals(Roles.ROLE_USER.name(), userRoleEntity.getName());

        RoleEntity adminRoleEntity = roleRepository.findByName(Roles.ROLE_ADMIN.name());
        assertNotNull(adminRoleEntity);
        assertEquals(Roles.ROLE_ADMIN.name(), adminRoleEntity.getName());




    }
}
