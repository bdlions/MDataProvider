/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.PageCategoryDAO;
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
public class PageCategoryCodec implements CollectibleCodec<PageCategoryDAO>{
    
    private Codec<Document> documentCodec;

    public PageCategoryCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public PageCategoryDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        PageCategoryDAO category = new PageCategoryDAO();
        try {
            category = mapper.readValue(document.toJson().toString(), PageCategoryDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return category;
    }

    @Override
    public void encode(BsonWriter writer, PageCategoryDAO category, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(category);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<PageCategoryDAO> getEncoderClass() {
        return PageCategoryDAO.class;
    }

    @Override
    public PageCategoryDAO generateIdIfAbsentFromDocument(PageCategoryDAO category) {
        if (!documentHasId(category)) {
            category.set_id(UUID.randomUUID().toString());
        }
        return category;
    }

    @Override
    public boolean documentHasId(PageCategoryDAO category) {
        return category.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(PageCategoryDAO category) {
        if (!documentHasId(category)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) category.get_id());
    }
}