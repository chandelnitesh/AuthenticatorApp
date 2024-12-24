package com.authenticator.application.entity;

import com.authenticator.application.model.ROLE;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 50, message = "Last name should be between 0 and 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "User name is required")
    @Size(min = 5, max = 15, message = "User name should be between 5 and 15 characters")
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be 'Male', 'Female', or 'Other'")
    @Column(name = "gender", nullable = false)
    private String gender;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 150")
    @Column(name = "age", nullable = false)
    private int age;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Please provide a valid phone number")
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ROLE role = ROLE.USER;

}
