/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.Work;
import java.util.List;

/**
 *
 * @author alamgir
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDAO implements Bson {

    private String _id;
    private String first_name;
    private String last_name;
    private String username;
    private List<Work> workList;

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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

    @Override
    public <C> BsonDocument toBsonDocument(final Class<C> documentClass, final CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper<UserDAO>(this, codecRegistry.get(UserDAO.class));
    }
}
