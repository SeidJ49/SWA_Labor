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
import de.hse.swa.jpa.orm.dao.Service_contractDao;
import de.hse.swa.jpa.orm.model.Service_contract;
import groovy.transform.stc.FirstParam.SecondGenericType;
import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
class DepartmentDaoTest {
    
    @Inject
    EntityManager em;
    
    @Inject
    CustomerDao customerDao;

    @Inject
    DepartmentDao departmentDao;

    @Inject
    Service_contractDao service_contract;
    
    @BeforeEach
	public void clearAllCust() {
		customerDao.removeAllCustomer();
	}

    @BeforeEach
    public void clearAllDep() {
        departmentDao.removeAllDepartments();
    }

    @BeforeEach
    public void clearAllContracts() {
        service_contract.removeAllServiceContracts();
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

    public Department addDep() {
        Department department = new Department();
        department.setAddress("Bahnhofstr");
        department.setDepname("thisIsName");
        departmentDao.save(department);

        return department;
    }

    @Test
    public void saveContract() {
        Department dep = addDep();
        Customer cust1 = createCustomer("first", dep.getId());
        Customer cust2 = createCustomer("second", dep.getId());

        Service_contract contract1 = new Service_contract();
        contract1.setDepartmentID(dep.getId());
        contract1.setCustomerID(cust1.getId());
        contract1.setSecCustomerID(cust2.getId());

        service_contract.save(contract1);
        List<Service_contract> returnContract = service_contract.getServiceContracts(dep.getId());
        assertEquals(1, returnContract.size());
    }
}