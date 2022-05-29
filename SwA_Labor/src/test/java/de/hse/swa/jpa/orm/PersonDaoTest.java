package de.hse.swa.jpa.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hse.swa.jpa.orm.dao.PersonDao;
import de.hse.swa.jpa.orm.model.Customer;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PersonDaoTest {
	
    @Inject
    PersonDao personDao;
    
	private Customer createUser(String prefix) {
		Customer person = new Customer();
		person.setUsername(prefix+"UserName");
		person.setProperties("xyz");
		return person;
	}
	
	public void addTwoUsers() {
		Customer first = createUser("First");
		personDao.save(first);
		Customer second = createUser("Second");
		personDao.save(second);
	}

	
	private void printUser(Customer user) {
		System.out.println("id: " + user.getId());
		System.out.println("Username: " + user.getUsername());
//		List<Project> projects = person.getProjects();
//		for (Project project: projects) {
//			System.out.println("  Project " + project.getId() + ": " + project.getProjectname());
//		}
	}
	
	@BeforeEach
	public void clearAllFromDatabase() {
		personDao.removeAllPersons();
	}
	
	@Test
	void addUser_1() {
		Customer first = createUser("First");
		personDao.save(first);
		List<Customer> users = personDao.getPersons();
		assertEquals(users.size(),1);
		printUser(users.get(0));
	}
	
	@Test
	void addUser_2() {
		addTwoUsers();
		List<Customer> users = personDao.getPersons();
		assertEquals(users.size(),2);
		printUser(users.get(1));
	}
	
	@Test
	void checkLogin_1() {
		Customer first = createUser("first");
		personDao.save(first);
		List<Customer> persons = personDao.getPersons();
		assertNotNull(personDao.login(persons.get(0).getUsername(), persons.get(0).getProperties()));
	}
	
}