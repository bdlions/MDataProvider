/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.ReligionsDAO;
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
 * @author Sampan-IT
 */
public class ReligionsCodec  implements CollectibleCodec<ReligionsDAO>{
    


    private Codec<Document> documentCodec;

    public ReligionsCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public ReligionsDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        ReligionsDAO religionList = new ReligionsDAO();
        try {
            religionList = mapper.readValue(document.toJson().toString(), ReligionsDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return religionList;
    }

    @Override
    public void encode(BsonWriter writer, ReligionsDAO religionList, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(religionList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<ReligionsDAO> getEncoderClass() {
        return ReligionsDAO.class;
    }
                        
    @Override
    public ReligionsDAO generateIdIfAbsentFromDocument(ReligionsDAO religionList) {
        if (!documentHasId(religionList)) {
            religionList.set_id(UUID.randomUUID().toString());
        }
        return religionList;
    }

    @Override
    public boolean documentHasId(ReligionsDAO religionList) {
        return religionList.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(ReligionsDAO religionList) {
        if (!documentHasId(religionList)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString(religionList.get_id());
    }
}
