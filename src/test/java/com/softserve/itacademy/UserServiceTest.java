package com.softserve.itacademy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserServiceTest{



    @Autowired
    private UserService userService;



    @Test
    @Transactional
    public void createUserTest(){
        User user1 =new User();
        user1.setFirstName("Ted");
        user1.setLastName("Robinson");
        user1.setEmail("ted@gmail.com");
        user1.setPassword("1234");
        user1.setId(1);
        userService.create(user1);
        assertEquals(user1.getEmail(), userService.readById(1).getEmail());
    }
    @Test
    public void getUserByIdTest(){
        User expected = new User();
        expected.setFirstName("Nora");
        expected.setLastName("White");
        expected.setEmail("nora@mail.com");
        expected.setPassword("$2a$10$CJgEoobU2gm0euD4ygru4ukBf9g8fYnPrMvYk.q0GMfOcIDtUhEwC");
        expected.setId(6L);

        User actual = userService.readById(6L);
        assertEquals(expected.getId(), actual.getId());
    }
    @Test
    @Transactional
    public void updateToDoTest(){
        String newMail = "noranora@mail.com";
        User user = userService.readById(6L);
        user.setEmail(newMail);
        User actual = userService.update(user);
        User expected = new User();
        expected.setEmail(newMail);
        expected.setId(6L);
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getId(),actual.getId());
    }
    @Test
    public void getAllUsersTest() {
        int expectedSize = 3;
        List<User> users = userService.getAll();
        assertTrue(expectedSize <= users.size(), String.format("At least %d users shuold be in users table", expectedSize));
    }
}
