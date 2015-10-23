package com.shampan.db.collections;

import com.shampan.db.collections.fragment.notification.*;
import java.util.List;

/**
 *
 * @author nazmul hasan
 */
public class NotificationDAO {
    private String _id;
    private String userId;
    private List<FriendNotification> friendNotifications;
    private List<MessageNotification> messageNotifications;
    private List<GeneralNotification> generalNotifications;
}
