/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.PagePhotoDAO;
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
public class PagePhotoCodec implements CollectibleCodec<PagePhotoDAO> {

    private Codec<Document> documentCodec;

    public PagePhotoCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public PagePhotoDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        PagePhotoDAO pagePhoto = new PagePhotoDAO();
        try {
            pagePhoto = mapper.readValue(document.toJson().toString(), PagePhotoDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pagePhoto;
    }

    @Override
    public void encode(BsonWriter writer, PagePhotoDAO pagePhoto, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(pagePhoto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<PagePhotoDAO> getEncoderClass() {
        return PagePhotoDAO.class;
    }

    @Override
    public PagePhotoDAO generateIdIfAbsentFromDocument(PagePhotoDAO pagePhoto) {
        if (!documentHasId(pagePhoto)) {
            pagePhoto.set_id(UUID.randomUUID().toString());
        }
        return pagePhoto;
    }

    @Override
    public boolean documentHasId(PagePhotoDAO pagePhoto) {
        return pagePhoto.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(PagePhotoDAO pagePhoto) {
        if (!documentHasId(pagePhoto)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) pagePhoto.get_id());
    }
}
