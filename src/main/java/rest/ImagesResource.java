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
import facades.ServerFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
@Path("image")
public class ImagesResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ServerFacade FACADE =  ServerFacade.getServerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Is this working?\"}";
    }

    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getImageDTO (@QueryParam("movie")String movie) {
        try {
            movie = movie.replaceAll(" ", "%20");
            ImageDTO imageDTO = FACADE.getImageFromAPI(movie);


            return GSON.toJson(imageDTO);
        } catch (Exception e) {
            return GSON.toJson(e);
        }
    }
}