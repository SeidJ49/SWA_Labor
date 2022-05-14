package de.hse.swa.jodel.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hse.swa.jodel.orm.dao.UserDao;
import de.hse.swa.jodel.orm.model.User;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class UserDaoTest {
	
    @Inject
    UserDao userDao;
    
	private User createUser(String prefix) {
		User user = new User();
		user.setUsername(prefix+"UserName");
		user.setPassword("xyz");
		return user;
	}
	
	public void addTwoUsers() {
		User firstPerson = createUser("first");
		userDao.save(firstPerson);
		User secondPerson = createUser("second");
		userDao.save(secondPerson);
	}

	
	private void printUser(User user) {
		System.out.println("id: " + user.getId());
		System.out.println("Username: " + user.getUsername());
//		List<Project> projects = person.getProjects();
//		for (Project project: projects) {
//			System.out.println("  Project " + project.getId() + ": " + project.getProjectname());
//		}
	}
	
	@BeforeEach
	public void clearAllFromDatabase() {
		userDao.deleteAllUsers();
	}
	
	@Test
	void addUser_1() {
		User firstPerson = createUser("first");
		userDao.save(firstPerson);
		List<User> users = userDao.getUsers();
		assertEquals(users.size(),1);
		printUser(users.get(0));
	}
	
	@Test
	void addUser_2() {
		addTwoUsers();
		List<User> users = userDao.getUsers();
		assertEquals(users.size(),2);
		printUser(users.get(1));
	}
	
	@Test
	void checkLogin_1() {
		User firstPerson = createUser("first");
		userDao.save(firstPerson);
		List<User> persons = userDao.getUsers();
		assertNotNull(userDao.login(persons.get(0).getUsername(), persons.get(0).getPassword()));
	}
	
}