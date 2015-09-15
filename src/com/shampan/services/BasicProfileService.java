package com.shampan.services;

import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.model.BasicProfileModel;

/**
 *
 * @author nazmul hasan
 */
public class BasicProfileService {

    private static BasicProfileModel basicProfileModel = new BasicProfileModel();

    //--------------------------------- About -> Works and Education ------------------------------------//
    /**
     * This method will return details of works and education section of a user
     *
     * @param userId, user idd
     * @return String, details of works and education
     * @author nazmul hasan on 5th September 2015
     */
    public static String getWorksEducation(String userId) {
        BasicProfileDAO worksEducation = basicProfileModel.getWorksEducation(userId);
        return worksEducation.toString();
    }

    /**
     * This method will add workplace of a user
     *
     * @param userId, user id
     * @param workPlaceData, workplace data to be added
     * @return string, status of the operation
     * @author nazmul hasan on 29th august 2015
     */
    public static String addWorkPlace(String userId, String workPlaceData) {
        String workPlace = basicProfileModel.addWorkPlace(userId, workPlaceData);
        return workPlace;
    }

    /**
     * This method will add professional skill of a user
     *
     * @param userId, user id
     * @param professionalSkillData, professional skill data to be added
     * @return string, status of the operation
     * @author nazmul hasan on 29th august 2015
     */
    public static String addProfessionalSkill(String userId, String professionalSkillData) {
        String pSkill = basicProfileModel.addProfessionalSkill(userId, professionalSkillData);
        return pSkill;
    }

    /**
     * This method will add university of a user
     *
     * @param userId, user id
     * @param universityData, university data to be added
     * @return string, status of the operation
     * @author nazmul hasan on 29th august 2015
     */
    public static String addUniversity(String userId, String universityData) {
        String univesity = basicProfileModel.addUniversity(userId, universityData);
        return univesity;
    }

    /**
     * This method will add college of a user
     *
     * @param userId, user id
     * @param collegeData, college data to be added
     * @return string, status of the operation
     * @author nazmul hasan on 29th august 2015
     */
    public static String addCollege(String userId, String collegeData) {
        String response = basicProfileModel.addCollege(userId, collegeData);
        return response;
    }

    /**
     * This method will add school of a user
     *
     * @param userId, user id
     * @param schoolData, school data to be added
     * @return string, status of the operation
     * @author nazmul hasan on 29th august 2015
     */
    public static String addSchool(String userId, String schoolData) {
        String response = basicProfileModel.addSchool(userId, schoolData);
        return response;
    }

    /**
     * This method will edit workplace of a user
     *
     * @return string, status of the operation
     * @author nazmul hasan on 5th September 2015
     */
    public static String UpdateWorkPlace(String userId, String workPlace, String workPlaceData) {
        String response = basicProfileModel.UpdateWorkPlace(userId, workPlace, workPlaceData);
        return response;
    }

    /**
     * This method will edit professional skill of a user
     *
     * @return string, status of the operation
     * @author nazmul hasan on 5th September 2015
     */
    public static String editProfessionalSkill() {

        return "";
    }

    /**
     * This method will edit university of a user
     *
     * @return string, status of the operation
     * @author nazmul hasan on 5th September 2015
     */
    public static String editUniversity() {

        return "";
    }

    /**
     * This method will edit college of a user
     *
     * @return string, status of the operation
     * @author nazmul hasan on 5th September 2015
     */
    public static String editCollege() {

        return "";
    }

    /**
     * This method will edit school of a user
     *
     * @return string, status of the operation
     * @author nazmul hasan on 5th September 2015
     */
    public static String editSchool() {

        return "";
    }

    public static String getOverview(String userId) {
        String overview = basicProfileModel.getOverview(userId);
        return overview;
    }

    public static String getCityTown(String userId) {
        BasicProfileDAO cityTown = basicProfileModel.getCityTown(userId);
        return cityTown.toString();
    }

    public static String getFamilyRelation(String userId) {
        BasicProfileDAO familyRelations = basicProfileModel.getFamilyRelation(userId);
        return familyRelations.toString();
    }

    public static String getContactBasicInfo(String userId) {
        BasicProfileDAO BasicInfo = basicProfileModel.getContactBasicInfo(userId);
        return BasicInfo.toString();
    }

    public static String getAboutFQuote(String userId) {
        BasicProfileDAO aboutFQuote = basicProfileModel.getAboutFQuote(userId);
        return aboutFQuote.toString();
    }

    public static String addAbout(String userId, String aboutInfo) {
        String workPlace = basicProfileModel.addAbout(userId, aboutInfo);
        return workPlace;
    }

    public static String addFQuote(String userId, String fQuoteInfo) {
        String workPlace = basicProfileModel.addFQuote(userId, fQuoteInfo);
        return workPlace;
    }

    public static String addCurrentCity(String userId, String additionalData) {
        String response = basicProfileModel.addCurrentCity(userId, additionalData);
        return response;
    }

    public static String addHomeTown(String userId, String additionalData) {
        String response = basicProfileModel.addHomeTown(userId, additionalData);
        return response;
    }

    public static String addRelationshipStatus(String userId, String relationshipStatus) {
        String response = basicProfileModel.addRelationshipStatus(userId, relationshipStatus);
        return response;
    }

    public static String addMobilePhone(String userId, String mobilePhoneInfo) {
        String response = basicProfileModel.addMobilePhone(userId, mobilePhoneInfo);
        return response;
    }

    public static String addAddress(String userId, String addressInfo) {
        String response = basicProfileModel.addAddress(userId, addressInfo);
        return response;
    }

    public static String addWebsite(String userId, String websiteInfo) {
        String response = basicProfileModel.addWebsite(userId, websiteInfo);
        return response;
    }

    public static String addEmail(String userId, String emailInfo) {
        String response = basicProfileModel.addEmail(userId, emailInfo);
        return response;
    }

}
