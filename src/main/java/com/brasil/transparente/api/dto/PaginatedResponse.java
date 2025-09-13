package com.brasil.transparente.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Setter
@Getter
public class PaginatedResponse<T> {

    private List<T> items;
    private long totalItems;
    private int totalPages;
    private int currentPage;

    public PaginatedResponse(List<T> items, long totalItems, int totalPages, int currentPage) {
        this.items = items;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

}