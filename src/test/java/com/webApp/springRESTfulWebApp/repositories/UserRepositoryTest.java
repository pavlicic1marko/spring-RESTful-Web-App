package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entities.UserEntity;
import org.junit.jupiter.api.BeforeAll;
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

    private static UserEntity userEntity;

    @Autowired
    private  UserRepository userRepository;

    private String email = "testemail@test.com";
    private String firstName = "Marko";
    private String lastName = "Pavlicic";
    private String encryptedPassword = "test";
    private String userID = "testId";

    @BeforeAll
    static final  void SetUp(@Autowired UserRepository userRepository) {
         String email = "testemail@test.com";
         String firstName = "Marko";
         String lastName = "Pavlicic";
         String encryptedPassword = "test";
         String userID = "testId";
        //Create a User Entity and save it
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
        assertEquals(lastName, entity.getLastName());
        assertEquals(encryptedPassword, entity.getEncryptedPassWord());
        assertEquals(userID, entity.getUserId());

    }

    @Test
    final void findUserEntityByUserId() {
        UserEntity entity = userRepository.findUserEntityByUserId(userID);
        assertNotNull(entity);
        assertEquals(email, entity.getEmail());
        assertEquals(lastName, entity.getLastName());
        assertEquals(encryptedPassword, entity.getEncryptedPassWord());
        assertEquals(userID, entity.getUserId());


    }

    @Test
    final void updateUserEntityFirstName() {
        String newFirstName = "Json";
        userRepository.updateUserEntityFirstName(newFirstName, userID);
        UserEntity entity = userRepository.findUserEntityByUserId(userID);
        assertNotNull(entity);
        assertEquals(newFirstName, entity.getFirstName());


    }
}
