package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);

    UserEntity findByEmail(String email);

    @Query(value = "select * from User_entity u where u.email=:email",
            nativeQuery = true)
    UserEntity findAllUsersWhereEmailEqualsToTesTEmail(@Param("email") String email);
}
