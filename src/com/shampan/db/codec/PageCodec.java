/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.PageDAO;
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
public class PageCodec implements CollectibleCodec<PageDAO>{
    
    private Codec<Document> documentCodec;

    public PageCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public PageDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        PageDAO  pageInfo= new PageDAO();
        try {
            pageInfo = mapper.readValue(document.toJson().toString(), PageDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pageInfo;
    }

    @Override
    public void encode(BsonWriter writer, PageDAO pageInfo, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(pageInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<PageDAO> getEncoderClass() {
        return PageDAO.class;
    }

    @Override
    public PageDAO generateIdIfAbsentFromDocument(PageDAO pageInfo) {
        if (!documentHasId(pageInfo)) {
            pageInfo.set_id(UUID.randomUUID().toString());
        }
        return pageInfo;
    }

    @Override
    public boolean documentHasId(PageDAO pageInfo) {
        return pageInfo.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(PageDAO pageInfo) {
        if (!documentHasId(pageInfo)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) pageInfo.get_id());
    }
}
