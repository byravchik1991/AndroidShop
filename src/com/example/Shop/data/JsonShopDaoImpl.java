package com.example.Shop.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 11.08.13
 */
public class JsonShopDaoImpl extends FileShopDaoImpl {
    public static final String FILE_NAME = "shop_json";

    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String PRICE = "price";
    private static final String QUANTITY = "quantity";
    private static final String PRODUCTS = "products";

    private String directory;

    @Override
    public void initialize(Map<String, Object> params) {
        directory = (String) params.get(ShopDao.DIRECTORY_PARAM_NAME);

        try {
            File file = new File(directory, FILE_NAME);
            FileInputStream inputStream = new FileInputStream(file);

            final int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            shop = createShop(new JSONObject(new String(buffer, "UTF-8")));

        } catch (Exception e) {
            shop = new Shop();
        }
    }

    @Override
    public void terminate() {
        try {
            File file = new File(directory, FILE_NAME);
            FileWriter writer = new FileWriter(file);
            writer.write(createJsonShop(shop).toString());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject createJsonProduct(Product product) throws JSONException {
        JSONObject jsonProduct = new JSONObject();
        jsonProduct.put(NAME, product.getName());
        jsonProduct.put(TYPE, product.getType().ordinal());
        jsonProduct.put(PRICE, product.getPrice());
        jsonProduct.put(QUANTITY, product.getQuantity());

        return jsonProduct;
    }

    private JSONObject createJsonShop(Shop shop) throws JSONException {
        JSONObject jsonShop = new JSONObject();
        JSONArray jsonProducts = new JSONArray();

        for (Product product : shop.getProducts()) {
            jsonProducts.put(createJsonProduct(product));
        }

        jsonShop.put(PRODUCTS, jsonProducts);

        return jsonShop;
    }

    private Product createProduct(JSONObject object) throws JSONException {
        return new Product(
                object.getString(NAME),
                ProductType.values()[object.getInt(TYPE)],
                object.getInt(PRICE),
                object.getInt(QUANTITY));
    }

    private Shop createShop(JSONObject object) throws JSONException {
        JSONArray jsonProducts = object.getJSONArray(PRODUCTS);
        Set<Product> products = new HashSet<Product>(jsonProducts.length());

        for (int i = 0; i < jsonProducts.length(); i++) {
            products.add(createProduct(jsonProducts.getJSONObject(i)));
        }

        Shop shop = new Shop();
        shop.setProducts(products);

        return shop;
    }
}
