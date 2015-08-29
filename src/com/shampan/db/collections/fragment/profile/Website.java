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
public class Website {

    private String website;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public static Website getWebsite(String jsonContent) {
        Website websites = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            websites = mapper.readValue(jsonContent, Website.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return websites;
    }

}
