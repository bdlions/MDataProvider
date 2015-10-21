package com.shampan.util;

/**
 *
 * @author nazmul hasan
 */
public class Utility {
    public Utility()
    {
    
    }
    
    /**
     * This method will return current time in unix format since Jan 1, 1970
     * @return string, system time in seconds
    */
    public String getCurrentTime()
    {
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime + "";
    }
}
