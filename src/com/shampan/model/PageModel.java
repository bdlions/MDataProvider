/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.PageAlbumDAO;
import com.shampan.db.collections.PageCategoryDAO;
import com.shampan.db.collections.PageDAO;
import com.shampan.db.collections.PageMemberDAO;
import com.shampan.db.collections.PagePhotoDAO;
import com.shampan.db.collections.PageSubCategoryDAO;
import com.shampan.db.collections.builder.PageAlbumDAOBuilder;
import com.shampan.db.collections.builder.PageDAOBuilder;
import com.shampan.db.collections.builder.PagePhotoDAOBuilder;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.page.AgeRange;
import com.shampan.db.collections.fragment.page.MemberInfo;
import com.shampan.db.collections.fragment.relation.RelationInfo;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sampan IT
 */
public class PageModel {

    Utility utility = new Utility();
    ResultEvent resultEvent = new ResultEvent();
    private final Logger logger = LoggerFactory.getLogger(PhotoModel.class);

    public PageModel() {
        PropertyProvider.add("com.shampan.properties/responseStatusCodes");
        PropertyProvider.add("com.shampan.properties/response");
        PropertyProvider.add("com.shampan.properties/attributes");
        PropertyProvider.add("com.shampan.properties/photos");
        PropertyProvider.add("com.shampan.properties/page");
    }

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

    public List<PageCategoryDAO> getCategories() {
        MongoCollection<PageCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PAGECATEGORIES.toString(), PageCategoryDAO.class);
        MongoCursor<PageCategoryDAO> cursorCategoryList = mongoCollection.find().iterator();
        List<PageCategoryDAO> categoryList = IteratorUtils.toList(cursorCategoryList);
        return categoryList;

    }

    public List<PageSubCategoryDAO> getSubCategories() {
        MongoCollection<PageSubCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PAGESUBCATEGORIES.toString(), PageSubCategoryDAO.class);
        MongoCursor<PageSubCategoryDAO> cursorCategoryList = mongoCollection.find().iterator();
        List<PageSubCategoryDAO> subCategoryList = IteratorUtils.toList(cursorCategoryList);
        return subCategoryList;

    }

//    public ResultEvent getSubCategories() {
//        try {
//            MongoCollection<PageSubCategoryDAO> mongoCollection
//                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGESUBCATEGORIES.toString(), PageSubCategoryDAO.class);
//            MongoCursor<PageSubCategoryDAO> cursorCategoryList = mongoCollection.find().iterator();
//            if (cursorCategoryList != null) {
//                List<PageSubCategoryDAO> subCategoryList = IteratorUtils.toList(cursorCategoryList);
//                this.getResultEvent().setResult(subCategoryList);
//                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
//            } else {
//                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
//            }
//        } catch (Exception ex) {
//            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
//        }
//        return this.resultEvent;
//
//    }
    public ResultEvent addPage(String pageInfo) {
        try {
            MongoCollection<PageDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGES.toString(), PageDAO.class);
            PageDAO pageInformation = new PageDAOBuilder().build(pageInfo);
            if (pageInformation != null) {
                String title = pageInformation.getTitle();
                String categoryId = pageInformation.getCategory().getCategoryId();
                String subCategoryId = pageInformation.getSubCategory().getSubCategoryId();
                ResultEvent result = getPageExist(title, categoryId);
                if (result.getResponseCode().equals(PropertyProvider.get("USER_ALLOW_TO_CREATE_PAGE"))) {
                    mongoCollection.insertOne(pageInformation);
                    this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
                } else {
                    this.getResultEvent().setResponseCode(result.getResponseCode());

                }
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent getPageExist(String title, String categoryId) {
        try {
            MongoCollection<PageDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGES.toString(), PageDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("title", title);
            selectDocument.put("category.categoryId", categoryId);
            PageDAO pageInfo = mongoCollection.find(selectDocument).first();
            if (pageInfo != null) {
                this.getResultEvent().setResponseCode(PropertyProvider.get("PAGE_IS_EXIST"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("USER_ALLOW_TO_CREATE_PAGE"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent updatePage(String pageInfo) {
        try {
            MongoCollection<PageDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGES.toString(), PageDAO.class);
            PageDAO pageInformation = new PageDAOBuilder().build(pageInfo);
            if (pageInformation != null) {
                Document selectDocument = new Document();
                selectDocument.put("pageId", pageInformation.getPageId());
                selectDocument.put("referenceId", pageInformation.getReferenceId());
                Document updatedDocument = new Document();
                updatedDocument.put("about", pageInformation.getAbout());
                updatedDocument.put("interestedAgeRange", JSON.parse(pageInformation.getInterestedAgeRange().toString()));
                updatedDocument.put("intertestedGender", pageInformation.getIntertestedGender());
                updatedDocument.put("referenceInfo", JSON.parse(pageInformation.getReferenceInfo().toString()));
                PageDAO result1 = mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updatedDocument));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent getPageInfo(String pageId) {
        ResultEvent resultEvent1 = new ResultEvent();
        try {
            MongoCollection<PageDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGES.toString(), PageDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            PageDAO pageInfo = mongoCollection.find(selectDocument).first();
            if (pageInfo != null) {
                resultEvent1.setResult(pageInfo);
                resultEvent1.setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                resultEvent1.setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            resultEvent1.setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return resultEvent1;

    }

    public PageDAO getPageBasicInfo(String pageId) {
        MongoCollection<PageDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PAGES.toString(), PageDAO.class);
        Document selectDocument = new Document();
        selectDocument.put("pageId", pageId);
        PageDAO pageInfo = mongoCollection.find(selectDocument).first();
        return pageInfo;
    }
//........................................page members ..........................................................

    public ResultEvent getMemeberCounter(String pageId) {
        ResultEvent resultEvent1 = new ResultEvent();
        try {
            MongoCollection<PageMemberDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            PageMemberDAO pageInfo = mongoCollection.find(selectDocument).first();
            if (pageInfo != null) {
                int members = pageInfo.getMemberList().size();
                resultEvent1.setResult(members);
                resultEvent1.setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                resultEvent1.setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            resultEvent1.setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return resultEvent1;

    }

    public ResultEvent getInviteMemberList(String userId, String pageId, int offset, int limit) {
        try {
            MongoCollection<PageMemberDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            PageMemberDAO pageInfo = mongoCollection.find(selectDocument).first();
            RelationModel friends = new RelationModel();
            String relationTypeId = PropertyProvider.get("RELATION_TYPE_FRIEND_ID");
            List<RelationInfo> friendList = friends.getRelationList(userId, relationTypeId, offset, limit);
            System.out.println(friendList);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent inviteMember(String pageId, String memberInfo) {
        
        try {
            MemberInfo mInfo = MemberInfo.getMemberInfo(memberInfo);
            if (mInfo != null) {
                
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent addPageLike(String pageId, String memberInfo) {
        try {
            MongoCollection<PageMemberDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            MemberInfo likedUserInfo = MemberInfo.getMemberInfo(memberInfo);
            if (memberInfo != null) {
                PageMemberDAO pageInfo = mongoCollection.find(selectDocument).first();
                if (pageInfo != null) {

                } else {
                }
            }

            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent unLikePage(String userId, String pageId) {
        try {
            leaveMember(userId, pageId);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent addPageMember(String pageId, String memberInfo) {
        try {
            MongoCollection<PageMemberDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            MemberInfo mInfo = MemberInfo.getMemberInfo(memberInfo);
            if (mInfo != null) {
                PageMemberDAO pageInfo = mongoCollection.find(selectDocument).first();
                if (pageInfo != null) {
                    mongoCollection.findOneAndUpdate(selectDocument, new Document("$push", new Document("memberList", JSON.parse(mInfo.toString()))));
                } else {
                    List<MemberInfo> mList = new ArrayList<MemberInfo>();
                    mList.add(mInfo);
                    PageMemberDAO pageMember = new PageMemberDAO();
                    pageMember.setPageId(pageId);
                    pageMember.setMemberList(mList);
                    mongoCollection.insertOne(pageMember);
                }
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent updateMemberStatus(String userId, String PageId, String StatusTypeId) {
        try {

            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent leaveMember(String userId, String pageId) {
        try {

            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will add list of photos , 
     * @param photoInfoList, photo list
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent addPhotos(String pageId, String albumId, String photoInfoList) {
        try {
            MongoCollection<PagePhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEPHOTOS.toString(), PagePhotoDAO.class);
            JSONArray photoArray = new JSONArray(photoInfoList);
            ArrayList<PagePhotoDAO> photoList = new ArrayList<PagePhotoDAO>();
            String defaultImg = "";
            String photoId = "";
            String referenceId = "";
            int newTotalImg = photoArray.length();
            if (photoArray != null) {
                PagePhotoDAO photoInfoObj1 = new PagePhotoDAOBuilder().build(photoArray.get(0).toString());
                defaultImg = photoInfoObj1.getImage();
                photoId = photoInfoObj1.getPhotoId();
                referenceId = photoInfoObj1.getReferenceId();
                PageAlbumDAO albumInfo = new PageAlbumDAO();
                albumInfo.setDefaultImg(defaultImg);
                albumInfo.setTotalImg(newTotalImg);
                albumInfo.setPhotoId(photoId);
                albumInfo.setReferenceId(referenceId);
                PageAlbumDAO oldAlbumInfo = getAlbumInfo(pageId, albumId);
                JSONObject resultJson = new JSONObject();
                Boolean refernceId = false;
                if (oldAlbumInfo != null) {
                    if (oldAlbumInfo.getAlbumId().equals(albumId)) {
                        if (oldAlbumInfo.getPhotoId() != null) {
                            int totalImg = newTotalImg + oldAlbumInfo.getTotalImg();
                            if (albumId.equals(PropertyProvider.get("PAGE_TIMELINE_PHOTOS_ALBUM_ID"))) {
                                refernceId = true;
                            } else if (albumId.equals(PropertyProvider.get("PAGE_PROFILE_PHOTOS_ALBUM_ID"))) {
                                refernceId = true;
                            } else if (albumId.equals(PropertyProvider.get("PAGE_COVER_PHOTOS_ALBUM_TITLE"))) {
                                refernceId = true;
                            }
                            if (refernceId == false) {
                                referenceId = oldAlbumInfo.getReferenceId();
                                resultJson.put("referenceId", referenceId);
                            }
                            editAlbumTotalImg(pageId, albumId, totalImg);
                        } else {
                            editAlbum(pageId, albumId, albumInfo.toString());
                        }
                    }

                } else {
                    if (albumId.equals(PropertyProvider.get("PAGE_TIMELINE_PHOTOS_ALBUM_ID"))) {
                        albumInfo.setTitle(PropertyProvider.get("PAGE_TIMELINE_PHOTOS_ALBUM_TITLE"));
                    } else if (albumId.equals(PropertyProvider.get("PAGE_PROFILE_PHOTOS_ALBUM_ID"))) {
                        albumInfo.setTitle(PropertyProvider.get("PAGE_PROFILE_PHOTOS_ALBUM_TITLE"));
                    } else if (albumId.equals(PropertyProvider.get("PAGE_COVER_PHOTOS_ALBUM_ID"))) {
                        albumInfo.setTitle(PropertyProvider.get("PAGE_COVER_PHOTOS_ALBUM_TITLE"));
                    }
                    albumInfo.setPageId(pageId);
                    albumInfo.setAlbumId(albumId);
                    createAlbum(albumInfo.toString());

                }
                for (int i = 0; i < newTotalImg; i++) {
                    PagePhotoDAO photoInfoObj = new PagePhotoDAOBuilder().build(photoArray.get(i).toString());
                    photoInfoObj.setPageId(pageId);
                    photoInfoObj.setCreatedOn(utility.getCurrentTime());
                    photoInfoObj.setModifiedOn(utility.getCurrentTime());
                    photoInfoObj.setReferenceId(referenceId);
                    photoList.add(photoInfoObj);
                }

                mongoCollection.insertMany(photoList);
                PageDAO pageInfo = getPageBasicInfo(pageId);
                if (pageInfo != null) {
                    this.getResultEvent().setResult(pageInfo.getTitle());
//                    resultJson.put("pageTitle", pageInfo.getTitle());
                }
//                this.getResultEvent().setResult(resultJson);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will create user album, 
     * @param albumInfo,user album information
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent createAlbum(String albumInfo) {
        try {
            MongoCollection<PageAlbumDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEALBUMS.toString(), PageAlbumDAO.class);
            PageAlbumDAO albumInfoObj = new PageAlbumDAOBuilder().build(albumInfo);
            if (albumInfoObj != null) {
                mongoCollection.insertOne(albumInfoObj);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public PageAlbumDAO getAlbumInfo(String pageId, String albumId) {
        MongoCollection<PageAlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEALBUMS.toString(), PageAlbumDAO.class);
        String attUserId = PropertyProvider.get("USER_ID");
        String attAlbumId = PropertyProvider.get("ALBUM_ID");
        String attPhotoId = PropertyProvider.get("PHOTO_ID");
        String attTotalImg = PropertyProvider.get("TOTAL_IMG");
        String attReferenceId = PropertyProvider.get("REFERENCE_ID");
        Document sQuery = new Document();
        sQuery.put("pageId", pageId);
        sQuery.put(attAlbumId, albumId);
        Document pQuery = new Document();
        pQuery.put("pageId", "$all");
        pQuery.put(attAlbumId, "$all");
        pQuery.put(attPhotoId, "$all");
        pQuery.put(attTotalImg, "$all");
        pQuery.put(attReferenceId, "$all");
        PageAlbumDAO albumInfo = mongoCollection.find(sQuery).projection(pQuery).first();
        return albumInfo;
    }

    public ResultEvent editAlbumTotalImg(String pageId, String albumId, int totalImgInfo) {
        try {
            MongoCollection<PageAlbumDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEALBUMS.toString(), PageAlbumDAO.class);
            Document selectionDocument = new Document();
            selectionDocument.put("pageId", pageId);
            selectionDocument.put("albumId", albumId);
            PageAlbumDAO result = mongoCollection.findOneAndUpdate(selectionDocument, new Document("$set", new Document("totalImg", totalImgInfo)));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will edit user album, 
     * @param albumId, album id
     * @param albumInfo,user album information
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent editAlbum(String pageId, String albumId, String albumInfo) {
        try {
            MongoCollection<PageAlbumDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEALBUMS.toString(), PageAlbumDAO.class);

            Document selectionDocument = new Document();
            selectionDocument.put("pageId", pageId);
            selectionDocument.put("albumId", albumId);
            PageAlbumDAO albumInfoObj = new PageAlbumDAOBuilder().build(albumInfo);
            Document modifiedQuery = new Document();
            modifiedQuery.put("photoId", albumInfoObj.getPhotoId());
            modifiedQuery.put("defaultImg", albumInfoObj.getDefaultImg());
            modifiedQuery.put("totalImg", albumInfoObj.getTotalImg());
            modifiedQuery.put("referenceId", albumInfoObj.getReferenceId());
            PageAlbumDAO result = mongoCollection.findOneAndUpdate(selectionDocument, new Document("$set", modifiedQuery));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public static void main(String args[]) {
        PageModel obj = new PageModel();
        MemberInfo memberinfo = new MemberInfo();
        memberinfo.setUserId("1");
        memberinfo.setFirstName("Rashida");
        memberinfo.setLastName("sultana");
        memberinfo.setLastName("2");
        memberinfo.setRelationTypeId(PropertyProvider.get("PAGE_MEMBER_STATUS_ID_INVITED"));

        System.out.println(obj.inviteMember("sKDuYWRygwssTdL", memberinfo.toString()));
//        System.out.println(obj.getInviteMemberList("7OdqKzxmuakkpRq", "7OdqKzxmuakkpRq", 0, 10));
    }
}
