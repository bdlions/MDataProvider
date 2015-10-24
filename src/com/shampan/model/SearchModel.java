/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.fragment.status.UserInfo;
import com.shampan.util.PropertyProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;

/**
 *
 * @author Sampan-IT
 */
public class SearchModel {

    public SearchModel() {
        PropertyProvider.add("com.shampan.properties/response");
        PropertyProvider.add("com.shampan.properties/attributes");
    }
    private ResultEvent resultEvent = new ResultEvent();

    /**
     * This method will return result event
     *
     * @return ResultEvent, result event
     */
    public ResultEvent getResultEvent() {
        return resultEvent;
    }

    /**
     * This method will set result event
     *
     * @param resultEvent, result event
     */
    public void setResultEvent(ResultEvent resultEvent) {
        this.resultEvent = resultEvent;
    }

    /**
     * This method will return users
     *
     * @param requestPatten, request String
     * @return users
     */
    public List<UserDAO> getUsers(String searchValue) {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        String attrUserId = PropertyProvider.get("USER_ID");
        String attrFirstName = PropertyProvider.get("FIRST_NAME");
        String attrLastName = PropertyProvider.get("LAST_NAME");
        Document selectDocument = new Document();
        selectDocument.put("$regex", searchValue);
        selectDocument.put("$options", 'i');
        List<Document> orDocument = new ArrayList<Document>();
        orDocument.add(new Document(attrFirstName, selectDocument));
        orDocument.add(new Document(attrLastName, selectDocument));

        Document sDocument = new Document();
        sDocument.put("$or", orDocument);
        Document projectionDocument = new Document();
        projectionDocument.put(attrUserId, "$all");
        projectionDocument.put(attrFirstName, "$all");
        projectionDocument.put(attrLastName, "$all");

        MongoCursor<UserDAO> userList = mongoCollection.find(sDocument).projection(projectionDocument).iterator();
        List<UserDAO> userInfoList = IteratorUtils.toList(userList);
        return userInfoList;
    }

}
