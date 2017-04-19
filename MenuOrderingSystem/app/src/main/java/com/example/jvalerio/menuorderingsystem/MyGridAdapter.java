/******************************************
 Programmer: Javier Valerio
 Date Originally Submitted: February 10th, 2017
 Last Modified on: February 20th, 2017
 Dr. Shrivastava
 CS 481 - Menu Ordering System - Part Two
 ******************************************/

package com.example.jvalerio.menuorderingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MyGridAdapter extends BaseAdapter {
    Context context;
    List<MenuItem> menuList = new ArrayList<>();

    TextView itemName, itemQuantity, itemSize, itemTotal;
    ImageView itemImage;

    public MyGridAdapter(Context context, List<MenuItem> list) {
        this.context = context;
        this.menuList = list;

    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.cell_item, parent, false);

        itemImage = (ImageView) v.findViewById(R.id.i_image);
        itemName = (TextView) v.findViewById(R.id.i_name);
        itemQuantity = (TextView) v.findViewById(R.id.i_quantity);
        itemSize = (TextView) v.findViewById(R.id.i_size);
        itemTotal = (TextView) v.findViewById(R.id.i_total);

        itemImage.setImageResource(menuList.get(position).getImageURL());
        itemName.setText(menuList.get(position).getName());
        itemQuantity.setText("Quantity: " + menuList.get(position).getQuantity());
        itemSize.setText("Size: " + menuList.get(position).getSize());

        double current = menuList.get(position).getQuantity() * menuList.get(position).getPrice();
        String itemTotalString = "(" + menuList.get(position).getQuantity() + ") x $" + menuList.get(position).getPrice() + " = $" + current;

        itemTotal.setText(itemTotalString);

        return v;
    }
}