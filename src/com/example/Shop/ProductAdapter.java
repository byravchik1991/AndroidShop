package com.example.Shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.Shop.data.Product;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 31.07.13
 */
public class ProductAdapter extends ArrayAdapter<Product> {
    private List<Product> itemList;

    public ProductAdapter(Context context, int resourceId, List<Product> objects) {
        super(context, resourceId, objects);
        this.itemList = objects;
    }

    public List getItemList() {
        return itemList;
    }

    public void setItemList(List itemList) {
        this.itemList = itemList;
    }

    public int getCount() {
        if (itemList != null) {
            return itemList.size();
        }

        return 0;
    }

    public Product getItem(int position) {
        if (itemList != null) {
            return itemList.get(position);
        }
        return null;
    }

    public long getItemId(int position) {
        if (itemList != null)
            return itemList.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.product, parent, false);

        final TextView nameTextView = (TextView) rowView.findViewById(R.id.name);
        final TextView typeTextView = (TextView) rowView.findViewById(R.id.type);
        final TextView priceTextView = (TextView) rowView.findViewById(R.id.price);
        final TextView quantityTextView = (TextView) rowView.findViewById(R.id.quantity);

        nameTextView.setText(getItem(position).getName());
        typeTextView.setText(getItem(position).getType().toString());
        priceTextView.setText(/*getPriceString(getItem(position).getPrice())*/String.valueOf(getItem(position).getPrice()));
        quantityTextView.setText(String.valueOf(getItem(position).getQuantity()));

        return rowView;
    }

/*    private String getPriceString(int price) {
        StringBuilder priceString = new StringBuilder(String.valueOf(price));

        int length = priceString.length();
        for (int i=(length-4); i>=0; i-=3) {
           priceString.insert(i, ' ');
        }

        return priceString.toString();
    }*/
}
