package com.example.Shop.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 30.07.13
 */
public class Shop implements Serializable {
    private List<Product> products = Collections.synchronizedList(new ArrayList<Product>());

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        assert product.getQuantity() > 0;

        if (!products.contains(product)) {
            products.add(product);

        } else {
            Product oldProduct = products.get(products.indexOf(product));
            oldProduct.setQuantity(product.getQuantity() + oldProduct.getQuantity());
        }
    }

    public void removeProduct(Product product, int quantity) {
        assert product.getQuantity() > 0;

        if (products.contains(product)) {
            final int newQuantity = product.getQuantity() - quantity;

            if (newQuantity > 0) {
                product.setQuantity(newQuantity);
            } else {
                products.remove(product);
            }
        }
    }
}
