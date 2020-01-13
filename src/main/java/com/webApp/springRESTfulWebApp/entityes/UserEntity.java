package com.webApp.springRESTfulWebApp.entityes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 4888534534534534538L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String userId;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String encryptedPassWord;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
