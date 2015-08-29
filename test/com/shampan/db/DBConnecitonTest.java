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
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.builder.BasicProfileDAOBuilder;
import com.shampan.db.collections.fragment.profile.About;
import com.shampan.db.collections.fragment.profile.Address;
import com.shampan.db.collections.fragment.profile.BasicInfo;
import com.shampan.db.collections.fragment.profile.BirthDate;
import com.shampan.db.collections.fragment.profile.College;
import com.shampan.db.collections.fragment.profile.Email;
import com.shampan.db.collections.fragment.profile.PSkill;
import com.shampan.db.collections.fragment.profile.School;
import com.shampan.db.collections.fragment.profile.University;
import com.shampan.db.collections.fragment.profile.WorkPlace;
import com.shampan.db.collections.fragment.profile.Gender;
import com.shampan.db.collections.fragment.profile.City;
import com.shampan.db.collections.fragment.profile.Town;
import com.shampan.db.collections.fragment.profile.MobilePhone;
import com.shampan.db.collections.fragment.profile.FamilyMember;
import com.shampan.db.collections.fragment.profile.FavouriteQuote;
import com.shampan.db.collections.fragment.profile.Language;
import com.shampan.db.collections.fragment.profile.RelationStatus;
import com.shampan.db.collections.fragment.profile.Religion;
import com.shampan.db.collections.fragment.profile.Website;



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


//        List<WorkPlaces> workPlaceList2 = new ArrayList<WorkPlaces>();
//        WorkPlace workPlace2 = new WorkPlace();
//        workPlace2.setCompany("NASA");
//        workPlace2.setPostion("Software Engineer");
//        workPlace2.setDescription("Going to be a mad");
//        workPlace2.setCity("Dhaka");
//        workPlaceList2.add(workPlace);
//        workPlaceList2.add(workPlace2);\
        
        
        
        
        
        About about = new About();
        about.setAbout(" honest,very simple,very careful about dearest persons ");
        FavouriteQuote fQuote = new FavouriteQuote();
        fQuote.setfQuote("khachar fake fake...porose mukhe mukhe...nirobe chokhe chokhe chae..");
        BirthDate birthDate = new BirthDate();
        birthDate.setBirthDay("04");
        birthDate.setBirthMonth("11");
        birthDate.setBirthYear("1991");
        
        Website website = new Website();
        website.setWebsite("sampan-it");
        CountriesDAO country =new CountriesDAO();
        country.setCode("012");
        country.setTitle("Australia");
        
        Address address = new Address();
        address.setAddress("Kapasia,Ranigong");
        address.setCity("Dhaka");
        address.setPostCode("025");
        address.setZip("Ranigong");
        
        Gender gender = new Gender();
        gender.setId("1");
        gender.setTitle("Female");
        
        City currentCity = new City();
        currentCity.setCityName("Sydney");
        currentCity.setCountry(country);
        Religion religion = new Religion();
        religion.setId("012");
        religion.setTilte("muslim");
        Language language = new Language();
        language.setLanguage("Bangla");
        List<Language> languageList = new ArrayList<Language>();
        languageList.add(language);
        
        Town homeTown = new Town();
        homeTown.setTownName("Dhaka");
        homeTown.setCountry(country);
        FamilyMember fMember = new FamilyMember();
        fMember.setMemebrName("Keya");
        fMember.setRelation("Best Friend");
        List<FamilyMember> fMemberList = new ArrayList<FamilyMember>();
        fMemberList.add(fMember);
        
        List<MobilePhone> mPhoneList = new ArrayList<MobilePhone>();
        MobilePhone mPhone = new MobilePhone();
        mPhone.setPhone("01723598606");
        MobilePhone mPhone1 = new MobilePhone();
        mPhone1.setPhone("01723598606");
        mPhoneList.add(mPhone);
        mPhoneList.add(mPhone1);
        List<Email> emailList = new ArrayList<Email>();
        Email email = new Email();
        email.setEmail("rashida57pust@gmail.com");
        emailList.add(email);
        RelationStatus relationStatus = new RelationStatus();
        relationStatus.setRelationshipStatus("Single");
        
        
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setWebsite(website);
        basicInfo.setGender(gender);
        basicInfo.setMobilePhones(mPhoneList);
        basicInfo.setEmails(emailList);
        basicInfo.setCity(currentCity);
        basicInfo.setTown(homeTown);
        basicInfo.setFamilyMember(fMemberList);
        basicInfo.setReligions(religion);
        basicInfo.setLanguage(languageList);
        basicInfo.setAddresses(address);
        basicInfo.setBirthDate(birthDate);
        basicInfo.setRelationshipStatus(relationStatus);
        basicInfo.setAbout(about);
        basicInfo.setfQuote(fQuote);

        List<WorkPlace> workPlaceList = new ArrayList<WorkPlace>();
        WorkPlace workPlace = new WorkPlace();
        workPlace.setCompany("NASA");
        workPlace.setPosition("Software Engineer");
        workPlace.setDescription("Nothing to say");
        workPlace.setCity("Dhaka");
        workPlaceList.add(workPlace);
        
        List<PSkill>pSkillList = new ArrayList<PSkill>();
        PSkill pSkill = new PSkill();
        pSkill.setpSkill("Software Engineer at NASA");
        pSkillList.add(pSkill);
        
        List<University> universityList = new ArrayList<University>();
        University university = new University();
        university.setUniversity("Sydney University");
        university.setDescription("have nice memories");
        university.setStartDate("04-11-15");
        university.setEndDate("04-11-19");
        universityList.add(university);
        
        List<College>collegeList = new ArrayList<College>();
        College college = new College();
        college.setCollege("Cambridge College");
        college.setDescription("I was very Shy in my college Life");
        college.setStartDate("04-11-07");
        college.setEndDate("04-11-09");
        collegeList.add(college);
        
        List<School>schoolList = new ArrayList<School>();
        School school = new School();
        school.setSchool("Ranigong high School");
        school.setDescription("got some best friends");
        school.setStartDate("04-11-2002");
        school.setEndDate("04-11-2007");
        schoolList.add(school);
        
        BasicProfileDAO userProfileInfo = new BasicProfileDAOBuilder()
                .setUserId("100157")
                .setBasicInfo(basicInfo)
                .setWorkPlaces(workPlaceList)
                .setpSkills(pSkillList)
                .setUniversities(universityList)
                .setColleges(collegeList)
                .setSchools(schoolList)
                .build();

        System.out.print(userProfileInfo);

//.......insert basic profile info............................................
        mongoCollection.insertOne(userProfileInfo);

        //.....find sql......
////......select all document in a collection  .....
//        List<BasicProfileDAO> usersProfileInfo = new ArrayList<BasicProfileDAO>();
//        MongoCursor userProfiles = mongoCollection.find().iterator();
//        while (userProfiles.hasNext()) {
//            BasicProfileDAO userProfile = (BasicProfileDAO) userProfiles.next();
//            usersProfileInfo.add(userProfile);
////            System.out.println("BasicProfileDAO" + userProfile);
//        }
//        BasicProfileDAO usersPInfo = new BasicProfileDAO();
//        System.out.println("BasicProfileDAO Array "+usersProfileInfo);
////......need to conver BasicProfileDAO Array to BasicProfileDAO object
//
////......select all information  of a  document .........
//        BasicProfileDAO userProfile = mongoCollection.find().first();
//        int size = userProfile.getWorkPlaces().size();
//        WorkPlace lastWork = userProfile.getWorkPlaces().get(size - 1);
        
////      System.out.println(userProfile);
//
////..... select all information  of a  document by where clause.........
//        BasicProfileDAO selectQuary = new BasicProfileDAO();
//        selectQuary.setUserId("100157");
//        BasicProfileDAO selectUserProfileInfo = mongoCollection.find(selectQuary).first();
////      System.out.println(selectUserProfileInfo);
//
////......Select some of the field of a selected document ............
//        BasicProfileDAO selectQuary1 = new BasicProfileDAO();
//        selectQuary1.setUserId("100157");
//        Document projectionQuary = new Document();
//        projectionQuary.put("userId", "$all");
//        projectionQuary.put("workPlaces", "$all");
//        BasicProfileDAO selectUserProfileInfo1 = mongoCollection.find(selectQuary1).projection(projectionQuary).first();
////        System.out.println(selectUserProfileInfo1);
//
////......Select some filed that match with in a document.........
////......this method  return the filter data with where clause
//        Document projectionQuary1 = new Document();
//        projectionQuary1.put("userId", "100157");
//        projectionQuary1.put("fastName", "Keya");
//        BasicProfileDAO selectUserProfileInfo2 = mongoCollection.find().projection(projectionQuary1).first();
////        System.out.println(selectUserProfileInfo2);
////......Select gaterthan where clause documents...........................
//        Document gtQuery = new Document();
//        gtQuery.put("userId", new Document("$gt", 10));
//        BasicProfileDAO selectUserProfileInfo3 = mongoCollection.find(gtQuery).first();
////        System.out.println(selectUserProfileInfo3);
////......select not equel where cluse documents.........................
//        Document neQuery = new Document();
//        neQuery.put("userId", new Document("$ne", 57));
//        BasicProfileDAO selectUserProfileInfo4 = mongoCollection.find(neQuery).first();
////        System.out.println(selectUserProfileInfo4);
//
////....select and using $add cluse....
//        Document andQuery = new Document();
//        List<Document> obj = new ArrayList<Document>();
//        obj.add(new Document("firstName", "Keya"));
//        obj.add(new Document("lastName", "Moni"));
//        andQuery.put("$and", obj);
//        BasicProfileDAO selectUserProfileInfo5 = mongoCollection.find(andQuery).first();
//        System.out.println(selectUserProfileInfo5);
//......select and using $or cluse....
//        Document orQuery = new Document();
//        List<Document> obj1 = new ArrayList<Document>();
//        obj1.add(new Document("lastName", "Moni"));
//        obj1.add(new Document("userId", 10));
//        orQuery.put("$or", obj1);
//        MongoCursor selectUserProfileInfo6 = mongoCollection.find(orQuery).iterator();
//        while (selectUserProfileInfo6.hasNext()) {
////            System.out.println(selectUserProfileInfo6.next());
//        }
//......Select the document like sql...
//        Document regexQuery = new Document();
////        regexQuery.put("firstName", "Key");
////                .append("$options", "i"));
//        regexQuery.put("firstName",
//                new Document("$regex", "key")
//                .append("$options", "i"));
//        System.out.println(regexQuery.toString());
//.......update module........................................................
//......update...selected field...............................................
//        BasicProfileDAO updateSQL = new BasicProfileDAOBuilder()
//                .setFirstName("Keya")
//                .setUserId("100105")
//                .build();
//        mongoCollection.findOneAndReplace(updateSQL, userProfileInfo);
//        mongoCollection.findOneAndUpdate(updateSQL, userProfileInfo);
//        mongoCollection.updateOne(updateSQL, userProfileInfo);
//           System.out.println(bp);
////        QueryBuilder.start("basicInfo.city").is(currentCity);
//        BasicInfo ob = new BasicInfo();
//        ob.setCity(currentCity);
//
//        System.out.println(currentCity);
//        System.out.println(QueryBuilder.start("basicInfo.city").is(currentCity).get());
//..........add to existing data...........................................................
//        BasicProfileDAO selectedUsers1 = mongoCollection.find(userProfileInfo).first();
////        System.out.println(selectedUsers);
//        BasicProfileDAO updateUserProfileInfo1 = new BasicProfileDAOBuilder().build();
//        updateUserProfileInfo1.setWorkPlaces(selectedUsers1.getWorkPlaces());
//        updateUserProfileInfo1.getWorkPlaces().add(workPlace);
//        updateUserProfileInfo1.setUserId("100105");
//        mongoCollection.findOneAndUpdate(updateSQL, new Document("$set", updateUserProfileInfo1));
//        ...................test ...................................................................
//        BasicProfileDAO selectedUsers = mongoCollection.find(userProfileInfo).first();
//        BasicProfileDAO updateUserProfileInfo = new BasicProfileDAOBuilder().build();
//        updateUserProfileInfo.setWorkPlaces(selectedUsers.getWorkPlaces());
//        updateUserProfileInfo.getWorkPlaces().add(workPlace);
//        updateUserProfileInfo.setUserId("11111111");
//        QueryBuilder builder = new QueryBuilder();
//        BasicProfileDAO projectedUserProfileInfo = new BasicProfileDAOBuilder().build();
//        System.out.println(new Document("userId","$all" ).toJson());
//        projectedUserProfileInfo.setUserId("$all");
//        Document projectinoDoc = new Document("workPlaces.company", "$all");
//        projectedUserProfileInfo."$all");
//        System.out.println(projectedUserProfileInfo);
//        updateUserProfileInfo.setUserId("11111111");
//         System.out.println(new Document("$set", updateUserProfileInfo));
//        System.out.println(userProfileInfo);
//        System.out.println(updateUserProfileInfo);
//         mongoCollection.insertOne(userProfileInfo);
//        MongoCursor selectedUsers = mongoCollection.find(userProfileInfo).iterator();
//        BasicProfileDAO selectedUsers = mongoCollection.find(userProfileInfo).first();
//        mongoCollection.findOneAndUpdate(userProfileInfo, new Document("$set", updateUserProfileInfo));
//        
//        
//        
//        builder.elemMatch(new BasicDBObject("userId", 100444)).get();
//        
//        
//        mongoCollection.find().projection(updateUserProfileInfo);
//        
//        System.out.println(mongoCollection.find().projection(projectinoDoc).first());
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
