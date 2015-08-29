/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.profile;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class BirthDate {
    private String birthDay;
    private String birthMonth;
    private String birthYear;

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
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

    public static BirthDate getBirthDate(String jsonContent) {
        BirthDate birthDate = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            birthDate = mapper.readValue(jsonContent, BirthDate.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return birthDate;
    }
}
