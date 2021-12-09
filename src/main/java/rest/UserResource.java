/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ImageDTO;
import dtos.MovieDTO;
import dtos.QuotesDTO;
import dtos.UserDTO;
import entities.User;
import facades.ServerFacade;
import facades.UserFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author peter
 */

@Path("user")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade FACADE =  UserFacade.getUserFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Is this working?\"}";
    }

    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
    
    return GSON.toJson(FACADE.getAllUsers());          
    }

    
    @Path("createUser")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getCreateUser(String user)
    {
        UserDTO uDTO = GSON.fromJson(user, UserDTO.class);
        uDTO = FACADE.createUserDTO(uDTO);
        return GSON.toJson(uDTO);
    }  
        
}
