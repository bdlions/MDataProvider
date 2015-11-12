package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.StatusDAO;
import com.shampan.db.collections.builder.StatusDAOBuilder;
import com.shampan.db.collections.fragment.status.Comment;
import com.shampan.db.collections.fragment.status.Like;
import com.shampan.db.collections.fragment.status.ReferenceInfo;
import com.shampan.db.collections.fragment.status.ReferenceList;
import com.shampan.db.collections.fragment.status.Share;
import com.shampan.db.collections.fragment.status.UserInfo;
import com.shampan.util.LogWriter;
import com.shampan.util.PropertyProvider;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;
import org.json.JSONObject;

/**
 *
 * @author nazmul
 */
public class StatusModel {

    ResultEvent resultEvent = new ResultEvent();

    NotificationModel notificationModel = new NotificationModel();
    RelationModel relationModel = new RelationModel();
    UserModel userModel = new UserModel();

    public StatusModel() {
        PropertyProvider.add("response");
        PropertyProvider.add("com.shampan.properties/common");
        PropertyProvider.add("com.shampan.properties/attributes");
        PropertyProvider.add("com.shampan.properties/relations");

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

    /**
     * *
     * Add a status parameter Status Info
     *
     * @param statusInfo, status information
     * @author created by Rashida on 15 October
     */
    public String addStatus(String statusInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        StatusDAO statusInfoObj = new StatusDAOBuilder().build(statusInfo);
        try {
            if (statusInfoObj != null) {
                mongoCollection.insertOne(statusInfoObj);
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }
        resultEvent.setResponseCode(PropertyProvider.get("success"));
        return resultEvent.toString();
    }

    /**
     * *
     * this method will return all status of a user newsfeed
     *
     * @param userId, user Id
     * @param offset, offset
     * @param limit, limit
     * @author created by Rashida on 15 October
     */
    public List<JSONObject> getStatuses(String userId, int offset, int limit) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        String relationTypeId = PropertyProvider.get("RELATION_TYPE_FRIEND_ID");
        String attrUserId = PropertyProvider.get("USER_ID");
        //add user own to status selection list
        List<Document> orSelectionDocument = new ArrayList<Document>();
        Document userDocument = new Document();
        userDocument.put(attrUserId, userId);
        orSelectionDocument.add(userDocument);
        //add user friend to status selection list
        List<String> userIdList = relationModel.getUserIdList(userId, relationTypeId);
        int userIdsSize = userIdList.size();
        if (userIdsSize > 0) {
            for (int i = 0; i < userIdsSize; i++) {
                Document userSelectionDocument = new Document();
                userSelectionDocument.put(attrUserId, userIdList.get(i));
                orSelectionDocument.add(userSelectionDocument);
            }
        }
        Document selectDocument = new Document();
        selectDocument.put("$or", orSelectionDocument);
        MongoCursor<StatusDAO> statusList = mongoCollection.find(selectDocument).skip(offset).limit(limit).iterator();
        List<JSONObject> statusInfoList = getStatusInfo(userId, statusList);
        return statusInfoList;
    }

    /**
     * *
     * this method will return all status of a user profile
     *
     * @param userId, user Id
     * @author created by Rashida on 9th Nov
     */
    public List<JSONObject> getUserProfileStatuses(String userId, String mappingId, int offset, int limit) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        Document selectdocument = new Document();
        selectdocument.put("mappingId", mappingId);
        MongoCursor<StatusDAO> statusList = mongoCollection.find(selectdocument).skip(offset).limit(limit).iterator();
        List<JSONObject> statusInfoList = getStatusInfo(userId, statusList);
        return statusInfoList;
    }

    /**
     * *
     * this method will return all status of a user profile
     *
     * @param userId, user Id
     * @param statusId, status Id
     * @author created by Rashida on 9th Nov
     */
    public List<JSONObject> getStatusDetails(String userId, String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        MongoCursor<StatusDAO> status = mongoCollection.find(selectQuery).limit(1).iterator();
        List<JSONObject> statusInfo = getStatusInfo(userId, status);
        return statusInfo;
    }

    /**
     * *
     * this method will return all status needed information
     *
     * @param statusList, status List
     * @author created by Rashida on 9th Nov
     */
    public List<JSONObject> getStatusInfo(String userId, MongoCursor<StatusDAO> statusList) {
        List<JSONObject> statusInfoList = new ArrayList<JSONObject>();
        while (statusList.hasNext()) {
            JSONObject statusJson = new JSONObject();
            StatusDAO status = (StatusDAO) statusList.next();
            statusJson.put("statusId", status.getStatusId());
            statusJson.put("userInfo", status.getUserInfo());
            statusJson.put("description", status.getDescription());
            statusJson.put("statusTypeId", status.getStatusTypeId());
            if (status.getImages() != null) {
                statusJson.put("images", status.getImages());
            }
            if (status.getLike() != null) {
                int likeSize = status.getLike().size();
                if (likeSize > 0) {
                    statusJson.put("likeCounter", likeSize);
                }
                int i = 0;
                while (likeSize > 0) {
                    String tempUserId = status.getLike().get(i).getUserInfo().getUserId();
                    if (tempUserId.equals(userId)) {
                        statusJson.put("likeStatus", PropertyProvider.get("YourLikeStatus"));
                    }
                    likeSize--;
                    i++;
                }

            }
            if (status.getComment() != null) {
                int commentSize = status.getComment().size();
                List<Comment> commentList = new ArrayList();
                 if (commentSize >= 2) {
                    Comment secondlastComment = status.getComment().get(commentSize - 2);
                    commentList.add(secondlastComment);
                }
                if (commentSize >= 1) {
                    Comment lastComment = status.getComment().get(commentSize - 1);
                    commentList.add(lastComment);
                }
                if (commentSize > 2) {
                    statusJson.put("commentCounter", commentSize - 2);
                }
                statusJson.put("commentList", commentList);
                System.out.println(commentList);
            }
            if (status.getShare() != null) {
                int shareSize = status.getShare().size();
                if (shareSize > 0) {
                    statusJson.put("shareCounter", shareSize);
                }
            }
            if (status.getReferenceInfo() != null) {
                statusJson.put("referenceInfo", status.getReferenceInfo());
            }
            if (!status.getUserId().equals(status.getMappingId())) {
                statusJson.put("mappingUserInfo", userModel.getUserInfo(status.getMappingId()));
            }
            statusInfoList.add(statusJson);
        }
        return statusInfoList;
    }

    /**
     * *
     * Delete a status parameter StatusId status id
     *
     * @param statusId, status Id
     * @author created by Rashida on 15 October
     */
    public String deleteStatus(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        mongoCollection.findOneAndDelete(selectQuery);
        resultEvent.setResponseCode(PropertyProvider.get("success"));
        return resultEvent.toString();

    }

    /**
     *
     * Update status parameter statusId and Status Description
     *
     * @param statusId, status Id
     * @param statusInfo, status description
     * @author created by Rashida on 15 October
     *
     */
    public String updateStatus(String statusId, String statusInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("description", statusInfo)));
        resultEvent.setResponseCode(PropertyProvider.get("success"));
        return resultEvent.toString();
    }

    /**
     * add Status like
     *
     * @param statusId, status id
     * @param likeInfo, status like user info
     */
    public ResultEvent addStatusLike(String userId, String statusId, String likeInfo) {
        try {
            MongoCollection<StatusDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
            String attrStatusId = PropertyProvider.get("STATUS_ID");
            String attrlike = PropertyProvider.get("LIKE");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrStatusId).is(statusId).get();
            Like statusLikeInfo = Like.getStatusLike(likeInfo);
            if (statusLikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document(attrlike, JSON.parse(statusLikeInfo.toString()))));
                UserInfo userInfo = statusLikeInfo.getUserInfo();
                notificationModel.addGeneralNotificationStatusLike(userId, statusId, userInfo.toString());
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * Add Status Comment
     *
     * @param statusId, status id
     * @param commentInfo, status comment and user info
     *
     */
    public String addStatusComment(String userId, String statusId, String commentInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Comment statusCommentInfo = Comment.getStatusComment(commentInfo);
        try {
            if (statusCommentInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(statusCommentInfo.toString()))));
                UserInfo userInfo = statusCommentInfo.getUserInfo();
                notificationModel.addGeneralNotificationStatusComment(userId, statusId, userInfo.toString());
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }
        resultEvent.setResponseCode(PropertyProvider.get("success"));
        return resultEvent.toString();
    }

    /**
     * share Status
     *
     * @param statusId, status id
     * @param refUserInfo, reference info
     * @param shareInfo, new status info
     *
     */
    public String shareStatus(String userId, String statusId, String refUserInfo, String shareInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Share statusRefInfo = Share.getStatusShare(refUserInfo);
        try {
            if (statusRefInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("share", JSON.parse(statusRefInfo.toString()))));

            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }
        if (shareInfo != null) {
            addStatus(shareInfo);
            StatusDAO statusInfoObj = new StatusDAOBuilder().build(shareInfo);
            if (statusInfoObj != null) {
                UserInfo userInfo = statusInfoObj.getUserInfo();
                notificationModel.addGeneralNotificationStatusShare(userId, statusId, userInfo.toString());
            }

        }
        resultEvent.setResponseCode(PropertyProvider.get("success"));
        return resultEvent.toString();
    }

    /**
     * Status like list
     *
     * @param statusId, status id
     *
     */
    public String getStatusLikeList(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Document pQuery = new Document();
        pQuery.put("like", "$all");
        StatusDAO albumLikeList = mongoCollection.find(selectQuery).projection(pQuery).first();
        return albumLikeList.toString();
    }

    /**
     * Status comment list
     *
     * @param statusId, status id
     *
     */
    public String getStatusComments(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Document pQuery = new Document();
        pQuery.put("comment", "$all");
        StatusDAO albumCommentList = mongoCollection.find(selectQuery).projection(pQuery).first();
        return albumCommentList.toString();
    }

    /**
     * Status share list
     *
     * @param statusId, status id
     *
     */
    public String getStatusShareList(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Document pQuery = new Document();
        pQuery.put("share", "$all");
        StatusDAO albumCommentList = mongoCollection.find(selectQuery).projection(pQuery).first();
        if (albumCommentList != null) {
            return albumCommentList.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("unsuccess"));
            return resultEvent.toString();
        }
    }

    public String updateStatusPrivacy(String statusId, String privacyInfo) {

        return "Successful";
    }

}
