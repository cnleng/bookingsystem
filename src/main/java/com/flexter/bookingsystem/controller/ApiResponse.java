package com.flexter.bookingsystem.controller;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> implements Serializable {
    private String message;
    private T data;
}
