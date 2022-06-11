package de.hse.swa.jpa.orm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.persistence.Query;

import de.hse.swa.jpa.orm.model.Customer;
import de.hse.swa.jpa.orm.model.Department;


@ApplicationScoped
public class DepartmentDao {
    
	@Inject
    EntityManager em; 

	public Department getDepartment(Long id){
		return em.find(Department.class, id);
	}

	@Transactional
	public List<Department> getDepartment(String id){
		Customer template = new Customer();
		try{
			template = (Customer) em.createQuery("SELECT u FROM CUSTOMER u WHERE u.id=:id").setParameter("id", id).getSingleResult();
		}
		catch(NoResultException e){
			template.setRole("");
		}
		if(template.getRole().equalsIgnoreCase("adminSystem")){
			Query q = em.createQuery("SELECT u FROM DEPARTMENT u");
			@SuppressWarnings("unchecked")
			List<Department> deparments = q.getResultList();
			return deparments;
		}
		else {
			return new ArrayList<>();
		}
	}

	@Transactional
	public String save(Department department){
		try{
			Query q = em.createQuery("SELECT u FROM DEPARTMENT u WHERE u.departmentname=:name AND u.address=:address").setParameter("name", department.getDepname()).setParameter("address", department.getAddress());
			Department template = (Department) q.getSingleResult();
			if(template.getId() != 0L){
				return "Save: ID is Zero";
			}
		}
		catch(NoResultException e){
			try {
				if(department.getId() != 0L){
					em.merge(department);
				}
				else{ 
					em.persist(department);
				}
			}
			catch(PersistenceException ee){
				return "Save: Persistence Exception";
			}
		}
		return "Save: saved";
	}

	@Transactional
	public String deleteDepartment(Long id){
		try {
			Query qF = em.createQuery("DELETE User u WHERE departmentID IN(:departmentID)").setParameter("departmentID", id);
			qF.executeUpdate();

			Query qS = em.createQuery("DELETE Service_contract u WHERE departmentID IN(:departmentID").setParameter("departmentID", id);
			qS.executeUpdate();

			Department dp = em.find(Department.class, id);
			if(dp != null){
				em.remove(dp);
			}
		}
		catch(IllegalStateException e){
			return "Delete: IllegalStateException";
		}
		return "Delete: deleted";
	}
}