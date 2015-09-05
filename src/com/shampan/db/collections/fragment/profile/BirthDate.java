package com.shampan.db.collections.fragment.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author nazmul hasan
 */
public class BirthDate {
    @JsonProperty("bd")
    private String birthDay;
    @JsonProperty("bm")
    private String birthMonth;
    @JsonProperty("by")
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
