package com.lma.dao;

public interface MaintenanceDAO {

    void generateMaintenance(String month);

    void getPendingMaintenance();

    int getMaintenanceAmount(int siteId, String month);

    void updateMaintenanceStatus(int siteId, String month, int pendingAmount, String status);

    void getMaintenanceByOwner(int ownerId);
}
