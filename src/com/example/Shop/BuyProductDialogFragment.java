package com.example.Shop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import com.example.Shop.data.Product;
import com.example.Shop.data.ShopDaoFactory;
import com.example.Shop.data.ShopDataChangedListener;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 12.08.13
 */
public class BuyProductDialogFragment extends DialogFragment {
    private Product productToBuy;

    private EditText quantityEditText;

    private ShopDataChangedListener listener;

    public BuyProductDialogFragment(Product productToBuy, ShopDataChangedListener listener) {
        this.productToBuy = new Product(
                productToBuy.getName(),
                productToBuy.getType(),
                productToBuy.getPrice(),
                productToBuy.getQuantity());

        this.listener = listener;
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
                        productToBuy.setQuantity(quantity);

                        new BuyProductTask().execute();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        BuyProductDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    private class BuyProductTask extends AsyncTask {
        private static final long DELAY = 3000;

        @Override
        protected Object doInBackground(Object... params) {
            try {
                Thread.sleep(DELAY);
                ShopDaoFactory.getShopDao().buyProduct(productToBuy);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (listener != null) {
                listener.dataChanged();
            }
        }
    }
}