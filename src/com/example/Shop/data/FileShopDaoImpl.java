package com.example.Shop.data;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 03.08.13
 */
public abstract class FileShopDaoImpl implements ShopDao {
    protected Shop shop;

    @Override
    public Set<Product> getProducts() {
        return shop.getProducts();
    }

    @Override
    public void deliverProduct(Product product) {
        shop.addProduct(product);
    }

    @Override
    public void buyProduct(Product product) {
        shop.removeProduct(product);
    }

    @Override
    public abstract void initialize(Map<String, Object> params);

    @Override
    public abstract void terminate();
}
