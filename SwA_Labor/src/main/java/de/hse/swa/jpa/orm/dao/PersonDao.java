package de.hse.swa.jpa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.jpa.orm.model.Person;


@ApplicationScoped
public class PersonDao {
    
	@Inject
    EntityManager em; 

    
    public List<Person> getPersons() {
    	 TypedQuery<Person> query = em.createQuery("SELECT u FROM Person u", Person.class);
    	 List<Person> results = query.getResultList();
    	 return results;
    }
    
    public Person getPerson(Long id) {
   	 	return em.find(Person.class, id);
    }

    @Transactional
    public Person save(Person person) {
    	if (person.getId() != null) {
    		person = em.merge(person);
    	} else {
        	em.persist(person);
    	}
    	return person;
    }

    @Transactional
    public void removePerson(Person person) {
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
    	
    	TypedQuery<Person> checkCredentials = em.createQuery(queryString, Person.class);
    	checkCredentials.setParameter("uname", username);
    	List<Person> results = checkCredentials.getResultList();
    	return (results.size() > 0);
    }


}