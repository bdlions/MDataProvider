package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.StatusDAO;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.builder.StatusDAOBuilder;
import com.shampan.db.collections.fragment.status.Comment;
import com.shampan.db.collections.fragment.status.Like;
import com.shampan.db.collections.fragment.status.Share;
import com.shampan.db.collections.fragment.status.UserInfo;
import com.shampan.util.LogWriter;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.json.JSONObject;

/**
 *
 * @author nazmul
 */
public class StatusModel {

    ResultEvent resultEvent = new ResultEvent();
    Utility utility = new Utility();
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
    public ResultEvent addStatus(String statusInfo) {
        try {
            MongoCollection<StatusDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
            StatusDAO statusInfoObj = new StatusDAOBuilder().build(statusInfo);
            if (statusInfoObj != null) {
                statusInfoObj.setCreatedOn(utility.getCurrentTime());
                statusInfoObj.setModifiedOn(utility.getCurrentTime());
                mongoCollection.insertOne(statusInfoObj);
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
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
    public String getStatuses(String userId, int offset, int limit) {
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
        MongoCursor<StatusDAO> statusList = mongoCollection.find(selectDocument).sort(new Document("modifiedOn", -1)).skip(offset).limit(limit).iterator();
        List<JSONObject> statusInfoList = getStatusInfo(userId, statusList);
        JSONObject userStatusInfo = new JSONObject();
        userStatusInfo.put("statusInfoList", statusInfoList);
        userStatusInfo.put("userCurrentTime", utility.getCurrentTime());
        return userStatusInfo.toString();
    }

    /**
     * *
     * this method will return all status of a user profile
     *
     * @param userId, user Id
     * @author created by Rashida on 9th Nov
     */
    public String getUserProfileStatuses(String userId, String mappingId, int offset, int limit) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        String attrMappingId = PropertyProvider.get("MAPPING_ID");
        Document selectDocument = new Document();
        selectDocument.put(attrMappingId, mappingId);
        MongoCursor<StatusDAO> statusList = mongoCollection.find(selectDocument).sort(new Document("modifiedOn", -1)).skip(offset).limit(limit).iterator();
        List<JSONObject> statusInfoList = getStatusInfo(userId, statusList);
        JSONObject userStatusInfo = new JSONObject();
        userStatusInfo.put("statusInfoList", statusInfoList);
        userStatusInfo.put("userCurrentTime", utility.getCurrentTime());
        return userStatusInfo.toString();
    }

    /**
     * *
     * this method will return all status of a user profile
     *
     * @param userId, user Id
     * @param statusId, status Id
     * @author created by Rashida on 9th Nov
     */
    public String getStatusDetails(String userId, String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        String attrStatusId = PropertyProvider.get("STATUS_ID");
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrStatusId).is(statusId).get();
        MongoCursor<StatusDAO> status = mongoCollection.find(selectQuery).limit(1).iterator();
        List<JSONObject> statusInfoList = getStatusInfo(userId, status);
        JSONObject userStatusInfo = new JSONObject();
        userStatusInfo.put("statusInfoList", statusInfoList);
        userStatusInfo.put("userCurrentTime", utility.getCurrentTime());
        return userStatusInfo.toString();
    }

    /**
     * *
     * this method will return all status needed information
     *
     * @param statusList, status List
     * @author created by Rashida on 9th Nov
     */
    public List<JSONObject> getStatusInfo(String userId, MongoCursor<StatusDAO> statusList) {
        int commentLimit = 2;
        List<JSONObject> statusInfoList = new ArrayList<JSONObject>();

        while (statusList.hasNext()) {
            JSONObject statusJson = new JSONObject();
            StatusDAO status = (StatusDAO) statusList.next();
            statusJson.put("statusId", status.getStatusId());
            statusJson.put("userInfo", status.getUserInfo());
            statusJson.put("description", status.getDescription());
            statusJson.put("statusTypeId", status.getStatusTypeId());
            statusJson.put("createdOn", status.getCreatedOn());
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
                List<JSONObject> commentList = new ArrayList<JSONObject>();
                int commentSize = status.getComment().size();
                if (commentSize > 0) {
                    int commentLimitIn = 0;
//                    List<Comment> commentList = new ArrayList();
                    for (int j = commentSize; j > 0; j--) {
                        JSONObject commentJson = new JSONObject();
                        Comment comment = status.getComment().get(j - 1);
                        commentJson.put("commentId", comment.getCommentId());
                        commentJson.put("description", comment.getDescription());
                        commentJson.put("createdOn", comment.getCreatedOn());
                        commentJson.put("userInfo", comment.getUserInfo());
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
                        statusJson.put("commentCounter", commentSize - commentLimit);
                    }
                    statusJson.put("commentList", commentList);
                }
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
            if (status.getMappingId() != null) {
                if (!status.getUserId().equals(status.getMappingId())) {
                    statusJson.put("mappingUserInfo", userModel.getUserInfo(status.getMappingId()));
                }
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
    public ResultEvent deleteStatus(String statusId) {
        try {
            MongoCollection<StatusDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
            String attrStatusId = PropertyProvider.get("STATUS_ID");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrStatusId).is(statusId).get();
            mongoCollection.findOneAndDelete(selectQuery);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

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
    public ResultEvent updateStatus(String statusId, String statusInfo) {
        try {
            MongoCollection<StatusDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
            String attrStatusId = PropertyProvider.get("STATUS_ID");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrStatusId).is(statusId).get();
            Document updatedDocument = new Document();
            updatedDocument.put("description", statusInfo);
            updatedDocument.put("modifiedOn", utility.getCurrentTime());
            mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", updatedDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
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
     * add Status like
     *
     * @param statusId, status id
     * @param likeInfo, status like user info
     */
    public ResultEvent addStatusCommentLike(String statusId, String commentId, String likeInfo) {
        try {
            MongoCollection<StatusDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
            String attrStatusId = PropertyProvider.get("STATUS_ID");
            String attrlike = PropertyProvider.get("LIKE");
            String attrComment = PropertyProvider.get("COMMENT");
            String attrCommentId = PropertyProvider.get("COMMENT_ID");
            Document selectionDocument = new Document();
            selectionDocument.put(attrStatusId, statusId);
            selectionDocument.put(attrComment + "." + attrCommentId, commentId);
            Like statusLikeInfo = Like.getStatusLike(likeInfo);
            if (statusLikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectionDocument, new Document("$push", new Document("comment.$.like", JSON.parse(statusLikeInfo.toString()))));
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
    public ResultEvent addStatusComment(String referenceUserInfo, String statusId, String commentInfo) {
        try {
            MongoCollection<StatusDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
            String attrComment = PropertyProvider.get("COMMENT");
            String attrStatusId = PropertyProvider.get("STATUS_ID");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrStatusId).is(statusId).get();
            Comment statusCommentInfo = Comment.getStatusComment(commentInfo);

            if (statusCommentInfo != null) {
                statusCommentInfo.setCreatedOn(utility.getCurrentTime());
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document(attrComment, JSON.parse(statusCommentInfo.toString()))));
                UserInfo userInfo = statusCommentInfo.getUserInfo();
                notificationModel.addGeneralNotificationStatusComment(referenceUserInfo, statusId, userInfo.toString());
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * share Status
     *
     * @param statusId, status id
     * @param refUserInfo, reference info
     * @param shareInfo, new status info
     *
     */
    public ResultEvent shareStatus(String userId, String statusId, String refUserInfo, String shareInfo) {
        try {
            MongoCollection<StatusDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
            String attrStatusId = PropertyProvider.get("STATUS_ID");
            String attrShare = PropertyProvider.get("SHARE");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrStatusId).is(statusId).get();
            Share statusRefInfo = Share.getStatusShare(refUserInfo);

            if (statusRefInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document(attrShare, JSON.parse(statusRefInfo.toString()))));

            }
            if (shareInfo != null) {
                addStatus(shareInfo);
                StatusDAO statusInfoObj = new StatusDAOBuilder().build(shareInfo);
                if (statusInfoObj != null) {
                    UserInfo userInfo = statusInfoObj.getUserInfo();
                    notificationModel.addGeneralNotificationStatusShare(userId, statusId, userInfo.toString());
                }

            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /**
     * Status like list
     *
     * @param statusId, status id
     *
     */
    public StatusDAO getStatusLikeList(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        String attrStatusId = PropertyProvider.get("STATUS_ID");
        String attrLike = PropertyProvider.get("LIKE");
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrStatusId).is(statusId).get();
        Document pQuery = new Document();
        pQuery.put(attrLike, "$all");
        StatusDAO albumLikeList = mongoCollection.find(selectQuery).projection(pQuery).first();
        return albumLikeList;
    }

    /**
     * Status comment list
     *
     * @param statusId, status id
     *
     */
    public List<JSONObject> getStatusComments(String userId, String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        String attrStatusId = PropertyProvider.get("STATUS_ID");
        String attrComment = PropertyProvider.get("COMMENT");
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrStatusId).is(statusId).get();
        Document pQuery = new Document();
        pQuery.put(attrComment, "$all");
        StatusDAO status = mongoCollection.find(selectQuery).projection(pQuery).first();
        List<JSONObject> commentList = new ArrayList<JSONObject>();
        if (status.getComment() != null) {
            int commentSize = status.getComment().size();
            if (commentSize > 0) {
                for (int j = commentSize; j > 0; j--) {
                    JSONObject commentJson = new JSONObject();
                    Comment comment = status.getComment().get(j - 1);
                    commentJson.put("commentId", comment.getCommentId());
                    commentJson.put("description", comment.getDescription());
                    commentJson.put("createdOn", comment.getCreatedOn());
                    commentJson.put("userInfo", comment.getUserInfo());
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
                }
            }
        }
        return commentList;
    }

    /**
     * Status share list
     *
     * @param statusId, status id
     *
     */
    public StatusDAO getStatusShareList(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        String attrStatusId = PropertyProvider.get("STATUS_ID");
        String attrShare = PropertyProvider.get("SHARE");
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrStatusId).is(statusId).get();
        Document pQuery = new Document();
        pQuery.put(attrShare, "$all");
        StatusDAO albumCommentList = mongoCollection.find(selectQuery).projection(pQuery).first();
        return albumCommentList;
    }

    public String updateStatusPrivacy(String statusId, String privacyInfo) {

        return "Successful";
    }

}
