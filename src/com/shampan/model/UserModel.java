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
import com.shampan.db.collections.LoginAttemptDAO;
import com.shampan.db.collections.StatusDAO;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.builder.LoginAttemptDAOBuilder;
import com.shampan.db.collections.builder.UserDAOBuilder;
import com.shampan.db.collections.fragment.profile.BirthDate;
import com.shampan.db.collections.fragment.profile.City;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.list.LazyList;
import static org.bson.BSON.NULL;
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

    /*
     * This method will add registration of a user
     *@param registrationInfo registration Info  add to users collection
     *@param userBasicInfo user BasicInfo Info  add to user_profile collection
     */
    public ResultEvent userRegistration(String usersInfo, String userBasicInfo) {
        try {
            UserDAO userInfo = new UserDAOBuilder().build(usersInfo);
            if (userInfo != null) {
                String email = userInfo.getEmail();
                String userName = userInfo.getUserName();
                ResultEvent validationResult = emailAndUserNameCheck(email, userName);
                if (validationResult.getResponseCode().equals(PropertyProvider.get("USER_ALLOW_FOR_REGISTRATION"))) {
                    ResultEvent resultEvent = registration(userInfo);
                    if (resultEvent.getResponseCode() != PropertyProvider.get("ERROR_EXCEPTION")) {
                        ResultEvent returnResult = basicProfileModel.addUserBasicProfileInfo(userBasicInfo);
                        this.getResultEvent().setResponseCode(returnResult.getResponseCode());
                    }
                } else {
                    this.getResultEvent().setResponseCode(validationResult.getResponseCode());
                }

            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }

        return this.resultEvent;
    }

    /*
     * This method will add registration of a user
     *@param registrationInfo registration Info  add to users collection
     */
    public ResultEvent registration(UserDAO userInfo) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            if (userInfo != null) {
                userInfo.setCreatedOn(utility.getCurrentTime());
                userInfo.setLastLogin(utility.getCurrentTime());
                mongoCollection.insertOne(userInfo);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }
    /*
     *This method check email and user name already exsist or not
     *@param email user email address
     *@param userName user name
     */

    public ResultEvent emailAndUserNameCheck(String email, String userName) {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        String attrEmail = PropertyProvider.get("EMAIL");
        String attrUserName = PropertyProvider.get("USER_NAME");
        String attrUserId = PropertyProvider.get("USER_ID");
        List<Document> orSelectionDocument = new ArrayList<Document>();
        Document emailDocument = new Document();
        emailDocument.put(attrEmail, email);
        Document userNameDocument = new Document();
        userNameDocument.put(attrUserName, userName);
        orSelectionDocument.add(emailDocument);
        orSelectionDocument.add(userNameDocument);
        Document selectDocument = new Document();
        selectDocument.put("$or", orSelectionDocument);
        Document projectionDocument = new Document();
        projectionDocument.put(attrEmail, "$all");
        projectionDocument.put(attrUserName, "$all");
        projectionDocument.put(attrUserId, "$all");
        UserDAO userInfo = mongoCollection.find(selectDocument).projection(projectionDocument).first();
        if (userInfo != null) {
            if (email.equals(userInfo.getEmail())) {
                this.getResultEvent().setResponseCode(PropertyProvider.get("EMAIL_VALIDATION_EXIST"));
            } else if (userName.equals(userInfo.getUserName())) {
                this.getResultEvent().setResponseCode(PropertyProvider.get("USER_NAME_VALIDATION_EXIST"));
            }
        } else {
            this.getResultEvent().setResponseCode(PropertyProvider.get("USER_ALLOW_FOR_REGISTRATION"));
        }
        return this.resultEvent;

    }

    /*
     * This method will return a user Info of a user
     *@param identity is email address of a user
     */
    public ResultEvent getUserInfoByUserId(String userId) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrUserId = PropertyProvider.get("USER_ID");
            String attrFirstName = PropertyProvider.get("FIRST_NAME");
            String attrLastName = PropertyProvider.get("LAST_NAME");
            String attrEmail = PropertyProvider.get("EMAIL");
            String attrPassword = PropertyProvider.get("PASSWORD");
            String attrGroups = PropertyProvider.get("GROUPS");
            String attrAccountStatusId = PropertyProvider.get("ACCOUNT_STATUS_ID");
            String attrLastLogin = PropertyProvider.get("LAST_LOGIN");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrUserId).is(userId).get();
            Document pQueryDocument = new Document();
            pQueryDocument.put(attrUserId, "$all");
            pQueryDocument.put(attrFirstName, "$all");
            pQueryDocument.put(attrLastName, "$all");
            pQueryDocument.put(attrEmail, "$all");
            pQueryDocument.put(attrPassword, "$all");
            pQueryDocument.put(attrGroups, "$all");
            pQueryDocument.put(attrAccountStatusId, "$all");
            pQueryDocument.put(attrLastLogin, "$all");
            UserDAO userInfo = mongoCollection.find(selectQuery).projection(pQueryDocument).first();
            if (userInfo != null) {
                this.getResultEvent().setResult(userInfo);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will return a user Info of a user
     *@param identity is email address of a user
     */
    public ResultEvent getUSerInfoByEmail(String email) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrUserId = PropertyProvider.get("USER_ID");
            String attrFirstName = PropertyProvider.get("FIRST_NAME");
            String attrLastName = PropertyProvider.get("LAST_NAME");
            String attrEmail = PropertyProvider.get("EMAIL");
            String attrPassword = PropertyProvider.get("PASSWORD");
            String attrGroups = PropertyProvider.get("GROUPS");
            String attrAccountStatusId = PropertyProvider.get("ACCOUNT_STATUS_ID");
            String attrLastLogin = PropertyProvider.get("LAST_LOGIN");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("email").is(email).get();
            UserDAO userInfo = mongoCollection.find(selectQuery).first();
            if (userInfo != null) {
                this.getResultEvent().setResult(userInfo);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }


    /*
     * This function takes a userId and validates it
     * against an entry in the users table.
     *return user pasword and salt
     */
    public ResultEvent hashPasswordDB(String userId) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrUserId = PropertyProvider.get("USER_ID");
            String attrPassword = PropertyProvider.get("PASSWORD");
            String attrSalt = PropertyProvider.get("SALT");
            Document selectDocument = new Document();
            selectDocument.put(attrUserId, userId);
            Document projectionDocument = new Document();
            Document pQueryDocument = new Document();
            pQueryDocument.put(attrPassword, "$all");
            pQueryDocument.put(attrSalt, "$all");
            UserDAO userInfo = mongoCollection.find(selectDocument).projection(pQueryDocument).first();
            if (userInfo != null) {
                this.getResultEvent().setResult(userInfo);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }
    /*
     * This method will update the last login time
     * against an entry in the users table.
     *return user pasword and salt
     */

    public ResultEvent updateLastLogin(String userId) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);

            String attrUserId = PropertyProvider.get("USER_ID");
            String attrLastLogin = PropertyProvider.get("LAST_LOGIN");
            Document selectDocument = new Document();
            selectDocument.put(attrUserId, userId);
            Document updateDocument = new Document();
            updateDocument.put(attrLastLogin, utility.getCurrentTime());
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updateDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will update remember code
     *@param userId user id
     *@param salt salt
     */
    public ResultEvent rememberUser(String userId, String salt) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrUserId = PropertyProvider.get("USER_ID");
            String attrRememberCode = PropertyProvider.get("REMEMBER_CODE");
            Document selectDocument = new Document();
            selectDocument.put(attrUserId, userId);
            Document updateDocument = new Document();
            updateDocument.put(attrRememberCode, salt);
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updateDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will active a user account 
     *@param userId user id of user
     *@param activationCode user activation Code
     */
    public ResultEvent activeRegistration(String userId, String activationCode) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrUserId = PropertyProvider.get("USER_ID");
            String attractiveCode = PropertyProvider.get("ACTIVATION_CODE");
            String attractive = PropertyProvider.get("ACTIVE");
            Document selectDocument = new Document();
            selectDocument.put(attrUserId, userId);
            selectDocument.put(attractiveCode, activationCode);
            Document updateDocument = new Document();
            updateDocument.put(attractiveCode, NULL);
            updateDocument.put(attractive, 1);
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updateDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }
    /*
     * This method will deactive a user account 
     *@param userId user id of user
     *@param activationCode user activation Code
     */

    public ResultEvent deactivateRegistration(String userId, String activationCode) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrUserId = PropertyProvider.get("USER_ID");
            String attractiveCode = PropertyProvider.get("ACTIVATION_CODE");
            String attractive = PropertyProvider.get("ACTIVE");
            Document selectDocument = new Document();
            selectDocument.put(attrUserId, userId);
            Document updateDocument = new Document();
            updateDocument.put(attractiveCode, activationCode);
            updateDocument.put(attractive, 0);
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updateDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will change Password of a user
     *@param identity user email address
     *@param oldPassword user old password
     *@param newPassword user new password
     */
    public ResultEvent changePassword(String identity, String oldPassword, String newPassword) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrEmail = PropertyProvider.get("EMAIL");
            String attrPassword = PropertyProvider.get("PASSWORD");
            String attrRememberCode = PropertyProvider.get("REMEMBER_CODE");
            Document selectDocument = new Document();
            selectDocument.put(attrEmail, identity);
            selectDocument.put(attrPassword, oldPassword);
            Document updateDocument = new Document();
            updateDocument.put(attrPassword, newPassword);
            updateDocument.put(attrRememberCode, NULL);
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updateDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will clear a user account forgotten password code 
     *@param forgottenPasswordCode 
     */
    public ResultEvent clearForgottenPasswordCode(String forgottenPasswordCode) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);

            Document selectDocument = new Document();
            selectDocument.put("forgottenPasswordCode", forgottenPasswordCode);
            Document updateDocument = new Document();
            updateDocument.put("forgottenPasswordCode", NULL);
            updateDocument.put("forgottenPasswordTime", NULL);
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updateDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will reset a user password
     *@param identity user email address
     *@param newPassword new password
     */
    public ResultEvent resetPassword(String identity, String newPassword) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrEmail = PropertyProvider.get("EMAIL");
            String attrPassword = PropertyProvider.get("PASSWORD");
            String attrForgottenPasswordCode = PropertyProvider.get("FORGOTTEN_PASSWORD_CODE");
            String attrForgottenPasswordTime = PropertyProvider.get("FORGOTTEN_PASSWORD_TIME");
            String attrRememberCode = PropertyProvider.get("REMEMBER_CODE");
            Document selectDocument = new Document();
            selectDocument.put(attrEmail, identity);
            Document updateDocument = new Document();
            updateDocument.put(attrPassword, newPassword);
            updateDocument.put(attrForgottenPasswordCode, NULL);
            updateDocument.put(attrForgottenPasswordTime, NULL);
            updateDocument.put(attrRememberCode, NULL);
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updateDocument));

            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }


    /*
     * This method will add forgotten password code
     *@param identity user email address
     */
    public ResultEvent forgottenPassword(String identity) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrEmail = PropertyProvider.get("EMAIL");
            String attrForgottenPasswordCode = PropertyProvider.get("FORGOTTEN_PASSWORD_CODE");
            String attrForgottenPasswordTime = PropertyProvider.get("FORGOTTEN_PASSWORD_TIME");
            Document selectDocument = new Document();
            selectDocument.put(attrEmail, identity);
            Document updateDocument = new Document();
            updateDocument.put(attrForgottenPasswordCode, NULL);
            updateDocument.put(attrForgottenPasswordTime, utility.getCurrentTime());
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updateDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }


    /*
     * This method will login a remembed user
     *@param email
     *@param rememberCodet
     * return userInfo
     */
    public ResultEvent loginRememberedUser(String email, String rememberCode) {
        try {
            MongoCollection<UserDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
            String attrEmail = PropertyProvider.get("EMAIL");
            String attrRememberCode = PropertyProvider.get("REMEMBER_CODE");
            String attrUserId = PropertyProvider.get("USER_ID");
            String attrFirstName = PropertyProvider.get("FIRST_NAME");
            String attrLastName = PropertyProvider.get("LAST_NAME");
            String attrGroups = PropertyProvider.get("GROUPS");
            String attrLastLogin = PropertyProvider.get("LAST_LOGIN");
            Document selectDocument = new Document();
            selectDocument.put(attrEmail, email);
            selectDocument.put(attrRememberCode, rememberCode);
            Document projectionDocument = new Document();
            Document pQueryDocument = new Document();
            pQueryDocument.put(attrUserId, "$all");
            pQueryDocument.put(attrFirstName, "$all");
            pQueryDocument.put(attrLastName, "$all");
            pQueryDocument.put(attrEmail, "$all");
            pQueryDocument.put(attrGroups, "$all");
            pQueryDocument.put(attrLastLogin, "$all");
            UserDAO userInfo = mongoCollection.find(selectDocument).projection(pQueryDocument).first();
            if (userInfo != null) {
                this.getResultEvent().setResult(userInfo);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
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
        pQueryDocument.put("gender", "$all");
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
        if (userInfo != null) {
            if (userInfo.getGender() != null) {
                userGenderId = userInfo.getGender().getGenderId();
            }
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
        if (userBasicInfoList != null) {
            int userSize = userBasicInfoList.size();
            int userListSize = userInfoList.size();
            for (int j = 0; j < userSize; j++) {
                for (int i = 0; i < userListSize; i++) {
                    if (userInfoList.get(i).getUserId().equals(userBasicInfoList.get(j).getUserId())) {
//                        BirthDate birthDay = userBasicInfoList.get(j).getBasicInfo().getBirthDate();

                        JSONObject userJson = new JSONObject();
//                        int age = getAge(birthDay);
//                        userJson.put("age", age);
                        userJson.put("userId", userInfoList.get(i).getUserId());
                        userJson.put("firstName", userInfoList.get(i).getFirstName());
                        userJson.put("lastName", userInfoList.get(i).getLastName());
                        userJson.put("gender", userInfoList.get(i).getGender());
                        userJson.put("country", userInfoList.get(i).getCountry());
                        if (userBasicInfoList.get(j).getBasicInfo().getCity() != null) {
                            userJson.put("city", userBasicInfoList.get(j).getBasicInfo().getCity().getCityName());
                        }
                        if (userBasicInfoList.get(j).getpSkills() != null) {
                            int pSkillSize = userBasicInfoList.get(j).getpSkills().size();
                            userJson.put("pSkill", userBasicInfoList.get(j).getpSkills().get(pSkillSize - 1).getpSkill());
                        }
                        requestList.add(userJson);
                    }
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
