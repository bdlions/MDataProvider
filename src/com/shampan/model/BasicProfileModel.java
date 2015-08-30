package com.shampan.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.util.JSON;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.builder.BasicProfileDAOBuilder;
import com.shampan.db.collections.fragment.profile.About;
import com.shampan.db.collections.fragment.profile.Address;
import com.shampan.db.collections.fragment.profile.BasicInfo;
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
import java.util.List;
import org.apache.commons.collections4.list.AbstractLinkedList;
import org.bson.Document;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class BasicProfileModel {

    public BasicProfileModel() {

    }

//...........About Module.........................    
//...........About Overview.........................    
    public String getOverview(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        //pQuery.put("wp", "$all");
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
//.................... About Works and Educations.............................

    public BasicProfileDAO getWorksAndEducation(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
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

    public BasicProfileDAO getWorkPlaces(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("workPlaces", "$all");
        BasicProfileDAO workPlaceCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return workPlaceCursor;

    }

    public BasicProfileDAO getProfessionalSkills(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("pSkills", "$all");
        BasicProfileDAO pskillCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return pskillCursor;

    }

    public BasicProfileDAO getUniversities(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("universities", "$all");
        BasicProfileDAO universitiesCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return universitiesCursor;

    }

    public BasicProfileDAO getColleges(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("colleges", "$all");
        BasicProfileDAO collegesCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return collegesCursor;

    }

    public BasicProfileDAO getSchools(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("schools", "$all");
        BasicProfileDAO schoolsCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return schoolsCursor;

    }

    /**
     * This method will add workplace of a user
     *
     * @param userId, user id
     * @param additionalData, workplace data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public String addWorkPlace(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("wp", "$all");
        BasicProfileDAO workPlaces = mongoCollection.find(selectQuery).projection(pQuery).first();
        WorkPlace workPlaceInfo = WorkPlace.getWorkPlace(additionalData);
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
     * @param additionalData, professional skill data to be added
     * @author nazmul hasan on 29th august 2015
     */
    public String addPSkill(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("pSkills", "$all");
        BasicProfileDAO pSkillCoursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        System.out.println(pSkillCoursor.toString());
        PSkill profetionalSkill = PSkill.getProfessionalSkill(additionalData);
        if (profetionalSkill != null) {
            if (pSkillCoursor != null && pSkillCoursor.getpSkills() != null) {
                pSkillCoursor.getpSkills().add(profetionalSkill);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", pSkillCoursor));

            } else {
                BasicProfileDAO pskill = new BasicProfileDAO();
                pskill.setpSkills(new ArrayList<PSkill>());
                pskill.getpSkills().add(profetionalSkill);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", pskill));
            }
        }

        String response = "successful";
        return response;
    }

    public String addUniversity(String userId, String universityInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("uni", "$all");
        BasicProfileDAO universityCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        System.out.println(universityCursor);
        University university = University.getUniversity(universityInfo);
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

    public String addCollege(String userId, String collegeInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("uId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("clg", "$all");
        BasicProfileDAO collegeCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        College college = College.getCollege(collegeInfo);
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

    public String addSchool(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("schools", "$all");
        BasicProfileDAO schoolCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        School school = School.getSchool(additionalData);
        if (school != null) {
            schoolCursor.getSchools().add(school);
        }
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", schoolCursor));
        String response = "successful";
        return response;
    }

//................. About Places...............................    
    public BasicProfileDAO getCityTown(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo.city", "$all");
        pQuery.put("basicInfo.town", "$all");
        BasicProfileDAO cityTown = mongoCollection.find(selectQuery).projection(pQuery).first();
        return cityTown;
    }

    public String addCurrentCity(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        City currentCity = City.getCurrentCity(additionalData);
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.city", JSON.parse(currentCity.toString()))));
        String response = "successful";
        return response;
    }

    public String addHomeTown(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        Town homeTown = Town.getHomeTown(additionalData);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.town", JSON.parse(homeTown.toString()))));
        String response = "successful";
        return response;
    }

//...........About Family and Relation................
    public BasicProfileDAO getFamilyRelation(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo.familyMember", "$all");
        pQuery.put("basicInfo.relationshipStatus", "$all");
        BasicProfileDAO familyRelations = mongoCollection.find(selectQuery).projection(pQuery).first();
        return familyRelations;
    }

    public String addRelationshipStatus(String userId, String rStatusInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        try {
            if (rStatusInfo != null) {
                RelationStatus rStatus = RelationStatus.getRStatus(rStatusInfo);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.relationshipStatus", JSON.parse(rStatusInfo.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        String response = "successful";
        return response;
    }

//........ About Contact and BasicInfo...............
    public BasicProfileDAO getContactBasicInfo(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo", "$all");
        BasicProfileDAO basicInfo = mongoCollection.find(selectQuery).projection(pQuery).first();
        return basicInfo;
    }

    public BasicProfileDAO getBasicInfo(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("basicInfo", "$all");
        BasicProfileDAO basicInfoCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return basicInfoCursor;

    }

    public String addMobilePhone(String userId, String mobilePhoneInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        MobilePhone mobilePhone = MobilePhone.getMobilePhone(mobilePhoneInfo);
        BasicProfileDAO result1 = mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("basicInfo.mobilePhones", JSON.parse(mobilePhone.toString()))));
        String response = "successful";
        return response;
    }

    public String addAddress(String userId, String addressInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Address address = Address.getAddress(addressInfo);
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.addresses", JSON.parse(address.toString()))));
        String response = "successful";
        return response;
    }

    public String addWebsite(String userId, String websiteInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        try {
            if (websiteInfo != null) {
                Website website = Website.getWebsite(websiteInfo);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.website", JSON.parse(website.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        String response = "successful";
        return response;
    }

    public String addEmail(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Email email = Email.getEmail(additionalData);
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("basicInfo.emails", JSON.parse(email.toString()))));
        String response = "successful";
        return response;
    }

//..........About Places.........................
    public BasicProfileDAO getPlaces(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo.mobilePhones", "$all");
        BasicProfileDAO places = mongoCollection.find(selectQuery).projection(pQuery).first();
        return places;
    }

    //.................About yourself.............
    public BasicProfileDAO getAboutFQuote(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo.about", "$all");
        pQuery.put("basicInfo.fQuote", "$all");
        BasicProfileDAO aboutFQuote = mongoCollection.find(selectQuery).projection(pQuery).first();
        return aboutFQuote;
    }

    public String addFQuote(String userId, String favouriteQuoteInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        try {
            if (favouriteQuoteInfo != null) {
                FavouriteQuote fQuote = FavouriteQuote.getFavouriteQuote(favouriteQuoteInfo);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.fQuote", JSON.parse(fQuote.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        String response = "successful";
        return response;
    }

    public String addAbout(String userId, String aboutInfo) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        try {
            if (aboutInfo != null) {
                About about = About.getAbout(aboutInfo);
                BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("basicInfo.about", JSON.parse(about.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        String response = "successful";
        return response;
    }
//.................. End of About  module.....................

    public void addBasicProfile(Document AdditionalData) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        table.insertOne(AdditionalData);
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
