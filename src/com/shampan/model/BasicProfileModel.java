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
import com.shampan.db.collections.builder.BasicProfileDAOBuilder;
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
import com.shampan.util.PropertyProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class BasicProfileModel {

    ResultEvent resultEvent = new ResultEvent();

    public BasicProfileModel() {
        PropertyProvider.add("com.shampan.properties/response");
        PropertyProvider.add("com.shampan.properties/attributes");

    }

    /**
     * This method will return result event
     *
     * @return ResultEvent, result event
     */
    public ResultEvent getResultEvent() {
        return resultEvent;
    }

    /**
     * This method will set result event
     *
     * @param resultEvent, result event
     */
    public void setResultEvent(ResultEvent resultEvent) {
        this.resultEvent = resultEvent;
    }

    public ResultEvent addUserBasicProfileInfo(String userBasicInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicProfileDAO basicInfo = new BasicProfileDAOBuilder().build(userBasicInfo);
            if (basicInfo != null) {
                mongoCollection.insertOne(basicInfo);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    //--------------------------------- About -> Works and Education ------------------------------------//
    public List<BasicProfileDAO> getRecentUserInfo(String userIdList) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);

        JSONArray userIds = new JSONArray(userIdList);
        int userIdsSize = userIds.length();
        String attrUserId = PropertyProvider.get("USER_ID");
        List<Document> orSelectionDocument = new ArrayList<Document>();
        List<BasicProfileDAO> userList = null;
        if (userIdsSize > 0) {
            for (int i = 0; i < userIdsSize; i++) {
                Document userSelectionDocument = new Document();
                userSelectionDocument.put(attrUserId, userIds.get(i));
                orSelectionDocument.add(userSelectionDocument);
            }
            Document selectDocument = new Document();
            selectDocument.put("$or", orSelectionDocument);
            Document pQueryDocument = new Document();
            pQueryDocument.put(attrUserId, "$all");
            pQueryDocument.put("pSkills", "$all");
            pQueryDocument.put("basicInfo.city", "$all");
//            pQueryDocument.put("basicInfo.birthDate", "$all");
            MongoCursor<BasicProfileDAO> userInfoList = mongoCollection.find(selectDocument).projection(pQueryDocument).iterator();
            userList = IteratorUtils.toList(userInfoList);
        }

        return userList;
    }

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
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("workPlaces", "$all");
        pQuery.put("pSkills", "$all");
        pQuery.put("universities", "$all");
        pQuery.put("colleges", "$all");
        pQuery.put("schools", "$all");
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
    public ResultEvent addWorkPlace(String userId, String workPlaceData) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            WorkPlace workPlaceInfo = WorkPlace.getWorkPlace(workPlaceData);
            if (workPlaceInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("workPlaces", JSON.parse(workPlaceInfo.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * This method will add professional skill of a user
     *
     * @param userId, user id
     * @param professionalSkillData, professional skill data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public ResultEvent addProfessionalSkill(String userId, String professionalSkillData) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            PSkill professionalSkill = PSkill.getProfessionalSkill(professionalSkillData);
            if (professionalSkill != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("pSkills", JSON.parse(professionalSkill.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * This method will add university of a user
     *
     * @param userId, user id
     * @param universityData, university data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public ResultEvent addUniversity(String userId, String universityData) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            University university = University.getUniversity(universityData);
            if (university != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("universities", JSON.parse(university.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * This method will add college of a user
     *
     * @param userId, user id
     * @param collegeData, college data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public ResultEvent addCollege(String userId, String collegeData) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            College college = College.getCollege(collegeData);
            if (college != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("colleges", JSON.parse(college.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * This method will add school of a user
     *
     * @param userId, user id
     * @param schoolData, school data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public ResultEvent addSchool(String userId, String schoolData) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            School school = School.getSchool(schoolData);
            if (school != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("schools", JSON.parse(school.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * This method will edit work place of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public ResultEvent editWorkPlace(String userId, String workPlaceId, String workPlaceInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("workPlaces.id", workPlaceId);
            WorkPlace workPlace = WorkPlace.getWorkPlace(workPlaceInfo);
            if (workPlace != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("workPlaces.$", JSON.parse(workPlace.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * This method will edit professional skill of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public ResultEvent editProfessionalSkill(String userId, String pSkillId, String pSkillInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("pSkills.id", pSkillId);
            PSkill pSInfo = PSkill.getProfessionalSkill(pSkillInfo);
            if (pSInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("pSkills.$", JSON.parse(pSInfo.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * This method will edit university of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public ResultEvent editUniversity(String userId, String universityId, String universityInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("universities.id", universityId);
            University uvInfo = University.getUniversity(universityInfo);
            if (uvInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("universities.$", JSON.parse(uvInfo.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * This method will edit college of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public ResultEvent editCollege(String userId, String collegeId, String collegeInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("colleges.id", collegeId);
            College clgInfo = College.getCollege(collegeInfo);
            if (clgInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("colleges.$", JSON.parse(clgInfo.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * This method will edit school of a user
     *
     * @author nazmul hasan on 5th september 2015
     */
    public ResultEvent editSchool(String userId, String schoolId, String schoolInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("schools.id", schoolId);
            School schInfo = School.getSchool(schoolInfo);
            if (schInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$set", new Document("schools.$", JSON.parse(schInfo.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent deleteWrokPlace(String userId, String workPlaceId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("workPlaces.id", workPlaceId);
            mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("workPlaces", new Document("id", workPlaceId))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent deletePSkill(String userId, String pSkillId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("pSkills.id", pSkillId);
            mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("pSkills", new Document("id", pSkillId))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent deleteUniversity(String userId, String uvId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("universities.id", uvId);
            mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("universities", new Document("id", uvId))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent deleteCollege(String userId, String collegeId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("colleges.id", collegeId);
            mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("colleges", new Document("id", collegeId))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent deleteSchool(String userId, String schoolId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("schools.id", schoolId);
            mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("schools", new Document("id", schoolId))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

//...........About Module.........................    
//...........About Overview.........................    
    public String getOverview(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("workPlaces", "$all");
        pQuery.put("universities", "$all");
        pQuery.put("basicInfo.mobilePhones", "$all");
        pQuery.put("basicInfo.emails", "$all");
        pQuery.put("basicInfo.city", "$all");
        pQuery.put("basicInfo.addresses", "$all");
        pQuery.put("basicInfo.birthDate", "$all");
        pQuery.put("basicInfo.website", "$all");
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
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("workPlaces", "$all");
        BasicProfileDAO workPlaceCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return workPlaceCursor;

    }

    public BasicProfileDAO getProfessionalSkills(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("pSkills", "$all");
        BasicProfileDAO pskillCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return pskillCursor;

    }

    public BasicProfileDAO getUniversities(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("universities", "$all");
        BasicProfileDAO universitiesCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return universitiesCursor;

    }

    public BasicProfileDAO getColleges(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("colleges", "$all");
        BasicProfileDAO collegesCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return collegesCursor;

    }

    public BasicProfileDAO getSchools(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("schools", "$all");
        BasicProfileDAO schoolsCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return schoolsCursor;

    }

//................. About Places...............................    
    public BasicProfileDAO getCityTown(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo.city", "$all");
        pQuery.put("basicInfo.town", "$all");
        BasicProfileDAO cityTown = mongoCollection.find(selectQuery).projection(pQuery).first();
        return cityTown;
    }

    public ResultEvent addCurrentCity(String userId, String currentCityInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            City currentCity = City.getCurrentCity(currentCityInfo);
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.city", JSON.parse(currentCity.toString()))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent editCurrentCity(String userId, String cCityInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            City cityInfo = City.getCurrentCity(cCityInfo);
            if (cityInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.city", JSON.parse(cityInfo.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent deleteCurrentCity(String userId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("basicInfo.city", "")));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent addHomeTown(String userId, String additionalData) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Town homeTown = Town.getHomeTown(additionalData);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.town", JSON.parse(homeTown.toString()))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent editHomeTown(String userId, String hTownInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            Town townInfo = Town.getHomeTown(hTownInfo);
            if (townInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.town", JSON.parse(townInfo.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent deleteHomeTown(String userId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("basicInfo.town", "")));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

//...........About Family and Relation................
    public BasicProfileDAO getFamilyRelation(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo.familyMember", "$all");
        pQuery.put("basicInfo.relationshipStatus", "$all");
        BasicProfileDAO familyRelations = mongoCollection.find(selectQuery).projection(pQuery).first();
        return familyRelations;
    }

    public ResultEvent addRelationshipStatus(String userId, String rStatusInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            if (rStatusInfo != null) {
                RelationStatus rStatus = RelationStatus.getRStatus(rStatusInfo);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.relationshipStatus", JSON.parse(rStatusInfo.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent editRelationshipStatus(String userId, String relation) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.relationshipStatus.relationshipStatus", relation)));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent deleteRelationshipStatus(String userId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("basicInfo.relationshipStatus", "")));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

//........ About Contact and BasicInfo...............
    public BasicProfileDAO getContactBasicInfo(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo", "$all");
        BasicProfileDAO basicInfoResult = mongoCollection.find(selectQuery).projection(pQuery).first();
        return basicInfoResult;
    }

    public ResultEvent addMobilePhone(String userId, String mobilePhoneInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            MobilePhone mobilePhone = MobilePhone.getMobilePhone(mobilePhoneInfo);
            if (mobilePhone != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("basicInfo.mobilePhones", JSON.parse(mobilePhone.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent editMobilePhone(String userId, String mobileId, String mobilePhoneInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document selectQuery = new Document();
            selectQuery.put("userId", userId);
            selectQuery.put("basicInfo.mobilePhones.id", mobileId);
            MobilePhone mobilePhone = MobilePhone.getMobilePhone(mobilePhoneInfo);
            System.out.println(mobilePhone);
            if (mobilePhone != null) {
                BasicProfileDAO result1 = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.mobilePhones.$", JSON.parse(mobilePhone.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent deleteMobilePhone(String userId, String mobileId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document selectQuery = new Document();
            selectQuery.put("userId", userId);
            selectQuery.put("basicInfo.mobilePhones.id", mobileId);
            mongoCollection.findOneAndUpdate(selectQuery, new Document("$pull", new Document("basicInfo.mobilePhones", new Document("id", mobileId))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent addAddress(String userId, String addressInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            Address address = Address.getAddress(addressInfo);
            if (addressInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.addresses", JSON.parse(address.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent editAddress(String userId, String addressInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            Address address = Address.getAddress(addressInfo);

            if (addressInfo != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.addresses", JSON.parse(address.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent deleteAddress(String userId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("basicInfo.addresses", "")));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent addWebsite(String userId, String websiteInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            Website website = Website.getWebsite(websiteInfo);

            if (website != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.website", JSON.parse(website.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent editWebsite(String userId, String websiteInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            Website website = Website.getWebsite(websiteInfo);

            if (website != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.website", JSON.parse(website.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent deleteWebsite(String userId, String websiteId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$unset", new Document("basicInfo.website", "")));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent addEmail(String userId, String additionalData) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            Email email = Email.getEmail(additionalData);

            if (email != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("basicInfo.emails", JSON.parse(email.toString()))));
            }

            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent editEmail(String userId, String emailId, String emailInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document selectQuery = new Document();
            selectQuery.put("userId", userId);
            selectQuery.put("basicInfo.emails.id", emailId);
            Email email = Email.getEmail(emailInfo);
            if (email != null) {
                BasicProfileDAO result1 = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.emails.$", JSON.parse(email.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent deleteEmail(String userId, String emailId) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            Document sQuery = new Document();
            sQuery.put("userId", userId);
            sQuery.put("basicInfo.emails.id", emailId);
            mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("basicInfo.emails", new Document("id", emailId))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    //.................About yourself.............
    public BasicProfileDAO getAboutFQuote(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo.about", "$all");
        pQuery.put("basicInfo.fQuote", "$all");
        BasicProfileDAO aboutFQuote = mongoCollection.find(selectQuery).projection(pQuery).first();
        return aboutFQuote;
    }

    public ResultEvent addFQuote(String userId, String favouriteQuoteInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            FavouriteQuote fQuote = FavouriteQuote.getFavouriteQuote(favouriteQuoteInfo);

            if (fQuote != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.fQuote", JSON.parse(fQuote.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent addAbout(String userId, String aboutInfo) {
        try {
            MongoCollection<BasicProfileDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
            About about = About.getAbout(aboutInfo);

            if (about != null) {
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.about", JSON.parse(about.toString()))));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }
//.................. End of About  module.....................

    public void testEditField(String userId, String website) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERPROFILES.toString(), BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.website.website", website)));
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
