package com.charliekriska.financeuserdata.dao;

import com.charliekriska.financeuserdata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbc;

    public List<User> getAllUsers() {

        final String SELECT_ALL_USERS = "SELECT * FROM user;";
        List<User> users = jdbc.query(SELECT_ALL_USERS, new UserMapper());
        return users;

    }

    @Transactional
    public User addUser(User user) {

        final String INSERT_USER = "INSERT INTO user(username, password) " +
                "VALUES(?, ?)";

        jdbc.update(INSERT_USER,
                user.getUsername(),
                user.getPassword());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);

        return user;

    }

    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;

        }
    }

}
