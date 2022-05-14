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
import de.hse.swa.jpa.orm.model.Person;
import de.hse.swa.jpa.orm.model.Project;
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
    
	private Project createProject(String postfix) {
		Project project = new Project();
		project.setProjectname("Project "+postfix);
		return project;
	}
	
	public void addTwoProjects() {
		Project first = createProject("One");
		projectDao.save(first);
		Project second = createProject("Two");
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
	
	private Person createUser(String postfix) {
		Person person = new Person();
		person.setUsername("User " + postfix);
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
		
		Project aproject = createProject("Aproject");
		projectDao.save(aproject);
		
		Project bproject = createProject("Bproject");
		projectDao.save(bproject);
		
		Person first = createUser("First");
		Person second = createUser("Second");
		
		first = personDao.save(first);
		second = personDao.save(second);
		
		aproject.addPerson(first);
		aproject.addPerson(second);
		
		bproject.addPerson(second);
		
		aproject = projectDao.save(aproject);
		bproject = projectDao.save(bproject);
		
		List<Project> projects = projectDao.getProjects(second);
		for (Project proj: projects) {
			System.out.println(proj.getProjectname());
		}
				
		aproject.removePerson(first);
		
		projectDao.save(aproject);	
		
		List<Person> users = personDao.getPersons();
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