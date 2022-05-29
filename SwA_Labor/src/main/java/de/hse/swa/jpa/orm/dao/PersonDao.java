package de.hse.swa.jpa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.jpa.orm.model.Customer;


@ApplicationScoped
public class PersonDao {
    
	@Inject
    EntityManager em; 

    
    public List<Customer> getPersons() {
    	 TypedQuery<Customer> query = em.createQuery("SELECT u FROM Person u", Customer.class);
    	 List<Customer> results = query.getResultList();
    	 return results;
    }
    
    public Customer getPerson(Long id) {
   	 	return em.find(Customer.class, id);
    }

    @Transactional
    public Customer save(Customer person) {
    	if (person.getId() != null) {
    		person = em.merge(person);
    	} else {
        	em.persist(person);
    	}
    	return person;
    }

    @Transactional
    public void removePerson(Customer person) {
    	em.remove(person);
    }
    
    @Transactional
    public void removeAllPersons() {
    	try {

    	    Query del = em.createQuery("DELETE FROM Person WHERE id >= 0");
    	    del.executeUpdate();

    	} catch (SecurityException | IllegalStateException  e) {
    	    e.printStackTrace();
    	}

    	return;
    }
    
    public Boolean login(String username, String password) {
    	String queryString = "SELECT u FROM Person AS u WHERE u.username = :uname";
    	
    	TypedQuery<Customer> checkCredentials = em.createQuery(queryString, Customer.class);
    	checkCredentials.setParameter("uname", username);
    	List<Customer> results = checkCredentials.getResultList();
    	return (results.size() > 0);
    }


}