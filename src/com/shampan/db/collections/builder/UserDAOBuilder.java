package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.fragment.user.Country;
import com.shampan.db.collections.fragment.user.Group;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class UserDAOBuilder {

    private UserDAO user;

    public UserDAOBuilder() {
        user = new UserDAO();
    }

    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String ipAddress;
    private long createdOn;
    private String last_login;
    private String accountStatusId;
    private Country country;
    private List<Group> groups;

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
        return this;
    }

    public UserDAOBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserDAOBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDAOBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDAOBuilder setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public UserDAOBuilder setLast_login(String last_login) {
        this.last_login = last_login;
        return this;
    }

    public UserDAOBuilder setCountry(Country country) {
        this.country = country;
        return this;
    }

    public UserDAOBuilder setGroups(List<Group> groups) {
        this.groups = groups;
        return this;
    }

    public UserDAO build() {
        user.set_id(id);
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setAccountStatusId(accountStatusId);
        user.setCountry(country);
        user.setCreatedOn(createdOn);
        user.setEmail(email);
        user.setGroups(groups);
        return user;
    }

    public UserDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            user = mapper.readValue(daoContent, UserDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

}
