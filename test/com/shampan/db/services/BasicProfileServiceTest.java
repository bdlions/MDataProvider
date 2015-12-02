/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.mongodb.client.MongoCollection;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.builder.BasicProfileDAOBuilder;
import com.shampan.db.collections.fragment.profile.About;
import com.shampan.db.collections.fragment.profile.Address;
import com.shampan.db.collections.fragment.profile.BasicInfo;
import com.shampan.db.collections.fragment.profile.BirthDate;
import com.shampan.db.collections.fragment.profile.City;
import com.shampan.db.collections.fragment.profile.College;
import com.shampan.db.collections.fragment.profile.Email;
import com.shampan.db.collections.fragment.profile.FamilyMember;
import com.shampan.db.collections.fragment.profile.FavouriteQuote;
import com.shampan.db.collections.fragment.profile.Gender;
import com.shampan.db.collections.fragment.profile.Language;
import com.shampan.db.collections.fragment.profile.MobilePhone;
import com.shampan.db.collections.fragment.profile.PSkill;
import com.shampan.db.collections.fragment.profile.RelationStatus;
import com.shampan.db.collections.fragment.profile.Religion;
import com.shampan.db.collections.fragment.profile.School;
import com.shampan.db.collections.fragment.profile.Town;
import com.shampan.db.collections.fragment.profile.University;
import com.shampan.db.collections.fragment.profile.Website;
import com.shampan.db.collections.fragment.profile.WorkPlace;
import com.shampan.model.BasicProfileModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sampan-IT
 */
public class BasicProfileServiceTest {

    public String userId = "t87sqMzqcM86ee2";
    BasicProfileModel basicProfileModel = new BasicProfileModel();

    public BasicProfileServiceTest() {
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

//    @Test
    public void addWorkPlace() {
        WorkPlace workPlace = new WorkPlace();
        workPlace.setId("2");
        workPlace.setCompany("NASA");
        workPlace.setPosition("Software Engineer");
        workPlace.setDescription("Nothing to say");
        workPlace.setCity("Dhaka");
//        String workplace1 = workPlace.toString();
        basicProfileModel.addWorkPlace(userId, workPlace.toString());
    }

//    @Test
    public void addProSkill() {
        PSkill pSkill = new PSkill();
        pSkill.setpSkill("Software Engineer at NASA");
        pSkill.setId("1");
        String pskillString = pSkill.toString();
        basicProfileModel.addProfessionalSkill(userId, pskillString);
    }

//     @Test
    public void addUniversity() {
        University university = new University();
        university.setUniversity("Sydney University");
        university.setDescription("have nice memories");
        university.setStartDate("04-11-15");
        university.setEndDate("04-11-19");
        basicProfileModel.addUniversity(userId, university.toString());
    }

//     @Test
    public void addCollege() {
        College college = new College();
        college.setCollege("Cambridge College");
        college.setDescription("I was very Shy in my college Life");
        college.setStartDate("04-11-07");
        college.setEndDate("04-11-09");
        basicProfileModel.addCollege(userId, college.toString());
    }

//     @Test
    public void addSchool() {
        School school = new School();
        school.setSchool("Ranigong high School");
        school.setDescription(" I had no friend");
        school.setStartDate("04-11-2002");
        school.setEndDate("04-11-2007");
        basicProfileModel.addSchool(userId, school.toString());
    }

//    @Test
    public void addWorksAndEducation() {
        WorkPlace workPlace = new WorkPlace();
        workPlace.setId(userId);
        workPlace.setCompany("NASA");
        workPlace.setPosition("Software Engineer");
        workPlace.setDescription("Nothing to say");
        workPlace.setCity("Dhaka");
        String workplace1 = workPlace.toString();
        basicProfileModel.addWorkPlace(userId, workplace1);

        PSkill pSkill = new PSkill();
        pSkill.setpSkill("Software Engineer at NASA");
        pSkill.setId(userId);
        String pskillString = pSkill.toString();
        basicProfileModel.addProfessionalSkill(userId, pskillString);

        University university = new University();
        university.setId(userId);
        university.setUniversity("Sydney University");
        university.setDescription("have nice memories");
        university.setStartDate("04-11-15");
        university.setEndDate("04-11-19");
        basicProfileModel.addUniversity(userId, university.toString());

        College college = new College();
        college.setId(userId);
        college.setCollege("Cambridge College");
        college.setDescription("I was very Shy in my college Life");
        college.setStartDate("04-11-07");
        college.setEndDate("04-11-09");
        basicProfileModel.addCollege(userId, college.toString());

        School school = new School();
        school.setId(userId);
        school.setSchool("Ranigong high School");
        school.setDescription(" I had no friend");
        school.setStartDate("04-11-2002");
        school.setEndDate("04-11-2007");
        basicProfileModel.addSchool(userId, school.toString());

    }

//    @Test
    public void getWorksAndEducation() {
        System.out.println(basicProfileModel.getWorksEducation(userId));
    }
    @Test
    public void editEmail() {
        Email email = new Email();
        email.setId(userId);
        email.setEmail("Gfhgfh");
        String emailId = "krSUqEODwvmpeIS" ;
        System.out.println(basicProfileModel.editEmail(userId, emailId, email.toString()));
    }

//    @Test
    public void editWorksAndEducation() {

        WorkPlace workPlace = new WorkPlace();
        workPlace.setId("4");
        workPlace.setCompany("Shampan It");
        workPlace.setDescription("Nothing to say.......");
        workPlace.setCity("Dhaka");
        String workplace1 = workPlace.toString();
        basicProfileModel.editWorkPlace(userId, userId, workplace1.toString());

        PSkill pSkill = new PSkill();
        pSkill.setpSkill("Software Engineer at NASA.....");
        basicProfileModel.editProfessionalSkill(userId, userId, pSkill.toString());

        University university = new University();
        university.setUniversity("Sydney University");
        university.setDescription("have nice memories.............");
        university.setStartDate("04-11-15");
        university.setEndDate("04-11-19");
        basicProfileModel.editUniversity(userId, userId, university.toString());

        College college = new College();
        college.setCollege("Cambridge College");
        college.setDescription("I was very Shy in my college Life...........");
        college.setStartDate("04-11-07");
        college.setEndDate("04-11-09");
        basicProfileModel.editCollege(userId, userId, college.toString());

        School school = new School();
        school.setSchool("Ranigong high School");
        school.setDescription(" I had no friend........");
        school.setStartDate("04-11-2002");
        school.setEndDate("04-11-2007");
        basicProfileModel.editSchool(userId, userId, school.toString());

    }

   @Test
    public void deleteWorksAndEducation() {
//        basicProfileModel.deleteWrokPlace(userId, userId);
        basicProfileModel.deletePSkill("9FjYfJ47u3zVsm9", "WDxp0BS0SdGtKkV");
//        basicProfileModel.deleteUniversity(userId, userId);
//        basicProfileModel.deleteCollege(userId, userId);
//        basicProfileModel.deleteSchool(userId, userId);

    }
//   @Test

    public void getsWorksAndEducation() {
        System.out.println(basicProfileModel.getWorkPlaces(userId).toString());
        System.out.println(basicProfileModel.getProfessionalSkills(userId).toString());
        System.out.println(basicProfileModel.getUniversities(userId).toString());
        System.out.println(basicProfileModel.getColleges(userId).toString());
        System.out.println(basicProfileModel.getSchools(userId).toString());

    }



//    @Test
    public void editUniversity() {

    }

//    @Test
    public void editCollege() {

    }

//    @Test
    public void editSchool() {

    }

//    @Test
    public void deleteWrokPlace() {
        basicProfileModel.deleteWrokPlace(userId, "js67aTI7lbVkipY");
    }
//    @Test

    public void testEditField() {
        basicProfileModel.testEditField(userId, "-it");

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    //@Test


    //@Test
    public void getOverviewTest() {
        System.out.println(basicProfileModel.getOverview("Dq9y3wHnMC3Y8ag"));
    }

    public void getWorkPlaces() {
        System.out.println(basicProfileModel.getWorkPlaces(userId));
    }
//    @Test

    public void getProfessionalSkills() {
        System.out.println(basicProfileModel.getProfessionalSkills(userId));
    }

//    @Test
    public void getUniversities() {
        System.out.println(basicProfileModel.getUniversities(userId));
    }
//    @Test

    public void getColleges() {
        System.out.println(basicProfileModel.getColleges(userId));
    }

//    @Test
    public void getSchools() {
        System.out.println(basicProfileModel.getSchools(userId));
    }

//    @Test
    public void getCityTown() {
        System.out.println(basicProfileModel.getCityTown(userId));
    }
//    @Test

    public void addCurrentCity() {
        City currentCity = new City();
        currentCity.setId(userId);
        currentCity.setCityName("Sydney");
        System.out.println(basicProfileModel.addCurrentCity(userId, currentCity.toString()));
    }

//    @Test
    public void editCurrentCity() {
        City currentCity = new City();
        currentCity.setId(userId);
        currentCity.setCityName("Sydney......");
        basicProfileModel.editCurrentCity(userId, currentCity.toString());

    }

//    @Test
    public void deleteCurrentCity() {
        basicProfileModel.deleteCurrentCity(userId);

    }

//    @Test
    public void deleteHomeTown() {
        basicProfileModel.deleteHomeTown(userId);

    }

//    @Test
    public void addHomeTown() {
        Town homeTown = new Town();
        homeTown.setId(userId);      
        homeTown.setTownName("Dhaka");
        System.out.println(basicProfileModel.addHomeTown(userId, homeTown.toString()));
    }

//    @Test
    public void editHomeTown() {
        Town homeTown = new Town();
        homeTown.setTownName("Dhaka..........");
        basicProfileModel.editHomeTown(userId, homeTown.toString());

    }

//    @Test
    public void getFamilyRelation() {
        System.out.println(basicProfileModel.getFamilyRelation(userId));
    }
//    @Test

    public void addRelationshipStatus() {
        RelationStatus relationStatus = new RelationStatus();
        relationStatus.setRelationshipStatus("Single");
        System.out.println(basicProfileModel.addRelationshipStatus(userId, relationStatus.toString()));
    }

//    @Test
    public void getContactBasicInfo() {
        System.out.println(basicProfileModel.getContactBasicInfo(userId));
    }

//    @Test
    public void addMobilePhone() {
        MobilePhone mPhone1 = new MobilePhone();
        mPhone1.setId("1");
        mPhone1.setPhone("01723598606");
        System.out.println(basicProfileModel.addMobilePhone(userId, mPhone1.toString()));
    }

//    @Test
    public void editMobilePhone() {
        String mobileId = "1";
        MobilePhone mPhone1 = new MobilePhone();
        mPhone1.setId("1");
        mPhone1.setPhone("017235");
        System.out.println(basicProfileModel.editMobilePhone(userId, mobileId, mPhone1.toString()));
    }
//    @Test

    public void deleteMobilePhone() {
        String mobileId = "1";
        System.out.println(basicProfileModel.deleteMobilePhone(userId, mobileId));
    }

//    @Test
    public void addAddress() {
        Address address = new Address();
        address.setAddress("Kapasia,Ranigong");
        address.setCity("Dhaka");
        address.setPostCode("025");
        address.setZip("Ranigong");
        System.out.println(basicProfileModel.addAddress(userId, address.toString()));
    }

//    @Test
    public void deleteAddress() {
        System.out.println(basicProfileModel.deleteAddress(userId));
    }
//    @Test

    public void editAddress() {
        Address address = new Address();
        address.setAddress("Kapasia,ng");
        address.setCity("Dhaka");
        address.setPostCode("025");
        address.setZip("Ranigong");
        System.out.println(basicProfileModel.editAddress(userId, address.toString()));
    }

//    @Test
    public void addWebsite() {
        Website website = new Website();
        website.setWebsite("sampan-it");
        System.out.println(basicProfileModel.addWebsite(userId, website.toString()));
    }

//        @Test
    public void deleteWebsite() {
        String websiteId = "";
        System.out.println(basicProfileModel.deleteWebsite(userId, websiteId));
    }

//    @Test
    public void editWebsite() {
        Website website = new Website();
        website.setWebsite("sampan");
        System.out.println(basicProfileModel.editWebsite(userId, website.toString()));
    }

    //@Test
    public void getAboutFQuote() {
        System.out.println(basicProfileModel.getAboutFQuote(userId));
    }

    //@Test
    public void addFQuote() {
        FavouriteQuote fQuote = new FavouriteQuote();
        fQuote.setfQuote("khachar fake fake...porose mukhe mukhe...nirobe chokhe chokhe chae..");
        System.out.println(basicProfileModel.addFQuote(userId, fQuote.toString()));
    }
    //  @Test

    public void addAbout() {
        About about = new About();
        about.setAbout(" honest,very simple,very careful about dearest persons ");
        System.out.println(basicProfileModel.addAbout(userId, about.toString()));
    }
    
    
//      @Test
    public void editRelationshipStatus() {
        System.out.println(basicProfileModel.editRelationshipStatus(userId, "married"));
    }
//      @Test
    public void deleteRelationshipStatus() {
        System.out.println(basicProfileModel.deleteRelationshipStatus(userId));
    }

    //@Test
    public void addBasicProfile() {
        PSkill pSkill2 = new PSkill();
        pSkill2.setpSkill("Software Engineer at NASA2");
        String professionalSkillString = pSkill2.toString();
        basicProfileModel.addProfessionalSkill("Dq9y3wHnMC3Y8ag", professionalSkillString);
    }
    
    
}
