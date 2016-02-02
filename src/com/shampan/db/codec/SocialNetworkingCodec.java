/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.SocialNetworkDAO;
import com.shampan.db.collections.SocialNetworkDAO;
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
public class SocialNetworkingCodec implements CollectibleCodec<SocialNetworkDAO>{
     
    
    private Codec<Document> documentCodec;

    public SocialNetworkingCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }
    @Override
    public SocialNetworkDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        SocialNetworkDAO socalNetworkInfo = new SocialNetworkDAO();
        try {
            socalNetworkInfo = mapper.readValue(document.toJson().toString(), SocialNetworkDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return socalNetworkInfo;
    }

    @Override
    public void encode(BsonWriter writer, SocialNetworkDAO socalNetworkInfo, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(socalNetworkInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<SocialNetworkDAO> getEncoderClass() {
        return SocialNetworkDAO.class;
    }
                        
    @Override
    public SocialNetworkDAO generateIdIfAbsentFromDocument(SocialNetworkDAO socalNetworkInfo) {
        if (!documentHasId(socalNetworkInfo)) {
            socalNetworkInfo.set_id(UUID.randomUUID().toString());
        }
        return socalNetworkInfo;
    }

    @Override
    public boolean documentHasId(SocialNetworkDAO socalNetworkInfo) {
        return socalNetworkInfo.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(SocialNetworkDAO socalNetworkInfo) {
        if (!documentHasId(socalNetworkInfo)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString(socalNetworkInfo.get_id());
    }
    
}
