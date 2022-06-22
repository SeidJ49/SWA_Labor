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
import javax.persistence.TypedQuery;

import de.hse.swa.jpa.orm.model.Customer;
import de.hse.swa.jpa.orm.model.Department;

import org.hibernate.boot.TempTableDdlTransactionHandling;


@ApplicationScoped
public class DepartmentDao {
    
	@Inject
    EntityManager em; 

	public Department getDepartment(Long id){
		return em.find(Department.class, id);
	}

	@Transactional
	public List<Department> getDepartments(){
		TypedQuery<Department> query = em.createQuery("SELECT u FROM Department u", Department.class);
    	List<Department> results = query.getResultList();
    	return results;
	}

	/*@Transactional
	public String save(Department department){
		try{
			Query q = em.createQuery("SELECT u FROM Department u WHERE u.departmentname=:name AND u.address=:address").setParameter("name", department.getDepname()).setParameter("address", department.getAddress());
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
	}*/

	/*@Transactional
	public String save(Department department){
		try {
			if (department.getId() != 0) {
				em.merge(department);
			} else {
				em.persist(department);
			}
		} catch (PersistenceException ee) {
			return "Save: Persistence Exception";
		}
		return "Saved to database";
	}*/

	@Transactional
	public String save(Department department){
		em.persist(department);
		return "Save";
	}

	@Transactional
	public String deleteDepartment(Long id){
		try {
			/*Query qF = em.createQuery("DELETE Department u WHERE departmentId IN(:departmentId)").setParameter("departmentId", id);
			qF.executeUpdate();

			Query qS = em.createQuery("DELETE Service_contract u WHERE departmentId IN(:departmentId").setParameter("departmentId", id);
			qS.executeUpdate();
			*/
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

	@Transactional
    public void removeAllDepartments() {
        try{
            Query del = em.createQuery("DELETE FROM Department WHERE id >= 0");
            del.executeUpdate();
        }
        catch(IllegalStateException e) {
            e.printStackTrace();
        }

        return;
    }
}