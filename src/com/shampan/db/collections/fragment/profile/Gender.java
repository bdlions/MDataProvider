package com.shampan.db.collections.fragment.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author nazmul hasan
 */
public class Gender {
    @JsonProperty("gId")
    private String genderId;
    private String title;

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    
    
    public static Gender getGender(String jsonContent){
        Gender gender = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            gender = mapper.readValue(jsonContent, Gender.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gender;
    }

}
