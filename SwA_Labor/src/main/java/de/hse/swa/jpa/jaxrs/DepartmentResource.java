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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import de.hse.swa.jpa.orm.dao.DepartmentDao;
import de.hse.swa.jpa.orm.model.Customer;
import de.hse.swa.jpa.orm.model.Department;
import io.vertx.core.http.HttpServerRequest;

@RequestScoped
@Path("/CustomerResource/companies")
public class DepartmentResource {
    
    @Inject
    DepartmentDao departmentDao;

    @Context
    HttpServerRequest request;

    @PUT
    @Path("addDepartment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addDepartment(Department department){
        departmentDao.save(department);
    }
    
    @POST
    @Path("updateDepartment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateDepartment(Department department){
        departmentDao.save(department);
    }

    @POST
    @Path("allDepartment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> getAllDepartments(Customer customer){
        return departmentDao.getDepartments(customer.getId());
    }

    @DELETE
    @Path("deleteDepartment")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeDepartment(Department department){
        departmentDao.deleteDepartment((department.getId()));
    }
}
