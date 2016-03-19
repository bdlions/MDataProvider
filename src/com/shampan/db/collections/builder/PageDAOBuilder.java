/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.PageDAO;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.page.AgeRange;
import com.shampan.db.collections.fragment.page.PageCategory;
import com.shampan.db.collections.fragment.page.SubCategory;
import org.apache.log4j.Category;

/**
 *
 * @author Sampan IT
 */
public class PageDAOBuilder {

    PageDAO pageInfo;

    public PageDAOBuilder() {
        pageInfo = new PageDAO();
    }

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

    public PageDAOBuilder set_id(String _id) {
        this._id = _id;
        return this;
    }

    public PageDAOBuilder setPageId(String pageId) {
        this.pageId = pageId;
        return this;
    }

    public PageDAOBuilder setCategory(PageCategory category) {
        this.category = category;
        return this;
    }

    public PageDAOBuilder setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public PageDAOBuilder setReferenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public PageDAOBuilder setReferenceInfo(UserInfo referenceInfo) {
        this.referenceInfo = referenceInfo;
        return this;
    }

    public PageDAOBuilder setAbout(String about) {
        this.about = about;
        return this;
    }

    public PageDAOBuilder setInterestedAgeRange(AgeRange interestedAgeRange) {
        this.interestedAgeRange = interestedAgeRange;
        return this;
    }

    public PageDAOBuilder setIntertestedGender(String intertestedGender) {
        this.intertestedGender = intertestedGender;
        return this;
    }

    public PageDAOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PageDAOBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public PageDAOBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public PageDAOBuilder setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public PageDAOBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PageDAO build() {
        pageInfo.setPageId(pageId);
        pageInfo.setCategory(category);
        pageInfo.setSubCategory(subCategory);
        pageInfo.setReferenceId(referenceId);
        pageInfo.setReferenceInfo(referenceInfo);
        pageInfo.setTitle(title);
        pageInfo.setPhone(phone);
        pageInfo.setAbout(about);
        pageInfo.setCity(city);
        pageInfo.setStreet(street);
        pageInfo.setZipCode(zipCode);
        pageInfo.setInterestedAgeRange(interestedAgeRange);
        pageInfo.setIntertestedGender(intertestedGender);
        return pageInfo;
    }

    public PageDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            pageInfo = mapper.readValue(daoContent, PageDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pageInfo;
    }
}
