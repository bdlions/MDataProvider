/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.SocialNetworkDAO;
import com.shampan.db.collections.builder.SocalNetworkingDAOBulider;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;

/**
 *
 * @author Sampan IT
 */
public class SocialNetworkingModel {

    private ResultEvent resultEvent = new ResultEvent();
    Utility utility = new Utility();

    public SocialNetworkingModel() {
        PropertyProvider.add("com.shampan.properties/response");
        PropertyProvider.add("com.shampan.properties/attributes");
    }

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

    public void addSocialNetworkInfo(String socialNetworkingInfo) {
        try {
            MongoCollection<SocialNetworkDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), SocialNetworkDAO.class);

            SocialNetworkDAO sNetworkInfo = new SocalNetworkingDAOBulider().build(socialNetworkingInfo);
            if (sNetworkInfo != null) {
                mongoCollection.insertOne(sNetworkInfo);
                this.resultEvent.setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.resultEvent.setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.resultEvent.setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }
    public void is_user_mapped_to_social_network(String socialNetworkingId, String code) {
        try {
            MongoCollection<SocialNetworkDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), SocialNetworkDAO.class);

         
        } catch (Exception ex) {
            this.resultEvent.setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }

}
