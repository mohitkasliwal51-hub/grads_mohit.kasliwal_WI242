package com.lma;

import com.lma.dbOperation.UserDBOperations;

public class LMAMain {

    public static void main(String[] args) {
        // System.out.println("Hello World!");

        // // Site site = new Site();
        // // site.setSiteId(3);
        // // site.setSiteType(SiteType.OPEN_SITE);
        // // site.setLength(40);
        // // site.setWidth(60);
        // // site.setOpen(true);
        // // site.setOwnerId(102);
        // // new SiteDBOperations().addSite(site);
        // System.out.println("=====Get All Sites Called=====");
        // new SiteDBOperations().getAllSites();
        // System.out.println("=====Get Sites By Ownwrs Called=====");
        // new SiteDBOperations().getSitesByOwner(101);
        // // new SiteDBOperations().deleteSite(1);
        // // new SiteDBOperations().getSiteById(1);
        // new UserDBOperations().login();
        System.out.println("=======================================");
        System.out.println("   LAYOUT MAINTENANCE APPLICATION");
        System.out.println("=======================================");

        UserDBOperations userDB = new UserDBOperations();
        userDB.login();

    }
}
