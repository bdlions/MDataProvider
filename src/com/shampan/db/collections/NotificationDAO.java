package com.shampan.db.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.notification.*;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/**
 *
 * @author nazmul hasan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDAO implements Bson {

    private String _id;
    private String userId;
    private List<FriendNotification> friendNotifications;
    private List<MessageNotification> messageNotifications;
    private List<GeneralNotification> generalNotifications;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<FriendNotification> getFriendNotifications() {
        return friendNotifications;
    }

    public void setFriendNotifications(List<FriendNotification> friendNotifications) {
        this.friendNotifications = friendNotifications;
    }

    public List<MessageNotification> getMessageNotifications() {
        return messageNotifications;
    }

    public void setMessageNotifications(List<MessageNotification> messageNotifications) {
        this.messageNotifications = messageNotifications;
    }

    public List<GeneralNotification> getGeneralNotifications() {
        return generalNotifications;
    }

    public void setGeneralNotifications(List<GeneralNotification> generalNotifications) {
        this.generalNotifications = generalNotifications;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }

    @Override
    public <C> BsonDocument toBsonDocument(final Class<C> documentClass, final CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper<NotificationDAO>(this, codecRegistry.get(NotificationDAO.class));
    }

}
