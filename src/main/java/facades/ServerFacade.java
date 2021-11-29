/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import callables.ApiFetchCallable;
import com.google.gson.Gson;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import dtos.ImageDTO;
import dtos.MovieDTO;
import dtos.QuotesDTO;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
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
    private static Gson gson = new Gson();

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
    
        public QuotesDTO getDataFromMovie() throws ExecutionException, InterruptedException {
        
        
        QuotesDTO quotesDTO = null;
        
        try {

            //"C:\\Users\\peter\\Desktop\\Datamatiker\\3. sem\\CA3-Back\\src\\main\\resources\\files\\movies.json"
            
        URL resource = getClass().getClassLoader().getResource("files/movies.json");

        File file = new File(resource.toURI());
      
        Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
        
        
        quotesDTO = gson.fromJson(reader, QuotesDTO.class);    
            
        } catch (Exception ex) {
            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrrrrr");
            ex.printStackTrace();
        }       
        
       
        return quotesDTO;
    }

    public ImageDTO getImageFromAPI(String movie) {
        
        
        
        try {
             ExecutorService executor = Executors.newCachedThreadPool();
        
        String imageHost = "https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&query=" + movie;
        String imageData = executor.submit(new ApiFetchCallable(imageHost, "GET")).get();
        
        
        ImageDTO imageDTO = gson.fromJson(imageData, ImageDTO.class);
        
        return imageDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       return null;
        
    }
  
}
