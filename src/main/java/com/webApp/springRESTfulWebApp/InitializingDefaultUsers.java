package com.webApp.springRESTfulWebApp;

import com.webApp.springRESTfulWebApp.entities.AddressEntity;
import com.webApp.springRESTfulWebApp.entities.AuthorityEntity;
import com.webApp.springRESTfulWebApp.entities.RoleEntity;
import com.webApp.springRESTfulWebApp.entities.UserEntity;
import com.webApp.springRESTfulWebApp.repositories.AuthorityRepository;
import com.webApp.springRESTfulWebApp.repositories.RoleRepository;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import com.webApp.springRESTfulWebApp.security.Authorities;
import com.webApp.springRESTfulWebApp.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
//test123
//test feature 2
@Component
class InitializingDefaultUsers {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        AuthorityEntity readAuthority = createAuthority(Authorities.READ_AUTHORITY.name());
        AuthorityEntity writeAuthority = createAuthority(Authorities.WRITE_AUTHORITY.name());
        AuthorityEntity deleteAuthority = createAuthority(Authorities.DELETE_AUTHORITY.name());

        RoleEntity roleUser = createRole(Roles.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

        UserEntity adminUser = new UserEntity();

        adminUser.setEmail("adminuser@mail.com");
        adminUser.setAccountEnabled(true);
        adminUser.setFirstName("admin");
        adminUser.setLastName("user");
        adminUser.setEncryptedPassWord(bCryptPasswordEncoder.encode("1234"));
        adminUser.setUserId("0375a9e2-ceed-4912-a9f8-6112c0a4925e");
        adminUser.setRoles(Arrays.asList(roleAdmin));
        adminUser.setDateCreated(new Date());
        userRepository.save(adminUser);

        List<AddressEntity> addressEntityList = new ArrayList<>();
        AddressEntity addressEntity= new AddressEntity();
        addressEntity.setStreetNumber("12");
        addressEntity.setStreetName("Python");
        addressEntity.setCity("NY");
        addressEntity.setAddressType("work");
        addressEntity.setAddressId("6112c0a4925e-0375a9e2-c88d-4912-a9f8");
        addressEntityList.add(0, addressEntity);

        UserEntity regularUser = new UserEntity();
        regularUser.setEmail("regularuser@mail.com");
        regularUser.setAccountEnabled(false);
        regularUser.setFirstName("regular");
        regularUser.setLastName("user");
        regularUser.setEncryptedPassWord(bCryptPasswordEncoder.encode("1234"));
        regularUser.setUserId("694aeba3-e1e5-4bd5-9d7e-4ef2e7f1fa3d");
        regularUser.setRoles(Arrays.asList(roleUser));
        regularUser.setAddresses(addressEntityList);
        regularUser.setDateCreated(new Date());
        addressEntity.setUserDetails(regularUser);

        userRepository.save(regularUser);

    }

    @Transactional
    private AuthorityEntity createAuthority(String name) {
        AuthorityEntity authority = authorityRepository.findByName(name);
        if (authority == null) {
            authority = new AuthorityEntity(name);
            authorityRepository.save(authority);
        }
        return authority;
    }

    @Transactional
    private RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {
        RoleEntity role = roleRepository.findByName(name);
        if (role == null) {
            role = new RoleEntity(name);
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }
        return role;
    }

}
