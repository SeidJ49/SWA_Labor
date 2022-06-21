package de.hse.swa.jpa.orm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.boot.TempTableDdlTransactionHandling;
import org.jboss.logging.Logger;

import de.hse.swa.jpa.orm.model.*;

@ApplicationScoped
public class CustomerDao {
    @Inject
    EntityManager em; 

    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class);

    public List<Customer> getCustomers() {
    	 TypedQuery<Customer> query = em.createQuery("SELECT u FROM Customer u", Customer.class);
    	 List<Customer> results = query.getResultList();
    	 return results;
    }

    public Customer getCustomer(long id){
        Customer customer;
        Query qF = em.createQuery("SELECT u FROM Customer u WHERE u.id=:id").setParameter("id", id);
        try {
            customer = (Customer) qF.getSingleResult();
        }
        catch(NoResultException e) {
            customer = new Customer();
            customer.setId(0L);
        }

        /*Query qS = em.createQuery("SELECT u FROM Customer u WHERE u.departmentID=:departmentID").setParameter("departmentID", customer.getDepartmentId());
        @SuppressWarnings("unchecked")
        List <Service_contract> contracts = qS.getResultList();

        for(int j = 0; j < contracts.size(); j++){
            if(customer.getId().equals(contracts.get(j).getCustomerID())){
                customer.getAllContracts().add(contracts.get(j));
            }
        }*/
        return customer;
    }

    public Customer login(String Customername, String password) {
        Customer template;

        LOGGER.debug("Checking for customer name and password");
        Query qF = em.createQuery("SELECT u FROM Customer u WHERE u.username=:username AND u.password=:password").setParameter("username", Customername).setParameter("password", password);
        
        try {
<<<<<<< HEAD
            template = (Customer) qF.getSingleResult();
=======
            LOGGER.debug("Checking for customer name and password");
            template = (Customer) em.createQuery("SELECT u FROM Customer u WHERE u.username=:username AND "
                    + "u.password=:password")
                    .setParameter("username", Customername)
                    .setParameter("password", password).getSingleResult();
            template.setPassword("");
            template.setUsername("");
            return template;
>>>>>>> frontjaxrs
        } catch(NoResultException e) {
            template = new Customer();
            template.setId(0L);
        }
        return template;
    }

    public List<Customer> getDepCustomers(Long id){
        try{
            Query qF = em.createQuery("SELECT u FROM Customer u WHERE u.departmentID=:departmentID").setParameter("departmentID", id);
            @SuppressWarnings("unchecked")
            List<Customer> customers = qF.getResultList();

            Query qS = em.createQuery("SELECT u FROM Service_contract u WHERE u.departmentID=:departmentID").setParameter("departmentID", id);
            @SuppressWarnings("unchecked")
            List<Service_contract> service_contracts = qS.getResultList();

            for(int i = 0; i<customers.size(); i++){
                for(int j = 0; j < service_contracts.size(); j++){
                    if(customers.get(i).getId().equals(service_contracts.get(j).getCustomerID())|| customers.get(i).getId().equals(service_contracts.get(j).getSecCustomerID())){
                        customers.get(i).getAllContracts().add(service_contracts.get(j));
                    }
                }
            }
            return customers;
        }
        catch(NoResultException e){
            return new ArrayList<>();
        }
    }
     
    @Transactional
    public Customer addCustomer(Customer customer) {
    	if (customer.getId() != null) {
    		customer = em.merge(customer);
    	} else {
        	em.persist(customer);
    	}
    	return customer;
    }

    @Transactional
    public String deleteCustomer(Long id){
        try{
            Customer cm = em.find(Customer.class, id);
            if(cm != null){
                em.remove(cm);
            }
        }
        catch(IllegalStateException e){
            return "Illegal State Exception";
        }
        return "Deleted";
    }

    @Transactional
    public void removeAllCustomer() {
        try{
            Query del = em.createQuery("DELETE FROM Customer WHERE id >= 0");
            del.executeUpdate();
        }
        catch(IllegalStateException e) {
            e.printStackTrace();
        }

        return;
    }
}