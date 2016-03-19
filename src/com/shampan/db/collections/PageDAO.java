/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.page.AgeRange;
import com.shampan.db.collections.fragment.page.PageCategory;
import com.shampan.db.collections.fragment.page.SubCategory;
import org.apache.log4j.Category;

/**
 *
 * @author Sampan IT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDAO {

    private String _id;
    private String pageId;
    private PageCategory category;
    private SubCategory subCategory;
    private String referenceId;
    private UserInfo referenceInfo;
    private String about;
    private AgeRange interestedAgeRange;
    private String intertestedGender;
    private String title;
    private String street;
    private String city;
    private String zipCode;
    private String phone;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public UserInfo getReferenceInfo() {
        return referenceInfo;
    }

    public void setReferenceInfo(UserInfo referenceInfo) {
        this.referenceInfo = referenceInfo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public AgeRange getInterestedAgeRange() {
        return interestedAgeRange;
    }

    public void setInterestedAgeRange(AgeRange interestedAgeRange) {
        this.interestedAgeRange = interestedAgeRange;
    }

    public String getIntertestedGender() {
        return intertestedGender;
    }

    public void setIntertestedGender(String intertestedGender) {
        this.intertestedGender = intertestedGender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PageCategory getCategory() {
        return category;
    }

    public void setCategory(PageCategory category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
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
    

}
