package com.layout.dao;

import java.util.List;

import org.bson.Document;

public interface Admin {

    // ================= ADMIN LOGIN =================
    boolean adminLogin(String username, String password);

    // ================= APPROVE SITE REQUEST =================
    boolean approveSiteRequest(int siteId);

    // ================= REJECT SITE REQUEST =================
    boolean rejectSiteRequest(int siteId);

    // ================= CHANGE SITE TYPE =================
    boolean changeSiteType(int siteId, String newType);

    // ================= VIEW ALL OWNERS =================
    void viewAllOwners();

    void viewPendingSiteRequests();

    List<Document> getPendingSiteRequests();

}