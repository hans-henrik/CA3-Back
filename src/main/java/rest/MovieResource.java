/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import dtos.QuotesDTO;
import facades.ServerFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author peter
 */
@Path("movies")
public class MovieResource {



    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ServerFacade FACADE =  ServerFacade.getServerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Is this working?\"}";
    }

    @Path("quotes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuoteDTOList() {
        try {
            QuotesDTO quotesDTO = FACADE.getDataFromMovie();

            return GSON.toJson(quotesDTO);
        } catch (Exception e) {
            return GSON.toJson(e);
        }
    }
}
