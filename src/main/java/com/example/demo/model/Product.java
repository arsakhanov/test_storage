package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private long id;
    @Column(name = "vendor_code")
    private long vendorCode; //артикуль товара
    @Column(name = "product_name")
    private String name;
    @Column(name = "last_purchase_price")
    private double lastPurchasePrice; //цена последней покупки
    @Column(name = "last_sale_price")
    private double lastSalePrice;     //цена последнец продажи

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_storage")
    private Storage storage;

    public Product() {
    }

    public Product(long id, long vendorCode, String name, double lastPurchasePrice, double lastSalePrice) {
        this.id = id;
        this.vendorCode = vendorCode;
        this.name = name;
        this.lastPurchasePrice = lastPurchasePrice;
        this.lastSalePrice = lastSalePrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(long vendor_code) {
        this.vendorCode = vendor_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLastPurchasePrice() {
        return lastPurchasePrice;
    }

    public void setLastPurchasePrice(double lastPurchasePrice) {
        this.lastPurchasePrice = lastPurchasePrice;
    }

    public double getLastSalePrice() {
        return lastSalePrice;
    }

    public void setLastSalePrice(double lastSalePrice) {
        this.lastSalePrice = lastSalePrice;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", vendorCode=" + vendorCode +
                ", name='" + name + '\'' +
                ", lastPurchasePrice=" + lastPurchasePrice +
                ", lastSalePrice=" + lastSalePrice +
                '}';
    }
}
