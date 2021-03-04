package com.app.service;

import com.app.dao.UserDAO;
import com.app.model.Login;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserDAO userDAO;

    public Integer getUserId(Login login) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        List<User> users = userDAO.getUsersByUsername(login.getUsername());

        if (users.size() > 1) {
            System.out.println("There is more than 1 user with this username: " + login.getUsername());
        }

        if (!users.isEmpty() &&
                encoder.matches(login.getPassword(), users.get(0).getPassword())) {
            return users.get(0).getId();
        }

        return null;
    }
}
