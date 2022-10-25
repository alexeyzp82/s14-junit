package com.softserve.itacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepTest {
	@Autowired
    private UserRepository userRepository;
	
	@Test
    @Transactional
    public void saveUserTest(){
		User user = new User();
		user.setEmail("test@email.com");
		user.setFirstName("Tuser");
		user.setLastName("Tname");
		user.setPassword("jddasas");
		userRepository.save(user);
		Assertions.assertEquals(user.getFirstName(), userRepository.getUserByEmail("test@email.com").getFirstName());
	}
	
	@Test
    @Transactional
    public void findAllUsersTest(){
		userRepository.deleteAll();
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setEmail("test3@email.com");
		user.setFirstName("Tuser");
		user.setLastName("Tname");
		user.setPassword("jddasas");
		userRepository.save(user);
		users.add(user);
		User user2 = new User();
		user2.setEmail("test2@email.com");
		user2.setFirstName("Tusehgsr");
		user2.setLastName("Tnshsame");
		user2.setPassword("jddashjgas");
		userRepository.save(user2);
		users.add(user2);
		Assertions.assertEquals(users, userRepository.findAll());
	}
	
	@Test
    @Transactional
    public void deleteUserTest(){
		List<User> users = userRepository.findAll();
		userRepository.deleteById((long) 5);
		users.remove(users.get(0));
		Assertions.assertEquals(users, userRepository.findAll());
	}
	
	@Test
    @Transactional
    public void findUserByIdTest(){
		Optional<User> optional = userRepository.findById(6L);
        Assertions.assertEquals("Nora", optional.get().getFirstName());
	}
	
	@Test
    @Transactional
    public void updateUserTest(){
		User user = userRepository.getOne((long) 5);
		user.setEmail("test2@email.com");
		user.setFirstName("Tusehgsr");
		user.setLastName("Tnshsame");
		user.setPassword("jddashjgas");
		userRepository.save(user);
		Assertions.assertEquals(user, userRepository.getOne((long) 5));
	}
}
