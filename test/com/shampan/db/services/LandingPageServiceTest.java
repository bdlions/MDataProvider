package com.shampan.db.services;

import com.mongodb.client.MongoCollection;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.ReligionsDAO;
import com.shampan.db.collections.builder.ReligionsDAOBuilder;
import com.shampan.db.collections.builder.CountriesDAOBuilder;
import org.junit.Test;

/**
 *
 * @author Sampan-IT
 */
public class LandingPageServiceTest {

//    public static void main(String[] args) {
//        LandingPageServiceTest.storeCountries();
//    }
    @Test
    public void storeReligions() {
        DBConnection.getInstance().getConnection();
        MongoCollection<ReligionsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELIGIONS.toString(), ReligionsDAO.class);

        ReligionsDAO religionDAO1 = new ReligionsDAOBuilder()
                .setReligionId("1")
                .setTitle("Islam")
                .setOrder("1")
                .build();
        mongoCollection.insertOne(religionDAO1);

        ReligionsDAO religionDAO2 = new ReligionsDAOBuilder()
                .setReligionId("2")
                .setTitle("Hindu")
                .setOrder("2")
                .build();
        mongoCollection.insertOne(religionDAO2);

        ReligionsDAO religionDAO3 = new ReligionsDAOBuilder()
                .setReligionId("3")
                .setTitle("Christianity")
                .setOrder("3")
                .build();
        mongoCollection.insertOne(religionDAO3);

        ReligionsDAO religionDAO4 = new ReligionsDAOBuilder()
                .setReligionId("4")
                .setTitle("Buddhism")
                .setOrder("4")
                .build();
        mongoCollection.insertOne(religionDAO4);
    }
  @Test
    public  void storeCountries() {
        DBConnection.getInstance().getConnection();
        MongoCollection<CountriesDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.COUNTRIES.toString(), CountriesDAO.class);

        CountriesDAO countryDAO1 = new CountriesDAOBuilder()
                .setCode("BD")
                .setTitle("Bangladesh")
                .setOrder("1")
                .build();
        mongoCollection.insertOne(countryDAO1);

    }

}
