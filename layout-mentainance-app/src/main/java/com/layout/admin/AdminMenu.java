package com.layout.admin;

import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.layout.dbOperation.AdminDAO;
import com.layout.dbOperation.MaintenanceDAO;
import com.layout.dbOperation.SiteDAO;

public class AdminMenu {

    private AdminDAO adminDAO = new AdminDAO();
    private SiteDAO siteDAO = new SiteDAO();
    private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

    public void showMenu() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Take Action On Pending Requests");
            System.out.println("2. Change Site Type");
            System.out.println("3. View Pending Maintenance");
            System.out.println("4. Pay Maintenance");
            System.out.println("5. View All Owners");
            System.out.println("6. View All Sites");
            System.out.println("7. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    // adminDAO.viewPendingSiteRequests();
                    List<Document> requests = adminDAO.getPendingSiteRequests();

                    if (requests.isEmpty()) {
                        System.out.println("No pending requests.");
                        break;
                    }

                    System.out.println("\n--- Pending Site Requests ---");
                    for (Document site : requests) {
                        System.out.println("\nSite ID      : " + site.getInteger("siteId"));
                        System.out.println("Requested By : Owner " + site.getInteger("requestedBy"));
                        System.out.println("Site Type    : " + site.getString("siteType"));
                        System.out.println("Area         : " + site.getInteger("area"));
                    }

                    System.out.print("\nEnter Site ID to take action: ");
                    int actionSiteId = sc.nextInt();

                    System.out.println("1. Approve");
                    System.out.println("2. Reject");
                    System.out.print("Enter choice: ");
                    int actionChoice = sc.nextInt();

                    if (actionChoice == 1) {
                        if (adminDAO.approveSiteRequest(actionSiteId)) {
                            System.out.println("Request approved successfully.");
                        } else {
                            System.out.println("Approval failed.");
                        }
                    } else if (actionChoice == 2) {
                        if (adminDAO.rejectSiteRequest(actionSiteId)) {
                            System.out.println("Request rejected successfully.");
                        } else {
                            System.out.println("Rejection failed.");
                        }
                    } else {
                        System.out.println("Invalid option.");
                    }

                    break;

                case 2:
                    System.out.print("Enter Site ID: ");
                    int sId = sc.nextInt();
                    System.out.print("Enter new type (OPEN/OCCUPIED): ");
                    String type = sc.next();

                    if (adminDAO.changeSiteType(sId, type))
                        System.out.println("Site type updated.");
                    else
                        System.out.println("Update failed.");
                    break;

                case 3:
                    maintenanceDAO.viewPending();
                    break;

                case 4:
                    System.out.print("Enter Site ID: ");
                    int paySite = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    int amt = sc.nextInt();

                    if (maintenanceDAO.adminPay(paySite, amt))
                        System.out.println("Payment successful.");
                    else
                        System.out.println("Payment failed.");
                    break;
                case 5:
                    adminDAO.viewAllOwners();
                    break;
                case 6:
                    siteDAO.getAllSites();
                    break;

                case 7:
                    System.out.println("Admin logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

}
