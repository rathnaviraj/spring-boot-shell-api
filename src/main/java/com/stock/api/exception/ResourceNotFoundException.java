package com.stock.api.exception;

public class ResourceNotFoundException extends RuntimeException {

    long resourceId;

    String title;

    String resource;

    public ResourceNotFoundException(String title, String message, long resourceId){
        super(message);
        this.resourceId = resourceId;
        this.title = title;
        this.resource = String.format("/api/stocks/%d", resourceId);
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}