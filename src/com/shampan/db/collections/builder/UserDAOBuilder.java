package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.UserDAO;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class UserDAOBuilder {
    
    private UserDAO user;
    
    public UserDAOBuilder(){
        user = new UserDAO();
    }
    
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;

    public UserDAOBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    
    public UserDAOBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDAOBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public UserDAOBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return  this;
    }

    public UserDAOBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    
 
    
    public UserDAO build(){
        user.set_id(id);
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        return user;
    }
    public UserDAO build(String daoContent){
        ObjectMapper mapper = new ObjectMapper();
        try {
            user = mapper.readValue(daoContent, UserDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
