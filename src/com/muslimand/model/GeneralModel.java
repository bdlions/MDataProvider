/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muslimand.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.shampan.db.DBConnection;

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
