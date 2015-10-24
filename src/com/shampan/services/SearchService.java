/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.services;

import com.sampan.response.ResultEvent;
import com.shampan.model.SearchModel;

/**
 *
 * @author Sampan-IT
 */
public class SearchService {

    private static final SearchModel searchModel = new SearchModel();


    /**
     * This method will return users
     *
     * @param requestPatten, request String
     * @return users
     */
    public static String getUsers(String searchValue) {
        return searchModel.getUsers(searchValue).toString();
    }

}
