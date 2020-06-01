package com.example.kuducredittracker.Resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kuducredittracker.MarketPlace.Store;
import com.example.kuducredittracker.R;

import java.util.ArrayList;

public class StoreAdapter extends ArrayAdapter<Store> {

    private Context context;
    private ArrayList<Store> stores = new ArrayList<>();

    public StoreAdapter(Context context, ArrayList<Store> stores) {
        super(context, 0, stores);
        this.context = context;
        this.stores = stores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
        {
            listItem = LayoutInflater.from(context).inflate(R.layout.market_place_items, parent, false);
        }

        Store currentStore = stores.get(position);

        ImageView imageView = (ImageView) listItem.findViewById(R.id.m_store_img);
        imageView.setImageResource(currentStore.getImage());

        TextView title = (TextView) listItem.findViewById(R.id.m_store_title);
        title.setText(currentStore.getName());

        return listItem;
    }
}
