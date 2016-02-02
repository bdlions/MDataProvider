package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.fragment.profile.Gender;
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
    private Gender gender;
    private String password;
    private String email;
    private String ipAddress;
    private int createdOn;
    private int lastLogin;
    private String accountStatusId;
    private Country country;
    private List<Group> groups;
    private int active;
    private String activationCode;
    private String rememberCode;
    private String forgottenPasswordCode;
    private int forgottenPasswordTime;
    private String salt;
    

    public UserDAOBuilder setRememberCode(String rememberCode) {
        this.rememberCode = rememberCode;
        return this;
    }

    public UserDAOBuilder setForgottenPasswordCode(String forgottenPasswordCode) {
        this.forgottenPasswordCode = forgottenPasswordCode;
        return this;
    }

    public UserDAOBuilder setForgottenPasswordTime(int forgottenPasswordTime) {
        this.forgottenPasswordTime = forgottenPasswordTime;
        return this;
    }

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

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public UserDAOBuilder setLastLogin(int lastLogin) {
        this.lastLogin = lastLogin;
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

    public UserDAOBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public UserDAOBuilder setActive(int active) {
        this.active = active;
        return this;
    }

    public UserDAOBuilder setActivationCode(String activationCode) {
        this.activationCode = activationCode;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public UserDAOBuilder setSalt(String salt) {
        this.salt = salt;
        return this;
    }
    

    public UserDAO build() {
        user.set_id(id);
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setGender(gender);
        user.setPassword(password);
        user.setEmail(email);
        user.setIpAddress(ipAddress);
        user.setCreatedOn(createdOn);
        user.setLastLogin(lastLogin);
        user.setAccountStatusId(accountStatusId);
        user.setCountry(country);
        user.setGroups(groups);
        user.setActive(active);
        user.setActivationCode(activationCode);
        user.setRememberCode(rememberCode);
        user.setForgottenPasswordCode(forgottenPasswordCode);
        user.setForgottenPasswordTime(forgottenPasswordTime);
        user.setSalt(salt);
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
