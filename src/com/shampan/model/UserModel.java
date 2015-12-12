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
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.StatusDAO;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.builder.UserDAOBuilder;
import com.shampan.db.collections.fragment.profile.BirthDate;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.list.LazyList;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Sampan IT
 */
public class UserModel {

    private ResultEvent resultEvent = new ResultEvent();
    Utility utility = new Utility();
    BasicProfileModel basicProfileModel = new BasicProfileModel();

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
            pQueryDocument.put("gender", "$all");
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

    public String getUserGenderInfo(String userId) {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        String attrUserId = PropertyProvider.get("USER_ID");
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrUserId).is(userId).get();
        Document pQueryDocument = new Document();
        pQueryDocument.put("gender", "$all");
        UserDAO userInfo = mongoCollection.find(selectQuery).projection(pQueryDocument).first();
        String userGenderId = "";
        if (userInfo.getGender() != null) {
            userGenderId = userInfo.getGender().getGenderId();
        }
        return userGenderId;
    }

    public List<JSONObject> getRecentUser() {
        int offset = 0;
        int limit = 10;
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        Document projectionDocument = new Document();
        projectionDocument.put("firstName", "$all");
        projectionDocument.put("lastName", "$all");
        projectionDocument.put("userId", "$all");
        projectionDocument.put("gender", "$all");
        projectionDocument.put("country", "$all");
        List<JSONObject> requestList = new ArrayList<JSONObject>();
        List<UserDAO> userInfoList = new ArrayList<>();
        MongoCursor<UserDAO> userList = mongoCollection.find().sort(new Document("modifiedOn", -1)).skip(offset).limit(limit).projection(projectionDocument).iterator();
        List<String> userIds = new ArrayList<>();
        while (userList.hasNext()) {
            UserDAO user = userList.next();
            userIds.add(user.getUserId());
            userInfoList.add(user);

        }

        List<BasicProfileDAO> userBasicInfoList = basicProfileModel.getRecentUserInfo(userIds.toString());
        int userSize = userBasicInfoList.size();
        int userListSize = userInfoList.size();
        for (int j = 0; j < userSize; j++) {
            for (int i = 0; i < userListSize; i++) {
                if (userInfoList.get(i).getUserId().equals(userBasicInfoList.get(j).getUserId())) {
                    BirthDate birthDay = userBasicInfoList.get(j).getBasicInfo().getBirthDate();
                    JSONObject userJson = new JSONObject();
                    int age = getAge(birthDay);
                    userJson.put("age", age);
                    userJson.put("userId", userInfoList.get(i).getUserId());
                    userJson.put("firstName", userInfoList.get(i).getFirstName());
                    userJson.put("lastName", userInfoList.get(i).getLastName());
                    userJson.put("gender", userInfoList.get(i).getGender());
                    userJson.put("country", userInfoList.get(i).getCountry());
                    if (userBasicInfoList.get(j).getpSkills() != null) {
                       int pSkillSize= userBasicInfoList.get(j).getpSkills().size();
                        userJson.put("pSkill", userBasicInfoList.get(j).getpSkills().get(pSkillSize-1).getpSkill());
                    }
                    requestList.add(userJson);
                }
            }
        }

        return requestList;
    }

    public int getAge(BirthDate birthDay) {
        String year = birthDay.getBirthYear();
        String month = birthDay.getBirthMonth();
        String day = birthDay.getBirthDay();
        //set up date of birth
        Calendar calDOB = Calendar.getInstance();
        calDOB.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        //setup calNow as today.
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new java.util.Date());
        //calculate age in years.
        int ageYr = (calNow.get(Calendar.YEAR) - calDOB.get(Calendar.YEAR));
        // calculate additional age in months, possibly adjust years.
        int ageMo = (calNow.get(Calendar.MONTH) - calDOB.get(Calendar.MONTH));
        if (ageMo < 0) {
            //adjust years by subtracting one
            ageYr--;
        }
        return ageYr;
    }
}
