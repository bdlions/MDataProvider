package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.StatusDAO;
import com.shampan.db.collections.builder.StatusDAOBuilder;
import com.shampan.db.collections.fragment.status.ReferenceInfo;
import com.shampan.db.collections.fragment.status.ReferenceList;
import com.shampan.db.collections.fragment.status.UserInfo;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author nazmul
 */
public class StatusModel {

    public StatusModel() {

    }

    public String addStatus(String statusInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        StatusDAO statusInfoObj = new StatusDAOBuilder().build(statusInfo);
        mongoCollection.insertOne(statusInfoObj);
        return "successful";
    }

    public String getStatus(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        StatusDAO statusInfo = mongoCollection.find(selectQuery).first();
        return statusInfo.toString();

    }

    public String deleteStatus(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        Document selectQuary = new Document();
        selectQuary.put("statusId", statusId);
        mongoCollection.findOneAndDelete(selectQuary);
        return "successful";

    }

    public static void main(String[] args) {
        DBConnection.getInstance().getConnection();
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        String userId = "100157";
        String statusId = "1";
        UserInfo userInfo = new UserInfo();
        userInfo.setFristName("Alamgir");
        userInfo.setLastName("Kabir");
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFristName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("100105");
        ReferenceInfo referenceInfo = new ReferenceInfo();
        referenceInfo.setDescription("I Like this Invention");
        referenceInfo.setImg("shemin.jpg");
        referenceInfo.setUserInfo(rUserInfo);

        ReferenceList refStatusIdList = new ReferenceList();
        refStatusIdList.setStatusId(statusId);
        List<ReferenceList> refStatusList = new ArrayList<ReferenceList>();
        refStatusList.add(refStatusIdList);

        StatusDAO satusInfo = new StatusDAOBuilder()
                .setStatusId(statusId)
                .setUserId(userId)
                .setUserInfo(userInfo)
                .setMappingId(userId)
                .setDescription("this is a wounderful invention By scientist Shemin of NASA")
                .setStatusTypeId("1")
                .setReferenceInfo(referenceInfo)
                .setReferenceList(refStatusList)
                .build();
//        System.out.println("test" + satusInfo.toString());
//        Document projectionQuary = new Document();
//        projectionQuary.put("statusId", statusId);
//        mongoCollection.findOneAndDelete(projectionQuary);
        StatusModel ob = new StatusModel();
        System.out.println(ob.deleteStatus(statusId));
//        System.out.println(ob.getStatus(statusId));
//        ob.addStatus(satusInfo.toString());
    }

}
