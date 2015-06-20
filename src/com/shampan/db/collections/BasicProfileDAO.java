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
import com.shampan.db.collections.fragment.Name;
import com.shampan.db.collections.fragment.ProfessionalSkills;
import com.shampan.db.collections.fragment.Work;
import com.shampan.db.collections.fragment.WorkPlaces;
import java.util.List;

/**
 *
 * @author alamgir
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicProfileDAO implements Bson {

    private String _id;
    private String userId;
    private String firstName;
    private String lastName;
    private List<BasicInfo> basicInfo;
    private List<WorkPlaces> workPlaces;
    private List<ProfessionalSkills> pSkills;
    private Name name;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<BasicInfo> getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(List<BasicInfo> basicInfo) {
        this.basicInfo = basicInfo;
    }

    public List<WorkPlaces> getWorkPlaces() {
        return workPlaces;
    }

    public void setWorkPlaces(List<WorkPlaces> workPlaces) {
        this.workPlaces = workPlaces;
    }

    public List<ProfessionalSkills> getPSkills() {
        return pSkills;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setPSkills(List<ProfessionalSkills> pSkills) {
        this.pSkills = pSkills;
    }

    @Override
    public <C> BsonDocument toBsonDocument(final Class<C> documentClass, final CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper<BasicProfileDAO>(this, codecRegistry.get(BasicProfileDAO.class));
    }

}
