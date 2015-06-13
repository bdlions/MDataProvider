/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muslimand.model;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author nazmul
 */
public class GeneralModel{
    public GeneralModel()
    {
    
    }
    
    public String getAllCountries()
    {
        ArrayList arrayList = new ArrayList();
        
        MongoClient mongoClient = new MongoClient(Arrays.asList(
                    new ServerAddress("localhost", 27017)));        
        MongoDatabase db = mongoClient.getDatabase("muslimand_db");
        
        MongoCollection table = db.getCollection("countries");
        MongoCursor cursor = table.find().iterator();
        while (cursor.hasNext()) {
            arrayList.add(cursor.next());
        }
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        return json;
    }
}
