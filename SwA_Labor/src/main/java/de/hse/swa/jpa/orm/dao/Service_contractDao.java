package de.hse.swa.jpa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

import de.hse.swa.jpa.orm.model.*;

@ApplicationScoped
public class Service_contractDao {

	@Inject
	EntityManager em;

	public Service_contract getServiceContract(Long id) {
		return em.find(Service_contract.class, id);
	}

	/*@Transactional
	public List<Service_contract> getServiceContracts(Long id){
		Query qF = em.createQuery("SELECT u FROM Customer u WHERE u.departmentId =: departmentID").setParameter("departmentID", id);
		@SuppressWarnings("unchecked")
		List<Customer> allCustomer = qF.getResultList();

		Query qS = em.createQuery("SELECT u FROM Service_contract u WHERE u.departmentId =: departmentID").setParameter("departmentID", id);
		@SuppressWarnings("unchecked")
		List<Service_contract> service_contracts = qS.getResultList();
		if(!service_contracts.isEmpty()){
			for(int i = 0; i< service_contracts.size();i++){
				for(int j =0; j < allCustomer.size(); j++){
					String name = allCustomer.get(j).getFirstname()+""+allCustomer.get(j).getLastname();
					service_contracts.get(i).getAllUsers().add(name);
				}
			}
		}
		else {
			Service_contract template = new Service_contract();
			template.setId(0L);
			template.setDepartmentID(id);
			service_contracts.add(template);
			for(int i =0; i < allCustomer.size();i++){
				String name = allCustomer.get(i).getFirstname()+""+allCustomer.get(i).getLastname();
				service_contracts.get(0).getAllUsers().add(name);
			}
		}
		return service_contracts;
	}*/

	@Transactional
	public List<Service_contract> getServiceContracts(Long id) {
		TypedQuery<Service_contract> query = em.createQuery("SELECT u FROM Service_contract u", Service_contract.class);
    	List<Service_contract> results = query.getResultList();
    	return results;
	}

	/*@Transactional
	public String save (Service_contract service_contract){
		try{
			String first = service_contract.getResponsable();
			String second = service_contract.getSecondResponsable();
			if(service_contract.getId() != 0L){
				Query q = em.createQuery("SELECT u FROM Service_contract u WHERE u.ID =: service_contractID").setParameter("service_contractID", service_contract.getId());
				Service_contract template = (Service_contract) q.getSingleResult();

				if(!template.getEndDate().equalsIgnoreCase(service_contract.getEndDate()) || !template.getFirstIP().equalsIgnoreCase(service_contract.getFirstIP()) || 
				!template.getSecondIP().equalsIgnoreCase(service_contract.getSecondIP()) || !template.getIpSech().equalsIgnoreCase(service_contract.getIpSech())){
					String version = template.getVersion();
					String[] parts = version.split("\\.");
					int firstVersionTemp = Integer.parseInt(parts[0]);
					int secondVersionTemp = Integer.parseInt(parts[1]);
					if (secondVersionTemp != 9){
						secondVersionTemp = secondVersionTemp +1;
					}
					else {
						firstVersionTemp = firstVersionTemp +1;
						secondVersionTemp =0;
					}
					version = firstVersionTemp + "." + secondVersionTemp;
					service_contract.setVersion(version);
				}
				else {
					service_contract.setVersion(template.getVersion());
				}

				Long departmentID = template.getDepartmentID();
				service_contract.setDepartmentID(departmentID);
				service_contract.setId(template.getId());

				Query qF = em.createQuery("SELECT u FROM Customer u WHERE u.departmentID =: departmentID").setParameter("departmentID", template.getDepartmentID());
				@SuppressWarnings("unchecked")
				List<Customer> allCustomer = qF.getResultList();
				for(int i = 0; i< allCustomer.size(); i++){
					String name = allCustomer.get(i).getFirstname() + " " + allCustomer.get(i).getLastname();
					if(name.equalsIgnoreCase(first)){
						service_contract.setCustomerID(allCustomer.get(i).getId());
					}
					if(name.equalsIgnoreCase(second)){
						service_contract.setSecCustomerID(allCustomer.get(i).getId());
					}
				}
				em.merge(service_contract);
			}
			else {
				Query qF = em.createQuery("SELECT u FROM Customer u WHERE u.departmentID=:departmentID").setParameter("departmentID", service_contract.getDepartmentID());
				@SuppressWarnings("unchecked")
				List<Customer> allCustomer = qF.getResultList();
				for (int i = 0; i <allCustomer.size();i++){
					String name = allCustomer.get(i).getFirstname() + " " + allCustomer.get(i).getLastname();
					if(name.equalsIgnoreCase(first)){
						service_contract.setCustomerID(allCustomer.get(i).getId());
					}
					if(name.equalsIgnoreCase(second)){
						service_contract.setSecCustomerID(allCustomer.get(i).getId());
					}
				}	
				em.persist(service_contract);		
			}
		}
		catch(PersistenceException e){
			return "Save: Persistence Exception";
		}
		return "Save: saved";
	}*/

	@Transactional
	public String save(Service_contract service_contract) {
		//try {
		//	if (service_contract.getId() != 0) {
		//		em.merge(service_contract);
		//	} else {
				em.persist(service_contract);
		//	}
		//} catch (PersistenceException ee) {
		//	return "Save: Persistence Exception";
		//}
		return "Saved to database";
	}

	@Transactional
	public Service_contract updateKey(Long id) {
		try {
			Query q = em.createQuery("SELECT c FROM Service_contract c WHERE c.id=:id").setParameter("id", id);
			Service_contract template = (Service_contract) q.getSingleResult();
			// Änderung
			// template.setId(0L);
			String version = template.getVersion();
			String[] parts = version.split("\\.");
			int firstVersionTemp = Integer.parseInt(parts[0]);
			int secondVersionTemp = Integer.parseInt(parts[1]);
			if (secondVersionTemp != 9) {
				secondVersionTemp = secondVersionTemp + 1;
			} else {
				firstVersionTemp = firstVersionTemp + 1;
				secondVersionTemp = 0;
			}
			version = Integer.toString(firstVersionTemp) + "." + Integer.toString(secondVersionTemp);
			template.setVersion(version);
			return template;
		} catch (PersistenceException e) {
			Service_contract c = new Service_contract();
			c.setId(0L);
			return c;
		}
	}

	@Transactional
	public String deleteServiceContract(Long id) {
		try {
			Service_contract cm = em.find(Service_contract.class, id);
			if (cm != null) {
				em.remove(cm);
			}
		} catch (IllegalStateException e) {
			return "Delete: IllegalStateException";
		}
		return "Delete: deleted";
	}

	@Transactional
    public void removeAllServiceContracts() {
        try{
            Query del = em.createQuery("DELETE FROM Service_contract WHERE id >= 0");
            del.executeUpdate();
        }
        catch(IllegalStateException e) {
            e.printStackTrace();
        }

        return;
    }
}