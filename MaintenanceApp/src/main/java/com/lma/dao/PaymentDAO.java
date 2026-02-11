package com.lma.dao;

public interface PaymentDAO {

    void makePayment(int siteId,  int amount);

    void getPaymentHistory(int siteId);

    boolean collectPayment(int siteId, double amount);
}
