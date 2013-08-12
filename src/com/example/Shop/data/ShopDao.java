package com.example.Shop.data;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 03.08.13
 */
public interface ShopDao {
    final String DIRECTORY_PARAM_NAME = "dir";

    Set<Product> getProducts();

    void deliverProduct(Product product);

    void buyProduct(Product product);

    void initialize(Map<String, Object> params);

    void terminate();
}
