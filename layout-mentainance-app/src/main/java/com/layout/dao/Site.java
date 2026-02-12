package com.layout.dao;

public interface Site {

    // ================= GET ALL SITES =================
    void getAllSites();

    // ================= GET AVAILABLE SITES =================
    void getAvailableSites();

    // ================= CALCULATE AREA =================
    int calculateArea(int length, int width);

    // ================= GET RATE =================
    int getRate(String siteType);

}