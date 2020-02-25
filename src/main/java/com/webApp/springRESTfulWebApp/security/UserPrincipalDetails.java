package com.webApp.springRESTfulWebApp.security;

import com.webApp.springRESTfulWebApp.entities.AuthorityEntity;
import com.webApp.springRESTfulWebApp.entities.RoleEntity;
import com.webApp.springRESTfulWebApp.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserPrincipalDetails implements UserDetails {

    private static final long serialVersionUID = 8629191213156914336L;

    private UserEntity userEntity;

    private String userId;

    public UserPrincipalDetails(UserEntity userEntity) {
        this.userEntity =userEntity;
        this.userId = userEntity.getUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<AuthorityEntity> authorityEntities =  new ArrayList<>();

        //Get user Roles
        Collection<RoleEntity> roles =userEntity.getRoles();
        if (roles ==null)return authorities;

        roles.forEach((role)->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorityEntities.addAll(role.getAuthorities());
        });

        authorityEntities.forEach(authorityEntity -> authorities.add(new SimpleGrantedAuthority(authorityEntity.getName())));
        return authorities;

    }

    @Override
    public String getPassword() {
        return userEntity.getEncryptedPassWord();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEntity.isAccountEnabled();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
