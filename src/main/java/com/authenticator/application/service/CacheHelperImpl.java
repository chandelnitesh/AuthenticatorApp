package com.authenticator.application.service;

import com.authenticator.application.entity.UserEntity;
import com.authenticator.application.repository.UserRepository;
import com.authenticator.application.service.inf.CacheHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CacheHelperImpl implements CacheHelper {

    @Autowired
    UserRepository userRepo;

    Logger log = LoggerFactory.getLogger(CacheHelper.class);


    @Override
    @CachePut(value = "userName", key = "#user.userName")
    public UserEntity cacheUserName(UserEntity user) {
        log.info("Caching user name: {}", user.getUserName());
        return user;
    }

    @Override
    @CachePut(value = "email", key = "#user.email")
    public UserEntity cacheEmail(UserEntity user) {
        log.info("Caching user email: {}", user.getEmail());
        return user;
    }

    @Override
    @CachePut(value = "phoneNumber", key = "#user.phoneNumber")
    public UserEntity cachePhoneNumber(UserEntity user) {
        log.info("Caching user phone number: {}", user.getPhoneNumber());
        return user;
    }

    @Override
    @Cacheable(value = "email", key = "#email", unless = "#result == null")
    public UserEntity emailExists(String email) {
        log.debug("Calling DB to check if email: {} exists.", email);
        Optional<UserEntity> user = userRepo.findByEmail(email);
        return user.orElse(null);
    }

    @Override
    @Cacheable(value = "userName", key = "#userName", unless = "#result == null")
    public UserEntity userNameExists(String userName) {
        log.debug("Calling DB to check userName: {}", userName);
        Optional<UserEntity> user =  userRepo.findByUserName(userName);
        return user.orElse(null);
    }

    @Override
    @Cacheable(value = "phoneNumber", key = "#phoneNumber", unless = "#result == null")
    public UserEntity phoneNumberExists(String phoneNumber) {
        log.debug("Calling DB to check phone number: {}", phoneNumber);
        Optional<UserEntity> user = userRepo.findByPhoneNumber(phoneNumber);
        return user.orElse(null);
    }

}
