package com.shampan.db.collections.fragment.profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class Website {
    private String id;
    @JsonProperty("ws")
    private String website;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
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
