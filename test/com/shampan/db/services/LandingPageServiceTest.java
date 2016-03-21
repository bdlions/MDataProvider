package com.shampan.db.services;

import com.mongodb.client.MongoCollection;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.PageCategoryDAO;
import com.shampan.db.collections.PageSubCategoryDAO;
import com.shampan.db.collections.PhotoCategoryDAO;
import com.shampan.db.collections.ReligionsDAO;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.VideoCategoryDAO;
import com.shampan.db.collections.builder.ReligionsDAOBuilder;
import com.shampan.db.collections.builder.CountriesDAOBuilder;
import com.shampan.db.collections.builder.PageCategoryDAOBuilder;
import com.shampan.db.collections.builder.PageSubCategoryDAOBuilder;
import com.shampan.db.collections.builder.PhotoCategoryDAOBuilder;
import com.shampan.db.collections.builder.UserDAOBuilder;
import com.shampan.db.collections.builder.VideoCategoryDAOBuilder;
import com.shampan.db.collections.fragment.common.UserInfo;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Sampan-IT
 */
public class LandingPageServiceTest {

//    public static void main(String[] args) {
//        LandingPageServiceTest.storeCountries();
//    }
//    @Test
    public void storeReligions() {
        DBConnection.getInstance().getConnection();
        MongoCollection<ReligionsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELIGIONS.toString(), ReligionsDAO.class);

        ReligionsDAO religionDAO1 = new ReligionsDAOBuilder()
                .setReligionId("1")
                .setTitle("Islam")
                .setOrder("1")
                .build();

        ReligionsDAO religionDAO2 = new ReligionsDAOBuilder()
                .setReligionId("2")
                .setTitle("Hindu")
                .setOrder("2")
                .build();

        ReligionsDAO religionDAO3 = new ReligionsDAOBuilder()
                .setReligionId("3")
                .setTitle("Christianity")
                .setOrder("3")
                .build();

        ReligionsDAO religionDAO4 = new ReligionsDAOBuilder()
                .setReligionId("4")
                .setTitle("Buddhism")
                .setOrder("4")
                .build();
        List<ReligionsDAO> religions = new ArrayList<>();
        religions.add(religionDAO1);
        religions.add(religionDAO2);
        religions.add(religionDAO3);
        religions.add(religionDAO4);

        mongoCollection.insertMany(religions);
    }

//    @Test
    public void storeCountries() {
        DBConnection.getInstance().getConnection();
        MongoCollection<CountriesDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.COUNTRIES.toString(), CountriesDAO.class);

        CountriesDAO countryDAO1 = new CountriesDAOBuilder()
                .setCode("US")
                .setTitle("United States")
                .setOrder("1")
                .build();
        CountriesDAO countryDAO2 = new CountriesDAOBuilder()
                .setCode("CA")
                .setTitle("Canada")
                .setOrder("2")
                .build();
        CountriesDAO countryDAO3 = new CountriesDAOBuilder()
                .setCode("AF")
                .setTitle("Afghanistan")
                .setOrder("3")
                .build();

        CountriesDAO countryDAO4 = new CountriesDAOBuilder()
                .setCode("AL")
                .setTitle("Albania")
                .setOrder("4")
                .build();

        CountriesDAO countryDAO5 = new CountriesDAOBuilder()
                .setCode("DZ")
                .setTitle("Algeria")
                .setOrder("5")
                .build();
        CountriesDAO countryDAO6 = new CountriesDAOBuilder()
                .setCode("DS")
                .setTitle("American Samoa")
                .setOrder("6")
                .build();

        CountriesDAO countryDAO7 = new CountriesDAOBuilder()
                .setCode("AD")
                .setTitle("Andorra")
                .setOrder("7")
                .build();
        CountriesDAO countryDAO8 = new CountriesDAOBuilder()
                .setCode("AO")
                .setTitle("Angola")
                .setOrder("8")
                .build();
        CountriesDAO countryDAO9 = new CountriesDAOBuilder()
                .setCode("AI")
                .setTitle("Anguilla")
                .setOrder("9")
                .build();
        CountriesDAO countryDAO10 = new CountriesDAOBuilder()
                .setCode("AQ")
                .setTitle("Antarctica")
                .setOrder("10")
                .build();
        CountriesDAO countryDAO11 = new CountriesDAOBuilder()
                .setCode("AG")
                .setTitle("Antigua and/or Barbuda")
                .setOrder("11")
                .build();

        CountriesDAO countryDAO12 = new CountriesDAOBuilder()
                .setCode("AR")
                .setTitle("Argentina")
                .setOrder("12")
                .build();
        CountriesDAO countryDAO13 = new CountriesDAOBuilder()
                .setCode("AM")
                .setTitle("Armenia")
                .setOrder("13")
                .build();
        CountriesDAO countryDAO14 = new CountriesDAOBuilder()
                .setCode("AW")
                .setTitle("Aruba")
                .setOrder("14")
                .build();
        CountriesDAO countryDAO15 = new CountriesDAOBuilder()
                .setCode("AU")
                .setTitle("Australia")
                .setOrder("15")
                .build();

        CountriesDAO countryDAO16 = new CountriesDAOBuilder()
                .setCode("AT")
                .setTitle("Austria")
                .setOrder("16")
                .build();

        CountriesDAO countryDAO17 = new CountriesDAOBuilder()
                .setCode("AZ")
                .setTitle("Azerbaijan")
                .setOrder("17")
                .build();
        CountriesDAO countryDAO18 = new CountriesDAOBuilder()
                .setCode("BS")
                .setTitle("Bahamas")
                .setOrder("18")
                .build();
        CountriesDAO countryDAO19 = new CountriesDAOBuilder()
                .setCode("BH")
                .setTitle("Bahrain")
                .setOrder("19")
                .build();

        CountriesDAO countryDAO20 = new CountriesDAOBuilder()
                .setCode("BD")
                .setTitle("Bangladesh")
                .setOrder("20")
                .build();
        CountriesDAO countryDAO21 = new CountriesDAOBuilder()
                .setCode("BB")
                .setTitle("Barbados")
                .setOrder("21")
                .build();
        CountriesDAO countryDAO22 = new CountriesDAOBuilder()
                .setCode("BY")
                .setTitle("Belarus")
                .setOrder("22")
                .build();
        CountriesDAO countryDAO23 = new CountriesDAOBuilder()
                .setCode("BE")
                .setTitle("Belgium")
                .setOrder("23")
                .build();
        CountriesDAO countryDAO24 = new CountriesDAOBuilder()
                .setCode("BZ")
                .setTitle("Belize")
                .setOrder("24")
                .build();

        CountriesDAO countryDAO25 = new CountriesDAOBuilder()
                .setCode("BJ")
                .setTitle("Benin")
                .setOrder("25")
                .build();

        CountriesDAO countryDAO26 = new CountriesDAOBuilder()
                .setCode("BM")
                .setTitle("Bermuda")
                .setOrder("26")
                .build();

        CountriesDAO countryDAO27 = new CountriesDAOBuilder()
                .setCode("BT")
                .setTitle("Bhutan")
                .setOrder("27")
                .build();

        CountriesDAO countryDAO28 = new CountriesDAOBuilder()
                .setCode("BO")
                .setTitle("Bolivia")
                .setOrder("28")
                .build();
        CountriesDAO countryDAO29 = new CountriesDAOBuilder()
                .setCode("BA")
                .setTitle("Bosnia and Herzegovina")
                .setOrder("29")
                .build();
        CountriesDAO countryDAO30 = new CountriesDAOBuilder()
                .setCode("BW")
                .setTitle("Botswana")
                .setOrder("30")
                .build();
        CountriesDAO countryDAO31 = new CountriesDAOBuilder()
                .setCode("BV")
                .setTitle("Bouvet Island")
                .setOrder("31")
                .build();
        CountriesDAO countryDAO32 = new CountriesDAOBuilder()
                .setCode("BR")
                .setTitle("Brazil")
                .setOrder("32")
                .build();
        CountriesDAO countryDAO33 = new CountriesDAOBuilder()
                .setCode("IO")
                .setTitle("British lndian Ocean Territory")
                .setOrder("33")
                .build();
        CountriesDAO countryDAO34 = new CountriesDAOBuilder()
                .setCode("BN")
                .setTitle("Brunei Darussalam")
                .setOrder("34")
                .build();

        CountriesDAO countryDAO35 = new CountriesDAOBuilder()
                .setCode("BG")
                .setTitle("Bulgaria")
                .setOrder("35")
                .build();

        CountriesDAO countryDAO36 = new CountriesDAOBuilder()
                .setCode("BF")
                .setTitle("Burkina Faso")
                .setOrder("36")
                .build();

        CountriesDAO countryDAO37 = new CountriesDAOBuilder()
                .setCode("BI")
                .setTitle("Burundi")
                .setOrder("37")
                .build();

        CountriesDAO countryDAO38 = new CountriesDAOBuilder()
                .setCode("KH")
                .setTitle("Cambodia")
                .setOrder("38")
                .build();

        CountriesDAO countryDAO39 = new CountriesDAOBuilder()
                .setCode("CM")
                .setTitle("Cameroon")
                .setOrder("39")
                .build();

        CountriesDAO countryDAO40 = new CountriesDAOBuilder()
                .setCode("CV")
                .setTitle("Cape Verde")
                .setOrder("40")
                .build();
        CountriesDAO countryDAO41 = new CountriesDAOBuilder()
                .setCode("KY")
                .setTitle("Cayman Islands")
                .setOrder("41")
                .build();
        CountriesDAO countryDAO42 = new CountriesDAOBuilder()
                .setCode("CF")
                .setTitle("Central African Republic")
                .setOrder("42")
                .build();
        CountriesDAO countryDAO43 = new CountriesDAOBuilder()
                .setCode("TD")
                .setTitle("Chad")
                .setOrder("43")
                .build();
        CountriesDAO countryDAO44 = new CountriesDAOBuilder()
                .setCode("CL")
                .setTitle("Chile")
                .setOrder("44")
                .build();
        CountriesDAO countryDAO45 = new CountriesDAOBuilder()
                .setCode("CN")
                .setTitle("China")
                .setOrder("45")
                .build();
        CountriesDAO countryDAO46 = new CountriesDAOBuilder()
                .setCode("CX")
                .setTitle("Christmas Island")
                .setOrder("46")
                .build();
        CountriesDAO countryDAO47 = new CountriesDAOBuilder()
                .setCode("CC")
                .setTitle("Cocos (Keeling) Islands")
                .setOrder("47")
                .build();
        CountriesDAO countryDAO48 = new CountriesDAOBuilder()
                .setCode("CO")
                .setTitle("Colombia")
                .setOrder("48")
                .build();
        CountriesDAO countryDAO49 = new CountriesDAOBuilder()
                .setCode("KM")
                .setTitle("Comoros")
                .setOrder("49")
                .build();
        CountriesDAO countryDAO50 = new CountriesDAOBuilder()
                .setCode("CG")
                .setTitle("Congo")
                .setOrder("50")
                .build();
        CountriesDAO countryDAO51 = new CountriesDAOBuilder()
                .setCode("CK")
                .setTitle("Cook Islands")
                .setOrder("51")
                .build();
        CountriesDAO countryDAO52 = new CountriesDAOBuilder()
                .setCode("CR")
                .setTitle("Costa Rica")
                .setOrder("52")
                .build();
        CountriesDAO countryDAO53 = new CountriesDAOBuilder()
                .setCode("HR")
                .setTitle("Croatia (Hrvatska)")
                .setOrder("53")
                .build();
        CountriesDAO countryDAO54 = new CountriesDAOBuilder()
                .setCode("CU")
                .setTitle("Cuba")
                .setOrder("54")
                .build();
        CountriesDAO countryDAO55 = new CountriesDAOBuilder()
                .setCode("CY")
                .setTitle("Cyprus")
                .setOrder("55")
                .build();
        CountriesDAO countryDAO56 = new CountriesDAOBuilder()
                .setCode("CZ")
                .setTitle("Czech Republic")
                .setOrder("56")
                .build();
        CountriesDAO countryDAO57 = new CountriesDAOBuilder()
                .setCode("DK")
                .setTitle("Denmark")
                .setOrder("57")
                .build();
        CountriesDAO countryDAO58 = new CountriesDAOBuilder()
                .setCode("DJ")
                .setTitle("Djibouti")
                .setOrder("58")
                .build();
        CountriesDAO countryDAO59 = new CountriesDAOBuilder()
                .setCode("DM")
                .setTitle("Dominica")
                .setOrder("59")
                .build();
        CountriesDAO countryDAO60 = new CountriesDAOBuilder()
                .setCode("DO")
                .setTitle("Dominican Republic")
                .setOrder("60")
                .build();
        CountriesDAO countryDAO61 = new CountriesDAOBuilder()
                .setCode("TP")
                .setTitle("East Timor")
                .setOrder("61")
                .build();
        CountriesDAO countryDAO62 = new CountriesDAOBuilder()
                .setCode("EC")
                .setTitle("Ecudaor")
                .setOrder("62")
                .build();
        CountriesDAO countryDAO63 = new CountriesDAOBuilder()
                .setCode("EG")
                .setTitle("Egypt")
                .setOrder("63")
                .build();
        CountriesDAO countryDAO64 = new CountriesDAOBuilder()
                .setCode("SV")
                .setTitle("El Salvador")
                .setOrder("64")
                .build();
        CountriesDAO countryDAO65 = new CountriesDAOBuilder()
                .setCode("GQ")
                .setTitle("Equatorial Guinea")
                .setOrder("65")
                .build();
        CountriesDAO countryDAO66 = new CountriesDAOBuilder()
                .setCode("ER")
                .setTitle("Eritrea")
                .setOrder("66")
                .build();
        CountriesDAO countryDAO67 = new CountriesDAOBuilder()
                .setCode("EE")
                .setTitle("Estonia")
                .setOrder("67")
                .build();
        CountriesDAO countryDAO68 = new CountriesDAOBuilder()
                .setCode("ET")
                .setTitle("Ethiopia")
                .setOrder("68")
                .build();
        CountriesDAO countryDAO69 = new CountriesDAOBuilder()
                .setCode("FK")
                .setTitle("Falkland Islands (Malvinas)")
                .setOrder("69")
                .build();
        CountriesDAO countryDAO70 = new CountriesDAOBuilder()
                .setCode("FO")
                .setTitle("Faroe Islands")
                .setOrder("70")
                .build();
        CountriesDAO countryDAO71 = new CountriesDAOBuilder()
                .setCode("FJ")
                .setTitle("Fiji")
                .setOrder("71")
                .build();
        CountriesDAO countryDAO72 = new CountriesDAOBuilder()
                .setCode("FI")
                .setTitle("Finland")
                .setOrder("72")
                .build();
        CountriesDAO countryDAO73 = new CountriesDAOBuilder()
                .setCode("FR")
                .setTitle("France")
                .setOrder("73")
                .build();
        CountriesDAO countryDAO74 = new CountriesDAOBuilder()
                .setCode("FX")
                .setTitle("France, Metropolitan")
                .setOrder("74")
                .build();

        CountriesDAO countryDAO75 = new CountriesDAOBuilder()
                .setCode("GF")
                .setTitle("French Guiana")
                .setOrder("75")
                .build();
        CountriesDAO countryDAO76 = new CountriesDAOBuilder()
                .setCode("PF")
                .setTitle("French Polynesia")
                .setOrder("76")
                .build();
        CountriesDAO countryDAO77 = new CountriesDAOBuilder()
                .setCode("TF")
                .setTitle("French Southern Territories")
                .setOrder("77")
                .build();
        CountriesDAO countryDAO78 = new CountriesDAOBuilder()
                .setCode("GA")
                .setTitle("Gabon")
                .setOrder("78")
                .build();
        CountriesDAO countryDAO79 = new CountriesDAOBuilder()
                .setCode("GM")
                .setTitle("Gambia")
                .setOrder("79")
                .build();
        CountriesDAO countryDAO80 = new CountriesDAOBuilder()
                .setCode("GE")
                .setTitle("Georgia")
                .setOrder("80")
                .build();
        CountriesDAO countryDAO81 = new CountriesDAOBuilder()
                .setCode("DE")
                .setTitle("Germany")
                .setOrder("81")
                .build();
        CountriesDAO countryDAO82 = new CountriesDAOBuilder()
                .setCode("GH")
                .setTitle("Ghana")
                .setOrder("82")
                .build();
        CountriesDAO countryDAO83 = new CountriesDAOBuilder()
                .setCode("GI")
                .setTitle("Gibraltar")
                .setOrder("83")
                .build();
        CountriesDAO countryDAO84 = new CountriesDAOBuilder()
                .setCode("GR")
                .setTitle("Greece")
                .setOrder("84")
                .build();
        CountriesDAO countryDAO85 = new CountriesDAOBuilder()
                .setCode("GL")
                .setTitle("Greenland")
                .setOrder("85")
                .build();
        CountriesDAO countryDAO86 = new CountriesDAOBuilder()
                .setCode("GD")
                .setTitle("Grenada")
                .setOrder("86")
                .build();
        CountriesDAO countryDAO87 = new CountriesDAOBuilder()
                .setCode("GP")
                .setTitle("Guadeloupe")
                .setOrder("87")
                .build();
        CountriesDAO countryDAO88 = new CountriesDAOBuilder()
                .setCode("GU")
                .setTitle("Guam")
                .setOrder("88")
                .build();
        CountriesDAO countryDAO89 = new CountriesDAOBuilder()
                .setCode("GT")
                .setTitle("Guatemala")
                .setOrder("89")
                .build();
        CountriesDAO countryDAO90 = new CountriesDAOBuilder()
                .setCode("GN")
                .setTitle("Guinea")
                .setOrder("90")
                .build();
        CountriesDAO countryDAO91 = new CountriesDAOBuilder()
                .setCode("GW")
                .setTitle("Guinea-Bissau")
                .setOrder("91")
                .build();
        CountriesDAO countryDAO92 = new CountriesDAOBuilder()
                .setCode("GY")
                .setTitle("Guyana")
                .setOrder("92")
                .build();
        CountriesDAO countryDAO93 = new CountriesDAOBuilder()
                .setCode("HT")
                .setTitle("Haiti")
                .setOrder("93")
                .build();
        CountriesDAO countryDAO94 = new CountriesDAOBuilder()
                .setCode("HM")
                .setTitle("Heard and Mc Donald Islands")
                .setOrder("94")
                .build();
        CountriesDAO countryDAO95 = new CountriesDAOBuilder()
                .setCode("HN")
                .setTitle("Honduras")
                .setOrder("95")
                .build();
        CountriesDAO countryDAO96 = new CountriesDAOBuilder()
                .setCode("HK")
                .setTitle("Hong Kong")
                .setOrder("96")
                .build();
        CountriesDAO countryDAO97 = new CountriesDAOBuilder()
                .setCode("HU")
                .setTitle("Hungary")
                .setOrder("97")
                .build();
        CountriesDAO countryDAO98 = new CountriesDAOBuilder()
                .setCode("IS")
                .setTitle("Iceland")
                .setOrder("98")
                .build();
        CountriesDAO countryDAO99 = new CountriesDAOBuilder()
                .setCode("IN")
                .setTitle("India")
                .setOrder("99")
                .build();
        CountriesDAO countryDAO100 = new CountriesDAOBuilder()
                .setCode("ID")
                .setTitle("Indonesia")
                .setOrder("100")
                .build();
        CountriesDAO countryDAO101 = new CountriesDAOBuilder()
                .setCode("IR")
                .setTitle("Iran (Islamic Republic of)")
                .setOrder("101")
                .build();
        CountriesDAO countryDAO102 = new CountriesDAOBuilder()
                .setCode("IQ")
                .setTitle("Iraq")
                .setOrder("102")
                .build();
        CountriesDAO countryDAO103 = new CountriesDAOBuilder()
                .setCode("IE")
                .setTitle("Ireland")
                .setOrder("103")
                .build();
        CountriesDAO countryDAO104 = new CountriesDAOBuilder()
                .setCode("IL")
                .setTitle("Israel")
                .setOrder("104")
                .build();
        CountriesDAO countryDAO105 = new CountriesDAOBuilder()
                .setCode("IT")
                .setTitle("Italy")
                .setOrder("105")
                .build();
        CountriesDAO countryDAO106 = new CountriesDAOBuilder()
                .setCode("CI")
                .setTitle("Ivory Coast")
                .setOrder("106")
                .build();
        CountriesDAO countryDAO107 = new CountriesDAOBuilder()
                .setCode("JM")
                .setTitle("Jamaica")
                .setOrder("107")
                .build();
        CountriesDAO countryDAO108 = new CountriesDAOBuilder()
                .setCode("JP")
                .setTitle("Japan")
                .setOrder("108")
                .build();
        CountriesDAO countryDAO109 = new CountriesDAOBuilder()
                .setCode("JO")
                .setTitle("Jordan")
                .setOrder("109")
                .build();
        CountriesDAO countryDAO110 = new CountriesDAOBuilder()
                .setCode("KZ")
                .setTitle("Kazakhstan")
                .setOrder("110")
                .build();
        CountriesDAO countryDAO111 = new CountriesDAOBuilder()
                .setCode("KE")
                .setTitle("Kenya")
                .setOrder("111")
                .build();
        CountriesDAO countryDAO112 = new CountriesDAOBuilder()
                .setCode("KI")
                .setTitle("Kiribati")
                .setOrder("112")
                .build();
        CountriesDAO countryDAO113 = new CountriesDAOBuilder()
                .setCode("KP")
                .setTitle("Korea, Democratic People''s Republic of")
                .setOrder("113")
                .build();
        CountriesDAO countryDAO114 = new CountriesDAOBuilder()
                .setCode("KR")
                .setTitle("Korea, Republic of")
                .setOrder("114")
                .build();

        CountriesDAO countryDAO115 = new CountriesDAOBuilder()
                .setCode("KW")
                .setTitle("Kuwait")
                .setOrder("115")
                .build();
        CountriesDAO countryDAO116 = new CountriesDAOBuilder()
                .setCode("KG")
                .setTitle("Kyrgyzstan")
                .setOrder("116")
                .build();
        CountriesDAO countryDAO117 = new CountriesDAOBuilder()
                .setCode("LA")
                .setTitle("Lao People''s Democratic Republic")
                .setOrder("117")
                .build();

        CountriesDAO countryDAO118 = new CountriesDAOBuilder()
                .setCode("LV")
                .setTitle("Latvia")
                .setOrder("118")
                .build();

        CountriesDAO countryDAO119 = new CountriesDAOBuilder()
                .setCode("LB")
                .setTitle("Lebanon")
                .setOrder("119")
                .build();
        CountriesDAO countryDAO120 = new CountriesDAOBuilder()
                .setCode("LS")
                .setTitle("Lesotho")
                .setOrder("120")
                .build();
        CountriesDAO countryDAO121 = new CountriesDAOBuilder()
                .setCode("LR")
                .setTitle("Liberia")
                .setOrder("121")
                .build();
        CountriesDAO countryDAO122 = new CountriesDAOBuilder()
                .setCode("LY")
                .setTitle("Libyan Arab Jamahiriya")
                .setOrder("122")
                .build();
        CountriesDAO countryDAO123 = new CountriesDAOBuilder()
                .setCode("LI")
                .setTitle("Liechtenstein")
                .setOrder("123")
                .build();
        CountriesDAO countryDAO124 = new CountriesDAOBuilder()
                .setCode("LT")
                .setTitle("Lithuania")
                .setOrder("124")
                .build();
        CountriesDAO countryDAO125 = new CountriesDAOBuilder()
                .setCode("LU")
                .setTitle("Luxembourg")
                .setOrder("125")
                .build();
        CountriesDAO countryDAO126 = new CountriesDAOBuilder()
                .setCode("MO")
                .setTitle("Macau")
                .setOrder("126")
                .build();
        CountriesDAO countryDAO127 = new CountriesDAOBuilder()
                .setCode("MK")
                .setTitle("Macedonia")
                .setOrder("127")
                .build();
        CountriesDAO countryDAO128 = new CountriesDAOBuilder()
                .setCode("MG")
                .setTitle("Madagascar")
                .setOrder("128")
                .build();
        CountriesDAO countryDAO129 = new CountriesDAOBuilder()
                .setCode("MW")
                .setTitle("Malawi")
                .setOrder("129")
                .build();
        CountriesDAO countryDAO130 = new CountriesDAOBuilder()
                .setCode("MY")
                .setTitle("Malaysia")
                .setOrder("130")
                .build();
        CountriesDAO countryDAO131 = new CountriesDAOBuilder()
                .setCode("MV")
                .setTitle("Maldives")
                .setOrder("131")
                .build();
        CountriesDAO countryDAO132 = new CountriesDAOBuilder()
                .setCode("ML")
                .setTitle("Mali")
                .setOrder("132")
                .build();
        CountriesDAO countryDAO133 = new CountriesDAOBuilder()
                .setCode("MT")
                .setTitle("Malta")
                .setOrder("133")
                .build();
        CountriesDAO countryDAO134 = new CountriesDAOBuilder()
                .setCode("MH")
                .setTitle("Marshall Islands")
                .setOrder("134")
                .build();
        CountriesDAO countryDAO135 = new CountriesDAOBuilder()
                .setCode("MQ")
                .setTitle("Martinique")
                .setOrder("135")
                .build();
        CountriesDAO countryDAO136 = new CountriesDAOBuilder()
                .setCode("MR")
                .setTitle("Mauritania")
                .setOrder("136")
                .build();
        CountriesDAO countryDAO137 = new CountriesDAOBuilder()
                .setCode("MU")
                .setTitle("Mauritius")
                .setOrder("137")
                .build();

        CountriesDAO countryDAO138 = new CountriesDAOBuilder()
                .setCode("TY")
                .setTitle("Mayotte")
                .setOrder("138")
                .build();

        CountriesDAO countryDAO139 = new CountriesDAOBuilder()
                .setCode("MX")
                .setTitle("Mexico")
                .setOrder("139")
                .build();
        CountriesDAO countryDAO140 = new CountriesDAOBuilder()
                .setCode("FM")
                .setTitle("Micronesia, Federated States of")
                .setOrder("140")
                .build();
        CountriesDAO countryDAO141 = new CountriesDAOBuilder()
                .setCode("MD")
                .setTitle("Moldova, Republic of")
                .setOrder("141")
                .build();
        CountriesDAO countryDAO142 = new CountriesDAOBuilder()
                .setCode("MC")
                .setTitle("Monaco")
                .setOrder("142")
                .build();
        CountriesDAO countryDAO143 = new CountriesDAOBuilder()
                .setCode("MN")
                .setTitle("Mongolia")
                .setOrder("143")
                .build();
        CountriesDAO countryDAO144 = new CountriesDAOBuilder()
                .setCode("MS")
                .setTitle("Montserrat")
                .setOrder("144")
                .build();
        CountriesDAO countryDAO145 = new CountriesDAOBuilder()
                .setCode("MA")
                .setTitle("Morocco")
                .setOrder("145")
                .build();

        CountriesDAO countryDAO146 = new CountriesDAOBuilder()
                .setCode("MZ")
                .setTitle("Mozambique")
                .setOrder("146")
                .build();
        CountriesDAO countryDAO147 = new CountriesDAOBuilder()
                .setCode("MM")
                .setTitle("Myanmar")
                .setOrder("147")
                .build();
        CountriesDAO countryDAO148 = new CountriesDAOBuilder()
                .setCode("NA")
                .setTitle("Namibia")
                .setOrder("148")
                .build();
        CountriesDAO countryDAO149 = new CountriesDAOBuilder()
                .setCode("NR")
                .setTitle("Nauru")
                .setOrder("149")
                .build();
        CountriesDAO countryDAO150 = new CountriesDAOBuilder()
                .setCode("NP")
                .setTitle("Nepal")
                .setOrder("150")
                .build();
        CountriesDAO countryDAO151 = new CountriesDAOBuilder()
                .setCode("NP")
                .setTitle("Nepal")
                .setOrder("151")
                .build();
        CountriesDAO countryDAO152 = new CountriesDAOBuilder()
                .setCode("AN")
                .setTitle("Netherlands Antilles")
                .setOrder("152")
                .build();
        CountriesDAO countryDAO153 = new CountriesDAOBuilder()
                .setCode("NC")
                .setTitle("New Caledonia")
                .setOrder("153")
                .build();

        CountriesDAO countryDAO154 = new CountriesDAOBuilder()
                .setCode("NZ")
                .setTitle("New Zealand")
                .setOrder("154")
                .build();
        CountriesDAO countryDAO155 = new CountriesDAOBuilder()
                .setCode("NI")
                .setTitle("Nicaragua")
                .setOrder("155")
                .build();
        CountriesDAO countryDAO156 = new CountriesDAOBuilder()
                .setCode("NE")
                .setTitle("Niger")
                .setOrder("156")
                .build();
        CountriesDAO countryDAO157 = new CountriesDAOBuilder()
                .setCode("NG")
                .setTitle("Nigeria")
                .setOrder("157")
                .build();
        CountriesDAO countryDAO158 = new CountriesDAOBuilder()
                .setCode("NU")
                .setTitle("Niue")
                .setOrder("158")
                .build();
        CountriesDAO countryDAO159 = new CountriesDAOBuilder()
                .setCode("NF")
                .setTitle("Norfork Island")
                .setOrder("159")
                .build();
        CountriesDAO countryDAO160 = new CountriesDAOBuilder()
                .setCode("MP")
                .setTitle("Northern Mariana Islands")
                .setOrder("160")
                .build();
        CountriesDAO countryDAO161 = new CountriesDAOBuilder()
                .setCode("NO")
                .setTitle("Norway")
                .setOrder("161")
                .build();
        CountriesDAO countryDAO162 = new CountriesDAOBuilder()
                .setCode("OM")
                .setTitle("Oman")
                .setOrder("162")
                .build();
        CountriesDAO countryDAO163 = new CountriesDAOBuilder()
                .setCode("PK")
                .setTitle("Pakistan")
                .setOrder("163")
                .build();
        CountriesDAO countryDAO164 = new CountriesDAOBuilder()
                .setCode("PW")
                .setTitle("Palau")
                .setOrder("164")
                .build();
        CountriesDAO countryDAO165 = new CountriesDAOBuilder()
                .setCode("PA")
                .setTitle("Panama")
                .setOrder("165")
                .build();
        CountriesDAO countryDAO166 = new CountriesDAOBuilder()
                .setCode("PG")
                .setTitle("Papua New Guinea")
                .setOrder("166")
                .build();
        CountriesDAO countryDAO167 = new CountriesDAOBuilder()
                .setCode("PY")
                .setTitle("Paraguay")
                .setOrder("167")
                .build();
        CountriesDAO countryDAO168 = new CountriesDAOBuilder()
                .setCode("PE")
                .setTitle("Peru")
                .setOrder("168")
                .build();
        CountriesDAO countryDAO169 = new CountriesDAOBuilder()
                .setCode("PH")
                .setTitle("Philippines")
                .setOrder("169")
                .build();
        CountriesDAO countryDAO170 = new CountriesDAOBuilder()
                .setCode("PN")
                .setTitle("Pitcairn")
                .setOrder("170")
                .build();
        CountriesDAO countryDAO171 = new CountriesDAOBuilder()
                .setCode("PL")
                .setTitle("Poland")
                .setOrder("171")
                .build();
        CountriesDAO countryDAO172 = new CountriesDAOBuilder()
                .setCode("PT")
                .setTitle("Portugal")
                .setOrder("172")
                .build();
        CountriesDAO countryDAO173 = new CountriesDAOBuilder()
                .setCode("PR")
                .setTitle("Puerto Rico")
                .setOrder("173")
                .build();
        CountriesDAO countryDAO174 = new CountriesDAOBuilder()
                .setCode("QA")
                .setTitle("Qatar")
                .setOrder("174")
                .build();
        CountriesDAO countryDAO175 = new CountriesDAOBuilder()
                .setCode("RE")
                .setTitle("Reunion")
                .setOrder("175")
                .build();
        CountriesDAO countryDAO176 = new CountriesDAOBuilder()
                .setCode("RO")
                .setTitle("Romania")
                .setOrder("176")
                .build();
        CountriesDAO countryDAO177 = new CountriesDAOBuilder()
                .setCode("RU")
                .setTitle("Russian Federation")
                .setOrder("177")
                .build();
        CountriesDAO countryDAO178 = new CountriesDAOBuilder()
                .setCode("RW")
                .setTitle("Rwanda")
                .setOrder("178")
                .build();
        CountriesDAO countryDAO179 = new CountriesDAOBuilder()
                .setCode("KN")
                .setTitle("Saint Kitts and Nevis")
                .setOrder("179")
                .build();
        CountriesDAO countryDAO180 = new CountriesDAOBuilder()
                .setCode("LC")
                .setTitle("Saint Lucia")
                .setOrder("180")
                .build();
        CountriesDAO countryDAO181 = new CountriesDAOBuilder()
                .setCode("VC")
                .setTitle("Saint Vincent and the Grenadines")
                .setOrder("181")
                .build();
        CountriesDAO countryDAO182 = new CountriesDAOBuilder()
                .setCode("WS")
                .setTitle("Samoa")
                .setOrder("182")
                .build();
        CountriesDAO countryDAO183 = new CountriesDAOBuilder()
                .setCode("SM")
                .setTitle("San Marino")
                .setOrder("183")
                .build();
        CountriesDAO countryDAO184 = new CountriesDAOBuilder()
                .setCode("ST")
                .setTitle("Sao Tome and Principe")
                .setOrder("184")
                .build();
        CountriesDAO countryDAO185 = new CountriesDAOBuilder()
                .setCode("SA")
                .setTitle("Saudi Arabia")
                .setOrder("185")
                .build();
        CountriesDAO countryDAO186 = new CountriesDAOBuilder()
                .setCode("SN")
                .setTitle("Senegal")
                .setOrder("186")
                .build();
        CountriesDAO countryDAO187 = new CountriesDAOBuilder()
                .setCode("SC")
                .setTitle("Seychelles")
                .setOrder("187")
                .build();
        CountriesDAO countryDAO188 = new CountriesDAOBuilder()
                .setCode("SL")
                .setTitle("Sierra Leone")
                .setOrder("188")
                .build();
        CountriesDAO countryDAO189 = new CountriesDAOBuilder()
                .setCode("SG")
                .setTitle("Singapore")
                .setOrder("189")
                .build();
        CountriesDAO countryDAO190 = new CountriesDAOBuilder()
                .setCode("SA")
                .setTitle("Slovakia")
                .setOrder("190")
                .build();
        CountriesDAO countryDAO191 = new CountriesDAOBuilder()
                .setCode("SI")
                .setTitle("Slovenia")
                .setOrder("191")
                .build();
        CountriesDAO countryDAO192 = new CountriesDAOBuilder()
                .setCode("SB")
                .setTitle("Solomon Islands")
                .setOrder("192")
                .build();

        CountriesDAO countryDAO193 = new CountriesDAOBuilder()
                .setCode("SO")
                .setTitle("Somalia")
                .setOrder("193")
                .build();
        CountriesDAO countryDAO194 = new CountriesDAOBuilder()
                .setCode("ZA")
                .setTitle("South Africa")
                .setOrder("194")
                .build();
        CountriesDAO countryDAO195 = new CountriesDAOBuilder()
                .setCode("GS")
                .setTitle("South Georgia South Sandwich Islands")
                .setOrder("195")
                .build();
        CountriesDAO countryDAO196 = new CountriesDAOBuilder()
                .setCode("ES")
                .setTitle("Spain")
                .setOrder("196")
                .build();
        CountriesDAO countryDAO197 = new CountriesDAOBuilder()
                .setCode("LK")
                .setTitle("Sri Lanka")
                .setOrder("197")
                .build();
        CountriesDAO countryDAO198 = new CountriesDAOBuilder()
                .setCode("SH")
                .setTitle("St. Helena")
                .setOrder("198")
                .build();
        CountriesDAO countryDAO199 = new CountriesDAOBuilder()
                .setCode("PM")
                .setTitle("St. Pierre and Miquelon")
                .setOrder("199")
                .build();
        CountriesDAO countryDAO200 = new CountriesDAOBuilder()
                .setCode("SD")
                .setTitle("Sudan")
                .setOrder("200")
                .build();
        CountriesDAO countryDAO201 = new CountriesDAOBuilder()
                .setCode("SR")
                .setTitle("Suriname")
                .setOrder("201")
                .build();
        CountriesDAO countryDAO202 = new CountriesDAOBuilder()
                .setCode("SJ")
                .setTitle("Svalbarn and Jan Mayen Islands")
                .setOrder("202")
                .build();
        CountriesDAO countryDAO203 = new CountriesDAOBuilder()
                .setCode("SZ")
                .setTitle("Swaziland")
                .setOrder("203")
                .build();
        CountriesDAO countryDAO204 = new CountriesDAOBuilder()
                .setCode("SE")
                .setTitle("Sweden")
                .setOrder("204")
                .build();
        CountriesDAO countryDAO205 = new CountriesDAOBuilder()
                .setCode("CH")
                .setTitle("Switzerland")
                .setOrder("205")
                .build();
        CountriesDAO countryDAO206 = new CountriesDAOBuilder()
                .setCode("SY")
                .setTitle("Syrian Arab Republic")
                .setOrder("206")
                .build();
        CountriesDAO countryDAO207 = new CountriesDAOBuilder()
                .setCode("TW")
                .setTitle("Taiwan")
                .setOrder("207")
                .build();
        CountriesDAO countryDAO208 = new CountriesDAOBuilder()
                .setCode("TJ")
                .setTitle("Tajikistan")
                .setOrder("208")
                .build();
        CountriesDAO countryDAO209 = new CountriesDAOBuilder()
                .setCode("TZ")
                .setTitle("Tanzania, United Republic of")
                .setOrder("209")
                .build();
        CountriesDAO countryDAO210 = new CountriesDAOBuilder()
                .setCode("TH")
                .setTitle("Thailand")
                .setOrder("210")
                .build();
        CountriesDAO countryDAO211 = new CountriesDAOBuilder()
                .setCode("TG")
                .setTitle("Togo")
                .setOrder("211")
                .build();
        CountriesDAO countryDAO212 = new CountriesDAOBuilder()
                .setCode("TK")
                .setTitle("Tokelau")
                .setOrder("212")
                .build();
        CountriesDAO countryDAO213 = new CountriesDAOBuilder()
                .setCode("TO")
                .setTitle("Tonga")
                .setOrder("213")
                .build();
        CountriesDAO countryDAO214 = new CountriesDAOBuilder()
                .setCode("TT")
                .setTitle("Trinidad and Tobago")
                .setOrder("214")
                .build();
        CountriesDAO countryDAO215 = new CountriesDAOBuilder()
                .setCode("TN")
                .setTitle("Tunisia")
                .setOrder("215")
                .build();
        CountriesDAO countryDAO216 = new CountriesDAOBuilder()
                .setCode("TR")
                .setTitle("Turkey")
                .setOrder("216")
                .build();
        CountriesDAO countryDAO217 = new CountriesDAOBuilder()
                .setCode("TM")
                .setTitle("Turkmenistan")
                .setOrder("217")
                .build();
        CountriesDAO countryDAO218 = new CountriesDAOBuilder()
                .setCode("TC")
                .setTitle("Turks and Caicos Islands")
                .setOrder("218")
                .build();
        CountriesDAO countryDAO219 = new CountriesDAOBuilder()
                .setCode("TV")
                .setTitle("Tuvalu")
                .setOrder("219")
                .build();
        CountriesDAO countryDAO220 = new CountriesDAOBuilder()
                .setCode("UG")
                .setTitle("Uganda")
                .setOrder("220")
                .build();
        CountriesDAO countryDAO221 = new CountriesDAOBuilder()
                .setCode("UA")
                .setTitle("Ukraine")
                .setOrder("221")
                .build();
        CountriesDAO countryDAO222 = new CountriesDAOBuilder()
                .setCode("AE")
                .setTitle("United Arab Emirates")
                .setOrder("222")
                .build();
        CountriesDAO countryDAO223 = new CountriesDAOBuilder()
                .setCode("GB")
                .setTitle("United Kingdom")
                .setOrder("223")
                .build();
        CountriesDAO countryDAO224 = new CountriesDAOBuilder()
                .setCode("UM")
                .setTitle("United States minor outlying islands")
                .setOrder("224")
                .build();
        CountriesDAO countryDAO225 = new CountriesDAOBuilder()
                .setCode("UY")
                .setTitle("Uruguay")
                .setOrder("225")
                .build();
        CountriesDAO countryDAO226 = new CountriesDAOBuilder()
                .setCode("UZ")
                .setTitle("Uzbekistan")
                .setOrder("226")
                .build();
        CountriesDAO countryDAO227 = new CountriesDAOBuilder()
                .setCode("VU")
                .setTitle("Vanuatu")
                .setOrder("227")
                .build();
        CountriesDAO countryDAO228 = new CountriesDAOBuilder()
                .setCode("VA")
                .setTitle("Vatican City State")
                .setOrder("228")
                .build();
        CountriesDAO countryDAO229 = new CountriesDAOBuilder()
                .setCode("VE")
                .setTitle("Venezuela")
                .setOrder("229")
                .build();
        CountriesDAO countryDAO230 = new CountriesDAOBuilder()
                .setCode("VN")
                .setTitle("Vietnam")
                .setOrder("230")
                .build();
        CountriesDAO countryDAO231 = new CountriesDAOBuilder()
                .setCode("VG")
                .setTitle("Virigan Islands (British)")
                .setOrder("231")
                .build();
        CountriesDAO countryDAO232 = new CountriesDAOBuilder()
                .setCode("VI")
                .setTitle("Virgin Islands (U.S.)")
                .setOrder("232")
                .build();
        CountriesDAO countryDAO233 = new CountriesDAOBuilder()
                .setCode("WF")
                .setTitle("Wallis and Futuna Islands")
                .setOrder("233")
                .build();
        CountriesDAO countryDAO234 = new CountriesDAOBuilder()
                .setCode("EH")
                .setTitle("Western Sahara")
                .setOrder("234")
                .build();
        CountriesDAO countryDAO235 = new CountriesDAOBuilder()
                .setCode("YE")
                .setTitle("Yemen")
                .setOrder("235")
                .build();
        CountriesDAO countryDAO236 = new CountriesDAOBuilder()
                .setCode("YU")
                .setTitle("Yugoslavia")
                .setOrder("236")
                .build();
        CountriesDAO countryDAO237 = new CountriesDAOBuilder()
                .setCode("ZR")
                .setTitle("Zaire")
                .setOrder("237")
                .build();
        CountriesDAO countryDAO238 = new CountriesDAOBuilder()
                .setCode("ZM")
                .setTitle("Zambia")
                .setOrder("238")
                .build();
        CountriesDAO countryDAO239 = new CountriesDAOBuilder()
                .setCode("ZW")
                .setTitle("Zimbabwe")
                .setOrder("239")
                .build();

        List<CountriesDAO> countries = new ArrayList<>();
        countries.add(countryDAO1);
        countries.add(countryDAO2);
        countries.add(countryDAO3);
        countries.add(countryDAO4);
        countries.add(countryDAO5);
        countries.add(countryDAO6);
        countries.add(countryDAO7);
        countries.add(countryDAO8);
        countries.add(countryDAO9);
        countries.add(countryDAO10);
        countries.add(countryDAO11);
        countries.add(countryDAO12);
        countries.add(countryDAO13);
        countries.add(countryDAO14);
        countries.add(countryDAO15);
        countries.add(countryDAO16);
        countries.add(countryDAO17);
        countries.add(countryDAO18);
        countries.add(countryDAO19);
        countries.add(countryDAO20);
        countries.add(countryDAO21);
        countries.add(countryDAO22);
        countries.add(countryDAO23);
        countries.add(countryDAO24);
        countries.add(countryDAO25);
        countries.add(countryDAO26);
        countries.add(countryDAO27);
        countries.add(countryDAO28);
        countries.add(countryDAO29);
        countries.add(countryDAO30);
        countries.add(countryDAO31);
        countries.add(countryDAO32);
        countries.add(countryDAO33);
        countries.add(countryDAO34);
        countries.add(countryDAO35);
        countries.add(countryDAO36);
        countries.add(countryDAO37);
        countries.add(countryDAO38);
        countries.add(countryDAO39);
        countries.add(countryDAO40);
        countries.add(countryDAO41);
        countries.add(countryDAO42);
        countries.add(countryDAO43);
        countries.add(countryDAO44);
        countries.add(countryDAO45);
        countries.add(countryDAO46);
        countries.add(countryDAO47);
        countries.add(countryDAO48);
        countries.add(countryDAO49);
        countries.add(countryDAO50);
        countries.add(countryDAO51);
        countries.add(countryDAO52);
        countries.add(countryDAO53);
        countries.add(countryDAO54);
        countries.add(countryDAO55);
        countries.add(countryDAO56);
        countries.add(countryDAO57);
        countries.add(countryDAO58);
        countries.add(countryDAO59);
        countries.add(countryDAO60);
        countries.add(countryDAO61);
        countries.add(countryDAO62);
        countries.add(countryDAO63);
        countries.add(countryDAO64);
        countries.add(countryDAO65);
        countries.add(countryDAO66);
        countries.add(countryDAO67);
        countries.add(countryDAO68);
        countries.add(countryDAO69);
        countries.add(countryDAO70);
        countries.add(countryDAO71);
        countries.add(countryDAO72);
        countries.add(countryDAO73);
        countries.add(countryDAO74);
        countries.add(countryDAO75);
        countries.add(countryDAO76);
        countries.add(countryDAO77);
        countries.add(countryDAO78);
        countries.add(countryDAO79);
        countries.add(countryDAO80);
        countries.add(countryDAO81);
        countries.add(countryDAO82);
        countries.add(countryDAO83);
        countries.add(countryDAO84);
        countries.add(countryDAO85);
        countries.add(countryDAO86);
        countries.add(countryDAO87);
        countries.add(countryDAO88);
        countries.add(countryDAO89);
        countries.add(countryDAO90);
        countries.add(countryDAO91);
        countries.add(countryDAO92);
        countries.add(countryDAO93);
        countries.add(countryDAO94);
        countries.add(countryDAO95);
        countries.add(countryDAO96);
        countries.add(countryDAO97);
        countries.add(countryDAO98);
        countries.add(countryDAO99);
        countries.add(countryDAO100);
        countries.add(countryDAO101);
        countries.add(countryDAO102);
        countries.add(countryDAO103);
        countries.add(countryDAO104);
        countries.add(countryDAO105);
        countries.add(countryDAO106);
        countries.add(countryDAO107);
        countries.add(countryDAO108);
        countries.add(countryDAO109);
        countries.add(countryDAO110);
        countries.add(countryDAO111);
        countries.add(countryDAO112);
        countries.add(countryDAO113);
        countries.add(countryDAO114);
        countries.add(countryDAO115);
        countries.add(countryDAO116);
        countries.add(countryDAO117);
        countries.add(countryDAO118);
        countries.add(countryDAO119);
        countries.add(countryDAO120);
        countries.add(countryDAO121);
        countries.add(countryDAO122);
        countries.add(countryDAO123);
        countries.add(countryDAO124);
        countries.add(countryDAO125);
        countries.add(countryDAO126);
        countries.add(countryDAO127);
        countries.add(countryDAO128);
        countries.add(countryDAO129);
        countries.add(countryDAO130);
        countries.add(countryDAO131);
        countries.add(countryDAO122);
        countries.add(countryDAO133);
        countries.add(countryDAO134);
        countries.add(countryDAO135);
        countries.add(countryDAO136);
        countries.add(countryDAO137);
        countries.add(countryDAO138);
        countries.add(countryDAO139);
        countries.add(countryDAO140);
        countries.add(countryDAO141);
        countries.add(countryDAO142);
        countries.add(countryDAO143);
        countries.add(countryDAO144);
        countries.add(countryDAO145);
        countries.add(countryDAO146);
        countries.add(countryDAO147);
        countries.add(countryDAO148);
        countries.add(countryDAO149);
        countries.add(countryDAO150);
        countries.add(countryDAO151);
        countries.add(countryDAO152);
        countries.add(countryDAO153);
        countries.add(countryDAO154);
        countries.add(countryDAO155);
        countries.add(countryDAO156);
        countries.add(countryDAO157);
        countries.add(countryDAO158);
        countries.add(countryDAO159);
        countries.add(countryDAO160);
        countries.add(countryDAO161);
        countries.add(countryDAO162);
        countries.add(countryDAO163);
        countries.add(countryDAO164);
        countries.add(countryDAO165);
        countries.add(countryDAO166);
        countries.add(countryDAO167);
        countries.add(countryDAO168);
        countries.add(countryDAO169);
        countries.add(countryDAO170);
        countries.add(countryDAO171);
        countries.add(countryDAO172);
        countries.add(countryDAO173);
        countries.add(countryDAO174);
        countries.add(countryDAO175);
        countries.add(countryDAO176);
        countries.add(countryDAO177);
        countries.add(countryDAO178);
        countries.add(countryDAO179);
        countries.add(countryDAO180);
        countries.add(countryDAO181);
        countries.add(countryDAO182);
        countries.add(countryDAO183);
        countries.add(countryDAO184);
        countries.add(countryDAO185);
        countries.add(countryDAO186);
        countries.add(countryDAO187);
        countries.add(countryDAO188);
        countries.add(countryDAO189);
        countries.add(countryDAO190);
        countries.add(countryDAO191);
        countries.add(countryDAO192);
        countries.add(countryDAO193);
        countries.add(countryDAO194);
        countries.add(countryDAO195);
        countries.add(countryDAO196);
        countries.add(countryDAO197);
        countries.add(countryDAO198);
        countries.add(countryDAO199);
        countries.add(countryDAO200);
        countries.add(countryDAO201);
        countries.add(countryDAO202);
        countries.add(countryDAO203);
        countries.add(countryDAO204);
        countries.add(countryDAO205);
        countries.add(countryDAO206);
        countries.add(countryDAO207);
        countries.add(countryDAO208);
        countries.add(countryDAO209);
        countries.add(countryDAO210);
        countries.add(countryDAO211);
        countries.add(countryDAO212);
        countries.add(countryDAO213);
        countries.add(countryDAO214);
        countries.add(countryDAO215);
        countries.add(countryDAO216);
        countries.add(countryDAO217);
        countries.add(countryDAO218);
        countries.add(countryDAO219);
        countries.add(countryDAO220);
        countries.add(countryDAO221);
        countries.add(countryDAO222);
        countries.add(countryDAO223);
        countries.add(countryDAO224);
        countries.add(countryDAO225);
        countries.add(countryDAO226);
        countries.add(countryDAO227);
        countries.add(countryDAO228);
        countries.add(countryDAO229);
        countries.add(countryDAO230);
        countries.add(countryDAO231);
        countries.add(countryDAO232);
        countries.add(countryDAO233);
        countries.add(countryDAO234);
        countries.add(countryDAO235);
        countries.add(countryDAO236);
        countries.add(countryDAO237);
        countries.add(countryDAO238);
        countries.add(countryDAO239);
        mongoCollection.insertMany(countries);

    }

//    @Test
    public void photoCategories() {
        MongoCollection<PhotoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PHOTOCATEGORIES.toString(), PhotoCategoryDAO.class);
        PhotoCategoryDAO category1 = new PhotoCategoryDAOBuilder()
                .setCategoryId("1")
                .setTitle("Artisan Crafts")
                .build();
        PhotoCategoryDAO category2 = new PhotoCategoryDAOBuilder()
                .setCategoryId("3")
                .setTitle("Cartoons & Comics")
                .build();
        PhotoCategoryDAO category3 = new PhotoCategoryDAOBuilder()
                .setCategoryId("3")
                .setTitle("Comedy")
                .build();
        PhotoCategoryDAO category4 = new PhotoCategoryDAOBuilder()
                .setCategoryId("4")
                .setTitle("Contests")
                .build();
        PhotoCategoryDAO category5 = new PhotoCategoryDAOBuilder()
                .setCategoryId("5")
                .setTitle("Designs & Interfaces")
                .build();

        PhotoCategoryDAO category6 = new PhotoCategoryDAOBuilder()
                .setCategoryId("6")
                .setTitle("Digital Art")
                .build();
        PhotoCategoryDAO category7 = new PhotoCategoryDAOBuilder()
                .setCategoryId("7")
                .setTitle("Fan Art")
                .build();

        PhotoCategoryDAO category8 = new PhotoCategoryDAOBuilder()
                .setCategoryId("8")
                .setTitle("Film & Animation")
                .build();
        PhotoCategoryDAO category9 = new PhotoCategoryDAOBuilder()
                .setCategoryId("9")
                .setTitle("Fractal Art")
                .build();
        PhotoCategoryDAO category10 = new PhotoCategoryDAOBuilder()
                .setCategoryId("10")
                .setTitle("Game Development Art")
                .build();
        PhotoCategoryDAO category11 = new PhotoCategoryDAOBuilder()
                .setCategoryId("11")
                .setTitle("Literature")
                .build();
        PhotoCategoryDAO category12 = new PhotoCategoryDAOBuilder()
                .setCategoryId("12")
                .setTitle("People")
                .build();

        PhotoCategoryDAO category13 = new PhotoCategoryDAOBuilder()
                .setCategoryId("13")
                .setTitle("Pets & Animals")
                .build();
        PhotoCategoryDAO category14 = new PhotoCategoryDAOBuilder()
                .setCategoryId("14")
                .setTitle("Sports")
                .build();

        PhotoCategoryDAO category15 = new PhotoCategoryDAOBuilder()
                .setCategoryId("15")
                .setTitle("Traditional Art")
                .build();

        List<PhotoCategoryDAO> photoCategories = new ArrayList<>();
        photoCategories.add(category1);
        photoCategories.add(category2);
        photoCategories.add(category3);
        photoCategories.add(category4);
        photoCategories.add(category5);
        photoCategories.add(category6);
        photoCategories.add(category7);
        photoCategories.add(category8);
        photoCategories.add(category9);
        photoCategories.add(category10);
        photoCategories.add(category11);
        photoCategories.add(category12);
        photoCategories.add(category13);
        photoCategories.add(category14);
        photoCategories.add(category15);
        mongoCollection.insertMany(photoCategories);
    }

//    @Test
    public void videoCategories() {
        MongoCollection<VideoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOCATEGORIES.toString(), VideoCategoryDAO.class);

        VideoCategoryDAO vedioCategory1 = new VideoCategoryDAOBuilder()
                .setCategoryId("1")
                .setTitle("Quran")
                .build();
        VideoCategoryDAO vedioCategory2 = new VideoCategoryDAOBuilder()
                .setCategoryId("2")
                .setTitle("Hadith")
                .build();
        VideoCategoryDAO vedioCategory3 = new VideoCategoryDAOBuilder()
                .setCategoryId("3")
                .setTitle("Islam")
                .build();
        VideoCategoryDAO vedioCategory4 = new VideoCategoryDAOBuilder()
                .setCategoryId("4")
                .setTitle("Dawah")
                .build();
        VideoCategoryDAO vedioCategory5 = new VideoCategoryDAOBuilder()
                .setCategoryId("5")
                .setTitle("Islamic History")
                .build();
        VideoCategoryDAO vedioCategory6 = new VideoCategoryDAOBuilder()
                .setCategoryId("6")
                .setTitle("Scientific Contribution of Islam")
                .build();
        VideoCategoryDAO vedioCategory7 = new VideoCategoryDAOBuilder()
                .setCategoryId("7")
                .setTitle("Nasheeds")
                .build();
        VideoCategoryDAO vedioCategory8 = new VideoCategoryDAOBuilder()
                .setCategoryId("8")
                .setTitle("Education")
                .build();
        VideoCategoryDAO vedioCategory9 = new VideoCategoryDAOBuilder()
                .setCategoryId("9")
                .setTitle("News & Politics")
                .build();
        VideoCategoryDAO vedioCategory10 = new VideoCategoryDAOBuilder()
                .setCategoryId("10")
                .setTitle("Non-profits & Activism")
                .build();
        VideoCategoryDAO vedioCategory11 = new VideoCategoryDAOBuilder()
                .setCategoryId("11")
                .setTitle("People & Blogs")
                .build();
        VideoCategoryDAO vedioCategory12 = new VideoCategoryDAOBuilder()
                .setCategoryId("12")
                .setTitle("Pets & Animals")
                .build();
        VideoCategoryDAO vedioCategory13 = new VideoCategoryDAOBuilder()
                .setCategoryId("13")
                .setTitle("Sports")
                .build();
        VideoCategoryDAO vedioCategory14 = new VideoCategoryDAOBuilder()
                .setCategoryId("14")
                .setTitle("Travels & Events")
                .build();

        VideoCategoryDAO vedioCategory15 = new VideoCategoryDAOBuilder()
                .setCategoryId("15")
                .setTitle("Styles")
                .build();
        VideoCategoryDAO vedioCategory16 = new VideoCategoryDAOBuilder()
                .setCategoryId("16")
                .setTitle("Entertainment & Arts")
                .build();

        List<VideoCategoryDAO> videoCategories = new ArrayList<>();
        videoCategories.add(vedioCategory1);
        videoCategories.add(vedioCategory2);
        videoCategories.add(vedioCategory3);
        videoCategories.add(vedioCategory4);
        videoCategories.add(vedioCategory5);
        videoCategories.add(vedioCategory6);
        videoCategories.add(vedioCategory7);
        videoCategories.add(vedioCategory8);
        videoCategories.add(vedioCategory9);
        videoCategories.add(vedioCategory10);
        videoCategories.add(vedioCategory11);
        videoCategories.add(vedioCategory12);
        videoCategories.add(vedioCategory13);
        videoCategories.add(vedioCategory14);
        videoCategories.add(vedioCategory15);
        videoCategories.add(vedioCategory16);
        mongoCollection.insertMany(videoCategories);
    }

//    @Test
    public void addUser() {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        UserDAO userInfo1 = new UserDAOBuilder()
                .setFirstName("Nazmul")
                .setLastName("Hasan")
                .setUserId("1")
                .build();
        UserDAO userInfo3 = new UserDAOBuilder()
                .setFirstName("Alamgir")
                .setLastName("Kabir")
                .setUserId("3")
                .build();
        UserDAO userInfo4 = new UserDAOBuilder()
                .setFirstName("Rashida")
                .setLastName("Sultana")
                .setUserId("4")
                .build();
        UserDAO userInfo5 = new UserDAOBuilder()
                .setFirstName("Keya")
                .setLastName("Moni")
                .setUserId("5")
                .build();
        UserDAO userInfo6 = new UserDAOBuilder()
                .setFirstName("Shemin")
                .setLastName("Haque")
                .setUserId("6")
                .build();
        UserDAO userInfo7 = new UserDAOBuilder()
                .setFirstName("Salma")
                .setLastName("Khatun")
                .setUserId("7")
                .build();
        UserDAO userInfo8 = new UserDAOBuilder()
                .setFirstName("Shobuj")
                .setLastName("Gope")
                .setUserId("8")
                .build();

        List<UserDAO> users = new ArrayList<>();
        users.add(userInfo1);
        users.add(userInfo4);
        users.add(userInfo5);
        users.add(userInfo6);
        users.add(userInfo7);
        users.add(userInfo8);
        mongoCollection.insertMany(users);
    }

//    @Test
    public void storeTestCountries() {
        DBConnection.getInstance().getConnection();
        MongoCollection<CountriesDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.COUNTRIES.toString(), CountriesDAO.class);

        CountriesDAO countryDAO1 = new CountriesDAOBuilder()
                .setCode("US")
                .setTitle("United States")
                .setOrder("1")
                .setGmtOffset("-11:00")
                .build();
        CountriesDAO countryDAO2 = new CountriesDAOBuilder()
                .setCode("BD")
                .setTitle("Bangladesh")
                .setOrder("2")
                .setGmtOffset("+08:00")
                .build();
        CountriesDAO countryDAO3 = new CountriesDAOBuilder()
                .setCode("AU")
                .setTitle("Australia")
                .setOrder("3")
                .setGmtOffset("+11:00")
                .build();
        List<CountriesDAO> countries = new ArrayList<>();
        countries.add(countryDAO1);
        countries.add(countryDAO2);
        countries.add(countryDAO3);
        mongoCollection.insertMany(countries);
    }

      @Test
    public void storeTestPageCategory() {
        DBConnection.getInstance().getConnection();
        MongoCollection<PageCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PAGECATEGORIES.toString(), PageCategoryDAO.class);

        PageCategoryDAO categoryDAO1 = new PageCategoryDAOBuilder()
                .setCategoryId("1")
                .setTitle("Brand")
                .build();
        PageCategoryDAO categoryDAO2 = new PageCategoryDAOBuilder()
                .setCategoryId("2")
                .setTitle("Product")
                .build();
        PageCategoryDAO categoryDAO3 = new PageCategoryDAOBuilder()
                .setCategoryId("3")
                .setTitle("Group")
                .build();
        PageCategoryDAO categoryDAO4 = new PageCategoryDAOBuilder()
                .setCategoryId("4")
                .setTitle("Community")
                .build();
        PageCategoryDAO categoryDAO5 = new PageCategoryDAOBuilder()
                .setCategoryId("5")
                .setTitle("Business")
                .build();
        PageCategoryDAO categoryDAO6 = new PageCategoryDAOBuilder()
                .setCategoryId("6")
                .setTitle("Place")
                .build();
        PageCategoryDAO categoryDAO7 = new PageCategoryDAOBuilder()
                .setCategoryId("7")
                .setTitle("Entertainment")
                .build();
        PageCategoryDAO categoryDAO8 = new PageCategoryDAOBuilder()
                .setCategoryId("8")
                .setTitle("Commpany")
                .build();
        PageCategoryDAO categoryDAO9 = new PageCategoryDAOBuilder()
                .setCategoryId("9")
                .setTitle("Organization")
                .build();
        PageCategoryDAO categoryDAO10 = new PageCategoryDAOBuilder()
                .setCategoryId("10")
                .setTitle("Institution")
                .build();
        PageCategoryDAO categoryDAO11 = new PageCategoryDAOBuilder()
                .setCategoryId("11")
                .setTitle("Artist or Band")
                .build();
        PageCategoryDAO categoryDAO12 = new PageCategoryDAOBuilder()
                .setCategoryId("12")
                .setTitle("Public Figure")
                .build();

        List<PageCategoryDAO> categoryList = new ArrayList<>();
        categoryList.add(categoryDAO1);
        categoryList.add(categoryDAO2);
        categoryList.add(categoryDAO3);
        categoryList.add(categoryDAO4);
        categoryList.add(categoryDAO5);
        categoryList.add(categoryDAO6);
        categoryList.add(categoryDAO7);
        categoryList.add(categoryDAO8);
        categoryList.add(categoryDAO9);
        categoryList.add(categoryDAO10);
        categoryList.add(categoryDAO11);
        categoryList.add(categoryDAO12);
        mongoCollection.insertMany(categoryList);
    }

    @Test
    public void storeTestPageSubCategory() {
        DBConnection.getInstance().getConnection();
        MongoCollection<PageSubCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PAGESUBCATEGORIES.toString(), PageSubCategoryDAO.class);

        PageSubCategoryDAO subCategoryDAO1 = new PageSubCategoryDAOBuilder()
                .setCategoryId("1")
                .setSubCategoryId("1")
                .setTitle("car")
                .build();
        PageSubCategoryDAO subCategoryDAO2 = new PageSubCategoryDAOBuilder()
                .setCategoryId("1")
                .setSubCategoryId("2")
                .setTitle("Colthing")
                .build();
        PageSubCategoryDAO subCategoryDAO3 = new PageSubCategoryDAOBuilder()
                .setCategoryId("2")
                .setSubCategoryId("1")
                .setTitle("Product1")
                .build();
        PageSubCategoryDAO subCategoryDAO4 = new PageSubCategoryDAOBuilder()
                .setCategoryId("2")
                .setSubCategoryId("2")
                .setTitle("Product2")
                .build();
        PageSubCategoryDAO subCategoryDAO5 = new PageSubCategoryDAOBuilder()
                .setCategoryId("5")
                .setSubCategoryId("1")
                .setTitle("Business1")
                .build();
        PageSubCategoryDAO subCategoryDAO6 = new PageSubCategoryDAOBuilder()
                .setCategoryId("5")
                .setSubCategoryId("2")
                .setTitle("Business2")
                .build();
        PageSubCategoryDAO subCategoryDAO7 = new PageSubCategoryDAOBuilder()
                .setCategoryId("6")
                .setSubCategoryId("1")
                .setTitle("Place1")
                .build();
        PageSubCategoryDAO subCategoryDAO8 = new PageSubCategoryDAOBuilder()
                .setCategoryId("6")
                .setSubCategoryId("2")
                .setTitle("Place2")
                .build();
        PageSubCategoryDAO subCategoryDAO9 = new PageSubCategoryDAOBuilder()
                .setCategoryId("7")
                .setSubCategoryId("1")
                .setTitle("Entertainment1")
                .build();
        PageSubCategoryDAO subCategoryDAO10 = new PageSubCategoryDAOBuilder()
                .setCategoryId("7")
                .setSubCategoryId("2")
                .setTitle("Entertainment2")
                .build();
        PageSubCategoryDAO subCategoryDAO11 = new PageSubCategoryDAOBuilder()
                .setCategoryId("8")
                .setSubCategoryId("1")
                .setTitle("Commpany1")
                .build();
        PageSubCategoryDAO subCategoryDAO12 = new PageSubCategoryDAOBuilder()
                .setCategoryId("8")
                .setSubCategoryId("2")
                .setTitle("Commpany2")
                .build();
        PageSubCategoryDAO subCategoryDAO13 = new PageSubCategoryDAOBuilder()
                .setCategoryId("11")
                .setSubCategoryId("1")
                .setTitle("artist")
                .build();
        PageSubCategoryDAO subCategoryDAO14 = new PageSubCategoryDAOBuilder()
                .setCategoryId("11")
                .setSubCategoryId("2")
                .setTitle("band")
                .build();
        PageSubCategoryDAO subCategoryDAO15 = new PageSubCategoryDAOBuilder()
                .setCategoryId("12")
                .setSubCategoryId("1")
                .setTitle("public")
                .build();
        PageSubCategoryDAO subCategoryDAO16 = new PageSubCategoryDAOBuilder()
                .setCategoryId("12")
                .setSubCategoryId("2")
                .setTitle("figure")
                .build();

        List<PageSubCategoryDAO> categoryList = new ArrayList<>();
        categoryList.add(subCategoryDAO1);
        categoryList.add(subCategoryDAO2);
        categoryList.add(subCategoryDAO3);
        categoryList.add(subCategoryDAO4);
        categoryList.add(subCategoryDAO5);
        categoryList.add(subCategoryDAO6);
        categoryList.add(subCategoryDAO7);
        categoryList.add(subCategoryDAO8);
        categoryList.add(subCategoryDAO9);
        categoryList.add(subCategoryDAO10);
        categoryList.add(subCategoryDAO11);
        categoryList.add(subCategoryDAO12);
        categoryList.add(subCategoryDAO13);
        categoryList.add(subCategoryDAO14);
        categoryList.add(subCategoryDAO15);
        categoryList.add(subCategoryDAO16);
        mongoCollection.insertMany(categoryList);
    }

}
