package com.example.Shop.data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 03.08.13
 */
public interface ShopDao {
    List<Product> getProducts();

    void sellProduct(Product product);

    void buyProduct(Product product, int quantity);
}
