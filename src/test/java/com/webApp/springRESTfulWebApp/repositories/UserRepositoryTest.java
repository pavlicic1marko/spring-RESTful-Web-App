package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private
    UserRepository userRepository;

    private UserEntity userEntity;
    private String email = "testemail@test.com";
    private String firstName = "Marko";
    private String lastName = "Pavlicic";
    private String encryptedPassword = "test";
    private String userID = "testId";

    @BeforeEach
    void SetUp() {
        //Create a User Entity
        userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEncryptedPassWord(encryptedPassword);
        userEntity.setUserId(userID);
        userRepository.save(userEntity);

    }


    @Test
    final void GetUserWhereEmailIsEqualToTestEmail() {
        UserEntity entity = userRepository.findAllUsersWhereEmailEqualsToTesTEmail(email);
        assertNotNull(entity);
        assertEquals(email, entity.getEmail());
        assertEquals(firstName, entity.getFirstName());
        assertEquals(lastName, entity.getLastName());
        assertEquals(encryptedPassword, entity.getEncryptedPassWord());
        assertEquals(userID, entity.getUserId());

    }
}
