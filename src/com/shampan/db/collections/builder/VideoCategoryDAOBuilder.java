/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.shampan.db.codec.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.VideoCategoryDAO;

/**
 *
 * @author Sampan-IT
 */
public class VideoCategoryDAOBuilder {

    private VideoCategoryDAO vedioCategory;

    public VideoCategoryDAOBuilder() {
        vedioCategory = new VideoCategoryDAO();
    }
    private String _id;
    private String categoryId;
    private String title;

    public VideoCategoryDAOBuilder set_id(String _id) {
        this._id = _id;
        return this;
    }

    public VideoCategoryDAOBuilder setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public VideoCategoryDAOBuilder setTitle(String title) {
        this.title = title;
        return this;

    }

    public VideoCategoryDAO build() {
        vedioCategory.setCategoryId(categoryId);
        vedioCategory.setTitle(title);
        return vedioCategory;
    }

    public VideoCategoryDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            vedioCategory = mapper.readValue(daoContent, VideoCategoryDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vedioCategory;
    }

}
