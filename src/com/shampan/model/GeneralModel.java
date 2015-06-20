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
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.ReligionsDAO;
import com.shampan.db.collections.builder.CountriesDAOBuilder;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;

/**
 *
 * @author nazmul
 */
public class GeneralModel {

    public GeneralModel() {

    }


    public List<CountriesDAO> getAllCountries() {
        DBConnection.getInstance().getConnection();
        MongoCollection<CountriesDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("countries", CountriesDAO.class);
        MongoCursor<CountriesDAO> cursorCountryList = mongoCollection.find().iterator();
        List<CountriesDAO> countryList = IteratorUtils.toList(cursorCountryList);
        return countryList;
    }

    public List<ReligionsDAO> getAllReligions() {
        MongoDatabase db = DBConnection.getInstance().getConnection();
        MongoCollection<ReligionsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("religions", ReligionsDAO.class);
        MongoCursor<ReligionsDAO> CursorReligionList = mongoCollection.find().iterator();
        List<ReligionsDAO> religionList = IteratorUtils.toList(CursorReligionList);
        return religionList;
      
    }

}
