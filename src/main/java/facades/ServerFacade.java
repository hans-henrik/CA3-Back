/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
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
    
}
