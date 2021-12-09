package dtos;

import entities.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserDTO {
    private String userName;
    private String userPass;
    // private Long id;
    
    public UserDTO(String userName, String userPass) {
    this.userName = userName;
    this.userPass = userPass;
  }
    
    public UserDTO(User u) {
        this.userName = u.getUserName();
        this.userPass = u.getUserPass();
    }

    public UserDTO() {
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userName=" + userName + ", userPass=" + userPass + '}';
    }

    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    
}