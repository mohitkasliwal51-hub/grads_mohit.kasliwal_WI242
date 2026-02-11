package com.lma.model;

public class UpdateRequest {

    private int requestId;
    private int siteId;
    private String requestedField;
    private String oldValue;
    private String newValue;
    private RequestStatus status;

    public UpdateRequest() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getRequestedField() {
        return requestedField;
    }

    public void setRequestedField(String requestedField) {
        this.requestedField = requestedField;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
