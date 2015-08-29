/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.CountriesDAO;

/**
 *
 * @author Sampan-IT
 */
public class Town {

    private String townName;
    private CountriesDAO country;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public CountriesDAO getCountry() {
        return country;
    }

    public void setCountry(CountriesDAO country) {
        this.country = country;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public static Town getHomeTown(String jsonContent) {
        Town town = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            town = mapper.readValue(jsonContent, Town.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return town;
    }

}
