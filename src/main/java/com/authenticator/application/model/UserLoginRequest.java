package com.authenticator.application.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    @NotBlank(message = "User name is required")
    @Size(min = 5, max = 15, message = "User name should be between 5 and 15 characters")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    private String password;
}
