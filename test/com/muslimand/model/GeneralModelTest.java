/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muslimand.model;

import com.shampan.model.basicProfileModel;
import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alamgir
 */
public class GeneralModelTest {

    public GeneralModelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void main() {
//        Gson gson = new Gson();
//        GeneralModel gm = new GeneralModel();
//        String result =gm.getAllCountries();
//        System.out.println(gm.getAllCountries());
//        gm.getAllReligions();
        
//        System.out.println(gm.getAllReligions());
//        Object_id=557d207230cada0554fb7b08
        String userID = "556311458267404811000028";
        String DocumentID ="Shemin";
        String DocumentIndexID ="Female";
        Document document = new Document();
        document.put("user_id", "556311458267404811000028");
        document.put("first_name", "Shemin");
        document.put("last_name", "Haque");
        document.put("email", "shemin_cse@gmail.com");
        document.put("gender", new Document("id", "1").append("title", "Female"));
        System.out.println(document);
        System.out.println(document.toJson());
        basicProfileModel obj = new basicProfileModel();
//        obj.addBasicProfile(document);
        obj.updateBasicProfile(userID, document);
//        String Result = obj.getBasicProfile(userID);
//        String Result = obj.getBasicProfile(userID,DocumentID);
//        String Result = obj.getBasicProfile(userID,DocumentID,DocumentIndexID);
//        System.out.println(Result);
    }
}
