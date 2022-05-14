package de.hse.swa.jpa.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hse.swa.jpa.orm.dao.DepartmentDao;
import de.hse.swa.jpa.orm.model.Department;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class DepartmentDaoTest {
	
    @Inject
    DepartmentDao departmentDao;
    
	private Department createDepartment(String prefix) {
		Department department = new Department();
		department.setDepname(prefix+"DepartmentName");
		return department;
	}
	
	public void addTwoDepartments() {
		Department first = createDepartment("firstDep");
		departmentDao.addDepartment(first);
		Department second = createDepartment("secondDep");
		departmentDao.addDepartment(second);
	}

	
	private void printDepartment(Department department) {
		System.out.println("id: " + department.getId());
		System.out.println("Depname: " + department.getDepname());
//		List<Project> projects = department.getProjects();
//		for (Project project: projects) {
//			System.out.println("  Project " + project.getId() + ": " + project.getProjectname());
//		}
	}
	
	@BeforeEach
	public void clearAllFromDatabase() {
		departmentDao.removeAllDepartments();
	}
	
	@Test
	void addDepartment_1() {
		Department first = createDepartment("first");
		departmentDao.addDepartment(first);
		List<Department> deps = departmentDao.getDepartments();
		assertEquals(deps.size(),1);
		printDepartment(deps.get(0));
	}
	
	@Test
	void addDepartment_2() {
		addTwoDepartments();
		List<Department> deps = departmentDao.getDepartments();
		assertEquals(deps.size(),2);
		printDepartment(deps.get(1));
	}
	
}