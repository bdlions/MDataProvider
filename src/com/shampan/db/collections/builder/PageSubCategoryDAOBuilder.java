/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.PageSubCategoryDAO;

/**
 *
 * @author Sampan-IT
 */
public class PageSubCategoryDAOBuilder {

    private PageSubCategoryDAO pageSubCategoryInfo;

    public PageSubCategoryDAOBuilder() {
        pageSubCategoryInfo = new PageSubCategoryDAO();
    }
    private String _id;
    private String categoryId;
    private String subCategoryId;
    private String title;

    public PageSubCategoryDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public PageSubCategoryDAOBuilder setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public PageSubCategoryDAOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PageSubCategoryDAOBuilder setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
        return this;
    }
    

    public PageSubCategoryDAO build() {
        pageSubCategoryInfo.setSubCategoryId(subCategoryId);
        pageSubCategoryInfo.setTitle(title);
        pageSubCategoryInfo.setCategoryId(categoryId);
        return pageSubCategoryInfo;
    }

    public PageSubCategoryDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            pageSubCategoryInfo = mapper.readValue(daoContent, PageSubCategoryDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pageSubCategoryInfo;
    }

}
