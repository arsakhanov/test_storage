package com.example.demo.model;

import java.util.List;

public class Selling {

    private long number;
    private Storage storage;
    private List<Product> products;

    public Selling() {
    }

    public Selling(long number, Storage storage, List<Product> products) {
        this.number = number;
        this.storage = storage;
        this.products = products;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
