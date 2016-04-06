/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.services;

import com.sampan.response.ResultEvent;
import com.shampan.db.collections.PhotoDAO;
import com.shampan.model.PageModel;
import com.shampan.model.PhotoModel;
import com.shampan.model.StatusModel;
import com.shampan.util.PropertyProvider;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class PageService {

    ResultEvent resultEvent = new ResultEvent();

    /**
     * This method will return result event
     *
     * @return ResultEvent, result event
     */
    public ResultEvent getResultEvent() {
        return resultEvent;
    }

    /**
     * This method will set result event
     *
     * @param resultEvent, result event
     */
    public void setResultEvent(ResultEvent resultEvent) {
        this.resultEvent = resultEvent;
    }
    private static PageModel pageObject = new PageModel();

    public static String getCategorySubCategory() {
        JSONObject json = new JSONObject();
        json.put("categoryList", pageObject.getCategories());
        json.put("subCategoryList", pageObject.getSubCategories());
        return json.toString();
    }

    public static String getSubCategories() {
        String response = pageObject.getSubCategories().toString();
        return response;
    }

    public static String addPage(String pageInfo) {
        String response = pageObject.addPage(pageInfo).toString();
        return response;
    }

    public static String updatePage(String pageInfo) {
        String response = pageObject.updatePage(pageInfo).toString();
        return response;
    }

    public static String addPageLike(String pageId, String memberInfo) {
        String response = pageObject.addPageLike(pageId, memberInfo).toString();
        return response;
    }

    public static String addPhotos(String pageId, String albumId, String photoList) {
        String response = pageObject.addPhotos(pageId, albumId, photoList).toString();
        return response;
    }

    public static String getInviteFriendList(String pageId, String albumId, int offset, int limit) {
        String response = pageObject.getInviteFriendList(pageId, albumId, offset, limit).toString();
        return response;
    }

    public static String inviteMember(String pageId, String memberInfo) {
        String response = pageObject.inviteMember(pageId, memberInfo).toString();
        return response;
    }

    public static String joinPageMamberShip(String pageId, String memberInfo) {
        String response = pageObject.joinPageMamberShip(pageId, memberInfo).toString();
        return response;
    }

    public static String leavePageMemberShip(String pageId, String userId) {
        String response = pageObject.leavePageMemberShip(pageId, userId).toString();
        return response;
    }

    public static String getSliderPhotos(String userId, String referenceId) {
        String response = pageObject.getSliderPhotos(userId, referenceId).toString();
        return response;
    }

    public static String addPhotoLike(String userId, String photoId, String referenceId, String likeInfo) {
        StatusModel statusObject = new StatusModel();
        String response = PropertyProvider.get("ERROR_EXCEPTION");
        ResultEvent resultEvent = statusObject.addStatusLike(userId, referenceId, likeInfo);
        if (resultEvent.getResponseCode().equals(PropertyProvider.get("SUCCESSFUL_OPERATION"))) {
            response = pageObject.addPhotoLike(photoId, likeInfo).toString();
        }
        return response;
    }

    public static String addMPhotoLike(String userId, String photoId, String likeInfo) {
        String response = pageObject.addMPhotoLike(userId, photoId, likeInfo).toString();
        return response;
    }

    public static String getTimelinePhotos(String pageId, String userId) {
        JSONObject json = new JSONObject();
        json.put("pageInfo", pageObject.getPageInfo(pageId));
        json.put("pageMemberInfo", pageObject.getMemeberInfo(pageId, userId));
        json.put("photoList", pageObject.getTimelinePhotos(pageId));
        return json.toString();
    }

    public static String getAlbums(String pageId) {
        JSONObject albums = new JSONObject();
        albums.put("albumList", pageObject.getAlbums(pageId));
        return albums.toString();
    }

    public static String getSliderAlbum(String mappingId, String albumId, String userId) {
        String response = pageObject.getSliderAlbum(mappingId, albumId, userId).toString();
        return response;
    }

    public static String getPhotos(String userId, String mappingId, String albumId) {
        JSONObject photos = new JSONObject();
        photos.put("albumInfo", pageObject.getAlbum(userId, mappingId, albumId));
        photos.put("photoList", pageObject.getPhotos(mappingId, albumId));
        return photos.toString();
    }

    public static String addPhotoComment(String photoId, String referenceId, String commentInfo, String referenceInfo, String statusTypeId) {
        PropertyProvider.add("com.shampan.properties/status");
        StatusModel statusObject = new StatusModel();
        if (statusTypeId.equals(PropertyProvider.get("STATUS_TYPE_ID_PAGE_CHANGE_PROFILE_PICTURE"))
                || statusTypeId.equals(PropertyProvider.get("STATUS_TYPE_ID_PAGE_CHANGE_COVER_PICTURE"))
                || statusTypeId.equals(PropertyProvider.get("STATUS_TYPE_ID_POST_STATUS_BY_USER_AT_HIS_PROFILE_WITH_PHOTO"))
                || statusTypeId.equals(PropertyProvider.get("STATUS_TYPE_ID_POST_STATUS_BY_USER_AT_FRIEND_PROFILE_WITH_PHOTO"))) {
            statusObject.addStatusComment(referenceInfo, referenceId, commentInfo).toString();
        }
        String response = pageObject.addPhotoComment(photoId, commentInfo).toString();
        return response;
    }

    public static String getPageInfo(String pageId, String userId) {
        JSONObject json = new JSONObject();
        json.put("pageInfo", pageObject.getPageInfo(pageId));
        json.put("pageMemberInfo", pageObject.getMemeberInfo(pageId, userId));
        return json.toString();
    }

}
