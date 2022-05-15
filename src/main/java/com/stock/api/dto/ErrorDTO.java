package com.stock.api.dto;

/**
 * IETF devised RFC 7807, generalized error-handling schema.
 */

public class ErrorDTO {

    public ErrorDTO() {
    }

    public ErrorDTO(RuntimeException ex) {
        this.detail = ex.getMessage();
    }

    /**
     * type URI do not support for this implementation
     */
    String type = "-";

    String title;

    int status;

    String detail;

    String instance;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
