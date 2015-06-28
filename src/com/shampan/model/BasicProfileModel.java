/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.builder.BasicProfileDAOBuilder;
import com.shampan.db.collections.fragment.BasicInfo;
import com.shampan.db.collections.fragment.City;
import com.shampan.db.collections.fragment.College;
import com.shampan.db.collections.fragment.PSkill;
import com.shampan.db.collections.fragment.School;
import com.shampan.db.collections.fragment.Town;
import com.shampan.db.collections.fragment.University;
import com.shampan.db.collections.fragment.WorkPlace;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class BasicProfileModel {

    public BasicProfileModel() {

    }

    public String getOverview(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("workPlaces", "$all");
        pQuery.put("pSkills", "$all");
        pQuery.put("universities", "$all");
        pQuery.put("colleges", "$all");
        pQuery.put("schools", "$all");
        pQuery.put("basicInfo", "$all");
        BasicProfileDAO overviewCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        int size = overviewCursor.getWorkPlaces().size();
        int size1 = overviewCursor.getUniversities().size();
        WorkPlace lastWork = overviewCursor.getWorkPlaces().get(size - 1);
        University lastUniversity = overviewCursor.getUniversities().get(size1 - 1);
        System.out.println(lastWork.toString());
        System.out.println(lastUniversity.toString());
        JSONObject json = new JSONObject();
        json.put("workPlace", lastWork);
        json.put("University", lastUniversity);
        //System.out.println(json.toString());
        return json.toString();

    }

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

    public BasicProfileDAO getPlaces(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo.mobilePhones", "$all");
        BasicProfileDAO places = mongoCollection.find(selectQuery).projection(pQuery).first();
        return places;
    }

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

    public String addWorkPlace(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("workPlaces", "$all");
        BasicProfileDAO workPlaceCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        WorkPlace workPlace = WorkPlace.getWorkPlace(additionalData);
        System.out.println(workPlace);
        if (workPlace != null) {
            workPlaceCursor.getWorkPlaces().add(workPlace);
        }

        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", workPlace));
        String response = "successful";
        return response;
    }

    public String addUniversity(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("universities", "$all");
        BasicProfileDAO universityCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        University university = University.getUniversity(additionalData);
        if (university != null) {
            universityCursor.getUniversities().add(university);
        }
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", universityCursor));
        String response = "successful";
        return response;
    }

    public String addCollege(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("colleges", "$all");
        BasicProfileDAO collegeCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        College college = College.getCollege(additionalData);
        if (college != null) {
            collegeCursor.getColleges().add(college);
        }
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", collegeCursor));
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

    public String addPSkill(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("pSkills", "$all");
        BasicProfileDAO pSkillCoursor = mongoCollection.find(selectQuery).projection(pQuery).first();
        PSkill pSkill = PSkill.getPSkill(additionalData);
        if (pSkill != null) {
            pSkillCoursor.getpSkills().add(pSkill);
        }
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", pSkillCoursor));
        String response = "successful";
        return response;
    }

    public String addCurrentCity(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo", "$all");
        City currentCity = City.getCurrentCity(additionalData);
        BasicProfileDAO basicInfo = mongoCollection.find(selectQuery).projection(pQuery).first();
        basicInfo.getBasicInfo().setCity(currentCity);
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", basicInfo));
        String response = "successful";
        return response;
    }

    public String addHomeTown(String userId, String additionalData) {
      MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        Town homeTown = Town.getHomeTown(additionalData);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo", "$all");
        BasicProfileDAO basicInfo = mongoCollection.find(selectQuery).projection(pQuery).first();
        basicInfo.getBasicInfo().setTown(homeTown);
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", basicInfo));
        String response = "successful";
        return response;
    }

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

    public BasicProfileDAO getWorkPlaces(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("workPlaces", "$all");
        BasicProfileDAO workPlaceCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return workPlaceCursor;

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

    public BasicProfileDAO getColleges(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("colleges", "$all");
        BasicProfileDAO collegesCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return collegesCursor;

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

    public BasicProfileDAO getProfessionalSkills(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject searchQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document selectQuery = new Document();
        selectQuery.put("pSkills", "$all");
        BasicProfileDAO pskillCursor = mongoCollection.find(searchQuery).projection(selectQuery).first();
        return pskillCursor;

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

    public MongoCursor getSecurityQuestion(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        Document selectQuery = new Document();
        selectQuery.put("s_questions", "$all");
        MongoCursor cursor = table.find(searchQuery).projection(selectQuery).iterator();
        return cursor;
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
    public static void main(String[] args) {

//        Document document = new Document();
        String userId = "100147";
        Document workPlace = new Document();
        workPlace.append("company", "NASA");
        workPlace.append("position", "Software Engineer");
        workPlace.append("city", "Gazipur");
        workPlace.append("description", "I want to fly");
        CountriesDAO country = new CountriesDAO();
        country.setCode("012");
        country.setTitle("Australia");
        City currentCity = new City();
        currentCity.setCityName("ffffff");
        currentCity.setCountry(country);
        Town homeTown = new Town();
        homeTown.setTownName("Dhaka");
        homeTown.setCountry(country);

//        workPlace.append("time_period", "20-01-15");
//        document.put("work_place", workPlace);
        BasicProfileModel ob = new BasicProfileModel();
//        ob.getOverview(userId);
//        System.out.println(ob.getCityTown(userId));
//        String homeTown1 = ob.addHomeTown(userId, homeTown.toString());
        String workEducation = ob.addCurrentCity(userId, currentCity.toString());

//        String workEducation1 = ob.addWorkPlace(userId, workPlace.toString());
//        System.out.println(workEducation1);
//        System.out.println(workEducation.toString());
//        ob.updateBasicProfile(userId, document);
//        MongoCursor workPlaces = ob.getWorkPlaces(userId);
//        Document doc = new Document();
//        while (workPlaces.hasNext()) {
//            doc = (Document) workPlaces.next();
//
//        }
//        System.out.println(doc.toJson());
//        String work1 = doc.get("work_place");
//        String work2 = document.toJson();
//        String work = work1 + work2 ;
//        System.out.println(work);
//        System.out.println(work);
//        Document final22 = new Document();
//        final22.put("11", document.toJson());
//        final22.put("12", work2);
//        System.out.println(final22.toJson());
//        Document d1 = new Document();
//        Document companyList = new Document();
//        ArrayList companyList = new ArrayList<>();
//        companyList.add(document.get("work_place"));
//        companyList.add(doc.get("work_place"));
//        
//        d1.put("companyList", companyList);
//        System.out.println(d1.toJson());
//        
//        
//        
//        System.out.println(doc.get("work_place"));
//        ArrayList<Document> workPlaceList = new ArrayList<>();
//        workPlaceList.add(doc);
//        workPlaceList.add(document);
//         System.out.println(workPlaceList);
        
//         public String addHomeTown(String userId, String additionalData) {
//        MongoCollection<BasicProfileDAO> mongoCollection
//                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
//        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
//        Town homeTown = Town.getHomeTown(additionalData);
//        System.out.println(homeTown);
//        BasicProfileDAO bp = new BasicProfileDAO();
//        bp.setBasicInfo(new BasicInfo());
//        bp.getBasicInfo().setTown(homeTown);
//        System.out.println(bp);
////        BasicProfileDAO result1 = mongoCollection.f;
//        BasicProfileDAO result2 = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", bp));
//        String response = "successful";
//        return response;
//    }
    }

}
