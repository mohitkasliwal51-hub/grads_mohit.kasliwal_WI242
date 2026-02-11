package com.layout.model;

public class Maintenance {

    private int siteId;

    private int ratePerSqft;
    private int totalAmount;

    private int paidAmount;
    private int pendingAmount;

    private String paymentStatus; // PENDING / PARTIAL / PAID
    private String lastPaidBy;    // ADMIN / OWNER

    // No-argument constructor
    public Maintenance() {
    }

    // Parameterized constructor
    public Maintenance(int siteId, int ratePerSqft, int totalAmount,
                       int paidAmount, int pendingAmount,
                       String paymentStatus, String lastPaidBy) {

        this.siteId = siteId;
        this.ratePerSqft = ratePerSqft;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.pendingAmount = pendingAmount;
        this.paymentStatus = paymentStatus;
        this.lastPaidBy = lastPaidBy;
    }

    // Getters and Setters

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getRatePerSqft() {
        return ratePerSqft;
    }

    public void setRatePerSqft(int ratePerSqft) {
        this.ratePerSqft = ratePerSqft;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    public int getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(int pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getLastPaidBy() {
        return lastPaidBy;
    }

    public void setLastPaidBy(String lastPaidBy) {
        this.lastPaidBy = lastPaidBy;
    }
}
