/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.LoginAttemptDAO;

/**
 *
 * @author Sampan IT
 */
public class LoginAttemptDAOBuilder {

    private LoginAttemptDAO loginAttempt;

    public LoginAttemptDAOBuilder() {
        loginAttempt = new LoginAttemptDAO();
    }

    private String _id;
    private String ipAddress;
    private String login;
    private int time;

    public LoginAttemptDAOBuilder set_id(String _id) {
        this._id = _id;
        return this;
    }

    public LoginAttemptDAOBuilder setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public LoginAttemptDAOBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public LoginAttemptDAOBuilder setTime(int time) {
        this.time = time;
        return this;
    }

    public LoginAttemptDAO build() {
        loginAttempt.setIpAddress(ipAddress);
        loginAttempt.setLogin(login);
        loginAttempt.setTime(time);
        loginAttempt.set_id(_id);
        return loginAttempt;
    }

    public LoginAttemptDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            loginAttempt = mapper.readValue(daoContent, LoginAttemptDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginAttempt;
    }

}
