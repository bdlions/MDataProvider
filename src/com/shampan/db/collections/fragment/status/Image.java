/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.status;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class Image {
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
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

    public static Image getImageList(String jsonContent) {
        Image imageInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            imageInfo = mapper.readValue(jsonContent, Image.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return imageInfo;
    }

}
