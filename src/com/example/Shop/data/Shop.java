package com.example.Shop.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 30.07.13
 */
public class Shop implements Serializable {
    private Set<Product> productSet = Collections.newSetFromMap(new ConcurrentHashMap<Product, Boolean>());

    public Set<Product> getProducts() {
        return productSet;
    }

    public void setProducts(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public void addProduct(Product newProduct) {
        assert newProduct.getQuantity() > 0;

        if (productSet.contains(newProduct)) {
            for (Product product : productSet) {
                if (product.equals(newProduct)) {
                    product.setQuantity(product.getQuantity() + newProduct.getQuantity());
                }
            }

        } else {
            productSet.add(newProduct);
        }
    }

    public void removeProduct(Product productToRemove) {
        assert productToRemove.getQuantity() > 0;

        if (productSet.contains(productToRemove)) {
            for (Product product : new HashSet<Product>(productSet)) {
                if (product.equals(productToRemove)) {
                    final int newQuantity = product.getQuantity() - productToRemove.getQuantity();

                    if (newQuantity > 0) {
                        product.setQuantity(newQuantity);

                    } else {
                        productSet.remove(product);
                    }
                }
            }
        }
    }
}
