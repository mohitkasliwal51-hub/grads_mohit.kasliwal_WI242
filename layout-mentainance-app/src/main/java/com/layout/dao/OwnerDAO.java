package com.layout.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.layout.config.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
// import com.mongodb.client.result.UpdateResult;

public class OwnerDAO {

        private MongoDatabase database;

        public OwnerDAO() {
                database = MongoConnection.getDatabase();
        }

        // ================= OWNER LOGIN =================
        public Document ownerLogin(String username, String password) {

                MongoCollection<Document> ownerCol = database.getCollection("owners");

                return ownerCol.find(
                                new Document("username", username)
                                                .append("password", password))
                                .first();
        }

        // ================= REQUEST SITE =================
        public boolean requestSite(int ownerId, int siteId) {

                MongoCollection<Document> siteCol = database.getCollection("sites");

                Document query = new Document("siteId", siteId)
                                .append("siteStatus", "AVAILABLE");

                Document update = new Document("$set",
                                new Document("siteStatus", "REQUESTED")
                                                .append("requestStatus", "PENDING")
                                                .append("requestedBy", ownerId));

                return siteCol.updateOne(query, update)
                                .getModifiedCount() > 0;
        }

        // ================= PAY MAINTENANCE =================
        public boolean payMaintenance(int ownerId, int siteId, int amount) {

                MongoCollection<Document> payMaintenance = database.getCollection("maintenance");

                // 1️⃣ Check if site is assigned to this owner
                Document site = payMaintenance.find(
                                new Document("siteId", siteId)
                                                .append("ownerId", ownerId))
                                .first();

                if (site == null) {
                        System.out.println("You are not authorized to pay for this site.");
                        return false;
                }

                // 2️⃣ Proceed with maintenance payment

                Document maintenance = payMaintenance.find(new Document("siteId", siteId)).first();

                if (maintenance == null)
                        return false;

                int paid = maintenance.getInteger("paidAmount");
                int total = maintenance.getInteger("totalAmount");

                int newPaid = paid + amount;
                if (newPaid > total) {
                        System.out.println("Payment exceeds total maintenance amount.");
                        return false;
                }

                int pending = total - newPaid;

                String status = (pending <= 0) ? "PAID" : "PARTIAL";

                Document update = new Document("$set",
                                new Document("paidAmount", newPaid)
                                                .append("pendingAmount", pending)
                                                .append("paymentStatus", status)
                                                .append("lastPaidBy", "OWNER"));

                return payMaintenance.updateOne(
                                new Document("siteId", siteId),
                                update).getModifiedCount() > 0;
        }

        // ================= VIEW MY SITE =================
        public List<Integer> getMySiteIds(int ownerId) {

                MongoCollection<Document> mainCol = database.getCollection("maintenance");
                List<Integer> siteIds = new ArrayList<>();

                for (Document doc : mainCol.find(new Document("ownerId", ownerId))) {
                        siteIds.add(doc.getInteger("siteId"));
                }

                return siteIds;
        }

        public void viewMySites(int ownerId) {

                List<Integer> siteIds = getMySiteIds(ownerId);

                if (siteIds.isEmpty()) {
                        System.out.println("No site assigned.");
                        return;
                }

                MongoCollection<Document> siteCol = database.getCollection("sites");

                for (Integer siteId : siteIds) {
                        Document site = siteCol.find(new Document("siteId", siteId))
                                        .projection(new Document("_id", 0))
                                        .first();

                        if (site != null) {
                                System.out.println("\n--- Site Details ---");
                                System.out.println("Site ID : " + site.getInteger("siteId"));
                                System.out.println("length : " + site.getInteger("length"));
                                System.out.println("width : " + site.getInteger("width"));
                                System.out.println("area : " + site.getInteger("area"));
                                System.out.println("Type : " + site.getString("siteType"));
                        }
                }
        }

        public void viewMyMaintenance(int ownerId) {

                MongoCollection<Document> mainCol = database.getCollection("maintenance");
                List<Integer> siteIds = getMySiteIds(ownerId);

                boolean found = false;
                System.out.println("\n--- Maintenance Details ---");
                for (Integer siteId : siteIds) {

                        Document maintenance = mainCol.find(
                                        new Document("siteId", siteId)).first();

                        if (maintenance != null) {
                                System.out.println("-----------------------------");
                                System.out.println("Site ID        : " + siteId);
                                System.out.println("Total Amount   : " + maintenance.getInteger("totalAmount"));
                                System.out.println("Paid Amount    : " + maintenance.getInteger("paidAmount"));
                                System.out.println("Pending Amount : " + maintenance.getInteger("pendingAmount"));
                                System.out.println("Status         : " + maintenance.getString("paymentStatus"));
                                found = true;
                        }
                }

                if (!found) {
                        System.out.println("No maintenance records found.");
                }
        }

        // ================= VIEW MAINTENANCE DETAILS FOR A SELECTED SITE
        // =================
        // public void viewMaintenance(int ownerId, int siteId) {

        // MongoCollection<Document> mainCol = database.getCollection("maintenance");

        // Document maintenance = mainCol.find(
        // new Document("siteId", siteId)
        // .append("ownerId", ownerId))
        // .first();

        // if (maintenance == null) {
        // System.out.println("No maintenance record or unauthorized access.");
        // return;
        // }

        // System.out.println("\n--- Maintenance Details ---");
        // System.out.println("Site ID : " + siteId);
        // System.out.println("Total Amount : " +
        // maintenance.getInteger("totalAmount"));
        // System.out.println("Paid Amount : " + maintenance.getInteger("paidAmount"));
        // System.out.println("Pending Amount : " +
        // maintenance.getInteger("pendingAmount"));
        // System.out.println("Status : " + maintenance.getString("paymentStatus"));
        // }

}
