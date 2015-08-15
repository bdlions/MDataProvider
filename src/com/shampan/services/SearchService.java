/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.services;

import com.shampan.model.SearchModel;

/**
 *
 * @author Sampan-IT
 */
public class SearchService {

    private static SearchModel searchObj = new SearchModel();
    public static String getUsers(String statusInfo) {
        String response = searchObj.getUsers(statusInfo);
        return response;
    }

}
