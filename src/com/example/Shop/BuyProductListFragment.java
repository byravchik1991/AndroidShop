package com.example.Shop;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import com.example.Shop.data.Product;
import com.example.Shop.data.ShopDaoFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 01.08.13
 */
public class BuyProductListFragment extends ListFragment implements ShopDataChangedListener {

    private ProductAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ProductAdapter(
                getActivity(),
                R.layout.product,
                Collections.<Product>emptyList());

        setListAdapter(adapter);
        dataChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        BuyProductDialogFragment dialog = new BuyProductDialogFragment((Product) l.getAdapter().getItem(position), this);
        dialog.show(getActivity().getSupportFragmentManager(), getString(R.string.buy));
    }

    @Override
    public void dataChanged() {
        new LoadProductsTask().execute();
    }

    private class LoadProductsTask extends AsyncTask<Object, Object, List<Product>> {

        @Override
        protected List<Product> doInBackground(Object... params) {
            List<Product> products = new ArrayList<Product>(ShopDaoFactory.getShopDao().getProducts());

            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product lhs, Product rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });

            return products;
        }

        @Override
        protected void onPostExecute(List<Product> o) {
            super.onPostExecute(o);

            adapter.setItemList(o);
            adapter.notifyDataSetChanged();
        }
    }
}
