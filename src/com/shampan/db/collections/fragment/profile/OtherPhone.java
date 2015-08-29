/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.profile;

import com.shampan.db.collections.CountriesDAO;

/**
 *
 * @author Sampan-IT
 */
public class OtherPhone {
    private String phone ;
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
    
}
