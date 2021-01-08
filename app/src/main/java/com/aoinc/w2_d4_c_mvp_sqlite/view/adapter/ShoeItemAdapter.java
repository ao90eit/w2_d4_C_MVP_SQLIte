package com.aoinc.w2_d4_c_mvp_sqlite.view.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aoinc.w2_d4_c_mvp_sqlite.R;
import com.aoinc.w2_d4_c_mvp_sqlite.model.Shoe;

import java.util.ArrayList;
import java.util.List;

public class ShoeItemAdapter extends BaseAdapter {
    private ShoeItemDelegate shoeItemDelegate;
    private List<Shoe> shoeList;

    public interface ShoeItemDelegate {
        void SelectShoeItem(Shoe selectedShoe);
    }

    public ShoeItemAdapter(ShoeItemDelegate shoeItemDelegate, List<Shoe> shoeList) {
        this.shoeItemDelegate = shoeItemDelegate;
        this.shoeList = shoeList;
    }

    @Override
    public int getCount() {
        return shoeList.size();
    }

    @Override
    public Object getItem(int position) {
        return shoeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the selected shoe (using the passed position arg)
        Shoe shoe = shoeList.get(position);

        View listItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shoe_layout_list_item, parent, false);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoeItemDelegate.SelectShoeItem(shoe);
            }
        });

        Resources resources = listItemView.getResources();

        TextView model = listItemView.findViewById(R.id.item_model_listView);
        model.setText(shoe.getShoeModel());

        TextView size = listItemView.findViewById(R.id.item_size_listView);
        size.setText(resources.getString(R.string.size_formatted, shoe.getShoeSize()));

        TextView price = listItemView.findViewById(R.id.item_price_listView);
        price.setText(resources.getString(R.string.price_formatted, shoe.getShoePrice()));

        return listItemView;
    }
}
