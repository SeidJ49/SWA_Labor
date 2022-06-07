package de.hse.swa.jpa.orm.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import de.hse.swa.jpa.orm.model.*;
// Dies ist ein Kommentar
// Dies ist ein weiterer Kommentar

@ApplicationScoped
public class CustomerDao {
    @Inject
    EntityManager em; 

    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class);
    public Customer getCustomer(long id){
        Query qF = em.createQuery("SELECT u FROM User u WEHRE u.id=:id").setParameter("id", id);
        Customer customer = (Customer) qF.getSingleResult();

        Query qS = em.createQuery("SELECT u FROM User u WEHRE u.departmentID=:departmentID").setParameter("departmentID", customer.getDepartmentId());
        @SuppressWarnings("unchecked")
        List <Service_contract> contracts = qS.getResultList();

        for(int j = 0; j < contracts.size(); j++){
            if(customer.getId().equals(contracts.get(j).getCustomerID())){
                customer.getAllContracts().add(contracts.get(j));
            }
        }
        return customer;
    }

    public Customer login(String username, String password) {
        Customer template = new Customer();
        try {
            LOGGER.debug("Checking for user name and password");
            template = (Customer) em.createQuery("SELECT u FROM Customer u WHERE u.username=:username AND "
                    + "u.password=:password")
                    .setParameter("username", username)
                    .setParameter("password", password).getSingleResult();
            template.setPassword("");
            template.setUsername("");
            return template;
        } catch(NoResultException e) {
            Customer u = new Customer();
            u.setId(0L);
            return u;
        }
    }

    

}