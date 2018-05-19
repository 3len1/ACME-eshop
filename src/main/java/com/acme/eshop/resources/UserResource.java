package com.acme.eshop.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;

/**
 * Created by Eleni on 17/5/2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResource {

    private static final String MAIL_PATTERN = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$";
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9@#$%^&]*$";
    private static final String CHARACTERS_PATTERN = "^[a-zA-Z ]*$";
    private static final String PHONE_PATTERN = "^2[0-9]{9}|69[0-9]{8}$";
    private static final int PHONE_SIZE = 10;
    private static final int PASSWORD_MIN_SIZE = 6;

    @NotNull(message = "{user.mail.null}")
    @Pattern(regexp = MAIL_PATTERN, message = "{user.mail.invalid}")
    private String email;

    @NotNull(message = "{user.password.null}")
    @Pattern(regexp = PASSWORD_PATTERN, message = "{user.password.invalid}")
    @Size(min = PASSWORD_MIN_SIZE, message = "{user.password.size}")
    private String password;

    @Pattern(regexp = CHARACTERS_PATTERN, message = "{user.lastname.invalid}")
    private String lastName;

    @Pattern(regexp = CHARACTERS_PATTERN, message = "{user.firstname.invalid}")
    private String firstName;

    private String gender;

    @Pattern(regexp = PHONE_PATTERN, message = "user.phone.invalid}")
    private String phone;

    private Instant birthday;

    private String type;

    private AddressResource address;

}
