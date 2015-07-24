package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.RelationsDAO;
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
 * @author nazmul hasan
 */
public class RelationsCodec  implements CollectibleCodec<RelationsDAO>{
    


    private Codec<Document> documentCodec;

    public RelationsCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public RelationsDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        RelationsDAO religionList = new RelationsDAO();
        try {
            religionList = mapper.readValue(document.toJson().toString(), RelationsDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return religionList;
    }

    @Override
    public void encode(BsonWriter writer, RelationsDAO religionList, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(religionList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<RelationsDAO> getEncoderClass() {
        return RelationsDAO.class;
    }
                        
    @Override
    public RelationsDAO generateIdIfAbsentFromDocument(RelationsDAO religionList) {
        if (!documentHasId(religionList)) {
            religionList.set_id(UUID.randomUUID().toString());
        }
        return religionList;
    }

    @Override
    public boolean documentHasId(RelationsDAO religionList) {
        return religionList.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(RelationsDAO religionList) {
        if (!documentHasId(religionList)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString(religionList.get_id());
    }
}
