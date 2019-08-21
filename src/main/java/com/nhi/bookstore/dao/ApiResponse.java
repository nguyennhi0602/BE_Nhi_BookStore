package com.nhi.bookstore.dao;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class ApiResponse {
    private Boolean success;
    private String message;
    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
