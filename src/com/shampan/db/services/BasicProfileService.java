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

    public static String getWorksEducation(String userId) {
        BasicProfileDAO worksEducation = obj.getWorksAndEducation(userId);
        return worksEducation.toString();
    }

    public static String getCityTown(String userId) {
        BasicProfileDAO cityTown = obj.getCityTown(userId);
        return cityTown.toString();
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
        BasicProfileModel obj = new BasicProfileModel();
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

    public static String getOverview(String userId) {
        String overViewPage = obj.getOverview(userId);
        BasicProfileDAO workPlaces = obj.getWorkPlaces(userId);
        BasicProfileDAO universities = obj.getWorkPlaces(userId);
        return overViewPage;
    }

}
