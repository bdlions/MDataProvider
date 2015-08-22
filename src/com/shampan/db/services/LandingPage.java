package com.shampan.db.services;

import com.shampan.model.GeneralModel;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class LandingPage {
    
    public static void main(String args[])
    {
        String result = LandingPage.getCountryAndReligion();
        System.out.println(result);
    }
    
    
    
    public static String getCountryAndReligion(){
        GeneralModel model = new GeneralModel();

        JSONObject json = new JSONObject();
        json.put("countryList", model.getAllCountries());
        json.put("religionList", model.getAllReligions());
        return json.toString();
    }
}
