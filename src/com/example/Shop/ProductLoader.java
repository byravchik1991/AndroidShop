package com.example.Shop;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.example.Shop.data.Product;
import com.example.Shop.data.ShopDao;
import com.example.Shop.data.ShopDaoImpl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 03.08.13
 */
public class ProductLoader extends AsyncTaskLoader<List<Product>> {
    private ShopDao shopDao;

    public ProductLoader(Context context) {
        super(context);
        shopDao = ShopDaoImpl.getInstance();
    }

    @Override
    protected void onStartLoading() {
        // just make sure if we already have content to deliver
        if (shopDao != null)
            deliverResult(shopDao.getProducts());

        // otherwise if something has been changed or first try
        if (takeContentChanged() || shopDao == null)
            forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();

        // clear reference to object
        // it's necessary to allow GC to collect the object
        // to avoid memory leaking
        shopDao = null;
    }


    @Override
    public List<Product> loadInBackground() {
        return shopDao.getProducts();
    }
}
