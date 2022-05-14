package de.hse.swa.jpa.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hse.swa.jpa.orm.dao.ProjectDao;
import de.hse.swa.jpa.orm.model.Project;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ProjectDaoTest {
	
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
	
	private void printProject(Project project) {
		System.out.println("id: " + project.getId());
		System.out.println("Projectname: " + project.getProjectname());
//		List<Project> projects = project.getProjects();
//		for (Project project: projects) {
//			System.out.println("  Project " + project.getId() + ": " + project.getProjectname());
//		}
	}
	
	@BeforeEach
	public void clearAllFromDatabase() {
		projectDao.removeAllProjects();
	}
	
	@Test
	void addProject_1() {
		Project first = createProject("One");
		projectDao.save(first);
		List<Project> projects = projectDao.getProjects();
		assertEquals(projects.size(),1);
		printProject(projects.get(0));
	}
	
	@Test
	void addProject_2() {
		addTwoProjects();
		List<Project> projects = projectDao.getProjects();
		assertEquals(projects.size(),2);
		printProject(projects.get(1));
	}
	
}