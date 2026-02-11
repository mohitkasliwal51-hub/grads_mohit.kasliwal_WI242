package com.lma.controller;

import java.io.IOException;

import com.lma.dao.*;
import com.lma.util.BufferReader;

import com.lma.dbOperation.SiteDBOperations;
import com.lma.dbOperation.MaintenanceDBOperations;
import com.lma.dbOperation.PaymentDBOperations;

import com.lma.dbOperation.UpdateRequestDBOperations;

public class OwnerController {

    private SiteDAO siteDAO = new SiteDBOperations();
    private MaintenanceDAO maintenanceDAO = new MaintenanceDBOperations();
    private PaymentDAO paymentDAO = new PaymentDBOperations();
    private UpdateRequestDAO requestDAO = new UpdateRequestDBOperations();

    public void viewMySite(int ownerId) {
        siteDAO.getSitesByOwner(ownerId);
    }

    public void viewMaintenance(int ownerId) {
        maintenanceDAO.getMaintenanceByOwner(ownerId);
    }

    public void payMaintenance(int ownerId) throws IOException {
        System.out.print("Enter Site ID: ");
        int siteId = Integer.parseInt(BufferReader.br.readLine());

        System.out.print("Enter Amount to Pay: ");
        int amount = Integer.parseInt(BufferReader.br.readLine());

        paymentDAO.makePayment(siteId, amount);
    }

    public void requestSiteUpdate(int ownerId) throws IOException {
        System.out.print("Enter Site ID: ");
        int siteId = Integer.parseInt(BufferReader.br.readLine());

        System.out.print("Field to Update: ");
        String field = BufferReader.br.readLine();

        System.out.print("New Value: ");
        String newValue = BufferReader.br.readLine();

        requestDAO.raiseRequest(siteId, field, newValue, ownerId);
    }
}
