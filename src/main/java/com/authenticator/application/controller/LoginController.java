package com.authenticator.application.controller;

import com.authenticator.application.entity.UserEntity;
import com.authenticator.application.model.GeneralMetaDataResponse;
import com.authenticator.application.model.Meta;
import com.authenticator.application.model.UserLoginRequest;
import com.authenticator.application.service.inf.CacheHelper;
import com.authenticator.application.service.inf.SignUpLogin;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    SignUpLogin signUpLogin;

    @Autowired
    CacheHelper cacheHelper;

    Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/health")
    public GeneralMetaDataResponse healthOfApp() {
        GeneralMetaDataResponse generalMetaDataResponse = new GeneralMetaDataResponse();
        Meta meta = new Meta();
        meta.setMessageDescription("Bus Anywhere app is up and running!");
        generalMetaDataResponse.setMeta(meta);
        System.out.println("Meta: "+ meta.getMessageDescription());
        return generalMetaDataResponse;
    }

    @PostMapping("/signup")
    public GeneralMetaDataResponse signupUser(@Valid  @RequestBody UserEntity user, BindingResult validationResult) {
        log.info("User: {} request received for signup.", user.getUserName());
        GeneralMetaDataResponse generalMetaDataResponse = new GeneralMetaDataResponse();
        Meta meta = new Meta();
        if (validationResult.hasErrors()) {
            List<String> errorMessages = validationResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .toList();

            meta.setSuccess(false);
            meta.setMessageDescription("Data errors in request, refer error list. Resolve and retry!");
            generalMetaDataResponse.setMeta(meta);
            Map<String, List<String>> errorMap = Map.of("errorList", errorMessages);
            generalMetaDataResponse.setData(errorMap);
            return generalMetaDataResponse;
        }

        if (cacheHelper.userNameExists(user.getUserName()) != null) {
            meta.setMessageDescription("Username already taken");
            generalMetaDataResponse.setMeta(meta);
            return generalMetaDataResponse;
        }

        if (cacheHelper.emailExists(user.getEmail()) != null) {
            meta.setMessageDescription("Email already registered");
            generalMetaDataResponse.setMeta(meta);
            return generalMetaDataResponse;
        }

        if (cacheHelper.phoneNumberExists(user.getPhoneNumber()) != null) {
            meta.setMessageDescription("Phone number already registered");
            generalMetaDataResponse.setMeta(meta);
            return generalMetaDataResponse;
        }
        signUpLogin.signUpUser(user);
        meta.setMessageDescription("User signed up successfully!");
        generalMetaDataResponse.setMeta(meta);
        return generalMetaDataResponse;
    }

    @PostMapping("/login")
    public GeneralMetaDataResponse userLogin(@Valid @RequestBody UserLoginRequest userLogin, BindingResult validationResult) {
        log.info("Login request for user: {} received.", userLogin.getUserName());
        GeneralMetaDataResponse generalMetaDataResponse = new GeneralMetaDataResponse();
        Meta meta = new Meta();

        if (validationResult.hasErrors()) {
            List<String> errorMessages = validationResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .toList();

            meta.setSuccess(false);
            meta.setMessageDescription("Data errors in request, refer error list. Resolve and retry!");
            generalMetaDataResponse.setMeta(meta);
            Map<String, List<String>> errorMap = Map.of("errorList", errorMessages);
            generalMetaDataResponse.setData(errorMap);
            return generalMetaDataResponse;
        }

        if (!signUpLogin.validateUserLogin(userLogin)) {
            meta.setSuccess(false);
            meta.setMessageDescription("Invalid credentials, Login failed!");
            generalMetaDataResponse.setMeta(meta);
            return generalMetaDataResponse;
        }

        meta.setMessageDescription("User logged in successfully.");
        generalMetaDataResponse.setMeta(meta);
        return generalMetaDataResponse;
    }

    @GetMapping("/all-users")
    public GeneralMetaDataResponse getAllUsers() {
        GeneralMetaDataResponse generalMetaDataResponse = new GeneralMetaDataResponse();
        Meta meta = new Meta();
        List<UserEntity> users = signUpLogin.getAllUsers();

        if (users.isEmpty()) {
            meta.setSuccess(false);
            meta.setMessageDescription("No users found.");
        } else {
            meta.setSuccess(true);
            meta.setMessageDescription("Users fetched successfully.");
            generalMetaDataResponse.setData(users);
        }
        Map<String, List<UserEntity>> userMap = Map.of("users", users);
        generalMetaDataResponse.setData(userMap);
        generalMetaDataResponse.setMeta(meta);
        return generalMetaDataResponse;
    }

    @GetMapping("/{userName}")
    public GeneralMetaDataResponse getUserById(@PathVariable String userName) {
        GeneralMetaDataResponse generalMetaDataResponse = new GeneralMetaDataResponse();
        Meta meta = new Meta();
        Optional<UserEntity> user = signUpLogin.getUserByUserName(userName);

        if (user.isPresent()) {
            meta.setMessageDescription("User found successfully.");
            generalMetaDataResponse.setData(user.get());
        } else {
            meta.setSuccess(false);
            meta.setMessageDescription("User not found with userName: " + userName);
        }

        generalMetaDataResponse.setMeta(meta);
        return generalMetaDataResponse;
    }


}
