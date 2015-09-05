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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    //@Test
    public void main() {
        DBConnection.getInstance().getConnection();
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);

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
        CountriesDAO country = new CountriesDAO();
        country.setCode("012");
        country.setTitle("Australia");

        Address address = new Address();
        address.setAddress("Kapasia,Ranigong");
        address.setCity("Dhaka");
        address.setPostCode("025");
        address.setZip("Ranigong");

        Gender gender = new Gender();
        gender.setGenderId("1");
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

        List<PSkill> pSkillList = new ArrayList<PSkill>();
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

        List<College> collegeList = new ArrayList<College>();
        College college = new College();
        college.setCollege("Cambridge College");
        college.setDescription("I was very Shy in my college Life");
        college.setStartDate("04-11-07");
        college.setEndDate("04-11-09");
        collegeList.add(college);

        List<School> schoolList = new ArrayList<School>();
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
                .setBasicInfo(basicInfo)
                .setColleges(collegeList)
                .setSchools(schoolList)
                .build();

        System.out.print(userProfileInfo);

//.......insert basic profile info............................................
        mongoCollection.insertOne(userProfileInfo);
    }

    //@Test
    public void getOverviewTest() {
        System.out.println(basicProfileModel.getOverview("Dq9y3wHnMC3Y8ag"));
    }

    @Test
    public void addWorkPlace() {
        WorkPlace workPlace = new WorkPlace();
        workPlace.setCompany("NASA2");
        workPlace.setPosition("Software Engineer2");
        workPlace.setDescription("Nothing to say2");
        workPlace.setCity("Dhaka2");
        String workplace1 = workPlace.toString();
        System.out.println(basicProfileModel.addWorkPlace("U6NjMyVfmZ7lMCf", workplace1));
    }

    //@Test
    public void addProSkill() {
        PSkill pSkill = new PSkill();
        pSkill.setpSkill("Software Engineer at NASA2");
        String pskillString = pSkill.toString();
        basicProfileModel.addPSkill("Dq9y3wHnMC3Y8ag", pskillString);
    }

    //@Test
    public void addUniversity() {
        University university = new University();
        university.setUniversity("Sydney University");
        university.setDescription("have nice memories");
        university.setStartDate("04-11-15");
        university.setEndDate("04-11-19");
        basicProfileModel.addUniversity("100157", university.toString());
    }
    //@Test
    public void addCollege() {
        College college = new College();
        college.setCollege("Cambridge College");
        college.setDescription("I was very Shy in my college Life");
        college.setStartDate("04-11-07");
        college.setEndDate("04-11-09");
        basicProfileModel.addCollege("100157", college.toString());
    }

    //@Test
    public void addBasicProfile() {
        PSkill pSkill2 = new PSkill();
        pSkill2.setpSkill("Software Engineer at NASA2");
        String professionalSkillString = pSkill2.toString();
        basicProfileModel.addPSkill("Dq9y3wHnMC3Y8ag", professionalSkillString);
    }
}
