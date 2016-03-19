/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.PageMemberDAO;
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
public class PageMemeberCodec implements CollectibleCodec<PageMemberDAO>{
    
    private Codec<Document> documentCodec;

    public PageMemeberCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public PageMemberDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        PageMemberDAO  pageInfo= new PageMemberDAO();
        try {
            pageInfo = mapper.readValue(document.toJson().toString(), PageMemberDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pageInfo;
    }

    @Override
    public void encode(BsonWriter writer, PageMemberDAO pageInfo, EncoderContext encoderContext) {
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
    public Class<PageMemberDAO> getEncoderClass() {
        return PageMemberDAO.class;
    }

    @Override
    public PageMemberDAO generateIdIfAbsentFromDocument(PageMemberDAO pageInfo) {
        if (!documentHasId(pageInfo)) {
            pageInfo.set_id(UUID.randomUUID().toString());
        }
        return pageInfo;
    }

    @Override
    public boolean documentHasId(PageMemberDAO pageInfo) {
        return pageInfo.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(PageMemberDAO pageInfo) {
        if (!documentHasId(pageInfo)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) pageInfo.get_id());
    }
}
