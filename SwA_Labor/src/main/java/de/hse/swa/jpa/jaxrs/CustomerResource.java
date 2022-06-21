package de.hse.swa.jpa.jaxrs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import de.hse.swa.jpa.orm.dao.CustomerDao;
import de.hse.swa.jpa.orm.model.Customer;
import io.vertx.core.http.HttpServerRequest;


@RequestScoped
@Path("/")
public class CustomerResource {
    @Inject
    CustomerDao customerDao;

    @Context
    HttpServerRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List <Customer> getCustomers(){
        return customerDao.getCustomers();
    }

    @GET
    @Path("id")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(Long id){
        return customerDao.getCustomer(id);
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer login(Customer customer){
        return customerDao.login(customer.getUsername(), customer.getPassword());
    }

    @PUT
    @Path("addcustomer")
    @Consumes(MediaType.APPLICATION_JSON)   
    @Produces(MediaType.APPLICATION_JSON)
    public String addCustomer(Customer customer){
        return customerDao.save(customer);
    }

    @POST
    @Path("updatecustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateCustomer(Customer customer){
        return customerDao.save(customer);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void removeAllCustomers(){
        customerDao.removeAllCustomer();
    }
}
