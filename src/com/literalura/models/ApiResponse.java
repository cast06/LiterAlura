package com.literalura.models;

import java.util.List;

public class ApiResponse {
    private List<BookItem> items;

    public List<BookItem> getItems() {
        return items;
    }

    public void setItems(List<BookItem> items) {
        this.items = items;
    }
}
