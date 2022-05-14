package de.hse.swa.jpa.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hse.swa.jpa.orm.dao.PersonDao;
import de.hse.swa.jpa.orm.model.Person;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PersonDaoTest {
	
    @Inject
    PersonDao personDao;
    
	private Person createUser(String prefix) {
		Person person = new Person();
		person.setUsername(prefix+"UserName");
		person.setProperties("xyz");
		return person;
	}
	
	public void addTwoUsers() {
		Person first = createUser("First");
		personDao.save(first);
		Person second = createUser("Second");
		personDao.save(second);
	}

	
	private void printUser(Person user) {
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
		Person first = createUser("First");
		personDao.save(first);
		List<Person> users = personDao.getPersons();
		assertEquals(users.size(),1);
		printUser(users.get(0));
	}
	
	@Test
	void addUser_2() {
		addTwoUsers();
		List<Person> users = personDao.getPersons();
		assertEquals(users.size(),2);
		printUser(users.get(1));
	}
	
	@Test
	void checkLogin_1() {
		Person first = createUser("first");
		personDao.save(first);
		List<Person> persons = personDao.getPersons();
		assertNotNull(personDao.login(persons.get(0).getUsername(), persons.get(0).getProperties()));
	}
	
}