package com.charliekriska.financeuserdata.service;

import com.charliekriska.financeuserdata.dao.UserDao;
import com.charliekriska.financeuserdata.model.User;
import com.charliekriska.financeuserdata.utility.Samples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserDao userDao;

    @Test
    void testGetAllUsers() {

        when(userDao.getAllUsers()).thenReturn(Samples.sampleUserList());

        List<User> users = userServiceImpl.getAllUsers();

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

        when(userDao.addUser(any(User.class)))
                .thenReturn(Samples.sampleUserOutput());

        User user = userServiceImpl.addUser(Samples.sampleUserInput());

        assertEquals(1, user.getUserId());
        assertEquals("sampleuser123", user.getUsername());
        assertEquals("password123", user.getPassword());

    }

}
