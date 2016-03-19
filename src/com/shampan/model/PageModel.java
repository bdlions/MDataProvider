/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.PageCategoryDAO;
import com.shampan.db.collections.PageDAO;
import com.shampan.db.collections.PageMemberDAO;
import com.shampan.db.collections.PageSubCategoryDAO;
import com.shampan.db.collections.builder.PageDAOBuilder;
import com.shampan.db.collections.fragment.page.MemberInfo;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;
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
                updatedDocument.put("interestedAgeRange", pageInformation.getInterestedAgeRange());
                updatedDocument.put("intertestedGender", pageInformation.getIntertestedGender());
                updatedDocument.put("referenceInfo", pageInformation.getReferenceInfo());
                mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updatedDocument));
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent getPageInfo(String pageId) {
        try {
            MongoCollection<PageDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGES.toString(), PageDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            PageDAO pageInfo = mongoCollection.find(selectDocument).first();
            if (pageInfo != null) {
                this.getResultEvent().setResult(pageInfo);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent getMemeberCounter(String pageId) {
        try {
            MongoCollection<PageMemberDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PAGEMEMBERS.toString(), PageMemberDAO.class);
            Document selectDocument = new Document();
            selectDocument.put("pageId", pageId);
            PageMemberDAO pageInfo = mongoCollection.find(selectDocument).first();
            if (pageInfo != null) {
                int members = pageInfo.getMemberList().size();
                this.getResultEvent().setResult(members);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public ResultEvent InviteMember(String pageId, String memberInfo) {
        try {
            addPageMember(pageId, memberInfo);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
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
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
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

}
