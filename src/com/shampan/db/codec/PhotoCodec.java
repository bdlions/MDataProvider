/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.PhotoDAO;
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
public class PhotoCodec implements CollectibleCodec<PhotoDAO> {

    private Codec<Document> documentCodec;

    public PhotoCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public PhotoDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        PhotoDAO userAlbum = new PhotoDAO();
        try {
            userAlbum = mapper.readValue(document.toJson().toString(), PhotoDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userAlbum;
    }

    @Override
    public void encode(BsonWriter writer, PhotoDAO userAlbum, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(userAlbum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<PhotoDAO> getEncoderClass() {
        return PhotoDAO.class;
    }

    @Override
    public PhotoDAO generateIdIfAbsentFromDocument(PhotoDAO userAlbum) {
        if (!documentHasId(userAlbum)) {
            userAlbum.set_id(UUID.randomUUID().toString());
        }
        return userAlbum;
    }

    @Override
    public boolean documentHasId(PhotoDAO userAlbum) {
        return userAlbum.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(PhotoDAO userAlbum) {
        if (!documentHasId(userAlbum)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) userAlbum.get_id());
    }
}
