/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class PSkill {

    private String pSkill;

    public String getpSkill() {
        return pSkill;
    }

    public void setpSkill(String pSkill) {
        this.pSkill = pSkill;
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

    public static PSkill getPSkill(String jsonContent) {
        PSkill pSkill = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            pSkill = mapper.readValue(jsonContent, PSkill.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pSkill;
    }

}
