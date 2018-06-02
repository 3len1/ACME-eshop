package com.acme.eshop.service;

import com.acme.eshop.domain.User;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Created by Eleni on 11/5/2018.
 */
@Service
public interface LoginService {

    User getUser(UUID token);
    User logIn(String email, String password);
    void logOut(UUID token);
}
