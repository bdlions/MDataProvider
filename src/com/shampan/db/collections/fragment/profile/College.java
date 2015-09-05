package com.shampan.db.collections.fragment.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author nazmul hasan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class College {
    @JsonProperty("id")
    private String id;
    @JsonProperty("clg")
    private String college;
    @JsonProperty("desc")
    private String description;
    @JsonProperty("sd")
    private String startDate;
    @JsonProperty("ed")
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
    
    public static College getCollege(String jsonContent) {
        College college = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            college = mapper.readValue(jsonContent, College.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return college;
    }

}
