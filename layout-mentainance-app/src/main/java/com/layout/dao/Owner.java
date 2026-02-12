package com.layout.dao;

import java.util.List;

import org.bson.Document;

public interface Owner {

    // ================= OWNER LOGIN =================
    Document ownerLogin(String username, String password);

    // ================= REQUEST SITE =================
    boolean requestSite(int ownerId, int siteId);

    // ================= PAY MAINTENANCE =================
    boolean payMaintenance(int ownerId, int siteId, int amount);

    // ================= VIEW MY SITE =================
    List<Integer> getMySiteIds(int ownerId);

    void viewMySites(int ownerId);

    void viewMyMaintenance(int ownerId);

}