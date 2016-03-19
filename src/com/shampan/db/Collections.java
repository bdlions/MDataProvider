package com.shampan.db;

/**
 *
 * @author nazmul hasan
 */
public enum Collections {

    RELIGIONS("religions"),
    COUNTRIES("countries"),
    USERS("users"),
    LOGINATTEMPTS("login_attempts"),
    USERPROFILES("user_profiles"),
    RELATIONS("relations"),
    STATUSES("statuses"),
    USERALBUMS("user_albums"),
    ALBUMPHOTOS("album_photos"),
    PHOTOCATEGORIES("photo_categories"),
    VIDEOCATEGORIES("video_categories"),
    VIDEOS("videos"),
    NOTIFICATIONS("notifications"),
    MESSAGES("messages"),
    MESSAGESDETAILS("messages_details"),
    PAGECATEGORIES("page_categories"),
    PAGESUBCATEGORIES("page_sub_categories"),
    PAGES("pages"),
    PAGEMEMBERS("pageMembers");

    private String name;

    Collections(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
