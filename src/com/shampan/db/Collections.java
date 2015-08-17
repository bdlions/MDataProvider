package com.shampan.db;

/**
 *
 * @author nazmul hasan
 */
public enum Collections {

    USERS("users"),
    BASICPROFILE("basic_profiles"),
    RELATIONS("relations"),
    STATUSES("statuses"),
    USERALBUMS("user_albums"),
    ALBUMPHOTOS("album_photos"),
    PHOTOCATEGORIES("photo_categories");

    private String name;

    Collections(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
