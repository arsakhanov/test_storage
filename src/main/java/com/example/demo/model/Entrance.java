package com.example.demo.model;

import java.util.List;

public class Entrance {

    private long number;
    private Storage storage;
    private List<Product> products;

    public Entrance() {
    }

    public Entrance(long number, Storage storage, List<Product> products) {
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

    @Override
    public String toString() {
        return "Entrance{" +
                "number=" + number +
                ", storage=" + storage +
                ", products=" + products +
                '}';
    }
}
