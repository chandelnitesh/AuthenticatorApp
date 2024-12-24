package com.authenticator.application.repository;

import com.authenticator.application.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findByUserNameAndPassword(String userName, String Password);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    boolean existsByPhoneNumber(String phoneNumber);
}
