// Layout Maintenance Application
// ---------------------------------------
// Types -  Villa, Apartment, Independent House, Open Site
// Total Sites - 35 Sites
// First 10 sites are of 40x60 ft size
// Next 10 sites are of 30x50 ft size
// Last 15 sites are of 30x40 ft size
// Open sites are charged 6Rs/sqft
// Occupied sites are charged 9Rs./sqft

// Admin	- 
// 	Can add/edit/remove the owner details and site details
// 	Can collect the maintenance and update
// 	Can see the pending details of all sites or the specific site
// 	Can approve/reject the site owners update about their own sites
// Site Owner -
// 	Can only see/update the details of his/her own site (but should be approved by Admin)


package com.layout;

import java.util.Scanner;

import org.bson.Document;

import com.layout.admin.AdminMenu;
import com.layout.dao.AdminDAO;
import com.layout.dao.OwnerDAO;
import com.layout.owner.OwnerMenu;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AdminDAO adminDAO = new AdminDAO();
        OwnerDAO ownerDAO = new OwnerDAO();

        while (true) {

            System.out.println("\n===== LAYOUT MAINTENANCE SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Owner Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Admin Username: ");
                    String aUser = sc.next();
                    System.out.print("Admin Password: ");
                    String aPass = sc.next();

                    if (adminDAO.adminLogin(aUser, aPass)) {
                        System.out.println("Admin login successful.");
                        new AdminMenu().showMenu();
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;

                case 2:
                    System.out.print("Owner Username: ");
                    String oUser = sc.next();
                    System.out.print("Owner Password: ");
                    String oPass = sc.next();

                    Document owner = ownerDAO.ownerLogin(oUser, oPass);

                    if (owner != null) {
                        System.out.println("Owner login successful.");
                        new OwnerMenu().showMenu(owner);
                    } else {
                        System.out.println("Invalid owner credentials.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you. Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
