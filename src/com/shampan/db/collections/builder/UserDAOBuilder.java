/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.fragment.Work;
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
    private String firstName;
    private String lastName;
    private String userName;
    private List workList;

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
    
    public UserDAOBuilder setWorkList(List<Work> workList){
        this.workList = workList;
        return this;
    }
    
    
    public UserDAO build(){
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.set_id(id);
        user.setUsername(userName);
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
