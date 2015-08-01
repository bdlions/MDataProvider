/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class FavouriteQuote {
    private String fQuote;

    public String getfQuote() {
        return fQuote;
    }

    public void setfQuote(String fQuote) {
        this.fQuote = fQuote;
    }
    
    
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

    public static FavouriteQuote getFavouriteQuote(String jsonContent) {
        FavouriteQuote fQuote = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            fQuote = mapper.readValue(jsonContent, FavouriteQuote.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fQuote;
    }
    
}
