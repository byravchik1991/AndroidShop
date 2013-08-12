package com.example.Shop.data;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 11.08.13
 */
public class ShopDaoFactory {
    private static ShopDao instance;

    public static ShopDao getShopDao() {
        if (instance == null) {
            instance = new JsonShopDaoImpl();//new SerializationShopDaoImpl();
        }

        return instance;
    }
}
