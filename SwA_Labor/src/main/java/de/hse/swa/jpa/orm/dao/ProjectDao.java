package de.hse.swa.jpa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.jpa.orm.model.Customer;
import de.hse.swa.jpa.orm.model.Service_contract;


@ApplicationScoped
public class ProjectDao {
	
    @Inject
    EntityManager em; 

    
    public List<Service_contract> getProjects() {
    	 TypedQuery<Service_contract> query = em.createQuery("SELECT u FROM Project u", Service_contract.class);
    	 List<Service_contract> results = query.getResultList();
    	 return results;
    }
    
    public Service_contract getProject(Long id) {
   	 	return em.find(Service_contract.class, id);
    }
    
    public List<Service_contract> getProjects(Customer person) {
   	 TypedQuery<Service_contract> query = em.createQuery(
   			 "SELECT proj FROM Project AS proj JOIN proj.persons pers WHERE pers.id = :PERS", 
   			 Service_contract.class);
   	 query.setParameter("PERS",person.getId());
   	 List<Service_contract> results = query.getResultList();
   	 return results;
   }


    @Transactional
    public Service_contract save(Service_contract project) {
    	if (project.getId() != null) {
    		project = em.merge(project);
    	} else {
    		em.persist(project);
    	}
    	return project;
    }

    @Transactional
    public void removeProject(Service_contract project) {
    	em.remove(project);
    }
    
    @Transactional
    public void addPersonToProject(Customer person, Service_contract project) {

		project.addPerson(person);
		save(project);
		
    }
    
    @Transactional
    public void removeAllProjects() {
    	try {

    	    Query del = em.createQuery("DELETE FROM Project WHERE id >= 0");
    	    del.executeUpdate();

    	} catch (SecurityException | IllegalStateException  e) {
    	    e.printStackTrace();
    	}

    	return;
    }

}