/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.BasicInfo;
import com.shampan.db.collections.fragment.College;
import com.shampan.db.collections.fragment.PSkill;
import com.shampan.db.collections.fragment.School;
import com.shampan.db.collections.fragment.University;
import com.shampan.db.collections.fragment.WorkPlace;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alamgir
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicProfileDAO implements Bson {

    private String _id;
    private String userId;
    private BasicInfo basicInfo;
    private List<WorkPlace> workPlaces;
    private List<PSkill> pSkills;
    private List<University> universities;
    private List<College> colleges;
    private List<School> schools;
  
    public List<PSkill> getpSkills() {
        return pSkills;
    }

    public void setpSkills(List<PSkill> pSkills) {
        this.pSkills = pSkills;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    public List<College> getColleges() {
        return colleges;
    }

    public void setColleges(List<College> colleges) {
        this.colleges = colleges;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WorkPlace> getWorkPlaces() {
        return workPlaces;
    }

    public void setWorkPlaces(List<WorkPlace> workPlaces) {
        this.workPlaces = workPlaces;
    }

    public List<PSkill> getPSkills() {
        return pSkills;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setPSkills(List<PSkill> pSkills) {
        this.pSkills = pSkills;
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

    @Override
    public <C> BsonDocument toBsonDocument(final Class<C> documentClass, final CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper<BasicProfileDAO>(this, codecRegistry.get(BasicProfileDAO.class));
    }

}
