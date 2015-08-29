/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.ReligionsDAO;
import com.shampan.db.collections.builder.ReligionsDAOBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class GeneralService {
    
    public GeneralService() {
        
    }
    
    public static void main(String[] args) {
        GeneralModel model = new GeneralModel();
        
        JSONObject json = new JSONObject();
//        System.out.println(model.getAllCountries());
//        System.out.println(model.getAllReligions());
        
//        json.put("countryList", model.getAllCountries());
        json.put("religionList", model.getAllReligions());
        System.out.println(json.toString());
//        Document countryList = new Document("countryList", countriesCursor);
//        System.out.println(countryList.toJson());

//        DBConnection.getInstance().getConnection();
        MongoCollection<ReligionsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELIGIONS.toString(), ReligionsDAO.class);
        
        ReligionsDAO religion1 = new ReligionsDAOBuilder()
                .setOrder("1")
                .setReligionId("1")
                .setTitle("Islam")
                .build();
        
        ReligionsDAO religion2 = new ReligionsDAOBuilder()
                .setOrder("2")
                .setReligionId("2")
                .setTitle("Hindu")
                .build();
        
        ReligionsDAO religion3 = new ReligionsDAOBuilder()
                .setOrder("3")
                .setReligionId("3")
                .setTitle("Christianity")
                .build();
        
        mongoCollection.insertOne(religion2);
        mongoCollection.insertOne(religion3);

//        MongoCursor religionCursor = model.getAllReligions();
//        ArrayList<CountriesDAO> countryList = new ArrayList<>();
//        while (countriesCursor.hasNext()) {
//            countryList.add(countriesCursor.next());
//        }
//        ObjectMapper mapper = new ObjectMapper();
//        Document doc = null;
//        String json = "";
//        try {
//            json = mapper.writeValueAsString( countryList);
//            doc = new Document("countryList", json);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        System.out.println(doc.toJson());
//        ArrayList<Document> religionList = new ArrayList<>();
//        while (religionCursor.hasNext()) {
//            religionList.add((Document)religionCursor.next());
//        }
//        Document registerInfo = new Document();
//        registerInfo.put("countryList", countryList);
//        registerInfo.put("religionList", religionList);
//        System.out.println(registerInfo.toJson());
    }
    
}
