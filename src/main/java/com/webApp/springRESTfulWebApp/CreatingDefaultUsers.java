package com.webApp.springRESTfulWebApp;

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
        AuthorityEntity readAuthority = createAuthority(Authorities.READ_AUTHORITY.name());
        AuthorityEntity writeAuthority = createAuthority(Authorities.WRITE_AUTHORITY.name());
        AuthorityEntity deleteAuthority = createAuthority(Authorities.DELETE_AUTHORITY.name());

        RoleEntity roleUser = createRole(Roles.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

        if(roleAdmin == null) return;
        UserEntity adminUser = new UserEntity();

        adminUser.setEmail("adminUser@mail.com");
        adminUser.setFirstName("admin");
        adminUser.setLastName("user");
        adminUser.setEncryptedPassWord(bCryptPasswordEncoder.encode("1234"));
        adminUser.setUserId("0375a9e2-ceed-4912-a9f8-6112c0a4925e");
        adminUser.setRoles(Arrays.asList(roleAdmin));
        userRepository.save(adminUser);

        if(roleUser==null) return;
        UserEntity regularUser = new UserEntity();
        regularUser.setEmail("regularUser@mail.com");
        regularUser.setFirstName("regular");
        regularUser.setLastName("user");
        regularUser.setEncryptedPassWord(bCryptPasswordEncoder.encode("1234"));
        regularUser.setUserId("694aeba3-e1e5-4bd5-9d7e-4ef2e7f1fa3d");
        regularUser.setRoles(Arrays.asList(roleUser));
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
