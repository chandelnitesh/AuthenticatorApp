package com.authenticator.application.service.inf;

import com.authenticator.application.entity.UserEntity;
import com.authenticator.application.model.GeneralMetaDataResponse;
import com.authenticator.application.model.UserLoginRequest;

import java.util.List;
import java.util.Optional;

public interface SignUpLogin {

    GeneralMetaDataResponse signUpUser(UserEntity user);

    boolean validateUserLogin(UserLoginRequest userLogin);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUserByUserName(String userName);
}
