package com.lma.controller;

import java.io.IOException;

import com.lma.dao.MaintenanceDAO;
import com.lma.dao.PaymentDAO;
import com.lma.dao.SiteDAO;
import com.lma.dao.UpdateRequestDAO;
import com.lma.dbOperation.MaintenanceDBOperations;
import com.lma.dbOperation.PaymentDBOperations;
import com.lma.dbOperation.SiteDBOperations;
import com.lma.dbOperation.UpdateRequestDBOperations;
import com.lma.model.Site;
import com.lma.model.SiteType;
import com.lma.util.BufferReader;

public class AdminController {

    private SiteDAO siteDAO = new SiteDBOperations();

    private MaintenanceDAO maintenanceDAO = new MaintenanceDBOperations();

    private PaymentDAO paymentDAO = new PaymentDBOperations();

    private UpdateRequestDAO requestDAO = new UpdateRequestDBOperations();

    public void addSite() throws IOException {
        Site site = new Site();

        System.out.print("Enter Site Type : ");
        site.setSiteType(SiteType.valueOf(BufferReader.br.readLine().toUpperCase()));

        System.out.print("Enter Length : ");
        site.setLength(Integer.parseInt(BufferReader.br.readLine()));

        System.out.print("Enter Width : ");
        site.setWidth(Integer.parseInt(BufferReader.br.readLine()));

        System.out.print("Is Open Site (true/false) : ");
        site.setOpen(Boolean.valueOf((BufferReader.br.readLine())));

        System.out.print("Enter Owner ID : ");
        site.setOwnerId(Integer.parseInt(BufferReader.br.readLine()));

        siteDAO.addSite(site);
        System.out.println("Site Added Successfully");
    }

    public void updateSite() throws IOException {
        System.out.print("Enter Site ID : ");
        int siteId = Integer.parseInt(BufferReader.br.readLine());

        Site site = new Site();

        siteDAO.updateSite(site);
    }

    public void removeSite() throws IOException {
        System.out.print("Enter Site ID: ");
        siteDAO.deleteSite(Integer.parseInt(BufferReader.br.readLine()));
    }

    public void viewAllSites() {
        siteDAO.getAllSites();
    }

    //Maintenance 
    public void viewPendingMaintenance() {
        maintenanceDAO.getPendingMaintenance();
    }

    public void collectMaintenance() throws IOException {
        System.out.print("Enter Site ID: ");
        int siteId = Integer.parseInt(BufferReader.br.readLine());

        System.out.print("Enter Amount: ");
        double amount = Integer.parseInt(BufferReader.br.readLine());

        paymentDAO.collectPayment(siteId, amount);
    }

    public void processUpdateRequests() throws IOException {
        requestDAO.getPendingRequests();
        System.out.print("Enter Request ID to process: ");
        int requestId = Integer.parseInt(BufferReader.br.readLine());

        System.out.print("Approve or Reject : ");
        String decision = BufferReader.br.readLine();

        requestDAO.processRequest(requestId, decision);
    }

    public void processGenerateMaintenance() throws IOException{
        System.out.print("Enter month to generate the Maintenance for : ");
        String month = BufferReader.br.readLine();
        maintenanceDAO.generateMaintenance(month);
    }
}
