package com.layout.dbOperation;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.layout.config.MongoConnection;
import com.layout.dao.Maintenance;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

public class MaintenanceDAO implements Maintenance {

        private MongoDatabase database;

        public MaintenanceDAO() {
                database = MongoConnection.getDatabase();
        }

        // ================= CREATE MAINTENANCE =================
        @Override
        public void createMaintenance(int siteId, int area, int rate) {

                MongoCollection<Document> mainCol = database.getCollection("maintenance");

                int total = area * rate;

                Document maintenance = new Document("siteId", siteId)
                                .append("ratePerSqft", rate)
                                .append("totalAmount", total)
                                .append("paidAmount", 0)
                                .append("pendingAmount", total)
                                .append("paymentStatus", "PENDING")
                                .append("lastPaidBy", "ADMIN");

                mainCol.insertOne(maintenance);
        }

        // ================= ADMIN PAY =================
        @Override
        public boolean adminPay(int siteId, int amount) {

                MongoCollection<Document> mainCol = database.getCollection("maintenance");

                Document maintenance = mainCol.find(new Document("siteId", siteId)).first();

                if (maintenance == null)
                        return false;

                int paid = maintenance.getInteger("paidAmount");
                int total = maintenance.getInteger("totalAmount");

                int newPaid = paid + amount;
                int pending = total - newPaid;

                String status = pending <= 0 ? "PAID" : "PARTIAL";

                Document update = new Document("$set",
                                new Document("paidAmount", newPaid)
                                                .append("pendingAmount", pending)
                                                .append("paymentStatus", status)
                                                .append("lastPaidBy", "ADMIN"));

                UpdateResult result = mainCol.updateOne(
                                new Document("siteId", siteId),
                                update);

                return result.getModifiedCount() > 0;
        }

        // ================= UPDATE RATE =================
        @Override
        public boolean updateRate(int siteId, int newRate, int area) {

                MongoCollection<Document> mainCol = database.getCollection("maintenance");

                int total = newRate * area;

                Document update = new Document("$set",
                                new Document("ratePerSqft", newRate)
                                                .append("totalAmount", total)
                                                .append("pendingAmount", total));

                return mainCol.updateOne(
                                new Document("siteId", siteId),
                                update).getModifiedCount() > 0;
        }

        // ================= VIEW PENDING =================
        @Override
        public void viewPending() {

                MongoCollection<Document> mainCol = database.getCollection("maintenance");

                for (Document doc : mainCol.find(new Document("paymentStatus",
                                new Document("$ne", "PAID")))
                                .projection(new Document("_id", 0))) {
                        System.out.println("-----------------------------");
                        System.out.println(doc.toJson(
                                        JsonWriterSettings.builder().indent(true).build()));
                }
        }
}
