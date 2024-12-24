package com.authenticator.application.service.inf;

import com.authenticator.application.entity.UserEntity;


public interface CacheHelper {

    UserEntity cacheUserName(UserEntity user);

    UserEntity cacheEmail(UserEntity user);

    UserEntity cachePhoneNumber(UserEntity user);

    UserEntity emailExists(String email);

    UserEntity userNameExists(String userName);

    UserEntity phoneNumberExists(String phoneNumber);
}
