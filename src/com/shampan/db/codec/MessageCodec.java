/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.MessageDAO;
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
public class MessageCodec implements CollectibleCodec<MessageDAO>{
    private Codec<Document> documentCodec;
      public MessageCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }
    @Override
    public MessageDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        MessageDAO message = new MessageDAO();
        try {
            message = mapper.readValue(document.toJson().toString(), MessageDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return message;
    }

    @Override
    public void encode(BsonWriter writer, MessageDAO message, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<MessageDAO> getEncoderClass() {
        return MessageDAO.class;
    }

    @Override
    public MessageDAO generateIdIfAbsentFromDocument(MessageDAO message) {
        if (!documentHasId(message)) {
            message.set_id(UUID.randomUUID().toString());
        }
        return message;
    }

    @Override
    public boolean documentHasId(MessageDAO message) {
        return message.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(MessageDAO message) {
        if (!documentHasId(message)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) message.get_id());
    }
}
