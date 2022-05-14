package de.hse.swa.jodel.jaxrs.resources;

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

import de.hse.swa.jodel.orm.dao.UserDao;
import de.hse.swa.jodel.orm.model.User;
import io.vertx.core.http.HttpServerRequest;

@RequestScoped
@Path("/step4/users")
public class Step4UserResource {

    @Inject
    UserDao userDao;
    
    @Context
    HttpServerRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userDao.getUsers();
    }
    
    @GET
    @Path("id")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    /**
     * Update an existing user or create a new one
     * @param user
     * @return the updated user
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        return userDao.save(user);
    } 
    
    /**
     * Create a new user
     * @param user
     * @return the new user
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        return userDao.save(user);
    }
    
    /**
     * Create a new user
     * @param user
     * @return the new user
     */
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User login (User user) {
        return userDao.login(user.getUsername(), user.getPassword());
    }
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void removeAllUsers() {
    	userDao.deleteAllUsers();
    }
}