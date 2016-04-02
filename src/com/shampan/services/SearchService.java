/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.services;

import com.sampan.response.ResultEvent;
import com.shampan.model.SearchModel;
import org.json.JSONObject;

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
    public static String getSearchResult(String searchValue, int offset, int limit) {
        JSONObject searchResult = new JSONObject();
        searchResult.put("userList", searchModel.getUsers(searchValue, offset, limit));
        searchResult.put("pageList", searchModel.getPages(searchValue, offset, limit));
        return searchResult.toString();
    }

}
