/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.shampan.db.collections.CountriesDAO;

/**
 *
 * @author Sampan-IT
 */
public class CountriesDAOBuilder {

    private CountriesDAO country;

    public CountriesDAOBuilder() {
        country = new CountriesDAO();
    }
    private String _id;
    private String code;
    private String title;
    private String order;

    public CountriesDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public CountriesDAOBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public CountriesDAOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CountriesDAOBuilder setOrder(String order) {
        this.order = order;
        return this;
    }

    public CountriesDAO build() {
        country.set_id(_id);
        country.setCode(code);
        country.setTitle(title);
        country.setOrder(order);
        return country;
    }

}
