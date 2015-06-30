/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.CountriesDAO;

/**
 *
 * @author Sampan-IT
 */
public class MobilePhone {
    private String phone;
    private CountriesDAO country;

    public CountriesDAO getCountry() {
        return country;
    }

    public void setCountry(CountriesDAO country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
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

    public static MobilePhone getMobilePhone(String jsonContent) {
        MobilePhone mobilePhone = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mobilePhone = mapper.readValue(jsonContent, MobilePhone.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mobilePhone;
    }
    
}
