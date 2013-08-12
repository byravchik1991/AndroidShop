package com.example.Shop.data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 30.07.13
 */
public class Product implements Serializable {
    private String name;
    private ProductType type;
    private int price;
    private int quantity;

    public Product() {
    }

    public Product(String name, ProductType type, int price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (price != product.price) return false;
        if (!name.equals(product.name)) return false;
        if (type != product.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + price;
        return result;
    }
}
