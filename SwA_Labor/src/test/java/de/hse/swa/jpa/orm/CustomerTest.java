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
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CustomerDaoTest {
    
    @Inject
    EntityManager em;
    
    @Inject
    CustomerDao customerDao;
    
    @BeforeEach
	public void clearAll() {
		customerDao.removeAllCustomer();
	}

    private Customer createCustomer(String prefix) {
		Customer customer = new Customer();
		customer.setUsername(prefix+"UserName");
		customer.setPassword("xyz");
        customer.setEmail("test@123.de");
        customer.setRole("Admin");
        customer.setFirstname("Peter");
        customer.setLastname("Hans");
		return customer;
	}
	
	public void addTwoCustomer() {
		Customer first = createCustomer("First");
		customerDao.save(first);
		Customer second = createCustomer("Second");
		customerDao.save(second);
    }
	

	
	private void printCustomer(Customer customer) {
		System.out.println("id: " + customer.getId());
		System.out.println("Username: " + customer.getUsername());
//		List<Project> projects = person.getProjects();
//		for (Project project: projects) {
//			System.out.println("  Project " + project.getId() + ": " + project.getProjectname());
//		}
	}

    @Test
    void addUser_1() {
        Customer first = createCustomer("First");
        customerDao.save(first);
        List<Customer> customers = customerDao.getCustomers();
        assertEquals(1, customers.size());
    }

    @Test
    void addUser_1Wrong() {
        Customer first = createCustomer("First");
        first.setId(0L);
        customerDao.save(first);
        List<Customer> customers = customerDao.getCustomers();
        assertEquals(1, customers.size());
    }

    @Test
    void addUser_2() {
        addTwoCustomer();
        List<Customer> customers = customerDao.getCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    void deleteCust() {
        Customer single = createCustomer("single");
        customerDao.save(single);
        List<Customer> customers = customerDao.getCustomers();
        assertEquals(customers.size(), 1);
        customerDao.deleteCustomer(single.getId());
        customers = customerDao.getCustomers();;
        assertEquals(0, customers.size());
    }

    @Test
    void deleteWrongCust() {
        long id = 100;
        String returnValue;
        Customer single = createCustomer("single");
        customerDao.save(single);
        List<Customer> customers = customerDao.getCustomers();
        assertEquals(customers.size(), 1);
        returnValue = customerDao.deleteCustomer(id);
        customers = customerDao.getCustomers();
        assertEquals(1, customers.size());
        System.out.println(returnValue);
    }

    @Test
    void getOneCust() {
        Customer single = createCustomer("single");
        customerDao.save(single);
        Customer returnCust = customerDao.getCustomer(single.getId());
        assertNotNull(returnCust.getId());
    }

    @Test
    void getWrongCust() {
        long id = -1;
        Customer single = createCustomer("single");
        customerDao.save(single);
        Customer returnCust = customerDao.getCustomer(id);
        assertEquals(0L, returnCust.getId());
    }

    @Test
    void loginTest() {
        Customer single = new Customer();
        single.setUsername("FinnGuist");
        single.setPassword("1234");
        customerDao.save(single);
        Customer loginCust = customerDao.login("FinnGuist", "1234");
        assertEquals(single.getId(), loginCust.getId());
    }

    @Test
    void loginWrongTest() {
        Customer single = new Customer();
        single.setUsername("FinnGuist");
        single.setPassword("1234");
        customerDao.save(single);
        Customer loginCust = customerDao.login("Finn", "1234");
        assertEquals(0L, loginCust.getId());
    }
}

