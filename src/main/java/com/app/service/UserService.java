package com.app.service;

import com.app.dao.UserDAO;
import com.app.model.Registration;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void storeUser(Registration registration) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        registration.setPassword(encoder.encode(registration.getPassword()));

        userDAO.storeUser(registration);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
