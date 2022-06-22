package de.hse.swa.jpa.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    

    public void addTwoCustomer() {
		Customer first = createCustomer("First", 10L);
		customerDao.save(first);
		Customer second = createCustomer("Second", 10L);
		customerDao.save(second);
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
		customerDao.save(first);
		Customer second = createCustomer("Second", DepId);
		customerDao.save(second);
    }

    @Test
    public void addDep() {
        Department department = new Department();
        department.setAddress("Bahnhofstr");
        department.setDepname("thisIsName");
        //department.setId(10L);
        departmentDao.save(department);
    }

    @Test
    public void test() {
        Customer cust = createCustomer("first", 10L);
        customerDao.save(cust);

    }

    @Test
    public void getDep() {
        Department department = new Department();
        department.setAddress("Bahnhofstr");
        department.setDepname("thisIsName");
        //department.setId(10L);
        departmentDao.save(department);
        Department returnDep = departmentDao.getDepartment(department.getId());
        assertEquals(department.getId(), returnDep.getId());
    }

    @Test
    public void addDepAndCust() {
        Department department = new Department();
        department.setAddress("Bahnhofstr");
        department.setDepname("thisIsName2");
        //department.setId(10L);
        departmentDao.save(department);
        Customer test = createCustomer("test", department.getId());
        customerDao.save(test);
    }

    @Test
    public void addTwoCust() {
        Department department = new Department();
        department.setAddress("Bahnhofstr");
        department.setDepname("thisIsName2");
        //department.setId(10L);
        departmentDao.save(department);
        addTwoCustomer(department.getId());

    }

    @Test
    public void getCustFromDep() {
        Department department = new Department();
        department.setAddress("Bahnhofstr");
        department.setDepname("thisIsName2");
        //department.setId(10L);
        departmentDao.save(department);
        addTwoCustomer(department.getId());
        List<Customer> custList = customerDao.getDepCustomers(department.getId());
        assertEquals(2, custList.size());
    }

    @Test
    public void get2Deps() {
        Department department1 = new Department();
        department1.setAddress("Bahnhofstr");
        department1.setDepname("thisIsName1");
        departmentDao.save(department1);

        Department department2 = new Department();
        department2.setAddress("Bahnhofstr 2");
        department2.setDepname("thisIsName2");
        departmentDao.save(department2);

        List<Department> DepList = departmentDao.getDepartments();
        assertEquals(2, DepList.size());
    }

    @Test
    public void getNoDeps() {
        List<Department> DepList = departmentDao.getDepartments();
        assertEquals(0, DepList.size());
    }

    @Test
    public void deleteDep() {
        Department department1 = new Department();
        department1.setAddress("Bahnhofstr");
        department1.setDepname("thisIsName1");
        departmentDao.save(department1);
        departmentDao.deleteDepartment(department1.getId());
        Department returnDep = departmentDao.getDepartment(department1.getId());
        //assertEquals(0L, returnDep.getId());
        assertNull(returnDep);
    }

}