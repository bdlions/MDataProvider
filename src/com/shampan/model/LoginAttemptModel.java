/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.LoginAttemptDAO;
import com.shampan.db.collections.builder.LoginAttemptDAOBuilder;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Sampan IT
 */
public class LoginAttemptModel {

    private ResultEvent resultEvent = new ResultEvent();
    Utility utility = new Utility();

    public LoginAttemptModel() {
        PropertyProvider.add("com.shampan.properties/response");
        PropertyProvider.add("com.shampan.properties/attributes");
    }

    /**
     * This method will return result event
     *
     * @return ResultEvent, result event
     */
    public ResultEvent getResultEvent() {
        return resultEvent;
    }

    /**
     * This method will set result event
     *
     * @param resultEvent, result event
     */
    public void setResultEvent(ResultEvent resultEvent) {
        this.resultEvent = resultEvent;
    }

    /**
     * this method will return total number of login attempts of a user
     *
     * @param email *
     */
    public ResultEvent getAttemptsNum(String email) {
        try {
            MongoCollection<LoginAttemptDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.LOGINATTEMPTS.toString(), LoginAttemptDAO.class);
            String attrEmail = PropertyProvider.get("EMAIL");
            int count = 0;
            Document selectDocument = new Document();
            selectDocument.put(attrEmail, email);
            Document groupDocument = new Document();
            groupDocument.put("", email);
            List<ArrayList> array = new ArrayList<>();
//            mongoCollection.aggregate();
            MongoCursor<LoginAttemptDAO> loginattemptList = mongoCollection.find(selectDocument).iterator();
            while (loginattemptList.hasNext()) {
                count = count + 1;
                this.getResultEvent().setResult(count);
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * this method will remove all login attempts of a user
     *
     * @param email *
     */
    public ResultEvent clearLoginAttempts(String email) {
        try {
            MongoCollection<LoginAttemptDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.LOGINATTEMPTS.toString(), LoginAttemptDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("login", email);
            mongoCollection.deleteMany(selectDocument);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * this method will add login attempt of a user
     *
     * @param email *
     */
    public ResultEvent increaseLoginAttempt(String loginAttemptInfo) {
        try {
            MongoCollection<LoginAttemptDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.LOGINATTEMPTS.toString(), LoginAttemptDAO.class);
            LoginAttemptDAO loginInfo = new LoginAttemptDAOBuilder().build(loginAttemptInfo);
            if (loginInfo != null) {
                loginInfo.setTime(utility.getCurrentTime());
                mongoCollection.insertOne(loginInfo);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

}
