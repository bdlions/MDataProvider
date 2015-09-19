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

    public String userId = "eQeOwhE7hrUkCyP";
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
        workPlace.setId("1");
        workPlace.setCompany("NASA");
        workPlace.setPosition("Software Engineer");
        workPlace.setDescription("Nothing to say");
        workPlace.setCity("Dhaka");
        String workplace1 = workPlace.toString();
        basicProfileModel.addWorkPlace(userId, workplace1);
    }

    //@Test
    public void addProSkill() {
        PSkill pSkill = new PSkill();
        pSkill.setpSkill("Software Engineer at NASA");
        String pskillString = pSkill.toString();
        basicProfileModel.addProfessionalSkill(userId, pskillString);
    }

    // @Test
    public void addUniversity() {
        University university = new University();
        university.setUniversity("Sydney University");
        university.setDescription("have nice memories");
        university.setStartDate("04-11-15");
        university.setEndDate("04-11-19");
        basicProfileModel.addUniversity(userId, university.toString());
    }

    // @Test
    public void addCollege() {
        College college = new College();
        college.setCollege("Cambridge College");
        college.setDescription("I was very Shy in my college Life");
        college.setStartDate("04-11-07");
        college.setEndDate("04-11-09");
        basicProfileModel.addCollege(userId, college.toString());
    }

    // @Test
    public void addSchool() {
        School school = new School();
        school.setSchool("Ranigong high School");
        school.setDescription(" I had no friend");
        school.setStartDate("04-11-2002");
        school.setEndDate("04-11-2007");
        basicProfileModel.addSchool(userId, school.toString());
    }

    //@Test
    public void addWorksAndEducation() {
        WorkPlace workPlace = new WorkPlace();
        workPlace.setCompany("NASA");
        workPlace.setPosition("Software Engineer");
        workPlace.setDescription("Nothing to say");
        workPlace.setCity("Dhaka");
        String workplace1 = workPlace.toString();
        basicProfileModel.addWorkPlace(userId, workplace1);

        PSkill pSkill = new PSkill();
        pSkill.setpSkill("Software Engineer at NASA");
        String pskillString = pSkill.toString();
        basicProfileModel.addProfessionalSkill(userId, pskillString);

        University university = new University();
        university.setUniversity("Sydney University");
        university.setDescription("have nice memories");
        university.setStartDate("04-11-15");
        university.setEndDate("04-11-19");
        basicProfileModel.addUniversity(userId, university.toString());

        College college = new College();
        college.setCollege("Cambridge College");
        college.setDescription("I was very Shy in my college Life");
        college.setStartDate("04-11-07");
        college.setEndDate("04-11-09");
        basicProfileModel.addCollege(userId, college.toString());

        School school = new School();
        school.setSchool("Ranigong high School");
        school.setDescription(" I had no friend");
        school.setStartDate("04-11-2002");
        school.setEndDate("04-11-2007");
        basicProfileModel.addSchool(userId, school.toString());
    }

    //@Test
    public void getWorksAndEducation() {
        System.out.println(basicProfileModel.getWorksEducation(userId));
    }

//   @Test
    public void editWorkPlace() {
        WorkPlace workPlace = new WorkPlace();
        workPlace.setId("2");
        workPlace.setCompany("Shampan It");
        workPlace.setDescription("Nothing to say");
        workPlace.setCity("Dhaka");
        String workplace1 = workPlace.toString();
        basicProfileModel.editWorkPlace(userId, "2", workplace1.toString());
    }

//    @Test
    public void editProfessionalSkill() {
        PSkill pSkill = new PSkill();
        pSkill.setpSkill("Software Engineer at NASA.....");
        basicProfileModel.editProfessionalSkill(userId, "cPFVuGY0TVdlTHB", pSkill.toString());

    }

//    @Test
    public void editUniversity() {
        University university = new University();
        university.setUniversity("Sydney University");
        university.setDescription("have nice memories");
        university.setStartDate("04-11-15");
        university.setEndDate("04-11-19");
        basicProfileModel.editUniversity(userId, "bFnjzXgEwcORE6o", university.toString());

    }

//    @Test
    public void editCollege() {
        College college = new College();
        college.setCollege("Cambridge College");
        college.setDescription("I was very Shy in my college Life");
        college.setStartDate("04-11-07");
        college.setEndDate("04-11-09");
        basicProfileModel.editCollege(userId, "U91om5Mv6tRb4TY", college.toString());

    }

//    @Test
    public void editSchool() {
        School school = new School();
        school.setSchool("Ranigong high School");
        school.setDescription(" I had no friend");
        school.setStartDate("04-11-2002");
        school.setEndDate("04-11-2007");
        basicProfileModel.editSchool(userId, "FxtXekH69N4LU0Q", school.toString());

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
                .setUserId(userId)
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

    @Test
    public void getCityTown() {
        System.out.println(basicProfileModel.getCityTown(userId));
    }
//    @Test

    public void addCurrentCity() {
        City currentCity = new City();
        currentCity.setCityName("Sydney");
        System.out.println(basicProfileModel.addCurrentCity(userId, currentCity.toString()));
    }

//    @Test
    public void editCurrentCity() {
        City currentCity = new City();
        currentCity.setCityName("Sydney");
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
        homeTown.setTownName("Dhaka");
        System.out.println(basicProfileModel.addHomeTown(userId, homeTown.toString()));
    }

//    @Test
    public void editHomeTown() {
        Town homeTown = new Town();
        homeTown.setTownName("Dhaka");
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

    @Test
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
    @Test

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

    //@Test
    public void addBasicProfile() {
        PSkill pSkill2 = new PSkill();
        pSkill2.setpSkill("Software Engineer at NASA2");
        String professionalSkillString = pSkill2.toString();
        basicProfileModel.addProfessionalSkill("Dq9y3wHnMC3Y8ag", professionalSkillString);
    }
}
