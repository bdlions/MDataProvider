/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.StatusDAO;
import org.bson.*;
import org.bson.codecs.*;
import java.util.UUID;


/**
 *
 * @author Sampan-IT
 */
public class StatusCodec implements CollectibleCodec<StatusDAO>{
    
    private Codec<Document> documentCodec;

    public StatusCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public StatusDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        StatusDAO status = new StatusDAO();
        try {
            status = mapper.readValue(document.toJson().toString(), StatusDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    @Override
    public void encode(BsonWriter writer, StatusDAO status, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
//        doc.put("_id", status.get_id());
//        doc.put("userId", status.getUserId());
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<StatusDAO> getEncoderClass() {
        return StatusDAO.class;
    }

    @Override
    public StatusDAO generateIdIfAbsentFromDocument(StatusDAO status) {
        if (!documentHasId(status)) {
            status.set_id(UUID.randomUUID().toString());
        }
        return status;
    }

    @Override
    public boolean documentHasId(StatusDAO status) {
        return status.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(StatusDAO status) {
        if (!documentHasId(status)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) status.get_id());
    }
}