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
import org.bson.Document;

/**
 *
 * @author Sampan-IT
 */
public class basicProfileModel {

    public basicProfileModel() {

    }

    
    
    
    
    public void addBasicProfile(Document AdditionalData) {
//        System.out.println(userID);
//        System.out.println(AdditionalData);
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        
         table.insertOne(AdditionalData);
        
//        Document searchQuery = new Document();
//        searchQuery.put("user_id", userID);
//        MongoCursor cursor = table.find(searchQuery).iterator();
//        Document doc = new Document();
//
//        while (cursor.hasNext()) {
//            doc = (Document) cursor.next();
////             System.out.println(doc);
//        }
//        System.out.println(doc);
//        Document updateObj = new Document();
//        updateObj.put("$set", AdditionalData);
//
//        System.out.println(updateObj);

       

    }

    public void updateBasicProfile(String userId, Document additionalData) {
//        System.out.println(userID);
//        System.out.println(AdditionalData);
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        MongoCursor cursor = table.find(searchQuery).iterator();
        Document doc = new Document();

        while (cursor.hasNext()) {
            doc = (Document) cursor.next();
//             System.out.println(doc);
        }
        System.out.println(doc.toJson());
        Document updateObj = new Document();
        updateObj.put("$set", additionalData.toJson());

        System.out.println(updateObj);

        table.updateOne(doc, updateObj);

    }

    public String getBasicProfile(String userId) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", userId);
        MongoCursor cursor = table.find(searchQuery).iterator();
        while (cursor.hasNext()) {
//        System.out.println(cursor.next());
            return cursor.next().toString();
        }

        return null;

    }

    public MongoCursor getBasicProfile(String userId, String columnName) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("username", userId);
        searchQuery.put("first_name", columnName);
        
        Document filterQuery = new Document();
        filterQuery.put("username", "$all");
        filterQuery.put("first_name", "$all");
        
        MongoCursor cursor = table.find().projection(filterQuery).iterator();

//        while(cursor.hasNext()){
//        System.out.println(cursor.next());
////            return cursor.next().toString();
//        }
        return cursor;

    }

    class XYZ {

        public String user_id;
    }

    public String getBasicProfile(String UserId, String DocumentID, String DocumentIndexID) {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("users");
        Document searchQuery = new Document();
        searchQuery.put("user_id", UserId);
        searchQuery.put("first_name", DocumentID);
        searchQuery.append("gender.title", DocumentIndexID);
        Document fields = new Document();
        fields.put("first_name", DocumentID);
        MongoCursor cursor = table.find(searchQuery).iterator();
        cursor = table.find(searchQuery, XYZ.class).iterator();
//        MongoCursor cursor = table.find({},{first_name:DocumentID});
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
//            return cursor.next().toString();

        }

        return null;

    }

//    public static void main(String[] args) {
//        String userID = "4fccdd805e151c3e84f52578";
//        String DocumentID = "Admin";
////        String DocumentIndexID = "Female";
//        basicProfileModel bpm = new basicProfileModel();
//        MongoCursor profileCursor = bpm.getBasicProfile(userID, DocumentID);
//        MongoCursor profileCursor1 = bpm.getBasicProfile(userID, DocumentID);
//        while (profileCursor.hasNext()) {
//            System.out.println(profileCursor.next());
////            JSONObject myjson = new JSONObject(profileCursor.next());
////            System.out.println(myjson);
//
//            
//        }
//        
//        Document d = new Document();
//        d.put("Greeting", "Hi");
//        d.put("Curse", "Son of a bitch");
//        
//        System.out.println("Document to json: "+d.toJson());
//        
//
//        BasicDBObject document = (BasicDBObject)JSON.parse("{\"name\": \"kabir\", \"age\":50}");
//       
//        System.out.println("Name : " + document.get("name") + " Age: " + document.getInt("age") );
//        document.get("name");
//       
//        System.out.println(JSON.serialize(d));
//        
//        String firstName = "";
//        String lastName = "";
//
////        if(profileCursor.hasNext()){
////            Document profile = (Document)profileCursor.next();
////            firstName = profile.getString("first_name");
////        }
////        
////        if(profileCursor1.hasNext()){
////            Document profile = (Document)profileCursor1.next();
////            lastName = profile.getString("last_name");
////        }
////        
////        System.out.println("Name is : " + firstName + " " + lastName);
        
        
//        MongoDatabase db = DBConnection.getInstance().getConnection();
//        MongoCollection table = db.getCollection("users");
//        Document searchQuery = new Document();
//        searchQuery.put("username", "admin");
////        searchQuery.put("email", "admin@admin.com");
//        
//        Document filterQuery = new Document();
//        filterQuery.put("username", "$all");
//        filterQuery.put("first_name", "$all");
//        MongoCursor cursor = table.find().projection(filterQuery).iterator();
//        
//
//        while(cursor.hasNext()){
//        System.out.println(cursor.next());
////            return cursor.next().toString();
//        }
//        MongoCursor cursor2 = bpm.getBasicProfile(userID, DocumentID);
//        if(cursor2.hasNext()){
//            Document doc = (Document)cursor2.next();
//            
//            System.out.println(doc.toJson());
//        }
//        
//        
//        Document dm = new Document();
//        
//    }
    

}
