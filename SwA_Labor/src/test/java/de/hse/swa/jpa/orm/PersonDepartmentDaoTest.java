package de.hse.swa.jpa.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hse.swa.jpa.orm.dao.DepartmentDao;
import de.hse.swa.jpa.orm.dao.PersonDao;
import de.hse.swa.jpa.orm.model.Department;
import de.hse.swa.jpa.orm.model.Customer;
import de.hse.swa.jpa.orm.model.Service_contract;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PersonDepartmentDaoTest {
	
    @Inject
    PersonDao personDao;
    
    @Inject
    DepartmentDao departmentDao;
    
	private Department createDepartment(String postfix) {
		Department department = new Department();
		department.setDepname("Department "+ postfix);
		return department;
	}
	
	public void addTwoDepartments() {
		Department first = createDepartment("firstDep");
		departmentDao.addDepartment(first);
		Department second = createDepartment("secondDep");
		departmentDao.addDepartment(second);
	}
	
	private Customer createUser(String postfix) {
		Customer person = new Customer();
		person.setUsername("User " + postfix);
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
		System.out.println("id: " + user.getId() + " - " + user.getUsername());
		if (user.getDepartment() != null) {
			System.out.println("Department: " + user.getDepartment().getDepname());	
		}
		Set<Service_contract> projects = user.getProjects();
		for (Service_contract project: projects) {
			System.out.println("  Project " + project.getId() + ": " + project.getProjectname());
		}
	}
	
	@BeforeEach
	public void clearAllFromDatabase() {
		personDao.removeAllPersons();
		departmentDao.removeAllDepartments();
	}
	
	/* Create two new users and a department. Save department and assign
	 * department to both users. Save users.
	 */
	@Test
	void addUsersWithDepartment() {
		Customer first = createUser("First");
		Customer second = createUser("Second");
		
		Department adep = createDepartment("Adep");
		departmentDao.addDepartment(adep);
		
		first.setDepartment(adep);
		second.setDepartment(adep);
		
		personDao.save(first);
		personDao.save(second);
		
		List<Customer> users = personDao.getPersons();
		assertEquals(users.size(),2);
		printUser(users.get(1));
		
//		// Need to get this fresh
//		adep = users.get(1).getDepartment();
//		
//		users = adep.getPersons();
//		assertEquals(users.size(),2);
//		printUser(users.get(0));
	}
	
	
	
}