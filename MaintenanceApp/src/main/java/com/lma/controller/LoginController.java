package com.lma.controller;

import java.io.IOException;

import com.lma.dbOperation.UserDBOperations;
import com.lma.util.BufferReader;

public class LoginController {

    public static void showOwnerMenu(int ownerId) throws IOException {
        OwnerController ownerController = new OwnerController();
        int choice = 0;

        do {
            System.out.println("\n====== OWNER MENU ======");
            System.out.println("1. View My Site"); // Done
            System.out.println("2. View Maintenance Details"); //Done
            System.out.println("3. Pay Maintenance (Partial / Full)"); //Done
            System.out.println("4. Request Site Update");//Done
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            choice = Integer.parseInt(BufferReader.br.readLine());

            switch (choice) {
                case 1:
                    ownerController.viewMySite(ownerId);
                    break;

                case 2:
                    ownerController.viewMaintenance(ownerId);
                    break;

                case 3:
                    ownerController.payMaintenance(ownerId);
                    break;

                case 4:
                    ownerController.requestSiteUpdate(ownerId);
                    break;

                case 5:
                    System.out.println("Logging out...");
                    new UserDBOperations().login();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    public static void showAdminMenu() throws IOException {

        AdminController adminController = new AdminController();
        int choice = 0;

        do {
            System.out.println("\n====== ADMIN MENU ======");
            System.out.println("1. Add Site");//Done
            System.out.println("2. Update Site");
            System.out.println("3. Remove Site");//Done
            System.out.println("4. View All Sites"); //Done
            System.out.println("5. Generate Maintenance"); //Done
            System.out.println("6. View Pending Maintenance");//Done
            System.out.println("7. Approve / Reject Update Requests");//Done
            System.out.println("8. Logout");
            System.out.print("Enter choice: ");

            choice = Integer.parseInt(BufferReader.br.readLine());

            switch (choice) {
                case 1:
                    adminController.addSite();
                    break;

                case 2:
                    adminController.updateSite();
                    break;

                case 3:
                    adminController.removeSite();
                    break;

                case 4:
                    adminController.viewAllSites();
                    break;

                case 5:
                    adminController.processGenerateMaintenance();
                    break;

                case 6:
                    adminController.viewPendingMaintenance();
                    break;

                case 7:
                    adminController.processUpdateRequests();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    new UserDBOperations().login();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 8);
    }
}
