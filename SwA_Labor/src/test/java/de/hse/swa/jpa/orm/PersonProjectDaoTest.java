package de.hse.swa.jpa.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hse.swa.jpa.orm.dao.DepartmentDao;
import de.hse.swa.jpa.orm.dao.PersonDao;
import de.hse.swa.jpa.orm.dao.ProjectDao;
import de.hse.swa.jpa.orm.model.Department;
import de.hse.swa.jpa.orm.model.Customer;
import de.hse.swa.jpa.orm.model.Service_contract;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PersonProjectDaoTest {
	
    @Inject
    EntityManager em;
    
    @Inject
    PersonDao personDao;
    
    @Inject
    DepartmentDao departmentDao;
    
    @Inject
    ProjectDao projectDao;
    
	private Service_contract createProject(String postfix) {
		Service_contract project = new Service_contract();
		project.setProjectname("Project "+postfix);
		return project;
	}
	
	public void addTwoProjects() {
		Service_contract first = createProject("One");
		projectDao.save(first);
		Service_contract second = createProject("Two");
		projectDao.save(second);
	}
	
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

//		List<Project> projects = person.getProjects();
//		for (Project project: projects) {
//			System.out.println("  Project " + project.getId() + ": " + project.getProjectname());
//		}
	}
	
	@BeforeEach
	public void clearAllFromDatabase() {
		projectDao.removeAllProjects();
		personDao.removeAllPersons();
		departmentDao.removeAllDepartments();
	}
	
	/* Create two new users and two new projects. Save projects and assign
	 * projects to both users. Save users.
	 */
	@Test
	void addUsersWithProjects() {
		
		Service_contract aproject = createProject("Aproject");
		projectDao.save(aproject);
		
		Service_contract bproject = createProject("Bproject");
		projectDao.save(bproject);
		
		Customer first = createUser("First");
		Customer second = createUser("Second");
		
		first = personDao.save(first);
		second = personDao.save(second);
		
		aproject.addPerson(first);
		aproject.addPerson(second);
		
		bproject.addPerson(second);
		
		aproject = projectDao.save(aproject);
		bproject = projectDao.save(bproject);
		
		List<Service_contract> projects = projectDao.getProjects(second);
		for (Service_contract proj: projects) {
			System.out.println(proj.getProjectname());
		}
				
		aproject.removePerson(first);
		
		projectDao.save(aproject);	
		
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