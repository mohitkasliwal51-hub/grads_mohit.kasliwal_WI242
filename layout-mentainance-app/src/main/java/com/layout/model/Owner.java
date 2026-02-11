package com.layout.model;

public class Owner {

    private String ownerId;
    private String name;
    private String username;
    private String password;

    // Assigned site (null if not assigned)
    private Integer siteId;

    // Site request object
    private SiteRequest siteRequest;

    // No-argument constructor
    public Owner() {
    }

    // Parameterized constructor
    public Owner(String ownerId, String name, String username, String password,
                 Integer siteId, SiteRequest siteRequest) {
        this.ownerId = ownerId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.siteId = siteId;
        this.siteRequest = siteRequest;
    }

    // Getters and Setters

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public SiteRequest getSiteRequest() {
        return siteRequest;
    }

    public void setSiteRequest(SiteRequest siteRequest) {
        this.siteRequest = siteRequest;
    }

    // ---------- INNER CLASS FOR SITE REQUEST ----------
    public static class SiteRequest {

        private Integer requestedSiteId;
        private String requestedSiteType; // OPEN or OCCUPIED
        private String requestStatus;      // PENDING / APPROVED / REJECTED

        public SiteRequest() {
        }

        public SiteRequest(Integer requestedSiteId, String requestedSiteType, String requestStatus) {
            this.requestedSiteId = requestedSiteId;
            this.requestedSiteType = requestedSiteType;
            this.requestStatus = requestStatus;
        }

        public Integer getRequestedSiteId() {
            return requestedSiteId;
        }

        public void setRequestedSiteId(Integer requestedSiteId) {
            this.requestedSiteId = requestedSiteId;
        }

        public String getRequestedSiteType() {
            return requestedSiteType;
        }

        public void setRequestedSiteType(String requestedSiteType) {
            this.requestedSiteType = requestedSiteType;
        }

        public String getRequestStatus() {
            return requestStatus;
        }

        public void setRequestStatus(String requestStatus) {
            this.requestStatus = requestStatus;
        }
    }
}
