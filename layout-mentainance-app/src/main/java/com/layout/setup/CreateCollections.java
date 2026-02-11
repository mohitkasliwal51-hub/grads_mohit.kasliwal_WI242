package com.layout.setup;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CreateCollections {

        public static void main(String[] args) {

                MongoClient client = MongoClients.create("mongodb://localhost:27017");
                MongoDatabase db = client.getDatabase("layoutDB");

                db.drop();
                System.out.println("Old database cleared");

                MongoCollection<Document> sitesCol = db.getCollection("sites");
                MongoCollection<Document> ownersCol = db.getCollection("owners");
                MongoCollection<Document> maintenanceCol = db.getCollection("maintenance");
                MongoCollection<Document> adminsCol = db.getCollection("admins");

                /* ------------------ SITES ------------------ */
                List<Document> sites = new ArrayList<>();

                int siteId = 100;

                // First 10 : 40x60
                for (int i = 1; i <= 10; i++) {
                        sites.add(new Document("siteId", siteId++)
                                        .append("length", 40)
                                        .append("width", 60)
                                        .append("area", 2400)
                                        .append("siteType", "OPEN")
                                        .append("siteStatus", "AVAILABLE")
                                        .append("requestStatus", "none")
                                        .append("requestedBy", null));
                }

                // Next 10 : 30x50
                for (int i = 1; i <= 10; i++) {
                        sites.add(new Document("siteId", siteId++)
                                        .append("length", 30)
                                        .append("width", 50)
                                        .append("area", 1500)
                                        .append("siteType", "OPEN")
                                        .append("siteStatus", "AVAILABLE")
                                        .append("requestStatus", "none")
                                        .append("requestedBy", null));
                }

                // Last 15 : 30x40
                for (int i = 1; i <= 15; i++) {
                        sites.add(new Document("siteId", siteId++)
                                        .append("length", 30)
                                        .append("width", 40)
                                        .append("area", 1200)
                                        .append("siteType", "OPEN")
                                        .append("siteStatus", "AVAILABLE")
                                        .append("requestStatus", "none")
                                        .append("requestedBy", null));
                }

                sitesCol.insertMany(sites);
                System.out.println("Sites created");

                /* ------------------ OWNERS ------------------ */
                List<Document> owners = new ArrayList<>();

                owners.add(new Document("ownerId", 1)
                                .append("name", "Ramesh")
                                .append("username", "ramesh")
                                .append("password", "1234"));

                owners.add(new Document("ownerId", 2)
                                .append("name", "Suresh")
                                .append("username", "suresh")
                                .append("password", "1234"));

                owners.add(new Document("ownerId", 3)
                                .append("name", "Mahesh")
                                .append("username", "mahesh")
                                .append("password", "1234"));

                ownersCol.insertMany(owners);
                System.out.println("Owners created");

                /* ------------------ ADMIN ------------------ */
                adminsCol.insertOne(
                                new Document("adminId", "A1")
                                                .append("username", "admin")
                                                .append("password", "admin123"));
                System.out.println("Admin created");

                /* ------------------ MAINTENANCE ------------------ */
                for (Document site : sitesCol.find()) {

                        String siteType = site.getString("siteType");
                        int area = site.getInteger("area");

                        int ratePerSqft = siteType.equals("OCCUPIED") ? 9 : 6;

                        int total = area * ratePerSqft;

                        Document maintenance = new Document("siteId", site.getInteger("siteId"))
                                        .append("ownerId", null)
                                        .append("ratePerSqft", ratePerSqft)
                                        .append("totalAmount", total)
                                        .append("paidAmount", 0)
                                        .append("pendingAmount", total)
                                        .append("paymentStatus", "PENDING");

                        maintenanceCol.insertOne(maintenance);
                }

                System.out.println("Maintenance records created correctly");

                client.close();
                System.out.println("Database setup completed");
        }
}
