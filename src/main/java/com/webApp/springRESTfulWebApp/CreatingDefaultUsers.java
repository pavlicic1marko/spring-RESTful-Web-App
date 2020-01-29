package com.webApp.springRESTfulWebApp;

import com.webApp.springRESTfulWebApp.entities.AuthorityEntity;
import com.webApp.springRESTfulWebApp.entities.RoleEntity;
import com.webApp.springRESTfulWebApp.entities.UserEntity;
import com.webApp.springRESTfulWebApp.repositories.AuthorityRepository;
import com.webApp.springRESTfulWebApp.repositories.RoleRepository;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@Component
public class CreatingDefaultUsers {

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
        AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
        AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
        AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");

        RoleEntity roleUser = createRole("ROLE_USER", Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleAdmin = createRole("ROLE_ADMIN", Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

        if(roleAdmin == null) return;
        UserEntity adminUser = new UserEntity();

        adminUser.setEmail("adminUser@mail.com");
        adminUser.setFirstName("admin");
        adminUser.setLastName("user");
        adminUser.setEncryptedPassWord(bCryptPasswordEncoder.encode("admin"));
        adminUser.setUserId(UUID.randomUUID().toString());
        adminUser.setRoles(Arrays.asList(roleAdmin));
        userRepository.save(adminUser);

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
