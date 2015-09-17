package com.shampan.db.collections.fragment.profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class FavouriteQuote {
    private String id;
    @JsonProperty("fq")
    private String fQuote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getfQuote() {
        return fQuote;
    }

    public void setfQuote(String fQuote) {
        this.fQuote = fQuote;
    }
    
    
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

    public static FavouriteQuote getFavouriteQuote(String jsonContent) {
        FavouriteQuote fQuote = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            fQuote = mapper.readValue(jsonContent, FavouriteQuote.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fQuote;
    }
    
}
