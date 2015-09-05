package com.shampan.db.collections.fragment.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author nazmul hasan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkPlace {
    @JsonProperty("id")
    private String id;
    @JsonProperty("cmp")
    private String company;
    @JsonProperty("pos")
    private String position;
    @JsonProperty("ct")
    private String city;
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
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    /**
     * This method will return object from string
     * @param jsonContent, json format of the object
     */
    public static WorkPlace getWorkPlace(String jsonContent) {
        WorkPlace workPlace = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            workPlace = mapper.readValue(jsonContent, WorkPlace.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return workPlace;
    }
}
