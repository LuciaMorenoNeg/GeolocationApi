package com.exception;

public class ApiException extends RuntimeException {
    private Integer status;
    private String description;

    public ApiException(Integer status, String description, Throwable cause) {
        super(cause);
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
