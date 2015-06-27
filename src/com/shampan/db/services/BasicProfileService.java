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
    
    public static String getWorksEducation(String userId){
        BasicProfileModel obj = new BasicProfileModel();
        BasicProfileDAO  worksEducation = obj.getWorksAndEducation(userId);
        return worksEducation.toString();
    }
    public static String addWorkPlace(String userId,String additionalData){
        BasicProfileModel obj = new BasicProfileModel();
        String  workPlace = obj.addWorkPlace(userId,additionalData);
        return workPlace;
    }
    
    public static String addPSkill(String userId,String additionalData){
        BasicProfileModel obj = new BasicProfileModel();
        String  pSkill = obj.addPSkill(userId,additionalData);
        return pSkill;
    }
    public static String addUniversity(String userId,String additionalData){
        BasicProfileModel obj = new BasicProfileModel();
        String  univesity = obj.addUniversity(userId,additionalData);
        return univesity;
    }
    public static String addCollege(String userId,String additionalData){
        BasicProfileModel obj = new BasicProfileModel();
        String  response = obj.addCollege(userId,additionalData);
        return response;
    }
    public static String addSchool(String userId,String additionalData){
        BasicProfileModel obj = new BasicProfileModel();
        String  response = obj.addSchool(userId,additionalData);
        return response;
    }
     public static String getOverview(String userId){
        BasicProfileModel obj = new BasicProfileModel();
        String overViewPage = obj.getOverview(userId);
        BasicProfileDAO  workPlaces = obj.getWorkPlaces(userId);
        BasicProfileDAO  universities = obj.getWorkPlaces(userId);
        return overViewPage;
    }
    
}
