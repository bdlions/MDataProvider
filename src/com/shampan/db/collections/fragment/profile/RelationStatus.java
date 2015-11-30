package com.shampan.db.collections.fragment.profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class RelationStatus {
    private String id;
//    @JsonProperty("rs")
    private String relationshipStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
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

    public static RelationStatus getRStatus(String jsonContent) {
        RelationStatus rStatus = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            rStatus = mapper.readValue(jsonContent, RelationStatus.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rStatus;
    }
    
}
