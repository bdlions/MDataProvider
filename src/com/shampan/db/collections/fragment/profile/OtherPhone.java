package com.shampan.db.collections.fragment.profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shampan.db.collections.CountriesDAO;

/**
 *
 * @author Sampan-IT
 */
public class OtherPhone {
    private String id;
    @JsonProperty("phn")
    private String phone ;
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
    
}
