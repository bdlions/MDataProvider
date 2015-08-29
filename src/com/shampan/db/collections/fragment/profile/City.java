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
public class City {

    private String cityName;
    private CountriesDAO country;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public static City getCurrentCity(String jsonContent) {
        City city = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            city = mapper.readValue(jsonContent, City.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return city;
    }

}
