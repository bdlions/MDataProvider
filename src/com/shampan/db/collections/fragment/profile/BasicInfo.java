package com.shampan.db.collections.fragment.profile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 *
 * @author nazmul hasan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicInfo {
    @JsonProperty("ws")
    private Website website;
    @JsonProperty("bDate")
    private BirthDate birthDate;
    @JsonProperty("bDate")
    private Gender gender;
    @JsonProperty("ct")
    private City city;
    @JsonProperty("twn")
    private Town town;
    @JsonProperty("adrses")
    private Address addresses;
    @JsonProperty("rs")
    private RelationStatus relationshipStatus;
    @JsonProperty("mps")
    private List<MobilePhone> mobilePhones;
    @JsonProperty("ops")
    private List<OtherPhone> otherPhones;
    private List<Email> emails;
    @JsonProperty("abt")
    private About about;
    @JsonProperty("fq")
    private FavouriteQuote fQuote;
    private Religion religions;
    private List<FamilyMember> familyMember;
    private List<Language> language;
    public About getAbout() {
        return about;
    }

    public void setAbout(About about) {
        this.about = about;
    }

    public FavouriteQuote getfQuote() {
        return fQuote;
    }

    public void setfQuote(FavouriteQuote fQuote) {
        this.fQuote = fQuote;
    }
    public RelationStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Language> getLanguage() {
        return language;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }

    public List<FamilyMember> getFamilyMember() {
        return familyMember;
    }

    public Religion getReligions() {
        return religions;
    }

    public void setReligions(Religion religions) {
        this.religions = religions;
    }

    public void setFamilyMember(List<FamilyMember> familyMember) {
        this.familyMember = familyMember;
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

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
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
