/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.PageCategoryDAO;

/**
 *
 * @author Sampan-IT
 */
public class PageCategoryDAOBuilder {

    private PageCategoryDAO pageCategoryInfo;

    public PageCategoryDAOBuilder() {
        pageCategoryInfo = new PageCategoryDAO();
    }
    private String _id;
    private String categoryId;
    private String title;

    public PageCategoryDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public PageCategoryDAOBuilder setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public PageCategoryDAOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PageCategoryDAO build() {
        pageCategoryInfo.setCategoryId(categoryId);
        pageCategoryInfo.setTitle(title);
        return pageCategoryInfo;
    }

    public PageCategoryDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            pageCategoryInfo = mapper.readValue(daoContent, PageCategoryDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pageCategoryInfo;
    }

   
}
