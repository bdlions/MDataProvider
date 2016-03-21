/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.PageAlbumDAO;
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
public class PageAlbumCodec implements CollectibleCodec<PageAlbumDAO>{
    
    private Codec<Document> documentCodec;

    public PageAlbumCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public PageAlbumDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        PageAlbumDAO pageAlbum = new PageAlbumDAO();
        try {
            pageAlbum = mapper.readValue(document.toJson().toString(), PageAlbumDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pageAlbum;
    }

    @Override
    public void encode(BsonWriter writer, PageAlbumDAO pageAlbum, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(pageAlbum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<PageAlbumDAO> getEncoderClass() {
        return PageAlbumDAO.class;
    }

    @Override
    public PageAlbumDAO generateIdIfAbsentFromDocument(PageAlbumDAO pageAlbum) {
        if (!documentHasId(pageAlbum)) {
            pageAlbum.set_id(UUID.randomUUID().toString());
        }
        return pageAlbum;
    }

    @Override
    public boolean documentHasId(PageAlbumDAO pageAlbum) {
        return pageAlbum.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(PageAlbumDAO pageAlbum) {
        if (!documentHasId(pageAlbum)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) pageAlbum.get_id());
    }
}
