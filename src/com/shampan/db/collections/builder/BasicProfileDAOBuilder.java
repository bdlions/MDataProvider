/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.fragment.BasicInfo;
import com.shampan.db.collections.fragment.PSkill;
import com.shampan.db.collections.fragment.WorkPlace;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class BasicProfileDAOBuilder {

    private BasicProfileDAO basicProfile;

    public BasicProfileDAOBuilder() {
        basicProfile = new BasicProfileDAO();
    }

    private String _id;
    private String userId;
    private String firstName;
    private String latName;
    private BasicInfo basicInfo;
    private List workPlaces;
    private List pSkills;
    private List universities;
    private List colleges;
    private List schools;

    public BasicProfileDAOBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BasicProfileDAOBuilder setLatName(String latName) {
        this.latName = latName;
        return this;
    }

    public BasicProfileDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public BasicProfileDAOBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public BasicProfileDAOBuilder setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
        return this;
    }

    public BasicProfileDAOBuilder setWorkPlaces(List<WorkPlace> workPlaces) {
        this.workPlaces = workPlaces;
        return this;

    }

    public BasicProfileDAOBuilder setpSkills(List<PSkill> pSkills) {
        this.pSkills = pSkills;
        return this;
    }

    public BasicProfileDAOBuilder setUniversities(List universities) {
        this.universities = universities;
        return this;
    }

    public BasicProfileDAOBuilder setColleges(List colleges) {
        this.colleges = colleges;
        return this;
    }

    public BasicProfileDAOBuilder setSchools(List schools) {
        this.schools = schools;
        return this;
    }

    public BasicProfileDAO build() {
        basicProfile.set_id(_id);
        basicProfile.setUserId(userId);
        basicProfile.setFirstName(firstName);
        basicProfile.setLastName(latName);
        basicProfile.setBasicInfo(basicInfo);
        basicProfile.setWorkPlaces(workPlaces);
        basicProfile.setPSkills(pSkills);
        basicProfile.setUniversities(universities);
        basicProfile.setColleges(colleges);
        basicProfile.setSchools(schools);
        return basicProfile;
    }

    public BasicProfileDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            basicProfile = mapper.readValue(daoContent, BasicProfileDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return basicProfile;
    }
}
