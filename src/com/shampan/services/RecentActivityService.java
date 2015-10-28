package com.shampan.services;

import com.shampan.model.RecentActivityModel;

/**
 *
 * @author nazmul hasan
 */
public class RecentActivityService {
    private static RecentActivityModel recentActivityModel = new RecentActivityModel();
    public RecentActivityService()
    {
    
    }
    /**
     * This method will return recent activities of a user
     * @return string, recent activities
     */
    public static String getRecentActivities()
    {
        recentActivityModel.getRecentActivities();
        return "";
    }
}
