package com.lma.dao;

public interface UpdateRequestDAO {

    void raiseRequest(int siteId, String fieldName, String newValue, int ownerId);

    void getPendingRequests();

    void processRequest(int requestId, String decision);

//    boolean updateRequestStatus(int requestId, String status);
}
