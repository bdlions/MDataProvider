/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.status.Comment;

/**
 *
 * @author Sampan IT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Country {
    private String code;
    private String title;
    private String gmtOffset;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(String gmtOffset) {
        this.gmtOffset = gmtOffset;
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

    public static Country getCountryInfo(String jsonContent) {
        Country countryInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            countryInfo = mapper.readValue(jsonContent, Country.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryInfo;
    }    
}
