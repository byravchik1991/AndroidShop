package com.example.Shop.data;

import com.example.Shop.R;
import com.example.Shop.ShopActivity;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 30.07.13
 */
public enum ProductType {
    TYPE_1(R.string.type_1),
    TYPE_2(R.string.type_2),
    TYPE_3(R.string.type_3),
    TYPE_4(R.string.type_4),
    TYPE_5(R.string.type_5);

    private final int resourceId;

    ProductType(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return ShopActivity.getShopContext().getString(resourceId);
    }
}
