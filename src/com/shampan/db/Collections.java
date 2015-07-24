package com.shampan.db;

/**
 *
 * @author nazmul hasan
 */
public enum Collections {
    USERS("users"), 
    BASICPROFILE("basic_profiles"), 
    RELATIONS("relations");

    private String name;

    Collections(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
