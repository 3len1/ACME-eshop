package com.acme.eshop.converter;

import com.acme.eshop.domain.User;
import com.acme.eshop.exceptions.ResourceNotValid;
import com.acme.eshop.resources.UserResource;
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

    public User getUser(UserResource userResource) {
        return Try.of(() -> {
            User user = new User();
            user.setEmail(userResource.getEmail());
            user.setPassword(userResource.getPassword());
            user.setLastName(userResource.getLastName());
            user.setFirstName(userResource.getFirstName());
            user.setGender(Gender.fromString(userResource.getGender()));
            user.setPhone(userResource.getPhone());
            if (userResource.getType().equalsIgnoreCase(ADMIN))
                user.setAdmin(true);
            else user.setAdmin(false);
            user.setAddress(addressConverter.getAddress(userResource.getAddress()));
            user.setBirthday(userResource.getBirthday().toEpochMilli());
            return user;
        }).getOrElseThrow(()-> new ResourceNotValid("User resource is not valid"));
    }
}
