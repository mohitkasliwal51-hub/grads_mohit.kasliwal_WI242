package com.layout.owner;

import java.util.Scanner;

import org.bson.Document;

import com.layout.dao.OwnerDAO;
import com.layout.dao.SiteDAO;

public class OwnerMenu {

    private OwnerDAO ownerDAO = new OwnerDAO();
    private SiteDAO siteDAO = new SiteDAO();

    public void showMenu(Document owner) {

        Scanner sc = new Scanner(System.in);
        int ownerId = owner.getInteger("ownerId");

        while (true) {
            System.out.println("\n===== OWNER MENU =====");
            System.out.println("1. View My Sites");
            System.out.println("2. View Maintenance Details");
            System.out.println("3. View Available Sites");
            System.out.println("4. Request Site");
            System.out.println("5. Pay Maintenance");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    ownerDAO.viewMySites(ownerId);
                    break;
                case 2:
                    ownerDAO.viewMyMaintenance(ownerId);
                    // System.out.print("Enter Site ID: ");
                    // int siteId = sc.nextInt();
                    // sc.nextLine();
                    // ownerDAO.viewMaintenance(ownerId, siteId);
                    break;

                case 3:
                    siteDAO.getAvailableSites();
                    break;

                case 4:
                    System.out.print("Enter Site ID to request: ");
                    int reqSiteId = sc.nextInt();

                    if (ownerDAO.requestSite(ownerId, reqSiteId))
                        System.out.println("Site request sent.");
                    else
                        System.out.println("Request failed.");
                    break;

                case 5:
                    System.out.print("Enter Site ID: ");
                    int sId = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    int amt = sc.nextInt();

                    if (ownerDAO.payMaintenance(ownerId, sId, amt))
                        System.out.println("Payment successful.");
                    else
                        System.out.println("Payment failed.");
                    break;

                case 6:
                    System.out.println("Owner logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
