package com.layout.dao;

public interface Maintenance {

    // ================= CREATE MAINTENANCE =================
    void createMaintenance(int siteId, int area, int rate);

    // ================= ADMIN PAY =================
    boolean adminPay(int siteId, int amount);

    // ================= UPDATE RATE =================
    boolean updateRate(int siteId, int newRate, int area);

    // ================= VIEW PENDING =================
    void viewPending();

}