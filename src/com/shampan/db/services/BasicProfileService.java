/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.model.BasicProfileModel;

/**
 *
 * @author Sampan-IT
 */
public class BasicProfileService {

    private static BasicProfileModel obj = new BasicProfileModel();
    
    public static String getOverview(String userId) {
        String overview = obj.getOverview(userId);
        return overview;
    }
    public static String getWorksEducation(String userId) {
        BasicProfileDAO worksEducation = obj.getWorksAndEducation(userId);
        return worksEducation.toString();
    }

    public static String getCityTown(String userId) {
        BasicProfileDAO cityTown = obj.getCityTown(userId);
        return cityTown.toString();
    }

    public static String getFamilyRelation(String userId) {
        BasicProfileDAO familyRelations = obj.getFamilyRelation(userId);
        return familyRelations.toString();
    }
    public static String getContactBasicInfo(String userId) {
        BasicProfileDAO BasicInfo = obj.getContactBasicInfo(userId);
        return BasicInfo.toString();
    }

    public static String addWorkPlace(String userId, String additionalData) {
        String workPlace = obj.addWorkPlace(userId, additionalData);
        return workPlace;
    }

    public static String addPSkill(String userId, String additionalData) {
        String pSkill = obj.addPSkill(userId, additionalData);
        return pSkill;
    }

    public static String addUniversity(String userId, String additionalData) {
        String univesity = obj.addUniversity(userId, additionalData);
        return univesity;
    }

    public static String addCollege(String userId, String additionalData) {
        String response = obj.addCollege(userId, additionalData);
        return response;
    }

    public static String addSchool(String userId, String additionalData) {
        String response = obj.addSchool(userId, additionalData);
        return response;
    }

    public static String addCurrentCity(String userId, String additionalData) {
        String response = obj.addCurrentCity(userId, additionalData);
        return response;
    }

    public static String addHomeTown(String userId, String additionalData) {
        String response = obj.addHomeTown(userId, additionalData);
        return response;
    }
    public static String addRelationshipStatus(String userId, String relationshipStatus) {
        String response = obj.addRelationshipStatus(userId, relationshipStatus);
        return response;
    }
    public static String addMobilePhone(String userId, String mobilePhoneInfo) {
        String response = obj.addMobilePhone(userId, mobilePhoneInfo);
        return response;
    }
    public static String addAddress(String userId, String addressInfo) {
        String response = obj.addAddress(userId, addressInfo);
        return response;
    }
    public static String addWebsite(String userId, String websiteInfo) {
        String response = obj.addWebsite(userId, websiteInfo);
        return response;
    }
    public static String addEmail(String userId, String emailInfo) {
        String response = obj.addEmail(userId, emailInfo);
        return response;
    }

}
