package com.shampan.db.collections.fragment.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author nazmul hasan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PSkill {
//    @JsonProperty("id")
    private String id;
//    @JsonProperty("ps")
    private String pSkill;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
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

    public static PSkill getProfessionalSkill(String jsonContent) {
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
