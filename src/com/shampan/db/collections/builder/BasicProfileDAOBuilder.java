/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.fragment.BasicInfo;
import com.shampan.db.collections.fragment.ProfessionalSkills;
import com.shampan.db.collections.fragment.Work;
import com.shampan.db.collections.fragment.WorkPlaces;
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
    private List basicInfo;
    private List workPlaces;
    private List pSkills;

    public BasicProfileDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public BasicProfileDAOBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public BasicProfileDAOBuilder setBasicInfo(List<BasicInfo> basicInfo) {
        this.basicInfo = basicInfo;
        return this;
    }

    public BasicProfileDAOBuilder setWorkPlaces(List<WorkPlaces> workPlaces) {
        this.workPlaces = workPlaces;
        return this;

    }

    public BasicProfileDAOBuilder setpSkills(List<ProfessionalSkills> pSkills) {
        this.pSkills = pSkills;
        return this;
    }

    public BasicProfileDAO build() {
        basicProfile.set_id(_id);
        basicProfile.setUserId(userId);
        basicProfile.setBasicInfo(basicInfo);
        basicProfile.setWorkPlaces(workPlaces);
        basicProfile.setPSkills(pSkills);
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
