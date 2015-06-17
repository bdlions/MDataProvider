/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

/**
 *
 * @author alamgir
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.UserDAO;
import org.bson.*;
import org.bson.codecs.*;
import java.util.UUID;

/**
 * Created by Teo on 07/03/15.
 */
public class UserCodec implements CollectibleCodec<UserDAO> {

    private Codec<Document> documentCodec;

    public UserCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public UserDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        UserDAO user = new UserDAO();
        try {
            user = mapper.readValue(document.toJson().toString(), UserDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void encode(BsonWriter writer, UserDAO user, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<UserDAO> getEncoderClass() {
        return UserDAO.class;
    }
                        
    @Override
    public UserDAO generateIdIfAbsentFromDocument(UserDAO user) {
        if (!documentHasId(user)) {
            user.set_id(UUID.randomUUID().toString());
        }
        return user;
    }

    @Override
    public boolean documentHasId(UserDAO user) {
        return user.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(UserDAO user) {
        if (!documentHasId(user)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString(user.get_id());
    }
}
