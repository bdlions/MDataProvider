/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.LoginAttemptDAO;
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
public class LoginAttemptCodec  implements CollectibleCodec<LoginAttemptDAO>{
    
    
    private Codec<Document> documentCodec;

    public LoginAttemptCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }
    @Override
    public LoginAttemptDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        LoginAttemptDAO loginAttempt = new LoginAttemptDAO();
        try {
            loginAttempt = mapper.readValue(document.toJson().toString(), LoginAttemptDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginAttempt;
    }

    @Override
    public void encode(BsonWriter writer, LoginAttemptDAO loginAttempt, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(loginAttempt);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<LoginAttemptDAO> getEncoderClass() {
        return LoginAttemptDAO.class;
    }
                        
    @Override
    public LoginAttemptDAO generateIdIfAbsentFromDocument(LoginAttemptDAO loginAttempt) {
        if (!documentHasId(loginAttempt)) {
            loginAttempt.set_id(UUID.randomUUID().toString());
        }
        return loginAttempt;
    }

    @Override
    public boolean documentHasId(LoginAttemptDAO loginAttempt) {
        return loginAttempt.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(LoginAttemptDAO loginAttempt) {
        if (!documentHasId(loginAttempt)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString(loginAttempt.get_id());
    }
    
}
