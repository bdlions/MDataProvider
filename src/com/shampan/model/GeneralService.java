/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.CountriesDAO;
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
        json.put("countryList", model.getAllCountries());
        json.put("religionList", model.getAllReligions());
        System.out.println(json.toString());
//        Document countryList = new Document("countryList", countriesCursor);
//        System.out.println(countryList.toJson());
        
        
//        DBConnection.getInstance().getConnection();
//        MongoCollection<CountriesDAO> mongoCollection
//                = DBConnection.getInstance().getConnection().getCollection("countries", CountriesDAO.class);
//        mongoCollection.insertOne(null);
        
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
