package com.acme.eshop.converter;

import com.acme.eshop.domain.User;
import com.acme.eshop.dto.UserDto;
import com.acme.eshop.enums.Gender;
import javaslang.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Eleni on 17/5/2018.
 */
@Component
public class UserConverter {

    private static final String ADMIN = "ADMIN";

    @Autowired
    private AddressConverter addressConverter;

    public User getUser(UserDto userDto) {
        return Try.of(() -> {
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setLastName(userDto.getLastName());
            user.setFirstName(userDto.getFirstName());
            user.setGender(Gender.fromString(userDto.getGender()));
            user.setPhone(userDto.getPhone());
            if (userDto.getType().equalsIgnoreCase(ADMIN))
                user.setAdmin(true);
            else user.setAdmin(false);
            user.setAddress(addressConverter.getAddress(userDto.getAddress()));
            user.setBirthday(userDto.getBirthday().toEpochMilli());
            return user;
        }).getOrElseGet(null);
    }
}
