package com.authenticator.application.service;

import com.authenticator.application.entity.UserEntity;
import com.authenticator.application.model.GeneralMetaDataResponse;
import com.authenticator.application.model.Meta;
import com.authenticator.application.model.UserLoginRequest;
import com.authenticator.application.repository.UserRepository;
import com.authenticator.application.service.inf.CacheHelper;
import com.authenticator.application.service.inf.SignUpLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SignUpLoginImpl implements SignUpLogin {

    @Autowired
    UserRepository userRepo;

    @Autowired
    CacheHelper cache;

    Logger log = LoggerFactory.getLogger(SignUpLoginImpl.class);

    @Override
    public GeneralMetaDataResponse signUpUser(UserEntity user) {
        UserEntity updatedUser = userRepo.save(user);
        GeneralMetaDataResponse generalMetaDataResponse = new GeneralMetaDataResponse();
        Meta meta = new Meta();

        if (updatedUser.getId() == null) {
            meta.setSuccess(false);
            meta.setMessageDescription("User signup failed!");
            generalMetaDataResponse.setMeta(meta);
            return generalMetaDataResponse;
        }

        meta.setMessageDescription("User signup done successfully.");
        UserEntity userEntity = cache.cacheUserName(user);
        userEntity = cache.cacheEmail(user);
        userEntity = cache.cachePhoneNumber(user);
        generalMetaDataResponse.setMeta(meta);
        return generalMetaDataResponse;
    }


    @Override
    public boolean validateUserLogin(UserLoginRequest userLogin) {
        Optional<UserEntity> user = userRepo.findByUserNameAndPassword(userLogin.getUserName(), userLogin.getPassword());
        return user.isPresent();
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<UserEntity> getUserByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }
}
