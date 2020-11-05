/*
 * Author: Jani Heinikoski
 * Date created: 05.11.2020
 * Last modified: 05.11.2020
 */
package com.lut.listviewdemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.lut.listviewdemo.databinding.DialogItemBinding;

public class MainListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private String[] items, prices, descriptions;

    private DialogItemBinding binding;
    private Dialog dialog;

    public MainListAdapter(String[] items, String[] prices, String[] descriptions, Activity activity) {
        this.items = items;
        this.prices = prices;
        this.descriptions = descriptions;
        this.inflater = activity.getLayoutInflater();
        this.context = activity;
        this.binding = DialogItemBinding.inflate(inflater);
        setupDialog();
    }

    private void setupDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(binding.getRoot());
        binding.dialogContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.list_item_cardview, null);

        TextView itemName = (TextView) v.findViewById(R.id.list_item_name);
        TextView itemDescription = (TextView) v.findViewById(R.id.list_item_description);
        TextView itemPrice = (TextView) v.findViewById(R.id.list_item_price);

        itemName.setText(items[i]);
        itemDescription.setText(descriptions[i]);
        itemPrice.setText(prices[i]);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable;
                switch (i) {
                    case 0:
                        drawable =
                                ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_peach, null);
                        break;
                    case 1:
                        drawable =
                                ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_tomato, null);
                        break;
                    case 2:
                        drawable =
                                ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_banana, null);
                        break;
                    default:
                        drawable =
                                ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_baseline_error, null);
                        break;
                }
                binding.dialogPicture.setImageDrawable(drawable);
                dialog.show();
            }
        });

        return v;
    }
}
