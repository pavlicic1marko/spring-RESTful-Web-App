package com.webApp.springRESTfulWebApp.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "authorities")
@Data
public class AuthorityEntity implements Serializable {

    private static final long serialVersionUID = -736480279640972181L;

    public AuthorityEntity(String name) {
        this.name = name;
    }

    public AuthorityEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Collection<RoleEntity> roles;

    
}
