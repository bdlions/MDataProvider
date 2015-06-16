/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.fragment.Name;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alamgir
 */
public class DBConnecitonTest {
    
    public DBConnecitonTest() {
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
    public void main(){
        DBConnection.getInstance().getConnection();
        MongoCollection<UserDAO> mongoCollection =
                DBConnection.getInstance().getConnection().getCollection("user1", UserDAO.class);

        Name n = new Name();
        n.first = "Alamgir";
        n.last = "Kabir";
        UserDAO me = new UserDAO(n, "A.Kabir");
        

        mongoCollection.insertOne(me);

        UserDAO meAgain = mongoCollection.find(me).first();
//
        System.out.println(meAgain);
//

        UserDAO improvedMe = new UserDAO(n, "A.Kabir");
//        improvedMe.set_id(meAgain.get_id());
//
        FindOneAndReplaceOptions replaceOptions =
                new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
//
        improvedMe = mongoCollection.findOneAndReplace(me,
                improvedMe,
                replaceOptions);
//
        System.out.println(improvedMe);

    }
}