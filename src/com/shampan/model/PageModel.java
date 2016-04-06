/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
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
import com.shampan.db.collections.fragment.common.Comment;
import com.shampan.db.collections.fragment.common.Like;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.page.AgeRange;
import com.shampan.db.collections.fragment.page.MemberInfo;
import com.shampan.db.collections.fragment.relation.RelationInfo;
import com.shampan.util.LogWriter;
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
    UserModel userModel = new UserModel();
    NotificationModel notificationModel = new NotificationModel();
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
    public List<String> getPageIdList(String userId) {
        MongoCollection<PageMemberDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
        MongoCursor<PageMemberDAO> pageList = mongoCollection.find().iterator();
        List<String> pageIdList = new ArrayList<>();
        while (pageList.hasNext()) {
            PageMemberDAO pageInfo = pageList.next();
            if (pageInfo.getMemberList().size() > 0) {
                for (int i = 0; i < pageInfo.getMemberList().size(); i++) {

                    if (pageInfo.getMemberList().get(i).getUserId().equals(userId)) {
                        pageIdList.add(pageInfo.getPageId());
                    }
                }
            }
        }
        return pageIdList;
    }

    public JSONObject getMemeberInfo(String pageId, String userId) {
        JSONObject memberInfo = new JSONObject();
        try {
            MongoCollection<PageMemberDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            PageMemberDAO pageInfo = mongoCollection.find(selectDocument).first();
            if (pageInfo != null) {
                String relationTypeId = PropertyProvider.get("PAGE_MEMBER_STATUS_ID_LIKED");
                List<MemberInfo> memberList = pageInfo.getMemberList();
                int memberCounter = memberList.size();
                if (memberCounter > 0) {
                    int counter = 0;
                    for (int j = 0; j < memberList.size(); j++) {
                        if (memberList.get(j).getRelationTypeId().equals(relationTypeId)) {
                            counter = counter + 1;
                        }
                        if (userId.equals(memberList.get(j).getUserId())) {
                            memberInfo.put("memberShipStatus", memberList.get(j).getRelationTypeId());
                        }
                    }
                    memberInfo.put("counter", counter);
                }
                logger.debug(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                logger.debug(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        }
        return memberInfo;

    }

    public List<JSONObject> getInviteFriendList(String pageId, String userId, int offset, int limit) {
        List<JSONObject> jsonFriendList = new ArrayList<>();
        try {
            MongoCollection<PageMemberDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            PageMemberDAO pageInfo = mongoCollection.find(selectDocument).first();
            List<MemberInfo> memberList = new ArrayList<>();
            if (pageInfo != null) {
                memberList = pageInfo.getMemberList();
            }
            RelationModel friends = new RelationModel();
            String relationTypeId = PropertyProvider.get("RELATION_TYPE_FRIEND_ID");
            List<RelationInfo> friendList = friends.getRelationList(userId, relationTypeId, offset, limit);
            if (friendList.size() > 0) {
                for (int i = 0; i < friendList.size(); i++) {
                    JSONObject jsonFriend = new JSONObject();
                    jsonFriend.put("friendInfo", friendList.get(i));
                    if (memberList.size() > 0) {
                        for (int j = 0; j < memberList.size(); j++) {
                            if (friendList.get(i).getUserId().equals(memberList.get(j).getUserId())) {
                                jsonFriend.put("status", memberList.get(j).getRelationTypeId());
                            }
                        }
                    }
                    jsonFriendList.add(jsonFriend);
                }
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return jsonFriendList;
    }

    /*
     * This method will add member invitation , 
     * @param pageId, pageId
     * @param memberInfo, memberInfo
     * @author created by Rashida on 22th march 2016
     */
    public ResultEvent inviteMember(String pageId, String memberInfo) {
        try {
            MemberInfo mInfo = MemberInfo.getMemberInfo(memberInfo);
            if (mInfo != null) {
                mInfo.setRelationTypeId(PropertyProvider.get("PAGE_MEMBER_STATUS_ID_INVITED"));
                addPageMember(pageId, mInfo.toString());
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }
    /*
     * This method will add user to memberList , 
     * @param pageId, pageId
     * @param memberInfo, memberInfo
     * @author created by Rashida on 22th march 2016
     */

    public ResultEvent joinPageMamberShip(String pageId, String memberInfo) {
        try {
            MemberInfo likedUserInfo = MemberInfo.getMemberInfo(memberInfo);
            if (memberInfo != null) {
                likedUserInfo.setRelationTypeId(PropertyProvider.get("PAGE_MEMBER_STATUS_ID_LIKED"));
                addPageMember(pageId, memberInfo);
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    /*
     * This method will add user to memberList , 
     * @param pageId, pageId
     * @param memberInfo, memberInfo
     * @author created by Rashida on 22th march 2016
     */
    public ResultEvent addPageMember(String pageId, String memberInfo) {
        try {
            MongoCollection<PageMemberDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            MemberInfo mInfo = MemberInfo.getMemberInfo(memberInfo);
            if (mInfo != null) {
                PageMemberDAO pageInfo = mongoCollection.find(selectDocument).first();
                boolean userIsExist = false;
                if (pageInfo != null) {
                    List<MemberInfo> memberList = pageInfo.getMemberList();
                    List<MemberInfo> tempMemberList = new ArrayList<>();
                    if (memberList.size() > 0) {
                        for (int i = 0; i < memberList.size(); i++) {
                            MemberInfo tempMemberInfo = memberList.get(i);
                            if (tempMemberInfo.getUserId().equals(mInfo.getUserId())) {
                                tempMemberInfo.setRelationTypeId(PropertyProvider.get("PAGE_MEMBER_STATUS_ID_LIKED"));
                                userIsExist = true;
                            }
                            tempMemberList.add(tempMemberInfo);
                        }
                    }
                    if (userIsExist != false) {
                        mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", new Document("memberList", JSON.parse(tempMemberList.toString()))));
                    } else {
                        mongoCollection.findOneAndUpdate(selectDocument, new Document("$push", new Document("memberList", JSON.parse(mInfo.toString()))));
                    }
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

    /*
     * This method will remove user to memberList , 
     * @param pageId, pageId
     * @param userId, userId
     * @author created by Rashida on 22th march 2016
     */
    public ResultEvent leavePageMemberShip(String pageId, String userId) {
        try {
            MongoCollection<PageMemberDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
            Document selectionDocument = new Document();
            selectionDocument.put("pageId", pageId);
            selectionDocument.put("memberList.userId", userId);
            Document removedDocument = new Document();
            removedDocument.put("userId", userId);
            mongoCollection.findOneAndUpdate(selectionDocument, new Document("$pull", new Document("memberList", removedDocument)));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }
//.....................................................................photos......................................

    /*
     * This method will create page album, 
     * @param albumInfo,page album information
     * @author created by Rashida on 4th april 2016
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
    /*
     * This method will reutun page album info, 
     * @param pageId, pageid
     * @param albumId,page album id
     * @author created by Rashida on 5th april 2016
     */

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
    /*
     * This method will edit page album info, 
     * @param pageId, pageid
     * @param albumId,page album id
     * @param totalImgInfo,totalImgInfo
     * @author created by Rashida on 5th april 2016
     */

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
     * This method will edit page album, 
     * @param pageId, page id
     * @param albumId, album id
     * @param albumInfo,page album information
     * @author created by Rashida on 5th April 2016
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

    /*
     * This method will add page album like by status id, 
     * @param referenceId, status id
     * @param likeInfo, likeInfo
     * @author created by Rashida on 5th April 2016
     */
    public ResultEvent addAlbumLikeByReferenceId(String referenceId, String likeInfo) {
        try {
            MongoCollection<PageAlbumDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEALBUMS.toString(), PageAlbumDAO.class);
            String attrReferenceId = PropertyProvider.get("REFERENCE_ID");
            String attrLike = PropertyProvider.get("LIKE");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrReferenceId).is(referenceId).get();
            Like albumlikeInfo = Like.getLikeInfo(likeInfo);
            if (albumlikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document(attrLike, JSON.parse(albumlikeInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will return page all albums  by page id, 
     * @param pageId, pageId
     * @author created by Rashida on 5th April 2016
     */
    public List<PageAlbumDAO> getAlbums(String pageId) {
        MongoCollection<PageAlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEALBUMS.toString(), PageAlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("pageId").is(pageId).get();
        Document pQuery = new Document();
        pQuery.put("albumId", "$all");
        pQuery.put("title", "$all");
        pQuery.put("totalImg", "$all");
        pQuery.put("defaultImg", "$all");
        MongoCursor<PageAlbumDAO> cursorAlbumList = mongoCollection.find(selectQuery).projection(pQuery).iterator();
        List<PageAlbumDAO> albumList = IteratorUtils.toList(cursorAlbumList);
        return albumList;

    }
    
    
       /*
     * This method will add a photo comment , 
     * @param photoId, photo id
     * @param commentInfo, comment info
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent addAlbumCommentByReferenceId(String referenceId, String commentInfo) {
        try {
            MongoCollection<PagePhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEALBUMS.toString(), PagePhotoDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("referenceId").is(referenceId).get();
            Comment photoCommentInfo = Comment.getCommentInfo(commentInfo);
            if (photoCommentInfo != null) {
                photoCommentInfo.setCreatedOn(utility.getCurrentTime());
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(photoCommentInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will return page album , 
     * @param pageId, pageId
     * @param albumId, albumId
     * @author created by Rashida on 5th April 2016
     */
    public String getAlbum(String userId, String pageId, String albumId) {
        MongoCollection<PageAlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), PageAlbumDAO.class);
        Document selectDocument = new Document();
        selectDocument.put("pageId", pageId);
        selectDocument.put("albumId", albumId);
        PageAlbumDAO albumInfo = mongoCollection.find(selectDocument).first();
        JSONObject albumInfoJson = new JSONObject();
        try {
            albumInfoJson.put("albumId", albumInfo.getAlbumId());
            albumInfoJson.put("referenceId", albumInfo.getReferenceId());
            albumInfoJson.put("pageId", albumInfo.getPageId());
            albumInfoJson.put("title", albumInfo.getTitle());
            albumInfoJson.put("description", albumInfo.getDescription());
            albumInfoJson.put("totalImg", albumInfo.getTotalImg());
            albumInfoJson.put("defaultImg", albumInfo.getTotalImg());
            if (albumInfo.getLike() != null) {
                int likeSize = albumInfo.getLike().size();
                if (likeSize > 0) {
                    albumInfoJson.put("likeCounter", likeSize);

                }
                int i = 0;
                while (likeSize > 0) {
                    String tempUserId = albumInfo.getLike().get(i).getUserInfo().getUserId();
                    if (tempUserId.equals(userId)) {
                        albumInfoJson.put("likeStatus", "1");
                    }
                    likeSize--;
                    i++;
                }

            }
            if (albumInfo.getComment() != null) {
                int commentSize = albumInfo.getComment().size();
                List<Comment> commentList = new ArrayList();
                if (commentSize >= 1) {
                    Comment lastComment = albumInfo.getComment().get(commentSize - 1);
                    commentList.add(lastComment);
                }
                if (commentSize >= 2) {
                    Comment secondlastComment = albumInfo.getComment().get(commentSize - 2);
                    commentList.add(secondlastComment);
                }
                if (commentSize > 2) {
                    albumInfoJson.put("commentCounter", commentSize - 2);
                }
                albumInfoJson.put("comment", commentList);
            }
            if (albumInfo.getShare() != null) {
                int shareSize = albumInfo.getShare().size();
                if (shareSize > 0) {
                    albumInfoJson.put("shareCounter", shareSize);
                }
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }

        return albumInfoJson.toString();
    }

    /*
     * This method will return page album photos , 
     * @param userId, userId
     * @param referenceId, status id
     * @author created by Rashida on 5th April 2016
     */
    public JSONObject getSliderPhotos(String userId, String referenceId) {
        JSONObject userStatusInfo = new JSONObject();
        try {
            MongoCollection<PagePhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEPHOTOS.toString(), PagePhotoDAO.class);
            Document selectionDocument = new Document();
            selectionDocument.put("referenceId", referenceId);
            MongoCursor<PagePhotoDAO> photoList = mongoCollection.find(selectionDocument).iterator();
            List<JSONObject> photoInfoList = getPhotoInfo(userId, photoList);
            userStatusInfo.put("photoList", photoInfoList);
            userStatusInfo.put("userCurrentTime", utility.getCurrentTime());
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return userStatusInfo;

    }

    /*
     * This method will add list of photos , 
     * @param photoInfoList, photo list
     * @author created by Rashida on 5th September 2015
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
                Boolean statusUpdate = false;
                if (oldAlbumInfo != null) {
                    if (oldAlbumInfo.getAlbumId().equals(albumId)) {
                        if (oldAlbumInfo.getPhotoId() != null) {
                            statusUpdate = true;
                            int totalImg = newTotalImg + oldAlbumInfo.getTotalImg();
                            String coverId = PropertyProvider.get("PAGE_COVER_PHOTOS_ALBUM_ID");
                            String profileId = PropertyProvider.get("PAGE_PROFILE_PHOTOS_ALBUM_ID");
                            String timelineId = PropertyProvider.get("PAGE_TIMELINE_PHOTOS_ALBUM_ID");
                            if (albumId.equals(coverId)) {
                                refernceId = true;
                            } else if (albumId.equals(profileId)) {
                                refernceId = true;
                            } else if (albumId.equals(timelineId)) {
                                refernceId = true;
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
                List<String> images = new ArrayList<>();
                for (int i = 0; i < newTotalImg; i++) {
                    PagePhotoDAO photoInfoObj = new PagePhotoDAOBuilder().build(photoArray.get(i).toString());
                    photoInfoObj.setPageId(pageId);
                    photoInfoObj.setCreatedOn(utility.getCurrentTime());
                    photoInfoObj.setModifiedOn(utility.getCurrentTime());
                    photoInfoObj.setReferenceId(referenceId);
                    photoList.add(photoInfoObj);
                    if (refernceId != true && statusUpdate != false) {
                        images.add(photoInfoObj.getImage());
                    }
                }
                if (refernceId != true && statusUpdate != false) {
                    referenceId = oldAlbumInfo.getReferenceId();
                    StatusModel statusModel = new StatusModel();
                    ResultEvent rEvent = statusModel.updateStatusPhoto(referenceId, images.toString());
                    if (rEvent.getResponseCode().equals(PropertyProvider.get("SUCCESSFUL_OPERATION"))) {
                    }
                }
                mongoCollection.insertMany(photoList);
                PageDAO pageInfo = getPageBasicInfo(pageId);
                if (pageInfo != null) {
                    this.getResultEvent().setResult(pageInfo.getTitle());
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

    public List<JSONObject> getPhotoInfo(String userId, MongoCursor<PagePhotoDAO> photoList) {

        int commentLimit = Integer.parseInt(PropertyProvider.get("COMMENT_LIMIT"));
        List<JSONObject> photoInfoList = new ArrayList<JSONObject>();

        while (photoList.hasNext()) {
            JSONObject photoJson = new JSONObject();
            PagePhotoDAO photoInfo = (PagePhotoDAO) photoList.next();
            photoJson.put("photoId", photoInfo.getPhotoId());
            photoJson.put("pageId", photoInfo.getPageId());
            photoJson.put("referenceId", photoInfo.getReferenceId());
            photoJson.put("pageInfo", photoInfo.getPhotoId());
            photoJson.put("description", photoInfo.getDescription());
            photoJson.put("createdOn", photoInfo.getCreatedOn());
            photoJson.put("image", photoInfo.getImage());
            if (photoInfo.getLike() != null) {
                int likeSize = photoInfo.getLike().size();
                if (likeSize > 0) {
                    photoJson.put("likeCounter", likeSize);
                }
                int i = 0;
                while (likeSize > 0) {
                    String tempUserId = photoInfo.getLike().get(i).getUserInfo().getUserId();
                    if (tempUserId.equals(userId)) {
                        photoJson.put("likeStatus", PropertyProvider.get("YourLikeStatus"));
                    }
                    likeSize--;
                    i++;
                }

            }
            if (photoInfo.getComment() != null) {
                List<JSONObject> commentList = new ArrayList<JSONObject>();
                int commentSize = photoInfo.getComment().size();
                if (commentSize > 0) {
                    int commentLimitIn = 0;
//                    List<Comment> commentList = new ArrayList();
                    for (int j = commentSize; j > 0; j--) {
                        JSONObject commentJson = new JSONObject();
                        Comment comment = photoInfo.getComment().get(j - 1);
                        commentJson.put("commentId", comment.getCommentId());
                        commentJson.put("description", comment.getDescription());
                        commentJson.put("userInfo", comment.getUserInfo());
                        commentJson.put("createdOn", comment.getCreatedOn());
                        commentJson.put("userGenderId", userModel.getUserGenderInfo(comment.getUserInfo().getUserId()));
                        if (comment.getLike() != null) {
                            int commentLikeSize = comment.getLike().size();
                            if (commentLikeSize > 0) {
                                commentJson.put("commentlikeCounter", commentLikeSize);
                            }
                            int k = 0;
                            while (commentLikeSize > 0) {
                                String tempUserId = comment.getLike().get(k).getUserInfo().getUserId();
                                if (tempUserId.equals(userId)) {
                                    commentJson.put("CommentlikeStatus", PropertyProvider.get("YourLikeStatus"));
                                }
                                commentLikeSize--;
                                k++;
                            }
                        }
                        commentList.add(commentJson);
//                        commentList.add(comment);
                        commentLimitIn = commentLimitIn + 1;
                        if (commentLimitIn != commentLimit) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    if (commentSize > commentLimit) {
                        photoJson.put("commentCounter", commentSize - commentLimit);
                    }
                    photoJson.put("commentList", commentList);
                }
            }
            if (photoInfo.getShare() != null) {
                int shareSize = photoInfo.getShare().size();
                if (shareSize > 0) {
                    photoJson.put("shareCounter", shareSize);
                }
            }

            photoInfoList.add(photoJson);
        }
        return photoInfoList;
    }

    public ResultEvent addPhotoLikeByReferenceId(String referenceId, String likeInfo) {
        try {
            MongoCollection<PagePhotoDAO> mongoCollection = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEPHOTOS.toString(), PagePhotoDAO.class);
            String attrReferenceId = PropertyProvider.get("REFERENCE_ID");
            String attrLike = PropertyProvider.get("LIKE");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrReferenceId).is(referenceId).get();
            Like photoLikeInfo = Like.getLikeInfo(likeInfo);
            if (photoLikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document(attrLike, JSON.parse(photoLikeInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent addPhotoLike(String photoId, String likeInfo) {
        try {
            MongoCollection<PagePhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEPHOTOS.toString(), PagePhotoDAO.class
                    );
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
            Like photoLikeInfo = Like.getLikeInfo(likeInfo);
            if (photoLikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(photoLikeInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent addMPhotoLike(String userId, String photoId, String likeInfo) {
        try {
            MongoCollection<PagePhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEPHOTOS.toString(), PagePhotoDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
            Like photoLikeInfo = Like.getLikeInfo(likeInfo);
            if (photoLikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("modifiedOn", utility.getCurrentTime())));
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(photoLikeInfo.toString()))));
                UserInfo userInfo = photoLikeInfo.getUserInfo();
                notificationModel.addGeneralNotificationPagePhotoLike(userId, photoId, userInfo.toString());
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent getTimelinePhotos(String pageId) {
        try {
            MongoCollection<PagePhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEPHOTOS.toString(), PagePhotoDAO.class);
            Document selectionDocument = new Document();
            selectionDocument.put("pageId", pageId);
            selectionDocument.put("albumId", PropertyProvider.get("TIMELINE_PHOTOS_ALBUM_ID"));
            MongoCursor<PagePhotoDAO> photoList = mongoCollection.find(selectionDocument).iterator();
            List<PagePhotoDAO> photoInfoList = IteratorUtils.toList(photoList);
            this.getResultEvent().setResult(photoInfoList);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public List<PagePhotoDAO> getPhotos(String mappingId, String albumId) {
        MongoCollection<PagePhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PagePhotoDAO.class);
        Document selectDocument = new Document();
        selectDocument.put("pageId", mappingId);
        selectDocument.put("albumId", albumId);
        Document pQurey = new Document();
        pQurey.put("photoId", "$all");
        pQurey.put("albumId", "$all");
        pQurey.put("pageId", "$all");
        pQurey.put("image", "$all");
        MongoCursor<PagePhotoDAO> cursorStatusInfoList = mongoCollection.find(selectDocument).projection(pQurey).iterator();
        List<PagePhotoDAO> photoList = IteratorUtils.toList(cursorStatusInfoList);
        return photoList;

    }

    public JSONObject getSliderAlbum(String mappingId, String albumId, String userId) {
        JSONObject userStatusInfo = new JSONObject();
        try {
            MongoCollection<PagePhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEPHOTOS.toString(), PagePhotoDAO.class);
            Document selectionDocument = new Document();
            selectionDocument.put("albumId", albumId);
            selectionDocument.put("pageId", mappingId);
            MongoCursor<PagePhotoDAO> photoList = mongoCollection.find(selectionDocument).iterator();
            List<JSONObject> photoInfoList = getPhotoInfo(userId, photoList);
            userStatusInfo.put("photoList", photoInfoList);
            userStatusInfo.put("pageInfo", getPageInfo(mappingId));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return userStatusInfo;

    }
    
       /*
     * This method will add a photo comment , 
     * @param photoId, photo id
     * @param commentInfo, comment info
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent addPhotoCommentByReferenceId(String referenceId, String commentInfo) {
        try {
            MongoCollection<PagePhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEPHOTOS.toString(), PagePhotoDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("referenceId").is(referenceId).get();
            Comment photoCommentInfo = Comment.getCommentInfo(commentInfo);
            if (photoCommentInfo != null) {
                photoCommentInfo.setCreatedOn(utility.getCurrentTime());
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(photoCommentInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }
    
        public ResultEvent addPhotoComment(String photoId, String commentInfo) {
        try {
            MongoCollection<PagePhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEPHOTOS.toString(), PagePhotoDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
            Comment photoCommentInfo = Comment.getCommentInfo(commentInfo);
            if (photoCommentInfo != null) {
                photoCommentInfo.setCreatedOn(utility.getCurrentTime());
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(photoCommentInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
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
        memberinfo.setRelationTypeId(PropertyProvider.get("PAGE_MEMBER_STATUS_ID_INVITED"));

//        System.out.println(obj.leavePageMember("sKDuYWRygwssTdL", "1"));
//        System.out.println(obj.getInviteFriendList("sKDuYWRygwssTdL", "7OdqKzxmuakkpRq", 0, 10));
    }

    public Object addPageLike(String pageId, String memberInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
