package com.layout.model;

public class Site {

    private int siteId;
    private int length;
    private int width;
    private int area;
    private String siteType; // OPEN or OCCUPIED

    // No-argument constructor
    public Site() {
    }

    // Parameterized constructor
    public Site(int siteId, int length, int width, int area, String siteType) {
        this.siteId = siteId;
        this.length = length;
        this.width = width;
        this.area = area;
        this.siteType = siteType;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }
}
