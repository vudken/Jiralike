package com.app.dao;

import com.app.model.Registration;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void storeUser(Registration registration) {
        final String SQL_STORE_USER = "INSERT INTO users (name, surname, username, password, email) " +
                                      "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(SQL_STORE_USER,
                            registration.getName(),
                            registration.getSurname(),
                            registration.getUsername(),
                            registration.getPassword(),
                            registration.getEmail()
        );
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        return user;
    }

    public List<User> getAllUsers() {
        RowMapper<User> rowMapper = (rs, rowNumber) -> mapUser(rs);
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    public List<User> getUsersByUsername(String username) {
        RowMapper<User> rowMapper = ((rs, rowNum) -> mapUser(rs));
        return jdbcTemplate.query("SELECT * FROM users WHERE username = ?", rowMapper, username);
    }
}
