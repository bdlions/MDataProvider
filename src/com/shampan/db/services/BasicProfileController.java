package com.shampan.db.services;

import com.shampan.services.BasicProfileService;
import com.shampan.services.StatusService;
import io.vertx.ext.web.RoutingContext;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
//@RequestMapping("/basicProfile")
public class BasicProfileController {

//    //--------------------------------- About Works and Educations ------------------------------------//
    public static void getWorksEducation(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.getWorksEducation(userId));
    }

    public static void addWorkPlace(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String workPlaceData = routingContext.request().getParam("workPlaceData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addWorkPlace(userId, workPlaceData));
    }

    public static void addPSkill(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String professionalSkillData = routingContext.request().getParam("professionalSkillData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addProfessionalSkill(userId, professionalSkillData));
    }

    public static void addUniversity(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String universityData = routingContext.request().getParam("universityData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addUniversity(userId, universityData));
    }

    public static void addCollege(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String collegeData = routingContext.request().getParam("collegeData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addCollege(userId, collegeData));
    }

    public static void addSchool(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String schoolData = routingContext.request().getParam("schoolData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addSchool(userId, schoolData));
    }

    public static void editWorkPlace(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String workPlaceId = routingContext.request().getParam("workPlaceId");
        String workPlaceData = routingContext.request().getParam("workPlaceData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editWorkPlace(userId, workPlaceId, workPlaceData));
    }

    public static void editProfessionalSkill(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String pSkillId = routingContext.request().getParam("pSkillId");
        String pSkillData = routingContext.request().getParam("pSkillData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editProfessionalSkill(userId, pSkillId, pSkillData));
    }

    public static void editUniversity(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String universityId = routingContext.request().getParam("universityId");
        String universityData = routingContext.request().getParam("universityData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editUniversity(userId, universityId, universityData));
    }

    public static void editCollege(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String collegeId = routingContext.request().getParam("collegeId");
        String collegeData = routingContext.request().getParam("collegeData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editCollege(userId, collegeId, collegeData));
    }

    public static void editSchool(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String schoolId = routingContext.request().getParam("schoolId");
        String schoolData = routingContext.request().getParam("schoolData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editSchool(userId, schoolId, schoolData));
    }

    public static void deleteWrokPlace(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String wrokPlaceId = routingContext.request().getParam("wrokPlaceId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteWrokPlace(userId, wrokPlaceId));
    }

    public static void deletePSkill(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String pSkillId = routingContext.request().getParam("pSkillId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deletePSkill(userId, pSkillId));
    }

    public static void deleteUniversity(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String universityId = routingContext.request().getParam("universityId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteUniversity(userId, universityId));
    }

    public static void deleteCollege(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String collegeId = routingContext.request().getParam("collegeId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteCollege(userId, collegeId));
    }

    public static void deleteSchool(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String schoolId = routingContext.request().getParam("schoolId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteSchool(userId, schoolId));
    }

    public static void getOverview(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.getOverview(userId));
    }

    public static void getCityTown(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.getCityTown(userId));
    }

    public static void getFamilyRelation(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.getFamilyRelation(userId));
    }

    public static void getContactBasicInfo(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.getContactBasicInfo(userId));
    }

    public static void addCurrentCity(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String additionalData = routingContext.request().getParam("additionalData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addCurrentCity(userId, additionalData));
    }

    public static void editCurrentCity(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String cityData = routingContext.request().getParam("cityData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editCurrentCity(userId, cityData));
    }

    public static void deleteCurrentCity(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteCurrentCity(userId));
    }

    public static void addHomeTown(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String additionalData = routingContext.request().getParam("additionalData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addHomeTown(userId, additionalData));
    }

    public static void editHomeTown(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String townData = routingContext.request().getParam("townData");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editHomeTown(userId, townData));
    }

    public static void deleteHomeTown(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteHomeTown(userId));
    }

    public static void addRelationshipStatus(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String relationshipStatus = routingContext.request().getParam("relationshipStatus");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addRelationshipStatus(userId, relationshipStatus));
    }

    public static void addMobilePhone(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String mobilePhoneInfo = routingContext.request().getParam("mobilePhoneInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addMobilePhone(userId, mobilePhoneInfo));
    }

    public static void editMobilePhone(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String mobileId = routingContext.request().getParam("mobileId");
        String mobilePhoneInfo = routingContext.request().getParam("mobilePhoneInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editMobilePhone(userId, mobileId, mobilePhoneInfo));
    }

    public static void deleteMobilePhone(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String phoneId = routingContext.request().getParam("phoneId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteMobilePhone(userId, phoneId));
    }

    public static void addAddress(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String addressInfo = routingContext.request().getParam("addressInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addAddress(userId, addressInfo));
    }

    public static void editAddress(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String addressInfo = routingContext.request().getParam("addressInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editAddress(userId, addressInfo));
    }

    public static void deleteAddress(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String addressId = routingContext.request().getParam("addressId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteAddress(userId, addressId));
    }

    public static void addWebsite(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String websiteInfo = routingContext.request().getParam("websiteInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addWebsite(userId, websiteInfo));
    }

    public static void editWebsite(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String websiteInfo = routingContext.request().getParam("websiteInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editWebsite(userId, websiteInfo));
    }

    public static void deleteWebsite(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String websiteId = routingContext.request().getParam("websiteId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteWebsite(userId, websiteId));
    }

    public static void addEmail(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String emailInfo = routingContext.request().getParam("emailInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addEmail(userId, emailInfo));
    }

    public static void editEmail(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String emailInfo = routingContext.request().getParam("emailInfo");
        String emailId = routingContext.request().getParam("emailId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.editEmail(userId, emailId, emailInfo));
    }

//    public static void editRelationshipStatus(RoutingContext routingContext) {
//        String userId = routingContext.request().getParam("userId");
//        String relationshipStatus = routingContext.request().getParam("relationshipStatus");
//        routingContext.response()
//                .putHeader("content-type", "application/json; charset=utf-8")
//                .end(BasicProfileService.editRelationshipStatus(userId, relationshipStatus));
//    }
//
    public static void deleteEmail(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String emailId = routingContext.request().getParam("emailId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.deleteEmail(userId, emailId));
    }

    public static void getAboutFQuote(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.getAboutFQuote(userId));
    }

    public static void addAbout(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String aboutInfo = routingContext.request().getParam("aboutInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addAbout(userId, aboutInfo));
    }

    public static void addFQuote(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String fQuoteInfo = routingContext.request().getParam("fQuoteInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(BasicProfileService.addFQuote(userId, fQuoteInfo));
    }

}
