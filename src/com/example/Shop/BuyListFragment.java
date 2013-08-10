package com.example.Shop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.example.Shop.data.Product;
import com.example.Shop.data.ShopDaoImpl;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 01.08.13
 */
public class BuyListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Product>> {

    private ProductAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ProductAdapter(getActivity(), R.layout.product, Collections.<Product>emptyList());
        setListAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        BuyDialogFragment dialog = new BuyDialogFragment((Product) l.getAdapter().getItem(position));
        dialog.show(getActivity().getSupportFragmentManager(), getString(R.string.buy));
    }

    @Override
    public Loader<List<Product>> onCreateLoader(int id, Bundle args) {
        return new ProductLoader(getActivity().getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Product>> loader, List<Product> data) {
        adapter.setItemList(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Product>> loader) {
    }

    private class BuyDialogFragment extends DialogFragment {
        private Product productToBuy;

        private EditText quantityEditText;

        private BuyDialogFragment(Product productToBuy) {
            this.productToBuy = productToBuy;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.buy, null);

            quantityEditText = (EditText) view.findViewById(R.id.buyQuantityEditText);
            quantityEditText.setFilters(new InputFilter[]{new InputFilterMinMax(1, productToBuy.getQuantity())});

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle(R.string.buy)
                    .setView(view)
                    .setPositiveButton(R.string.buy, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            final int quantity = Integer.parseInt(quantityEditText.getText().toString());

                            ShopDaoImpl.getInstance().buyProduct(productToBuy, quantity);

                            BuyListFragment.this.getLoaderManager().restartLoader(0, null, BuyListFragment.this);
                            // loader.onContentChanged();
                            // adapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            BuyDialogFragment.this.getDialog().cancel();
                        }
                    });

            return builder.create();
        }
    }
}
