package com.layout.dbOperation;

import org.bson.Document;
import com.layout.config.MongoConnection;
import com.layout.dao.Site;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SiteDAO implements Site {

    private MongoDatabase database;

    public SiteDAO() {
        database = MongoConnection.getDatabase();
    }

    // ================= GET ALL SITES =================
    @Override
    public void getAllSites() {

        MongoCollection<Document> siteCol = database.getCollection("sites");

        for (Document site : siteCol.find()
                .projection(new Document("_id", 0))) {
            System.out.println("-----------------------------");
            // System.out.println(site.toJson(
            // JsonWriterSettings.builder().indent(true).build()));
            System.out.println("Site ID : " + site.getInteger("siteId"));
            System.out.println("length : " + site.getInteger("length"));
            System.out.println("width : " + site.getInteger("width"));
            System.out.println("area : " + site.getInteger("area"));
            System.out.println("Type : " + site.getString("siteType"));
            System.out.println("Status : " + site.getString("siteStatus"));
        }
    }

    // ================= GET AVAILABLE SITES =================
    @Override
    public void getAvailableSites() {

        MongoCollection<Document> siteCol = database.getCollection("sites");
        System.out.println("\n--- Available Sites ---");
        for (Document site : siteCol.find(new Document("siteStatus", "AVAILABLE"))
                .projection(new Document("_id", 0))) {
            System.out.println("-----------------------------");
            System.out.println("Site ID : " + site.getInteger("siteId"));
            System.out.println("length : " + site.getInteger("length"));
            System.out.println("width : " + site.getInteger("width"));
            System.out.println("area : " + site.getInteger("area"));
            System.out.println("Type : " + site.getString("siteType"));
        }
    }

    // ================= CALCULATE AREA =================
    @Override
    public int calculateArea(int length, int width) {
        return length * width;
    }

    // ================= GET RATE =================
    @Override
    public int getRate(String siteType) {

        if ("OPEN".equalsIgnoreCase(siteType))
            return 6;
        else
            return 9;
    }
}
