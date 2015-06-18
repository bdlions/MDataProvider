/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.builder.BasicProfileDAOBuilder;
import com.shampan.db.collections.fragment.BasicInfo;
import com.shampan.db.collections.fragment.WorkPlaces;
import java.util.ArrayList;
import java.util.List;
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
public class DBConnecitonTest {
    private BasicInfo[] abc;

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
    public void main() {
        DBConnection.getInstance().getConnection();
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);

        List<BasicInfo> basicInfoList = new ArrayList<BasicInfo>();
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setBirthDay("04");
        basicInfo.setBirthMonth("11");
        basicInfo.setBirthYear("91");
        basicInfo.setWebsite("sampan-it.com");
        basicInfoList.add(basicInfo);
        System.out.println("shemin");

        List<WorkPlaces> workPlaceList = new ArrayList<WorkPlaces>();
        WorkPlaces workPlace = new WorkPlaces();
        workPlace.setCompany("Shampan-it");
        workPlace.setPostion("Software Engineer");
        workPlace.setDescription("Going to be a mad");
        workPlace.setCity("Dhaka");
        workPlaceList.add(workPlace);

        List<WorkPlaces> workPlaceList2 = new ArrayList<WorkPlaces>();
        WorkPlaces workPlace2 = new WorkPlaces();
        workPlace2.setCompany("NASA");
        workPlace2.setPostion("Software Engineer");
        workPlace2.setDescription("Going to be a mad");
        workPlace2.setCity("Dhaka");
        workPlaceList2.add(workPlace);
        workPlaceList2.add(workPlace2);
       

        
        
        BasicProfileDAO userProfileInfo = new BasicProfileDAOBuilder()
                .setUserId("100555")
                .build();
        
        BasicProfileDAO selectedUsers = mongoCollection.find(userProfileInfo).first();
        BasicProfileDAO updateUserProfileInfo = new BasicProfileDAOBuilder().build();
        updateUserProfileInfo.setWorkPlaces(selectedUsers.getWorkPlaces());
        updateUserProfileInfo.getWorkPlaces().add(workPlace);
//        updateUserProfileInfo.setUserId("11111111");
        QueryBuilder builder = new QueryBuilder();
        
        BasicProfileDAO projectedUserProfileInfo = new BasicProfileDAOBuilder().build();
        System.out.println(new Document("userId","$all" ).toJson());
        projectedUserProfileInfo.setUserId("$all");
        Document projectinoDoc = new Document("workPlaces.company", "$all");
//        projectedUserProfileInfo."$all");
        System.out.println(projectedUserProfileInfo);
//        updateUserProfileInfo.setUserId("11111111");
//         System.out.println(new Document("$set", updateUserProfileInfo));

//        System.out.println(userProfileInfo);
//        System.out.println(updateUserProfileInfo);
        
//         mongoCollection.insertOne(userProfileInfo);
//        MongoCursor selectedUsers = mongoCollection.find(userProfileInfo).iterator();
//        BasicProfileDAO selectedUsers = mongoCollection.find(userProfileInfo).first();
        mongoCollection.findOneAndUpdate(userProfileInfo, new Document("$set", updateUserProfileInfo));
        
        
        
        builder.elemMatch(new BasicDBObject("userId", 100444)).get();
        
        
        mongoCollection.find().projection(updateUserProfileInfo);
        
        System.out.println(mongoCollection.find().projection(projectinoDoc).first());
//        mongoCollection.updateOne(selectedUsers, updateUserProfileInfo);
//         Document doc = new Document();
//        while (selectedUsers.hasNext()) {
//            BasicProfileDAO select = (BasicProfileDAO) selectedUsers.next();
//            System.out.println(select);
//             abc[0] = select.getBasicInfo().get(0);
//             System.out.println(abc[0].toString());
//            System.out.println(select.getUserId());
//            List<BasicInfo> basicInfos = select.getBasicInfo();
//            BasicInfo basicInfo1 =  basicInfos.get(0);
//            System.out.println(basicInfo1);
//            System.out.println("Size: "+select.getBasicInfo().size());
//            System.out.println(select.getBasicInfo());
//            System.out.println(select.getBasicInfo().get(0).getBirthDay());
//        }
//        updateUserProfileInfo.set_id(selectedUsers.get_id());
//        System.out.println(updateUserProfileInfo);
//        mongoCollection.findOneAndReplace(selectedUsers, updateUserProfileInfo);
        
//         System.out.println(doc);

        //        Work w = new Work();
//        w.setCompanyName("Shampan");
//        w.setAddress("Niketon");
//        workList.add(w );
//        
//        Work w2 = new Work();
//        w2.setCompanyName("Shampan2");
//        w2.setAddress("Niketon2");
//        workList.add(w2 );
//        UserDAO projectedUser = new UserDAOBuilder().buildProjection();
//        System.out.println(projectedUser);
//        System.out.println(me);
//
//        String json = "{\"first_name\":\"Etor\",\"last_name\":\"Khan\"}";
//        UserDAO daoJSONObject = new UserDAOBuilder().build(json);
//        System.out.println(daoJSONObject);
//        mongoCollection.insertOne(me);
//        mongoCollection.insertOne(daoJSONObject);
//
//        UserDAO meAgain = mongoCollection.find(me).projection(projectedUser).first();
//        UserDAO user = new UserDAOBuilder()
//                            .setWorkList(meAgain.getWorkList())
//                            .setLastName(meAgain.getLast_name())
//                            .build();
//        System.out.println(user);
//        
//        
//        Work w3 = new Work();
//        w3.setCompanyName("Shampan3");
//        w3.setAddress("Niketon3");
//        workList.add(w3 );
//        user.getWorkList().add(w3);
//        mongoCollection.insertOne(user);
//        mongoCollection.updateOne(meAgain, user);
//        System.out.println(meAgain);
//        
//        MongoCursor<UserDAO> userList = mongoCollection.find().iterator();
//        
//        while(userList.hasNext()){
//            UserDAO currentUser = userList.next();
//            System.out.println(currentUser.getFirst_name() + " " + currentUser.getLast_name());
//        }
//        UserDAO mejsonAgain = mongoCollection.find(daoJSONObject).first();
//////
//        System.out.println(meAgain);
//        System.out.println(mejsonAgain);
////
//
//        UserDAO improvedMe = new UserDAO(n, "A.Kabir");
////        improvedMe.set_id(meAgain.get_id());
////
//        FindOneAndReplaceOptions replaceOptions =
//                new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
////
//        improvedMe = mongoCollection.findOneAndReplace(me,
//                improvedMe,
//                replaceOptions);
////
//        System.out.println(improvedMe);
    }

}
