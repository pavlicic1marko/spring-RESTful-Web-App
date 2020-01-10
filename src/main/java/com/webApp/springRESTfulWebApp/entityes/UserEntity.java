package com.webApp.springRESTfulWebApp.entityes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 4888534534534534538L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50)
    private UUID uuid;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String encryptedPassWord;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassWord() {
        return encryptedPassWord;
    }

    public void setEncryptedPassWord(String encryptedPassWord) {
        this.encryptedPassWord = encryptedPassWord;
    }
}
