/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.common.UserInfo;

/**
 *
 * @author Sampan IT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgeRange {
    private String minAge; 
    private String maxAge; 

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
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

    public static AgeRange getUserInformation(String jsonContent) {
        AgeRange ageInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ageInfo = mapper.readValue(jsonContent, AgeRange.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ageInfo;
    }
}
