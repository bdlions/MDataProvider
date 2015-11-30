package com.shampan.db.collections.fragment.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author nazmul hasan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class University {
//    @JsonProperty("id")
    private String id;
//    @JsonProperty("uni")
    private String university;
//    @JsonProperty("desc")
    private String description;
//    @JsonProperty("sd")
    private String startDate;
//    @JsonProperty("ed")
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
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

    public static University getUniversity(String jsonContent) {
        University university = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            university = mapper.readValue(jsonContent, University.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return university;
    }

}
