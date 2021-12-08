/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.RenameMe;
import entities.Role;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {

    private static EntityManagerFactory emf;
    private static UserFacade facade;
    private User user;

    public static void populate() {
        emf = EMF_Creator.createEntityManagerFactory();
        facade = UserFacade.getUserFacade(emf);

        EntityManager em = emf.createEntityManager();

        User u = new User("Valdemar", "Vindersen");
        Role userRole = new Role("user");
        u.addRole(userRole);
        // EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        // FacadeExample fe = FacadeExample.getFacadeExample(emf);
        //UserFacade fa = UserFacade.getUserFacade(emf);
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        //fa.createUserDTO(new UserDTO(new User("Rasmus","HH123")));
        // fa.removeUser("HH");
        // fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
        // fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
        // fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
