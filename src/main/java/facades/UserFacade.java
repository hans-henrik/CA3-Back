package facades;

import dtos.UserDTO;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import security.errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }
     private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
     public UserDTO createUserDTO(UserDTO user) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        User u = new User(user);
        try {
            em.getTransaction().begin();
            if (user == null) {
                throw new WebApplicationException("Id does not exist");
            }
            em.persist(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(u);
    }
     
     public List<User> getUserByName(String name){
         EntityManager em = getEntityManager();
        try {
       Query query1 = em.createQuery("Select u FROM User u where u.userName = :Name", User.class);
       query1.setParameter("Name", name);
       List <User> result4 = query1.getResultList();
        return result4;  
        } finally {
            em.close();
        }
        
    }
     
     
        public List<User> getAllUsers() {
        
        EntityManager em = getEntityManager();
        
        try {
        TypedQuery<User> q1 = em.createQuery("SELECT u FROM User u", User.class);
        List<User> users = q1.getResultList();
        return users;
        } finally {
            em.close();
        }
        
    }
    
    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

}
