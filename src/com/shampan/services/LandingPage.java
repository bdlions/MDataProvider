package com.shampan.services;

import com.shampan.db.services.*;
import com.shampan.model.GeneralModel;
import com.shampan.model.LoginAttemptModel;
import com.shampan.model.UserModel;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class LandingPage {

  private static  UserModel userModel = new UserModel();
  private static  LoginAttemptModel loginAttemptModel = new LoginAttemptModel();

    public static void main(String args[]) {
        String result = LandingPage.getLandingPageInfo();
        System.out.println(result);
    }

    public static String getLandingPageInfo() {
        GeneralModel model = new GeneralModel();
        JSONObject json = new JSONObject();
        json.put("countryList", model.getAllCountries());
        json.put("religionList", model.getAllReligions());
        json.put("userList", userModel.getRecentUser());
        return json.toString();
    }

    public static String userRegistration(String usersInfo, String userBasicInfo) {
        String resultEvent = userModel.userRegistration(usersInfo, userBasicInfo).toString();
        return resultEvent;
    }

    public static String getUSerInfoByEmail(String email) {
        String resultEvent = userModel.getUSerInfoByEmail(email).toString();
        return resultEvent;
    }
    public static String getUserInfoByUserId(String userId) {
        String resultEvent = userModel.getUserInfoByUserId(userId).toString();
        return resultEvent;
    }
    public static String activeRegistration(String userId, String activationCode) {
        String resultEvent = userModel.activeRegistration(userId, activationCode).toString();
        return resultEvent;
    }
    public static String deactivateRegistration(String userId, String activationCode) {
        String resultEvent = userModel.deactivateRegistration(userId, activationCode).toString();
        return resultEvent;
    }
    public static String updateLastLogin(String userId) {
        String resultEvent = userModel.updateLastLogin(userId).toString();
        return resultEvent;
    }
    public static String changePassword(String identity, String oldPassword, String newPassword) {
        String resultEvent = userModel.changePassword(identity, oldPassword, newPassword).toString();
        return resultEvent;
    }
    public static String forgottenPassword(String identity) {
        String resultEvent = userModel.forgottenPassword(identity).toString();
        return resultEvent;
    }
    public static String resetPassword(String identity, String newPassword)  {
        String resultEvent = userModel.resetPassword(identity, newPassword).toString();
        return resultEvent;
    }
    public static String hashPasswordDB(String userId)  {
        String resultEvent = userModel.hashPasswordDB(userId).toString();
        return resultEvent;
    }
    public static String rememberUser(String userId, String salt)  {
        String resultEvent = userModel.rememberUser(userId, salt).toString();
        return resultEvent;
    }
    public static String getAttemptsNum(String userId)  {
        String resultEvent = loginAttemptModel.getAttemptsNum(userId).toString();
        return resultEvent;
    }
    public static String clearLoginAttempts(String email)  {
        String resultEvent = loginAttemptModel.clearLoginAttempts(email).toString();
        return resultEvent;
    }
    public static String increaseLoginAttempt(String loginAttemptInfo)  {
        String resultEvent = loginAttemptModel.increaseLoginAttempt(loginAttemptInfo).toString();
        return resultEvent;
    }
}
