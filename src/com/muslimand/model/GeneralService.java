/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muslimand.model;

import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Sampan-IT
 */
public class GeneralService {
    
    public GeneralService() {

    }
    
    
    
    public static void main(String[] args) {
        GeneralModel model = new GeneralModel();
        MongoCursor religionCursor = model.getAllReligions();
        MongoCursor countriesCursor = model.getAllCountries();
       
        ArrayList<Document> countryList = new ArrayList<>();
        while (countriesCursor.hasNext()) {
            countryList.add((Document)countriesCursor.next());
        }
        
        ArrayList<Document> religionList = new ArrayList<>();
        while (religionCursor.hasNext()) {
            religionList.add((Document)religionCursor.next());
        }
        
        Document registerInfo = new Document();
        registerInfo.put("countryList", countryList);
        registerInfo.put("religionList", religionList);
        
        System.out.println(registerInfo.toJson());
   
    }
    
    

    
}
