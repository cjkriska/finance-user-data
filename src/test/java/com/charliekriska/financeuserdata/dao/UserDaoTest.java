package com.charliekriska.financeuserdata.dao;

import com.charliekriska.financeuserdata.model.User;
import com.charliekriska.financeuserdata.utility.Samples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserDaoTest {

    @InjectMocks
    private UserDao userDao;

    @Mock
    private JdbcTemplate jdbc;

    @Test
    void testGetAllUsers() {

        when(jdbc.query(eq("SELECT * FROM user;"), any(UserDao.UserMapper.class)))
                .thenReturn(Samples.sampleUserList());

        List<User> users = userDao.getAllUsers();

        assertEquals(3, users.size());

        assertEquals(1, users.get(0).getUserId());
        assertEquals("sampleuser123", users.get(0).getUsername());
        assertEquals("password123", users.get(0).getPassword());

        assertEquals(2, users.get(1).getUserId());
        assertEquals("user234", users.get(1).getUsername());
        assertEquals("pw2345", users.get(1).getPassword());

        assertEquals(3, users.get(2).getUserId());
        assertEquals("user456", users.get(2).getUsername());
        assertEquals("pass45678", users.get(2).getPassword());

    }

    @Test
    void testAddUser() {

        final String INSERT_USER = "INSERT INTO user(username, password) " +
                "VALUES(?, ?)";

        when(jdbc.update(any(String.class),
                anyString(),
                anyString()))
                .then(invocation -> {
                    assertEquals(invocation.getArgument(0), INSERT_USER);
                    assertEquals(invocation.getArgument(1), "sampleuser123");
                    assertEquals(invocation.getArgument(2), "password123");
                    return 1;
                });

        when(jdbc.queryForObject(any(String.class), any(Class.class)))
                .thenReturn(1);

        User user = userDao.addUser(Samples.sampleUserInput());
        assertEquals(1, user.getUserId());
        assertEquals("sampleuser123", user.getUsername());
        assertEquals("password123", user.getPassword());

    }


}
