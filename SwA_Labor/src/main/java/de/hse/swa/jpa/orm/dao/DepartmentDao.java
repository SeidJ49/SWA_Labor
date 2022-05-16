package de.hse.swa.jpa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.jpa.orm.model.Department;

// Dies ist ein Kommentar

@ApplicationScoped
public class DepartmentDao {
    @Inject
    EntityManager em; 

    
    public List<Department> getDepartments() {
    	 TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d", Department.class);
    	 List<Department> results = query.getResultList();
    	 return results;
    }
    
    public Department getDepartment(Long id) {
   	 	return em.find(Department.class, id);
    }

    /**
     * Update an existing department
     * @param person
     * @return the new departments with the id set
     */
    @Transactional 
    public Department addDepartment(Department department) {
    	em.persist(department);
    	return department;
    } 
    
    @Transactional
    public Department updatePerson(Department department) {
    	em.merge(department);
    	return department;
    }

    @Transactional
    public void removePerson(Department department) {
    	em.remove(department);
    	return;
    }
    
    @Transactional
    public void removeAllDepartments() {
    	try {

    	    Query del = em.createQuery("DELETE FROM Department WHERE id >= 0");
    	    del.executeUpdate();

    	} catch (SecurityException | IllegalStateException  e) {
    	    e.printStackTrace();
    	}

    	return;
    }

}