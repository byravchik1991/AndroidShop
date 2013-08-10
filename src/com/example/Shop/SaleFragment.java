package com.example.Shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.Shop.data.Product;
import com.example.Shop.data.ProductType;
import com.example.Shop.data.ShopDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 01.08.13
 */
public class SaleFragment extends Fragment {
    private EditText nameEditText;
    private Spinner productTypeSpinner;
    private EditText priceEditText;
    private EditText quantityEditText;
    private Button saleButton;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        productTypeSpinner = (Spinner) getActivity().findViewById(R.id.productTypeSpinner);

        ArrayAdapter<ProductType> adapter = new ArrayAdapter<ProductType>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                ProductType.values());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productTypeSpinner.setAdapter(adapter);

        nameEditText = (EditText) getActivity().findViewById(R.id.nameEditText);

        priceEditText = (EditText) getActivity().findViewById(R.id.priceEditText);
        priceEditText.setFilters(new InputFilter[]{new InputFilterMinMax(1)});

        quantityEditText = (EditText) getActivity().findViewById(R.id.quantityEditText);
        quantityEditText.setFilters(new InputFilter[]{new InputFilterMinMax(1)});

        saleButton = (Button) getActivity().findViewById(R.id.saleButton);
        saleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean correct = checkEditText(nameEditText);
                correct &= checkEditText(priceEditText);
                correct &= checkEditText(quantityEditText);

                if (correct) {
                    final String name = nameEditText.getText().toString();
                    final ProductType type = (ProductType) productTypeSpinner.getSelectedItem();
                    final int price = Integer.parseInt(priceEditText.getText().toString());
                    final int quantity = Integer.parseInt(quantityEditText.getText().toString());

                    final Product newProduct = new Product(name, type, price, quantity);
                    ShopDaoImpl.getInstance().sellProduct(newProduct);

                    nameEditText.setText("");
                    priceEditText.setText("");
                    quantityEditText.setText("");
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sale, container, false);
    }

    private boolean checkEditText(EditText editText) {
        boolean correct = !"".equals(editText.getText().toString());

        if (!correct) {
            editText.setError(getActivity().getString(R.string.fill_correct));

        } else {
            editText.setError(null);
        }

        return correct;
    }
}
