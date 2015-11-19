/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.builder.UserDAOBuilder;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;
import org.json.JSONArray;

/**
 *
 * @author Sampan IT
 */
public class UserModel {

    private ResultEvent resultEvent = new ResultEvent();
    Utility utility = new Utility();

    public UserModel() {
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
     * This method will get a userInfo
     *
     * @param userId user id
     */
    public UserDAO getUserInfo(String userId) {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        String attrUserId = PropertyProvider.get("USER_ID");
        String attrFirstName = PropertyProvider.get("FIRST_NAME");
        String attrLastName = PropertyProvider.get("LAST_NAME");
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrUserId).is(userId).get();
        Document pQueryDocument = new Document();
        pQueryDocument.put(attrUserId, "$all");
        pQueryDocument.put(attrFirstName, "$all");
        pQueryDocument.put(attrLastName, "$all");
        UserDAO userInfo = mongoCollection.find(selectQuery).projection(pQueryDocument).first();
        return userInfo;
    }

    /**
     * This method will get user info list
     *
     * @param userIdList user id list of users
     */
    public List<UserDAO> getUserInfoList(String userIdList) {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);

        String attrUserId = PropertyProvider.get("USER_ID");
        String attrFirstName = PropertyProvider.get("FIRST_NAME");
        String attrLastName = PropertyProvider.get("LAST_NAME");
        JSONArray userIds = new JSONArray(userIdList);
        int userIdsSize = userIds.length();
        List<Document> orSelectionDocument = new ArrayList<Document>();
        List<UserDAO> userList = null;
        if (userIdsSize > 0) {
            for (int i = 0; i < userIdsSize; i++) {
                Document userSelectionDocument = new Document();
                userSelectionDocument.put(attrUserId, userIds.get(i));
                orSelectionDocument.add(userSelectionDocument);
            }
            Document selectDocument = new Document();
            selectDocument.put("$or", orSelectionDocument);
            Document pQueryDocument = new Document();
            pQueryDocument.put(attrUserId, "$all");
            pQueryDocument.put(attrFirstName, "$all");
            pQueryDocument.put(attrLastName, "$all");
            MongoCursor<UserDAO> userInfoList = mongoCollection.find(selectDocument).projection(pQueryDocument).iterator();
            userList = IteratorUtils.toList(userInfoList);
        }
        return userList;

    }

    public UserDAO getUserCountryInfo(String userId) {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        String attrUserId = PropertyProvider.get("USER_ID");
        String attrCountry = PropertyProvider.get("COUNTRY");
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrUserId).is(userId).get();
        Document pQueryDocument = new Document();
        pQueryDocument.put(attrCountry, "$all");
        UserDAO userInfo = mongoCollection.find(selectQuery).projection(pQueryDocument).first();
        return userInfo;
    }
}
