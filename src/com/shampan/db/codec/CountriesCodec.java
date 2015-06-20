/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.shampan.db.collections.CountriesDAO;
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
public class CountriesCodec  implements CollectibleCodec<CountriesDAO>{
    


    private Codec<Document> documentCodec;

    public CountriesCodec() {
        this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public CountriesDAO decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        ObjectMapper mapper = new ObjectMapper();
        CountriesDAO countryList = new CountriesDAO();
        try {
            countryList = mapper.readValue(document.toJson().toString(), CountriesDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryList;
    }

    @Override
    public void encode(BsonWriter writer, CountriesDAO countryList, EncoderContext encoderContext) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(countryList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        documentCodec.encode(writer, Document.parse(json), encoderContext);
    }

    @Override
    public Class<CountriesDAO> getEncoderClass() {
        return CountriesDAO.class;
    }
                        
    @Override
    public CountriesDAO generateIdIfAbsentFromDocument(CountriesDAO countryList) {
        if (!documentHasId(countryList)) {
            countryList.set_id(UUID.randomUUID().toString());
        }
        return countryList;
    }

    @Override
    public boolean documentHasId(CountriesDAO countryList) {
        return countryList.get_id() != null;
    }

    @Override
    public BsonValue getDocumentId(CountriesDAO countryList) {
        if (!documentHasId(countryList)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString(countryList.get_id());
    }
}
