package com.shampan.util;

import com.sampan.response.ResultEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;

/**
 *
 * @author nazmul hasan
 */
public class Utility {

    ResultEvent resultEvent = new ResultEvent();

    public Utility() {
        PropertyProvider.add("com.shampan.properties/attributes");
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
     * This method will return current gmt 0 time stamp
     *
     * @return string, system time in seconds
     */
    public int getCurrentTime(){
        long unixTime = System.currentTimeMillis();
        Date date = new Date(unixTime);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String currentDate = sDateFormat.format(date);
        int gmt0TimeStamp = (int) (unixTime / 1000L);
        try {
            gmt0TimeStamp = (int)(sDateFormat.parse(currentDate).getTime() / 1000L);
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return gmt0TimeStamp;
    }

    /**
     * This method will return user current date timestamp and time from user
     * GMT offset
     *
     * @param gmtOffset user time zone offset
     * @return string, current date
     */
    public long getUserCurrentDateUnixTime(String gmtOffset) {
        String timeZone = "GMT" + gmtOffset;
        long unixSeconds = System.currentTimeMillis();
        Date date = new Date(unixSeconds);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        String currentDate = sDateFormat.format(date);
        long currentDateTime = 0;
        try {
            currentDateTime = sDateFormat.parse(currentDate).getTime()/1000L;
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return currentDateTime;
    }

    /**
     * This method will return current date and time from user GMT offset
     *
     * @param gmtOffset user time zone offset
     * @return string, current date
     */
    public String getUserCurrentDate(String gmtOffset) {
        String timeZone = "GMT" + gmtOffset;
        long unixSeconds = System.currentTimeMillis();
        Date date = new Date(unixSeconds);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        String currentDate = sDateFormat.format(date);
        return currentDate;
    }

    /**
     * This method will return human readable date and time from user GMT offset
     *
     * @ unixTimeStampSeconds unix time in seconds
     * @param offset user time zone offset
     * @return string, current date
     */
    public String getUnixToHumanFomattedDate(int unixTimeStampSeconds, String gmtOffset) {
        String timeZone = "GMT" + gmtOffset;
        long unixTimeMilis = (long) unixTimeStampSeconds;
        Date date = new Date(unixTimeMilis);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        String formattedDate = sDateFormat.format(date);
        return formattedDate;
    }

    /**
     * This method will return user user current time to gmt 0 time stamp GMT
     * offset
     *
     * @param gmtOffset user time zone offset
     * @return string, current user gmt 0 time stamp
     */
    public long getUserCurrentTimeToGmt0TimeStamp(String gmtOffset) {
        long currentDateTime = getUserCurrentDateUnixTime(gmtOffset);
        Date mgtDate = new Date(currentDateTime);
        SimpleDateFormat gmtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String gmtDate = gmtFormat.format(mgtDate);
        long gmt0Time = 0;
        try {
            gmt0Time = gmtFormat.parse(gmtDate).getTime();
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return gmt0Time;
    }

    /**
     * This method will return gmt and time from user GMT offset
     *
     * @ unixTimeStampSeconds unix time in seconds
     * @param offset user time zone offset
     * @return string, current date
     */
    public long gmtTimeStampToUserTimeStamp(long unixTimeStampSeconds, String gmtOffset) {
        String timeZone = "GMT" + gmtOffset;
        long unixTimeMilis = unixTimeStampSeconds;
        Date date = new Date(unixTimeMilis);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        String formattedDate = sDateFormat.format(date);
        long formattedDateTime = 0;
        try {
            formattedDateTime = sDateFormat.parse(formattedDate).getTime();
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return formattedDateTime;
    }

    /**
     * This method will return human readable dates and times from unix time
     * list
     *
     * @param unixTimeList system time in seconds List
     * @return string, human readable dates and times
     */
//    public String convertUnixTimeToUserTime(String unixTimeList) {
//        DateFormat formatter = new SimpleDateFormat(" MMM d, yyyy - HH.mm a");
//        Calendar calendar = Calendar.getInstance();
//        JSONArray unixTimeArray = new JSONArray(unixTimeList.toString());
//        int unixTimeArraySize = unixTimeArray.length();
//        List<String> formatedDate = new ArrayList<>();
//        if (unixTimeArraySize > 0) {
//            for (int i = 0; i < unixTimeArraySize; i++) {
//                calendar.setTimeInMillis(unixTimeArray.optLong(i) * 1000);
//                String formattedDate = formatter.format(calendar.getTime());
//                formatedDate.add(formattedDate);
//            }
//        }
//        return formatedDate.toString();
//    }
}
