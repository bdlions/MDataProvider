/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.shampan.model.GeneralModel;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class LandingPage {
    public static String getCountryAndReligion(){
        GeneralModel model = new GeneralModel();

        JSONObject json = new JSONObject();
        json.put("countryList", model.getAllCountries());
        json.put("religionList", model.getAllReligions());
        return json.toString();
    }
}
