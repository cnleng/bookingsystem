package com.flexter.bookingsystem.controller;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiListResponse<T> implements Serializable {
    private String message;
    private long total;
    private List<T> data;
}