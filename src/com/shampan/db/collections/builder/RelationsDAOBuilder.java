package com.shampan.db.collections.builder;

import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.fragment.relations.RelationInfo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nazmul hasan
 */
public class RelationsDAOBuilder {

    private RelationsDAO relations;

    public RelationsDAOBuilder() {
        relations = new RelationsDAO();
    }
    private String _id;
    private String userId;
    private List<RelationInfo> friendList = new ArrayList<>();

    public RelationsDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }
    
    public RelationsDAOBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    
    public RelationsDAOBuilder setFriendList(List<RelationInfo> friendList) {
        this.friendList = friendList;
        return this;
    }

    public RelationsDAO build() {
        relations.set_id(_id);
        relations.setUserId(userId);
        relations.setFriendList(friendList);
        return relations;
    }
}