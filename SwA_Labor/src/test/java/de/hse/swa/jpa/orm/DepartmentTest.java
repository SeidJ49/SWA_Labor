package de.hse.swa.jpa.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.Console;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.gradle.api.logging.configuration.ConsoleOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.cj.Query;

import de.hse.swa.jpa.orm.model.Customer;
import de.hse.swa.jpa.orm.dao.CustomerDao;
import de.hse.swa.jpa.orm.model.Department;
import de.hse.swa.jpa.orm.dao.DepartmentDao;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class DepartmentDaoTest {
    
    @Inject
    EntityManager em;
    
    @Inject
    CustomerDao customerDao;

    @Inject
    DepartmentDao departmentDao;
    
    @BeforeEach
	public void clearAllCust() {
		customerDao.removeAllCustomer();
	}

    @BeforeEach
    public void clearAllDep() {
        departmentDao.removeAllDepartments();
    }

    private Customer createCustomer(String prefix, Long DepId) {
		Customer customer = new Customer();
		customer.setUsername(prefix+"UserName");
		customer.setPassword("xyz");
        customer.setEmail("test@123.de");
        customer.setRole("Admin");
        customer.setFirstname("Peter");
        customer.setLastname("Hans");
        customer.setDepartmentId(DepId);
		return customer;
	}

    public void addTwoCustomer(long DepId) {
		Customer first = createCustomer("First", DepId);
		customerDao.addCustomer(first);
		Customer second = createCustomer("Second", DepId);
		customerDao.addCustomer(second);
    }

    @Test
    public void addDep() {
        Department department = new Department();
        department.setAddress("Bahnhofstr");
        department.setDepname("thisIsName");
        //department.setId(10L);
        departmentDao.addDepartment(department);
    }

    @Test
    public void test() {
        Customer cust = createCustomer("first", 10L);
        customerDao.addCustomer(cust);

    }

    /*@Test
    public void getDep() {
        Department department = new Department();
        department.setAddress("Bahnhofstr");
        department.setDepname("thisIsName");
        //department.setId(10L);
        departmentDao.addDepartment(department);
        Department returnDep = departmentDao.getDepartment(department.getId());
        assertEquals(department.getId(), returnDep.getId());
    }

    /*@Test
    public void addDepAndCust() {
        Department department = new Department();
        department.setAddress("Bahnhofstr");
        department.setDepname("thisIsName");
        department.setId(10L);
        departmentDao.addDepartment(department);
        Customer test = createCustomer("test", 10L);
        
    }*/

}