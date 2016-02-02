package com.shampan.db.collections;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.profile.Gender;
import com.shampan.db.collections.fragment.user.Country;
import com.shampan.db.collections.fragment.user.Group;
import java.util.List;

/**
 *
 * @author alamgir
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDAO implements Bson {

    private String _id;
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String salt;
    private String ipAddress;
    private int createdOn;
    private int lastLogin;
    private String accountStatusId;
    private Country country;
    private Gender gender;
    private int active;
    private String activationCode;
    private String rememberCode;
    private String forgottenPasswordCode;
    private int forgottenPasswordTime;
    private List<Group> groups;

    public String getRememberCode() {
        return rememberCode;
    }

    public void setRememberCode(String rememberCode) {
        this.rememberCode = rememberCode;
    }

    public String getForgottenPasswordCode() {
        return forgottenPasswordCode;
    }

    public void setForgottenPasswordCode(String forgottenPasswordCode) {
        this.forgottenPasswordCode = forgottenPasswordCode;
    }

    public int getForgottenPasswordTime() {
        return forgottenPasswordTime;
    }

    public void setForgottenPasswordTime(int forgottenPasswordTime) {
        this.forgottenPasswordTime = forgottenPasswordTime;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public int getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(int lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    

  

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(String accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    @Override
    public <C> BsonDocument toBsonDocument(final Class<C> documentClass, final CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper<UserDAO>(this, codecRegistry.get(UserDAO.class));
    }

}
