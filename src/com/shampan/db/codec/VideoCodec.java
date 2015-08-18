/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.VideoDAO;
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
public class VideoCodec implements CollectibleCodec<VideoDAO>{
    
    private Codec<Document> documentCodec;

    public VideoCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public VideoDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        VideoDAO videoInfo = new VideoDAO();
        try {
            videoInfo = mapper.readValue(document.toJson().toString(), VideoDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return videoInfo;
    }

    @Override
    public void encode(BsonWriter writer, VideoDAO videoInfo, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(videoInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document doc = new Document();
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<VideoDAO> getEncoderClass() {
        return VideoDAO.class;
    }

    @Override
    public VideoDAO generateIdIfAbsentFromDocument(VideoDAO videoInfo) {
        if (!documentHasId(videoInfo)) {
            videoInfo.set_id(UUID.randomUUID().toString());
        }
        return videoInfo;
    }

    @Override
    public boolean documentHasId(VideoDAO videoInfo) {
        return videoInfo.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(VideoDAO videoInfo) {
        if (!documentHasId(videoInfo)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString((String) videoInfo.get_id());
    }
}