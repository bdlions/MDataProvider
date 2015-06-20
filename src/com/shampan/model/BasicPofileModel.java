/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.shampan.db.DBConnection;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author Sampan-IT
 */
public class BasicPofileModel {

    public BasicPofileModel() {

    }
//use for test methord

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

    public MongoCursor getWorkPlaces(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        Document selectQuery = new Document();
        selectQuery.put("work_place", "$all");
        MongoCursor cursor = table.find(searchQuery).projection(selectQuery).iterator();
        return cursor;

    }

    public MongoCursor getSchools(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        Document selectQuery = new Document();
        selectQuery.put("schools", "$all");
        MongoCursor cursor = table.find(searchQuery).projection(selectQuery).iterator();
        return cursor;

    }

    public MongoCursor getColleges(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        Document selectQuery = new Document();
        selectQuery.put("colleges", "$all");
        MongoCursor cursor = table.find(searchQuery).projection(selectQuery).iterator();
        return cursor;

    }

    public MongoCursor getUniversities(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        Document selectQuery = new Document();
        selectQuery.put("universities", "$all");
        MongoCursor cursor = table.find(searchQuery).projection(selectQuery).iterator();
        return cursor;

    }

    public MongoCursor getProfessionalSkills(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        Document selectQuery = new Document();
        selectQuery.put("p_skills", "$all");
        MongoCursor cursor = table.find(searchQuery).projection(selectQuery).iterator();
        return cursor;

    }

    public MongoCursor getBasicInfo(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        Document selectQuery = new Document();
        selectQuery.put("basic_info", "$all");
        MongoCursor cursor = table.find(searchQuery).projection(selectQuery).iterator();
        return cursor;

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

    public MongoCursor getPlaces(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        Document selectQuery = new Document();
        selectQuery.put("places", "$all");
        MongoCursor cursor = table.find(searchQuery).projection(selectQuery).iterator();
        return cursor;

    }
    public MongoCursor getOverview(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        MongoCursor cursor = table.find(searchQuery).iterator();
        return cursor;

    }

    public static void main(String[] args) {

        Document document = new Document();
        String userId = "556311458267404811000028";
        Document workPlace = new Document();
        workPlace.append("company", "NASA");
        workPlace.append("position", "Software Engineer");
        workPlace.append("city", "Gazipur");
        workPlace.append("description", "I want to fly");
        workPlace.append("time_period", "20-01-15");
        document.put("work_place", workPlace);
        BasicPofileModel ob = new BasicPofileModel();
//        ob.updateBasicProfile(userId, document);
        MongoCursor workPlaces = ob.getWorkPlaces(userId);
        Document doc = new Document();
        while (workPlaces.hasNext()) {
            doc = (Document) workPlaces.next();

        }
        
        System.out.println(doc.toJson());
//        String work1 = doc.get("work_place");
        String work2 = document.toJson();
//        String work = work1 + work2 ;
//        System.out.println(work);

//        System.out.println(work);
//        Document final22 = new Document();
//        final22.put("11", document.toJson());
//        final22.put("12", work2);
        
        
//        System.out.println(final22.toJson());
        
        Document d1 = new Document();
//        Document companyList = new Document();
        ArrayList companyList = new ArrayList<>();
        companyList.add(document.get("work_place"));
        companyList.add(doc.get("work_place"));
        
        d1.put("companyList", companyList);
        System.out.println(d1.toJson());
        
        
        
        System.out.println(doc.get("work_place"));
        ArrayList<Document> workPlaceList = new ArrayList<>();
        workPlaceList.add(doc);
        workPlaceList.add(document);
//         System.out.println(workPlaceList);

    }

}
