/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.NotificationDAO;
import java.util.UUID;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 *
 * @author Sampan IT
 */
public class NotificationCodec implements CollectibleCodec<NotificationDAO>{
    
      private Codec<Document> documentCodec;

    public NotificationCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }
    @Override
    public NotificationDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        NotificationDAO Notification = new NotificationDAO();
        try {
            Notification = mapper.readValue(document.toJson().toString(), NotificationDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Notification;
    }

    @Override
    public void encode(BsonWriter writer, NotificationDAO Notification, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(Notification);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<NotificationDAO> getEncoderClass() {
        return NotificationDAO.class;
    }

    @Override
    public NotificationDAO generateIdIfAbsentFromDocument(NotificationDAO Notification) {
        if (!documentHasId(Notification)) {
            Notification.set_id(UUID.randomUUID().toString());
        }
        return Notification;
    }

    @Override
    public boolean documentHasId(NotificationDAO Notification) {
        return Notification.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(NotificationDAO Notification) {
        if (!documentHasId(Notification)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) Notification.get_id());
    }
}
