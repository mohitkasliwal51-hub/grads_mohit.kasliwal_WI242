package com.layout.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.layout.config.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AdminDAO {

        private MongoDatabase database;

        public AdminDAO() {
                database = MongoConnection.getDatabase();
        }

        // ================= ADMIN LOGIN =================
        public boolean adminLogin(String username, String password) {

                MongoCollection<Document> adminCol = database.getCollection("admins");

                Document query = new Document("username", username)
                                .append("password", password);

                Document admin = adminCol.find(query).first();

                return admin != null;
        }

        // ================= APPROVE SITE REQUEST =================
        public boolean approveSiteRequest(int siteId) {

                MongoCollection<Document> siteCol = database.getCollection("sites");
                MongoCollection<Document> mainCol = database.getCollection("maintenance");

                // Step 1: Get site document
                Document site = siteCol.find(new Document("siteId", siteId)
                                .append("requestStatus", "PENDING")).first();

                if (site == null) {
                        return false;
                }

                int ownerId = site.getInteger("requestedBy");

                // Step 2: Update site
                Document siteUpdate = new Document("$set",
                                new Document("siteStatus", "ASSIGNED")
                                                .append("requestStatus", "APPROVED")
                                                .append("requestedBy", null));

                siteCol.updateOne(new Document("siteId", siteId), siteUpdate);

                // Step 3: Update maintenance
                Document mainUpdate = new Document("$set",
                                new Document("ownerId", ownerId));

                mainCol.updateOne(new Document("siteId", siteId), mainUpdate);

                return true;
        }

        // ================= REJECT SITE REQUEST =================
        public boolean rejectSiteRequest(int siteId) {

                MongoCollection<Document> siteCol = database.getCollection("sites");

                Document query = new Document("siteId", siteId)
                                .append("requestStatus", "PENDING");

                Document update = new Document("$set",
                                new Document("siteStatus", "AVAILABLE")
                                                .append("requestStatus", "REJECTED")
                                                .append("requestedBy", null));

                return siteCol.updateOne(query, update).getModifiedCount() > 0;
        }

        // ================= CHANGE SITE TYPE =================
        public boolean changeSiteType(int siteId, String newType) {

                MongoCollection<Document> siteCol = database.getCollection("sites");
                MongoCollection<Document> mainCol = database.getCollection("maintenance");

                Document query = new Document("siteId", siteId);

                Document update = new Document("$set",
                                new Document("siteType", newType));

                Document site = siteCol.find(query).first();
                if (site == null)
                        return false;
                int area = site.getInteger("area");
                int rate = newType.equals("OPEN") ? 6 : 9;
                Document maintenance = mainCol.find(new Document("siteId", siteId)).first();
                if (maintenance == null)
                        return false;

                int paid = maintenance.getInteger("paidAmount");
                int total = area * rate;
                int pending = total - paid;

                String status = (pending <= 0) ? "PAID" : "PARTIAL";

                // update maintenance
                Document mainUpdate = new Document("$set",
                                new Document("ratePerSqft", rate)
                                                .append("totalAmount", total)
                                                .append("pendingAmount", pending)
                                                .append("paymentStatus", status));

                return siteCol.updateOne(query, update).getModifiedCount() > 0
                                && mainCol.updateOne(query, mainUpdate).getModifiedCount() > 0;
        }

        // ================= VIEW ALL OWNERS =================
        public void viewAllOwners() {

                MongoCollection<Document> ownerCol = database.getCollection("owners");
                System.out.println("\n--- All Owners ---");
                for (Document doc : ownerCol.find()
                                .projection(new Document("_id", 0))) {
                        System.out.println("-----------------------------");
                        System.out.println("Owner ID : " + doc.getInteger("ownerId"));
                        System.out.println("Name : " + doc.getString("name"));
                }
        }

        public void viewPendingSiteRequests() {

                MongoCollection<Document> siteCol = database.getCollection("sites");

                Document query = new Document("siteStatus", "REQUESTED")
                                .append("requestStatus", "PENDING");

                for (Document site : siteCol.find(query)) {

                        System.out.println("\nSite ID      : " + site.getInteger("siteId"));
                        System.out.println("Requested By : Owner " + site.getInteger("requestedBy"));
                        System.out.println("Site Type    : " + site.getString("siteType"));
                        System.out.println("Area         : " + site.getInteger("area"));
                }
        }

        public List<Document> getPendingSiteRequests() {
                MongoCollection<Document> siteCol = database.getCollection("sites");
                List<Document> list = new ArrayList<>();

                for (Document d : siteCol.find(new Document("siteStatus", "REQUESTED"))) {
                        list.add(d);
                }
                return list;
        }

}