package com.shampan.db.collections.fragment.profile;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class Email {
    private String id;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static Email getEmail(String jsonContent) {
        Email email = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            email = mapper.readValue(jsonContent, Email.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return email;
    }
}
