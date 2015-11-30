package com.shampan.services;

import com.shampan.db.services.*;
import com.shampan.model.GeneralModel;
import com.shampan.model.UserModel;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class LandingPage {

    public static void main(String args[]) {
        String result = LandingPage.getLandingPageInfo();
        System.out.println(result);
    }

    public static String getLandingPageInfo() {
        GeneralModel model = new GeneralModel();
        UserModel userModel = new UserModel();

        JSONObject json = new JSONObject();
        json.put("countryList", model.getAllCountries());
        json.put("religionList", model.getAllReligions());
        json.put("userList", userModel.getRecentUser());
        return json.toString();
    }
}
