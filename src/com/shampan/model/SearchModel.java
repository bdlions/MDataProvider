/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.client.MongoCollection;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.fragment.status.UserInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class SearchModel {

    public SearchModel() {

    }

    public String getUsers(String statusInfo) {

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setFristName("Alamgir");
        userInfo1.setLastName("Kabir");
        userInfo1.setUserId("2");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setFristName("Nazmul");
        userInfo2.setLastName("Hasan");
        userInfo2.setUserId("1");

        UserInfo userInfo3 = new UserInfo();
        userInfo3.setFristName("Rashida");
        userInfo3.setLastName("Sultana");
        userInfo3.setUserId("3");

        UserInfo userInfo4 = new UserInfo();
        userInfo4.setFristName("Ridoy");
        userInfo4.setLastName("Khsn");
        userInfo4.setUserId("4");

        UserInfo userInfo5 = new UserInfo();
        userInfo5.setFristName("Salma");
        userInfo5.setLastName("Khatun");
        userInfo5.setUserId("5");

        List<UserInfo> userList = new ArrayList<UserInfo>();
        userList.add(userInfo2);
        userList.add(userInfo1);
        userList.add(userInfo3);
        userList.add(userInfo4);
        userList.add(userInfo5);

        return userList.toString();

    }

    public static void main(String[] args) {
        DBConnection.getInstance().getConnection();
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setFristName("Alamgir");
        userInfo1.setLastName("Kabir");
        userInfo1.setUserId("1");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setFristName("Nazmul");
        userInfo2.setLastName("Hasan");
        userInfo2.setUserId("2");

        UserInfo userInfo3 = new UserInfo();
        userInfo3.setFristName("Rashida");
        userInfo3.setLastName("Sultana");
        userInfo3.setUserId("3");

        UserInfo userInfo4 = new UserInfo();
        userInfo4.setFristName("Ridoy");
        userInfo4.setLastName("Khsn");
        userInfo4.setUserId("4");

        UserInfo userInfo5 = new UserInfo();
        userInfo5.setFristName("Salma");
        userInfo5.setLastName("Khatun");
        userInfo5.setUserId("5");

        List<UserInfo> userList = new ArrayList<UserInfo>();
        userList.add(userInfo1);
        userList.add(userInfo2);
        userList.add(userInfo3);

        System.out.println(userList.toString());

    }
}
