/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.LoginAttemptDAO;
import com.shampan.db.collections.SocialNetworkDAO;

/**
 *
 * @author Sampan IT
 */
public class SocalNetworkingDAOBulider {

    private SocialNetworkDAO socialNetworkingInfo;

    public SocalNetworkingDAOBulider() {
        socialNetworkingInfo = new SocialNetworkDAO();
    }
    private String _id;
    private String userId;
    private String SocialNetworkId;
    private int code;

    public SocalNetworkingDAOBulider setSocialNetworkingInfo(SocialNetworkDAO socialNetworkingInfo) {
        this.socialNetworkingInfo = socialNetworkingInfo;
        return this;
    }

    public SocalNetworkingDAOBulider set_id(String _id) {
        this._id = _id;
        return this;
    }

    public SocalNetworkingDAOBulider setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public SocalNetworkingDAOBulider setSocialNetworkId(String SocialNetworkId) {
        this.SocialNetworkId = SocialNetworkId;
        return this;
    }

    public SocalNetworkingDAOBulider setCode(int code) {
        this.code = code;
        return this;
    }
    

    public SocialNetworkDAO build() {
        socialNetworkingInfo.setSocialNetworkId(SocialNetworkId);
        socialNetworkingInfo.setUserId(userId);
        socialNetworkingInfo.set_id(_id);
        socialNetworkingInfo.setCode(code);
        return socialNetworkingInfo;
    }

    public SocialNetworkDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            socialNetworkingInfo = mapper.readValue(daoContent, SocialNetworkDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return socialNetworkingInfo;

    }

}
