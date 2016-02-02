/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.shampan.db.collections.UserDAO;
import com.shampan.model.UserModel;
import com.shampan.util.Utility;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Sampan IT
 */
public class UserServicesTest {

    UserModel userModel = new UserModel();
    String userId1 = "olYWzdinSllwTtu";
    String userId2 = "mCQntzqOGFQrOTE";
    String userId3 = "g59uk60UraL3Guf";

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
//       @Test 

    public void getUserGenderInfo() {

        System.out.println(userModel.getUserGenderInfo(userId1));

    }
//       @Test 

    public void emailAndUserNameCheck() {

        System.out.println(userModel.emailAndUserNameCheck("keya@gmail1.com", "rashida sultana"));

    }
       @Test 

    public void getUSerInfoByEmail() {

        System.out.println(userModel.getUSerInfoByEmail("salma@gmail.com"));

    }
    
    
//       @Test 

    public void getRecentUser() {
        int offset = 0;
        int limit = 10;
        System.out.println(userModel.getRecentUser());

    }
//    @Test 
    public void getUserInfoByUserId() {
       
        System.out.println(userModel.getUserInfoByUserId(userId1));

    }

//        @Test
    public void getUserCountryInfo() {
        Utility utility = new Utility();
        UserDAO userInfo = userModel.getUserCountryInfo(userId1);
        System.out.println(userInfo);
        String gmtOffset = "";
        if (userInfo.getCountry() != null) {
            gmtOffset = userInfo.getCountry().getGmtOffset();
        }
        System.out.println(gmtOffset);
        long createdOn = 1447916587;
//        String statusDate = utility.getUnixToHumanFomattedDate(createdOn, gmtOffset);
//        System.out.println(statusDate);
    }

//    @Test
    public void timeTest() throws ParseException {
        Utility utility = new Utility();
        String gmt0Time = utility.getUserCurrentDate("+0");
        String gmt0Time1 = utility.getUserCurrentDate("+6");
        System.out.println("gmt0TimefordatabaseStor=" + gmt0Time);
        System.out.println("gmt0TimefordatabaseStor=" + gmt0Time1);
//        long userCurrentDateUnixTime = utility.getUserCurrentDateUnixTime("+06");
//        System.out.println("userCurrentDateUnixTime" + userCurrentDateUnixTime);
//        String userCurrentDate = utility.getUserCurrentDate("+11");
//        System.out.println("user Current Date=" + userCurrentDate);
//        String humanDateFromTime = utility.getUnixToHumanFomattedDate(gmt0Time, "+6");
//        System.out.println("Time stamp to human date=" + humanDateFromTime);
        long userCurrentTimeToGmt0TimeStamp = utility.getUserCurrentTimeToGmt0TimeStamp("+6");
        long userCurrentTimeToGmt0TimeStamp1 = utility.getUserCurrentTimeToGmt0TimeStamp("+0");
        long userCurrentTimeToGmt0TimeStamp2 = utility.getUserCurrentTimeToGmt0TimeStamp("+11");
        System.out.println("userCurrentTimeToGmt0TimeStamp" + userCurrentTimeToGmt0TimeStamp);
        System.out.println("userCurrentTimeToGmt0TimeStamp" + userCurrentTimeToGmt0TimeStamp1);
        System.out.println("userCurrentTimeToGmt0TimeStamp" + userCurrentTimeToGmt0TimeStamp2);
//        long gmtTimeStampToUserTimeStamp = utility.gmtTimeStampToUserTimeStamp(userCurrentDateUnixTime, "+06");
//        System.out.println(gmtTimeStampToUserTimeStamp);

    }

}
