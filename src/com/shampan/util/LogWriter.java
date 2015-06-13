/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.util;

import org.apache.log4j.Logger;

/**
 *
 * @author alamgir
 */
public class LogWriter {
    private static final Logger debugLog;
    private static final Logger errorLog;
    
    static{
        debugLog = Logger.getLogger("debugLogger");
        errorLog = Logger.getLogger("errorLogger");
    }
    
    public static Logger getDebugLog(){
        return debugLog;
    }
    
    public static Logger getErrorLog(){
        return errorLog;
    }
    
//    public static void debug(String message){
//        debugLog.debug(message);
//    }
//    
//    public static void error(String message){
//        errorLog.error(message);
//    }
}
