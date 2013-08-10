package com.example.Shop.data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 03.08.13
 */
public class ShopDaoImpl implements ShopDao {
    private static ShopDaoImpl instance;

    Shop shop;

    private ShopDaoImpl() {
        shop = new Shop();
        shop.addProduct(new Product("продукт 1", ProductType.TYPE_1, 10000, 5));
        shop.addProduct(new Product("продукт 2", ProductType.TYPE_1, 2000, 15));
        shop.addProduct(new Product("продукт 3", ProductType.TYPE_2, 300, 15));
        shop.addProduct(new Product("продукт 4", ProductType.TYPE_2, 400000, 20));
    }

    public static ShopDaoImpl getInstance() {
        if (instance == null) {
            instance = new ShopDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Product> getProducts() {
        return shop.getProducts();
    }

    @Override
    public void sellProduct(Product product) {
        shop.addProduct(product);
    }

    @Override
    public void buyProduct(Product product, int quantity) {
        shop.removeProduct(product, quantity);
    }
}
