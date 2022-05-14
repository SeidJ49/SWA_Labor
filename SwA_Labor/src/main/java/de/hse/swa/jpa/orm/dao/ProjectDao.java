package de.hse.swa.jpa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.jpa.orm.model.Person;
import de.hse.swa.jpa.orm.model.Project;


@ApplicationScoped
public class ProjectDao {
	
    @Inject
    EntityManager em; 

    
    public List<Project> getProjects() {
    	 TypedQuery<Project> query = em.createQuery("SELECT u FROM Project u", Project.class);
    	 List<Project> results = query.getResultList();
    	 return results;
    }
    
    public Project getProject(Long id) {
   	 	return em.find(Project.class, id);
    }
    
    public List<Project> getProjects(Person person) {
   	 TypedQuery<Project> query = em.createQuery(
   			 "SELECT proj FROM Project AS proj JOIN proj.persons pers WHERE pers.id = :PERS", 
   			 Project.class);
   	 query.setParameter("PERS",person.getId());
   	 List<Project> results = query.getResultList();
   	 return results;
   }


    @Transactional
    public Project save(Project project) {
    	if (project.getId() != null) {
    		project = em.merge(project);
    	} else {
    		em.persist(project);
    	}
    	return project;
    }

    @Transactional
    public void removeProject(Project project) {
    	em.remove(project);
    }
    
    @Transactional
    public void addPersonToProject(Person person, Project project) {

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