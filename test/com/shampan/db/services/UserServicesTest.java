/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.shampan.model.UserModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Sampan IT
 */
public class UserServicesTest {

    UserModel userModel = new UserModel();
    String userId1 = "55Lj6k4iZReT4ck";
    String userId2 = "mqQ06eko9TqYYul";
    String userId3 = "9nSEiMgzieo1O4K";

//    @Test
    public void getUserInfo() {
        System.out.println(userModel.getUserInfo(userId1));
    }

//    @Test
    public void getUserInfoList() {

        List<String> userIdList = new ArrayList<>();
        userIdList.add(userId1);
        userIdList.add(userId2);
        userIdList.add(userId3);
        System.out.println(userModel.getUserInfoList(userIdList.toString()));

    }

}
