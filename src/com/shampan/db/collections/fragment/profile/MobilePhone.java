package com.shampan.db.collections.fragment.profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.CountriesDAO;

/**
 *
 * @author Sampan-IT
 */
public class MobilePhone {
    private String id;
    @JsonProperty("phn")
    private String phone;
    @JsonProperty("cty")
    private CountriesDAO country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public CountriesDAO getCountry() {
        return country;
    }

    public void setCountry(CountriesDAO country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public static MobilePhone getMobilePhone(String jsonContent) {
        MobilePhone mobilePhone = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mobilePhone = mapper.readValue(jsonContent, MobilePhone.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mobilePhone;
    }
    
}
