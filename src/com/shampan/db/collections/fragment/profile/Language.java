package com.shampan.db.collections.fragment.profile;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Sampan-IT
 */
public class Language {
    private String id;
//    @JsonProperty("lng")
    private String language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
}
