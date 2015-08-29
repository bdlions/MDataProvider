package com.shampan.db.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.relation.RelationInfo;
import java.util.ArrayList;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
/**
 *
 * @author nazmul hasan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelationsDAO implements Bson {

    private String _id;
    private String userId;
    private List<RelationInfo> friendList = new ArrayList<>();

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

    public List<RelationInfo> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<RelationInfo> friendList) {
        this.friendList = friendList;
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
        return new BsonDocumentWrapper<RelationsDAO>(this, codecRegistry.get(RelationsDAO.class));
    }
}
