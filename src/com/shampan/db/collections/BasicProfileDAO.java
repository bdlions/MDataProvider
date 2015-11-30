package com.shampan.db.collections;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.profile.BasicInfo;
import com.shampan.db.collections.fragment.profile.College;
import com.shampan.db.collections.fragment.profile.PSkill;
import com.shampan.db.collections.fragment.profile.School;
import com.shampan.db.collections.fragment.profile.University;
import com.shampan.db.collections.fragment.profile.WorkPlace;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alamgir
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicProfileDAO implements Bson {

    private String _id;
//    @JsonProperty("uId")
    private String userId;
//    @JsonProperty("bInfo")
    private BasicInfo basicInfo;
//    @JsonProperty("wps")
    private List<WorkPlace> workPlaces;
//    @JsonProperty("pss")
    private List<PSkill> pSkills;
//    @JsonProperty("unis")
    private List<University> universities;
//    @JsonProperty("clgs")
    private List<College> colleges;
//    @JsonProperty("schs")
    private List<School> schools;

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

    public List<PSkill> getpSkills() {
        return pSkills;
    }

    public void setpSkills(List<PSkill> pSkills) {
        this.pSkills = pSkills;
    }
    
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
