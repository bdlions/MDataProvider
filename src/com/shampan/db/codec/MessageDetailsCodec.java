/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.MessageDetailsDAO;
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
public class MessageDetailsCodec implements CollectibleCodec<MessageDetailsDAO>{
    private Codec<Document> documentCodec;
      public MessageDetailsCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }
    @Override
    public MessageDetailsDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        MessageDetailsDAO messageDetails = new MessageDetailsDAO();
        try {
            messageDetails = mapper.readValue(document.toJson().toString(), MessageDetailsDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return messageDetails;
    }

    @Override
    public void encode(BsonWriter writer, MessageDetailsDAO messageDetails, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(messageDetails);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<MessageDetailsDAO> getEncoderClass() {
        return MessageDetailsDAO.class;
    }

    @Override
    public MessageDetailsDAO generateIdIfAbsentFromDocument(MessageDetailsDAO messageDetails) {
        if (!documentHasId(messageDetails)) {
            messageDetails.set_id(UUID.randomUUID().toString());
        }
        return messageDetails;
    }

    @Override
    public boolean documentHasId(MessageDetailsDAO messageDetails) {
        return messageDetails.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(MessageDetailsDAO messageDetails) {
        if (!documentHasId(messageDetails)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) messageDetails.get_id());
    }
    
}
