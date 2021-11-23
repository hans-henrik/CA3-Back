/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import callables.ApiFetchCallable;
import com.google.gson.Gson;
import dtos.MovieDTO;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author peter
 */
public class ServerFacade {
    
    private static EntityManagerFactory emf;
    private static ServerFacade instance;
    private Gson gson = new Gson();

    private ServerFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static ServerFacade getServerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ServerFacade();
        }
        return instance;
    }
    
    public List<MovieDTO> getDataFromMovie() throws ExecutionException, InterruptedException {
        
        List<MovieDTO> movieDTOs = new ArrayList();
        
        try {
            
        Reader reader = Files.newBufferedReader(Paths.get("movies.json"));
        
        movieDTOs = Arrays.asList(gson.fromJson(reader, MovieDTO[].class));
        
        reader.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }       
        
        return movieDTOs;
    }
    
}
