/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author Sampan-IT
 */
public class BasicProfileService {

    public BasicProfileService() {
    }

    public String getWorkPlaces(String userId) {
        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor workPlacesCursor = ob.getWorkPlaces(userId);
        Document doc = new Document();
        while (workPlacesCursor.hasNext()) {
            doc = (Document) workPlacesCursor.next();
            return doc.toJson();

        }
        return null;
    }

    public String getSchools() {
        String userId = "556311458267404811000028";
        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor schoolsCursor = ob.getSchools(userId);
        Document doc = new Document();
        while (schoolsCursor.hasNext()) {
            doc = (Document) schoolsCursor.next();
            return doc.toJson();

        }
        return null;

    }

    public String getColleges(String userId) {
        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor collegesCursor = ob.getColleges(userId);
        Document doc = new Document();
        while (collegesCursor.hasNext()) {
            doc = (Document) collegesCursor.next();
            return doc.toJson();

        }
        return null;
    }

    public String getUniversities(String userId) {
        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor universitiesCursor = ob.getUniversities(userId);
        Document doc = new Document();
        while (universitiesCursor.hasNext()) {
            doc = (Document) universitiesCursor.next();
            return doc.toJson();

        }
        return null;

    }

    public String getProfessionalSkills(String userId) {
        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor pSkillsCursor = ob.getProfessionalSkills(userId);
        Document doc = new Document();
        while (pSkillsCursor.hasNext()) {
            doc = (Document) pSkillsCursor.next();
            return doc.toJson();

        }
        return null;
    }

    public String getBasicInfo(String userId) {
        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor basicInfoCursor = ob.getBasicInfo(userId);
        Document doc = new Document();
        while (basicInfoCursor.hasNext()) {
            doc = (Document) basicInfoCursor.next();
            return doc.toJson();

        }
        return null;

    }

    public String getSecurityQuestion(String userId) {
        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor sQuentionCursor = ob.getSecurityQuestion(userId);
        Document doc = new Document();
        while (sQuentionCursor.hasNext()) {
            doc = (Document) sQuentionCursor.next();
            return doc.toJson();

        }
        return null;

    }

    public String getPlaces(String userId) {
        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor placesCursor = ob.getPlaces(userId);
        Document doc = new Document();
        while (placesCursor.hasNext()) {
            doc = (Document) placesCursor.next();
            return doc.toJson();

        }
        return null;
    }

    public void getOverview() {
        String userId = "556311458267404811000028";
        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor overviewCursor = ob.getOverview(userId);
        Document doc = new Document();
        while (overviewCursor.hasNext()) {
            doc = (Document) overviewCursor.next();
            System.out.println(doc);

        }

    }

    public String getAboutInfo(String userId) {
        BasicProfileService ob = new BasicProfileService();
        String workplaces = ob.getWorkPlaces(userId);
        String universities = ob.getUniversities(userId);
        String colleges = ob.getColleges(userId);
        String schools = ob.getSchools();
        String pSkills = ob.getProfessionalSkills(userId);

        Document aboutInfo = new Document();
        aboutInfo.put("workPlaces", workplaces);
        aboutInfo.put("universiteis", universities);
        aboutInfo.put("colleges", colleges);
        aboutInfo.put("schools", schools);
        aboutInfo.put("pSkills", pSkills);
        return aboutInfo.toJson();

    }

    public void updateWorkPlace() {
        Document document = new Document();
        String userId = "556311458267404811000028";
        Document workPlace = new Document();
        workPlace.append("company", "Shampan-it");
        workPlace.append("position", "Software Engineer");
        workPlace.append("city", "Gazipur");
        workPlace.append("description", "I like my bosses");
        workPlace.append("time_period", "20-01-15");
        document.put("work_place", workPlace);
        System.out.println(document);

        BasicPofileModel ob = new BasicPofileModel();
        MongoCursor workPlaces = ob.getWorkPlaces(userId);
        Document doc = new Document();
        while (workPlaces.hasNext()) {
            doc = (Document) workPlaces.next();

        }
        String work = doc.get("work_place").toString();

        System.out.println(work);
        ArrayList<Document> workPlaceList = new ArrayList<>();
        workPlaceList.add(doc);
        workPlaceList.add(document);
        System.out.println(workPlaceList);

        ob.updateBasicProfile(userId, document);

    }

}
