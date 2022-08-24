package com.example.demo.model;

import java.util.List;

public class Moving {

    private long number;
    private Storage storage1;
    private Storage storage2;
    private List<Product> products;

    public Moving() {
    }

    public Moving(long number, Storage storage1, Storage storage2, List<Product> products) {
        this.number = number;
        this.storage1 = storage1;
        this.storage2 = storage2;
        this.products = products;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Storage getStorage1() {
        return storage1;
    }

    public void setStorage1(Storage storage1) {
        this.storage1 = storage1;
    }

    public Storage getStorage2() {
        return storage2;
    }

    public void setStorage2(Storage storage2) {
        this.storage2 = storage2;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
