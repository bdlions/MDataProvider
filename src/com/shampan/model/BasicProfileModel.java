package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.operation.FindAndDeleteOperation;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.fragment.profile.About;
import com.shampan.db.collections.fragment.profile.Address;
import com.shampan.db.collections.fragment.profile.BirthDate;
import com.shampan.db.collections.fragment.profile.City;
import com.shampan.db.collections.fragment.profile.College;
import com.shampan.db.collections.fragment.profile.Email;
import com.shampan.db.collections.fragment.profile.FavouriteQuote;
import com.shampan.db.collections.fragment.profile.MobilePhone;
import com.shampan.db.collections.fragment.profile.PSkill;
import com.shampan.db.collections.fragment.profile.RelationStatus;
import com.shampan.db.collections.fragment.profile.School;
import com.shampan.db.collections.fragment.profile.Town;
import com.shampan.db.collections.fragment.profile.University;
import com.shampan.db.collections.fragment.profile.Website;
import com.shampan.db.collections.fragment.profile.WorkPlace;
import com.shampan.util.LogWriter;
import java.util.ArrayList;
import org.bson.Document;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class BasicProfileModel {

    ResultEvent resultEvent = new ResultEvent();

    public BasicProfileModel() {

    }

    //--------------------------------- About -> Works and Education ------------------------------------//
    /**
     * This method will return list of work places , professional skills,
     * universities, colleges and schools of a user
     *
     * @param userId, user id
     * @author nazmul hasan on 5th September 2015
     */
    public BasicProfileDAO getWorksEducation(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("wps", "$all");
        pQuery.put("pss", "$all");
        pQuery.put("unis", "$all");
        pQuery.put("clgs", "$all");
        pQuery.put("schs", "$all");
        BasicProfileDAO workEducationCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        return workEducationCursor;
    }

    /**
     * This method will add workplace of a user
     *
     * @param userId, user id
     * @param workPlaceData, workplace data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public String addWorkPlace(String userId, String workPlaceData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("wps", "$all");
        BasicProfileDAO workPlaces = mongoCollection.find(selectQuery).projection(pQuery).first();
        WorkPlace workPlaceInfo = WorkPlace.getWorkPlace(workPlaceData);
        if (workPlaceInfo != null) {
            if (workPlaces != null && workPlaces.getWorkPlaces() != null) {
                workPlaces.getWorkPlaces().add(workPlaceInfo);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", workPlaces));
            } else {
                BasicProfileDAO wPlace = new BasicProfileDAO();
                wPlace.setWorkPlaces(new ArrayList<WorkPlace>());
                wPlace.getWorkPlaces().add(workPlaceInfo);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", wPlace));
            }
        }
        String response = "successful";
        return response;
    }

    /**
     * This method will add professional skill of a user
     *
     * @param userId, user id
     * @param professionalSkillData, professional skill data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public String addProfessionalSkill(String userId, String professionalSkillData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("pss", "$all");
        BasicProfileDAO pSkillCoursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        System.out.println(pSkillCoursor.toString());
        PSkill professionalSkill = PSkill.getProfessionalSkill(professionalSkillData);
        if (professionalSkill != null) {
            if (pSkillCoursor != null && pSkillCoursor.getpSkills() != null) {
                pSkillCoursor.getpSkills().add(professionalSkill);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", pSkillCoursor));

            } else {
                BasicProfileDAO pskill = new BasicProfileDAO();
                pskill.setpSkills(new ArrayList<PSkill>());
                pskill.getpSkills().add(professionalSkill);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", pskill));
            }
        }

        String response = "successful";
        return response;
    }

    /**
     * This method will add university of a user
     *
     * @param userId, user id
     * @param universityData, university data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public String addUniversity(String userId, String universityData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("unis", "$all");
        BasicProfileDAO universityCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        System.out.println(universityCursor);
        University university = University.getUniversity(universityData);
        if (university != null) {
            if (universityCursor != null && universityCursor.getUniversities() != null) {
                universityCursor.getUniversities().add(university);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", universityCursor));
            } else {
                BasicProfileDAO uniInfo = new BasicProfileDAO();
                uniInfo.setUniversities(new ArrayList<University>());
                uniInfo.getUniversities().add(university);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", uniInfo));
            }
        }
        String response = "successful";
        return response;
    }

    /**
     * This method will add college of a user
     *
     * @param userId, user id
     * @param collegeData, college data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public String addCollege(String userId, String collegeData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("clgs", "$all");
        BasicProfileDAO collegeCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        College college = College.getCollege(collegeData);
        if (college != null) {
            if (collegeCursor != null && collegeCursor.getColleges() != null) {
                collegeCursor.getColleges().add(college);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", collegeCursor));
            } else {
                BasicProfileDAO colInfo = new BasicProfileDAO();
                colInfo.setColleges(new ArrayList<College>());
                colInfo.getColleges().add(college);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", colInfo));
            }
        }
        String response = "successful";
        return response;
    }

    /**
     * This method will add school of a user
     *
     * @param userId, user id
     * @param schoolData, school data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public String addSchool(String userId, String schoolData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("schs", "$all");
        BasicProfileDAO schoolCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        School school = School.getSchool(schoolData);
        if (school != null) {
            if (schoolCursor != null && schoolCursor.getSchools() != null) {
                schoolCursor.getSchools().add(school);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", schoolCursor));
            } else {
                BasicProfileDAO sclInfo = new BasicProfileDAO();
                sclInfo.setSchools(new ArrayList<School>());
                sclInfo.getSchools().add(school);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", sclInfo));

            }
        }
        String response = "successful";
        return response;
    }

    /**
     * This method will edit work place of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public String editWorkPlace(String userId, String workPlaceId, String workPlaceInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        Document sQuery = new Document();
        sQuery.put("uId", userId);
        sQuery.put("wps.id", workPlaceId);
        WorkPlace workPlace = WorkPlace.getWorkPlace(workPlaceInfo);
        if (workPlace != null) {
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("wps.$", JSON.parse(workPlace.toString()))));
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /**
     * This method will edit professional skill of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public String editProfessionalSkill(String userId, String pSkillId, String pSkillInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        Document sQuery = new Document();
        sQuery.put("uId", userId);
        sQuery.put("pss.id", pSkillId);
        PSkill pSInfo = PSkill.getProfessionalSkill(pSkillInfo);
        if (pSInfo != null) {
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("pss.$", JSON.parse(pSInfo.toString()))));
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /**
     * This method will edit university of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public String editUniversity(String userId, String universityId, String universityInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        Document sQuery = new Document();
        sQuery.put("uId", userId);
        sQuery.put("unis.id", universityId);
        University uvInfo = University.getUniversity(universityInfo);
        if (uvInfo != null) {
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("unis.$", JSON.parse(uvInfo.toString()))));
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /**
     * This method will edit college of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public String editCollege(String userId, String collegeId, String collegeInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        Document sQuery = new Document();
        sQuery.put("uId", userId);
        sQuery.put("clgs.id", collegeId);
        College clgInfo = College.getCollege(collegeInfo);
        if (clgInfo != null) {
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("clgs.$", JSON.parse(clgInfo.toString()))));
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /**
     * This method will edit school of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public String editSchool(String userId, String schoolId, String schoolInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        Document sQuery = new Document();
        sQuery.put("uId", userId);
        sQuery.put("schs.id", schoolId);
        School schInfo = School.getSchool(schoolInfo);
        if (schInfo != null) {
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("schs.$", JSON.parse(schInfo.toString()))));
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String deleteWrokPlace(String userId, String workPlaceId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        Document sQuery = new Document();
        sQuery.put("uId", userId);
        sQuery.put("wps.id", workPlaceId);
        Document pQuery = new Document();
        pQuery.put("wps.$.id", "$all");

        System.out.println(mongoCollection.find(sQuery).projection(pQuery).first());
//        System.out.println(new Document("wps", "js67aTI7lbVkipY"));
//        BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("wps", "wps.$")));
//         BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$pull",new Document("wps.id",null)));
//         BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$unset",new Document("wps.$",null)));
        return "successful";
    }

//...........About Module.........................    
//...........About Overview.........................    
    public String getOverview(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("wp", "$all");
        pQuery.put("uni", "$all");
        pQuery.put("bInfo.mobilePhones", "$all");
        pQuery.put("bInfo.emails", "$all");
        pQuery.put("bInfo.city", "$all");
        pQuery.put("bInfo.addresses", "$all");
        pQuery.put("bInfo.bDate", "$all");
        pQuery.put("bInfo.website", "$all");
        BasicProfileDAO overview = mongoCollection.find(selectQuery).projection(pQuery).first();
        JSONObject overviewJson = new JSONObject();
        try {
            if (overview.getWorkPlaces() != null) {
                int workPlaceSize = overview.getWorkPlaces().size();
                WorkPlace lastWork = overview.getWorkPlaces().get(workPlaceSize - 1);
                overviewJson.put("workPlace", lastWork);
            }
            if (overview.getBasicInfo().getMobilePhones() != null) {
                int mobilePhoneSize = overview.getBasicInfo().getMobilePhones().size();
                MobilePhone lastMobilePhone = overview.getBasicInfo().getMobilePhones().get(mobilePhoneSize - 1);
                overviewJson.put("mobilePhone", lastMobilePhone);
            }
            if (overview.getUniversities() != null) {
                int universitySize = overview.getUniversities().size();
                University lastUniversity = overview.getUniversities().get(universitySize - 1);
                overviewJson.put("university", lastUniversity);
            }
            if (overview.getBasicInfo().getEmails() != null) {
                int emailSize = overview.getBasicInfo().getEmails().size();
                Email lastEmail = overview.getBasicInfo().getEmails().get(emailSize - 1);
                overviewJson.put("email", lastEmail);
            }
            if (overview.getBasicInfo().getBirthDate() != null) {
                BirthDate birthday = overview.getBasicInfo().getBirthDate();
                overviewJson.put("birthDate", birthday);
            }
            if (overview.getBasicInfo().getCity() != null) {
                City city = overview.getBasicInfo().getCity();
                overviewJson.put("city", city);
            }
            if (overview.getBasicInfo().getWebsite() != null) {
                Website website = overview.getBasicInfo().getWebsite();
                overviewJson.put("website", website);
            }
            if (overview.getBasicInfo().getAddresses() != null) {
                Address address = overview.getBasicInfo().getAddresses();
                overviewJson.put("address", address);
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }

        return overviewJson.toString();

    }

    public BasicProfileDAO getWorkPlaces(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("wp", "$all");
        BasicProfileDAO workPlaceCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return workPlaceCursor;

    }

    public BasicProfileDAO getProfessionalSkills(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("pSkills", "$all");
        BasicProfileDAO pskillCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return pskillCursor;

    }

    public BasicProfileDAO getUniversities(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("uni", "$all");
        BasicProfileDAO universitiesCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return universitiesCursor;

    }

    public BasicProfileDAO getColleges(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("clg", "$all");
        BasicProfileDAO collegesCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return collegesCursor;

    }

    public BasicProfileDAO getSchools(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("sch", "$all");
        BasicProfileDAO schoolsCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return schoolsCursor;

    }

//................. About Places...............................    
    public BasicProfileDAO getCityTown(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("bInfo.city", "$all");
        pQuery.put("bInfo.town", "$all");
        BasicProfileDAO cityTown = mongoCollection.find(selectQuery).projection(pQuery).first();
        return cityTown;
    }

    public String addCurrentCity(String userId, String currentCityInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        City currentCity = City.getCurrentCity(currentCityInfo);
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.city", JSON.parse(currentCity.toString()))));
        String response = "successful";
        return response;
    }

    public String editCurrentCity(String userId, String cCityInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        City cityInfo = City.getCurrentCity(cCityInfo);
        if (cityInfo != null) {
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.city", JSON.parse(cityInfo.toString()))));
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String deleteCurrentCity(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("bInfo.city", "")));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String addHomeTown(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        Town homeTown = Town.getHomeTown(additionalData);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.town", JSON.parse(homeTown.toString()))));
        String response = "successful";
        return response;
    }

    public String editHomeTown(String userId, String hTownInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Town townInfo = Town.getHomeTown(hTownInfo);
        if (townInfo != null) {
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.town", JSON.parse(townInfo.toString()))));
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String deleteHomeTown(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("bInfo.town", "")));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }
//...........About Family and Relation................

    public BasicProfileDAO getFamilyRelation(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("bInfo.familyMember", "$all");
        pQuery.put("bInfo.relationshipStatus", "$all");
        BasicProfileDAO familyRelations = mongoCollection.find(selectQuery).projection(pQuery).first();
        return familyRelations;
    }

    public String addRelationshipStatus(String userId, String rStatusInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        try {
            if (rStatusInfo != null) {
                RelationStatus rStatus = RelationStatus.getRStatus(rStatusInfo);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.relationshipStatus", JSON.parse(rStatusInfo.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

//........ About Contact and BasicInfo...............
    public BasicProfileDAO getContactBasicInfo(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("bInfo", "$all");
        BasicProfileDAO basicInfo = mongoCollection.find(selectQuery).projection(pQuery).first();
        return basicInfo;
    }

    public String addMobilePhone(String userId, String mobilePhoneInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        MobilePhone mobilePhone = MobilePhone.getMobilePhone(mobilePhoneInfo);
        try {
            if (mobilePhone != null) {
                BasicProfileDAO result1 = mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("bInfo.mps", JSON.parse(mobilePhone.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String editMobilePhone(String userId, String mobileId, String mobilePhoneInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        Document selectQuery = new Document();
        selectQuery.put("uId", userId);
        selectQuery.put("bInfo.mps.id", mobileId);
        MobilePhone mobilePhone = MobilePhone.getMobilePhone(mobilePhoneInfo);
        System.out.println(mobilePhone);
        try {
            if (mobilePhone != null) {
                BasicProfileDAO result1 = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.mps.$", JSON.parse(mobilePhone.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String deleteMobilePhone(String userId, String mobileId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        Document selectQuery = new Document();
        selectQuery.put("uId", userId);
        selectQuery.put("bInfo.mps.id", mobileId);
        BasicProfileDAO result1 = mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("bInfo.mps.$", "")));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String addAddress(String userId, String addressInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Address address = Address.getAddress(addressInfo);
        try {
            if (addressInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.adrses", JSON.parse(address.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String editAddress(String userId, String addressInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Address address = Address.getAddress(addressInfo);
        try {
            if (addressInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.adrses", JSON.parse(address.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String deleteAddress(String userId, String addressInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        try {
            if (addressInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("bInfo.adrses", "")));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String addWebsite(String userId, String websiteInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Website website = Website.getWebsite(websiteInfo);
        try {
            if (website != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.ws", JSON.parse(website.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String editWebsite(String userId, String websiteInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Website website = Website.getWebsite(websiteInfo);
        try {
            if (website != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.ws", JSON.parse(website.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String deleteWebsite(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("bInfo.ws", "")));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String addEmail(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Email email = Email.getEmail(additionalData);
        try {
            if (email != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("basicInfo.emails", JSON.parse(email.toString()))));
            }

        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        String response = "successful";
        return response;
    }

    //.................About yourself.............
    public BasicProfileDAO getAboutFQuote(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("bInfo.about", "$all");
        pQuery.put("bInfo.fQuote", "$all");
        BasicProfileDAO aboutFQuote = mongoCollection.find(selectQuery).projection(pQuery).first();
        return aboutFQuote;
    }

    public String addFQuote(String userId, String favouriteQuoteInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        FavouriteQuote fQuote = FavouriteQuote.getFavouriteQuote(favouriteQuoteInfo);
        try {
            if (fQuote != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.fQuote", JSON.parse(fQuote.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        String response = "successful";
        return response;
    }

    public String addAbout(String userId, String aboutInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        About about = About.getAbout(aboutInfo);
        try {
            if (about != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.about", JSON.parse(about.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        String response = "successful";
        return response;
    }
//.................. End of About  module.....................

    public void testEditField(String userId, String website) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("bInfo.website.website", website)));
    }

    public void updateBasicProfile(String userId, Document additionalData) {

        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        MongoCursor cursor = table.find(searchQuery).iterator();
        Document doc = new Document();
        while (cursor.hasNext()) {
            doc = (Document) cursor.next();
        }
        Document updateObj = new Document();
        updateObj.put("$set", additionalData);
        table.updateOne(doc, updateObj);
    }

//    public MongoCursor getPlaces(String userId) {
//        MongoDatabase db = DBConnection.getInstance().getConnection();
//        MongoCollection table = db.getCollection("users");
//        Document searchQuery = new Document();
//        searchQuery.put("user_id", userId);
//        Document selectQuery = new Document();
//        selectQuery.put("places", "$all");
//        MongoCursor cursor = table.find(searchQuery).projection(selectQuery).iterator();
//        return cursor;
//
//    }
}
