package com.shampan.db.collections.fragment.profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class Address {
    private String id;
//    @JsonProperty("adrs")
    private String address;
//    @JsonProperty("ct")
    private String city;
//    @JsonProperty("zip")
    private String zip;
//    @JsonProperty("pc")
    private String postCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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

    public static Address getAddress(String jsonContent) {
        Address address = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            address = mapper.readValue(jsonContent, Address.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return address;
    }

}
