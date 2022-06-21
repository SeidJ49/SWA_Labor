package de.hse.swa.jpa.jaxrs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hse.swa.jpa.orm.dao.Service_contractDao;
import de.hse.swa.jpa.orm.model.Service_contract;
import io.vertx.core.http.HttpServerRequest;

@RequestScoped
@Path("/service_contracts")
public class Service_contractResource {
    @Inject
    Service_contractDao contractDao;

    @Context
    HttpServerRequest request;

    @PUT 
    @Path("addServiceContract")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addServiceContract(Service_contract service_contract){
        contractDao.save(service_contract);
    }

    @POST
    @Path("updateServiceContract")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateServiceContract(Service_contract service_contract){
        contractDao.save(service_contract);
    }

    @POST
    @Path("updateKey")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Service_contract updateKey(Service_contract service_contract){
        Service_contract tmp = contractDao.updateKey(service_contract.getId());
        if(tmp.getId() == 0) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); 
       } else {
           return tmp;
       }
    }

    @POST
    @Path("getAllServiceContracts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Service_contract> getAllServiceContracts(Service_contract service_contract){
        return contractDao.getServiceContracts(service_contract.getDepartmentID());
    }

    @DELETE
    @Path("removeServiceContract")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeServiceContract(Service_contract service_contract){
        contractDao.deleteServiceContract(service_contract.getId());
    }
}
