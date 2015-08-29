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
import com.mongodb.util.JSON;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.builder.BasicProfileDAOBuilder;
import com.shampan.db.collections.fragment.About;
import com.shampan.db.collections.fragment.Address;
import com.shampan.db.collections.fragment.BasicInfo;
import com.shampan.db.collections.fragment.BirthDate;
import com.shampan.db.collections.fragment.City;
import com.shampan.db.collections.fragment.College;
import com.shampan.db.collections.fragment.Email;
import com.shampan.db.collections.fragment.FavouriteQuote;
import com.shampan.db.collections.fragment.MobilePhone;
import com.shampan.db.collections.fragment.PSkill;
import com.shampan.db.collections.fragment.RelationStatus;
import com.shampan.db.collections.fragment.School;
import com.shampan.db.collections.fragment.Town;
import com.shampan.db.collections.fragment.University;
import com.shampan.db.collections.fragment.Website;
import com.shampan.db.collections.fragment.WorkPlace;
import com.shampan.util.LogWriter;
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

//...........About Module.........................    
//...........About Overview.........................    
    public String getOverview(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
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

    public String addWorkPlace(String userId, String additionalData) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("workPlaces", "$all");
        BasicProfileDAO workPlaces = mongoCollection.find(selectQuery).projection(pQuery).first();
        WorkPlace workPlaceInfo = WorkPlace.getWorkPlace(additionalData);
        if (workPlaceInfo != null) {
            workPlaces.getWorkPlaces().add(workPlaceInfo);
        }
        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", workPlaces));
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

//    public String addWorkPlae(String userId, String additionalData) {
//        System.out.println(additionalData);
//        WorkPlace workPlace = WorkPlace.getWorkPlace(additionalData);
//        System.out.println(workPlace);
//        MongoCollection<BasicProfileDAO> mongoCollection
//                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
//        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
//        Document pQuery = new Document();
//        pQuery.put("workPlaces", "$all");
//        BasicProfileDAO workPlaces = mongoCollection.find(selectQuery).projection(pQuery).first();
//
//        try {
//            if (workPlace != null) {
//                workPlaces.getWorkPlaces().add(workPlace);
//            }
//        } catch (NullPointerException npe) {
//            LogWriter.getErrorLog().error(npe);
//        }
////        mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("workPlaces", workPlaces)));
//        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", workPlaces));
//        String response = "successful";
//        return response;
//    }
    
    //.................About yourself.............
    
     public BasicProfileDAO getAboutFQuote(String userId) {
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("basicInfo.about", "$all");
        pQuery.put("basicInfo.fQuote", "$all");
        BasicProfileDAO aboutFQuote= mongoCollection.find(selectQuery).projection(pQuery).first();
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
    public static void main(String[] args) {

//        Document document = new Document();
        String userId = "100157";
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
        homeTown.setTownName("Gazipur");
        MobilePhone mPhone = new MobilePhone();
        mPhone.setPhone("01720270651");
        Email email = new Email();
        email.setEmail("rashida57pust@gmail.com");
        Website website = new Website();
        website.setWebsite("sampit.com");
        Address address = new Address();
        address.setAddress("Niketon ");
        address.setCity("Dhaka");
        address.setPostCode("025");
        address.setZip("Niketon");
        About about = new About();
        about.setAbout(" honest,very simple,very careful about dearest persons,always cool ");
        FavouriteQuote fQuote = new FavouriteQuote();
        fQuote.setfQuote("khachar fake fake...porose mukhe mukhe...nirobe chokhe chokhe chae");
        
//        System.out.println(relation.getRelationshipStatus());
//        System.out.println(relation.toString());
//        homeTown.setCountry(country);

//        workPlace.append("time_period", "20-01-15");
//        document.put("work_place", workPlace);
        BasicProfileModel ob = new BasicProfileModel();
        System.out.println(ob.addWorkPlace(userId, userId));
//        System.out.println(ob.getAboutFQuote(userId));
//        ob.getOverview(userId);
//        System.out.println(ob.getFamilyRelation(userId));
//        System.out.println(ob.getCityTown(userId));
//        String homeTown1 = ob.addRelationshipStatus(userId, relation.getRelationshipStatus());
//        String mphone1 = ob.addMobilePhone(userId, mPhone.toString());
//        String address1 = ob.addAddress(userId, address.toString());
//        String workEducation = ob.addHomeTown(userId, homeTown.toString());
//        String email1 = ob.addEmail(userId, email.toString());
//        BasicProfileDAO basicInfo = ob.getContactBasicInfo(userId);
//         System.out.println(basicInfo.toString());
//        System.out.println(ob.addFabouriteQuote(userId, fQuote.toString()));
//        System.out.println(ob.addAbout(userId, about.toString()));
//        String workEducation1 = ob.addWorkPlace(userId, workPlace.toString());
//        String website1 = ob.addWebsite(userId, website.toString());
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
