/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

/**
 *
 * @author Sampan-IT
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.BasicProfileDAO;
import org.bson.*;
import org.bson.codecs.*;
import java.util.UUID;

/**
 * Created by Teo on 07/03/15.
 */
public class BasicProfileCodec implements CollectibleCodec<BasicProfileDAO> {

    private Codec<Document> documentCodec;

    public BasicProfileCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public BasicProfileDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        BasicProfileDAO basicProfile = new BasicProfileDAO();
        try {
            basicProfile = mapper.readValue(document.toJson().toString(), BasicProfileDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return basicProfile;
    }

    @Override
    public void encode(BsonWriter writer, BasicProfileDAO basicProfile, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(basicProfile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
//        doc.put("_id", basicProfile.get_id());
//        doc.put("userId", basicProfile.getUserId());
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<BasicProfileDAO> getEncoderClass() {
        return BasicProfileDAO.class;
    }

    @Override
    public BasicProfileDAO generateIdIfAbsentFromDocument(BasicProfileDAO basicProfile) {
        if (!documentHasId(basicProfile)) {
            basicProfile.set_id(UUID.randomUUID().toString());
        }
        return basicProfile;
    }

    @Override
    public boolean documentHasId(BasicProfileDAO basicProfile) {
        return basicProfile.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(BasicProfileDAO basicProfile) {
        if (!documentHasId(basicProfile)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) basicProfile.get_id());
    }
}
