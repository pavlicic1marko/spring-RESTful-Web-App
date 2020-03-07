package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entities.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);

    UserEntity findByEmail(String email);

    //Native SQL Queries

    @Query(value = "select * from User_entity u where u.email=:email",
            nativeQuery = true)
    UserEntity findAllUsersWhereEmailEqualsToTesTEmail(@Param("email") String email);


    //JPQL Java Persistence Query Language

    @Query("select user from UserEntity user where user.userId=:userId")
    UserEntity findUserEntityByUserId(@Param("userId") String userId);


    @Query("select user from UserEntity user where user.email like %:string% or user.firstName like %:string% or user.lastName like %:string%")
    List<UserEntity> searchForUser(@Param("string") String string);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u set u.firstName=:firstName where u.userId=:userId")
    void updateUserEntityFirstName(
            @Param("firstName") String firstName,
            @Param("userId") String userId);
}
