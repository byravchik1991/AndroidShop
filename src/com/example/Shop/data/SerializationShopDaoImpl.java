package com.example.Shop.data;

import java.io.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 11.08.13
 */
public class SerializationShopDaoImpl extends FileShopDaoImpl {
    public static final String FILE_NAME = "shop_ser";

    private String directory;

    @Override
    public void initialize(Map<String, Object> params) {
        directory = (String) params.get(ShopDao.DIRECTORY_PARAM_NAME);

        try {
            File file = new File(directory, FILE_NAME);
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));

            shop = (Shop) inputStream.readObject();

            inputStream.close();

        } catch (Exception e) {
            shop = new Shop();
        }
    }

    @Override
    public void terminate() {

        try {
            File file = new File(directory, FILE_NAME);
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));

            outputStream.writeObject(shop);
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
