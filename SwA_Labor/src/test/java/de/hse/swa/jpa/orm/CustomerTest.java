package de.hse.swa.jpa.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		return customer;
	}
	
	public void addTwoCustomer() {
		Customer first = createCustomer("First");
		//CustomerDao.save(first);
        //em.persist(first);
		Customer second = createCustomer("Second");
		//CustomerDao.save(second);
    }
	

	
	private void printCustomer(Customer customer) {
		System.out.println("id: " + customer.getId());
		System.out.println("Username: " + customer.getUsername());
//		List<Project> projects = person.getProjects();
//		for (Project project: projects) {
//			System.out.println("  Project " + project.getId() + ": " + project.getProjectname());
//		}
	}

    @BeforeEach
    public void clearAllFromDatabase() {
        customerDao.removeAllCustomer();
    }

    @Test
    void addUser_1() {
        Customer first = createCustomer("First");
        customerDao.addCustomer(first);
        //CustomerDao.save(customer);
        List<Customer> customers = customerDao.getCustomers();
        assertEquals(customers.size(), 1);
        printCustomer(customers.get(0));
    }
    
}

