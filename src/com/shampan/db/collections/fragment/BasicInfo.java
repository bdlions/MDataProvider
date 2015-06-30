/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicInfo {

    private String website;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private Gender gender;
    private City city;
    private Town town;
    private String relationshipStatus;
    private List<MobilePhone> mobilePhones;
    private List<OtherPhone> otherPhones;
    private List<Email> emails;
    private List<Address> addresses;
    private List<Religion> religions;
    private List<FamilyMember> familyMember;

    public List<FamilyMember> getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(List<FamilyMember> familyMember) {
        this.familyMember = familyMember;
    }

  

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<MobilePhone> getMobilePhones() {
        return mobilePhones;
    }

    public void setMobilePhones(List<MobilePhone> mobilePhones) {
        this.mobilePhones = mobilePhones;
    }

    public List<OtherPhone> getOtherPhones() {
        return otherPhones;
    }

    public void setOtherPhones(List<OtherPhone> otherPhones) {
        this.otherPhones = otherPhones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Religion> getReligions() {
        return religions;
    }

    public void setReligions(List<Religion> religions) {
        this.religions = religions;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }
     public static BasicInfo getBasicInfo(String jsonContent) {
        BasicInfo basicInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            basicInfo = mapper.readValue(jsonContent, BasicInfo.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return basicInfo;
    }

}
