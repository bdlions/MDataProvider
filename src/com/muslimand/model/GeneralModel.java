/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muslimand.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.shampan.db.DBConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author nazmul
 */
public class GeneralModel {

    public GeneralModel() {

    }

    public MongoCursor getAllCountries() {

        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("countries");
        MongoCursor cursor = table.find().iterator();
        return cursor;
    }

    public MongoCursor getAllReligions() {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection table = db.getCollection("religions");
        MongoCursor cursor = table.find().iterator();
        return cursor;
    }

}
