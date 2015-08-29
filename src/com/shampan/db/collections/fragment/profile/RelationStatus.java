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
public class RelationStatus {
        private String relationshipStatus;

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
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

    public static RelationStatus getRStatus(String jsonContent) {
        RelationStatus rStatus = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            rStatus = mapper.readValue(jsonContent, RelationStatus.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rStatus;
    }
    
}
