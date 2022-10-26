package com.softserve.itacademy;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ToDoServiceTest {

    @Autowired
    private ToDoService toDoService;

    @Test
    @Transactional
    public void createToDoTest() {
        ToDo todo = new ToDo();
        todo.setTitle("ToDo Test#1");
        todo.setCreatedAt(LocalDateTime.now());
        long todoId = 1L;
        todo.setId(todoId);

        toDoService.create(todo);
        assertEquals(todo.getTitle(), toDoService.readById(todoId).getTitle());

    }


    @Test
    public void readByIdToDoTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        ToDo expected = new ToDo();
        expected.setTitle("Nora's To-Do #2");
        expected.setCreatedAt(LocalDateTime.parse("2020-09-16 14:15:39.162460", formatter));
        long id = 13L;
        expected.setId(id);

        ToDo actual = toDoService.readById(id);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void updateToDoTest() {
        ToDo todo = toDoService.readById(13L);
        todo.setTitle("UPDATE");
        ToDo actual = toDoService.update(todo);

        ToDo expected = new ToDo();
        expected.setTitle("UPDATE");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        expected.setCreatedAt(LocalDateTime.parse("2020-09-16 14:15:39.162460", formatter));

        assertEquals(expected, actual);
    }



}
