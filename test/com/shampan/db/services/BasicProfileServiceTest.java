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
import com.shampan.db.collections.fragment.BasicInfo;
import com.shampan.db.collections.fragment.City;
import com.shampan.db.collections.fragment.College;
import com.shampan.db.collections.fragment.Email;
import com.shampan.db.collections.fragment.FamilyMember;
import com.shampan.db.collections.fragment.Gender;
import com.shampan.db.collections.fragment.Language;
import com.shampan.db.collections.fragment.MobilePhone;
import com.shampan.db.collections.fragment.PSkill;
import com.shampan.db.collections.fragment.Religion;
import com.shampan.db.collections.fragment.School;
import com.shampan.db.collections.fragment.Town;
import com.shampan.db.collections.fragment.University;
import com.shampan.db.collections.fragment.WorkPlace;
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
    
    @Test
    public void main(){
         DBConnection.getInstance().getConnection();
        MongoCollection<BasicProfileDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", BasicProfileDAO.class);
        
          CountriesDAO country =new CountriesDAO();
        country.setCode("012");
        country.setTitle("Australia");
        
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
        
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setBirthDay("19");
        basicInfo.setBirthMonth("6");
        basicInfo.setBirthYear("1991");
        basicInfo.setWebsite("Sampan-it");
        basicInfo.setRelationshipStatus("Single");
        basicInfo.setGender(gender);
        basicInfo.setMobilePhones(mPhoneList);
        basicInfo.setEmails(emailList);
        basicInfo.setCity(currentCity);
        basicInfo.setTown(homeTown);
        basicInfo.setFamilyMember(fMemberList);
        basicInfo.setReligions(religion);
        basicInfo.setLanguage(languageList);

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
    }
}
