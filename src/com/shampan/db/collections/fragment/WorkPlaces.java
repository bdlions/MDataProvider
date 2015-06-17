/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author Sampan-IT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkPlaces {
    private String company;
    private String postion;
    private String city;
    private String description;
    private String startDay;
    private String endDay;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
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

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
    
    
    
}
