package com.example.kuducredittracker.Resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.kuducredittracker.MarketPlace.Item;
import com.example.kuducredittracker.R;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter <HistoryItem>{
    Context context;
    ArrayList<HistoryItem> historyItems;
    public HistoryAdapter(@NonNull Context context, ArrayList<HistoryItem> historyItems) {
        super(context, 0, historyItems);
        this.historyItems = historyItems;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
        {
            listItem = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        }

        HistoryItem currentItem = historyItems.get(position);

        //ImageView imageView = (ImageView) listItem.findViewById(R.id.m_store_img);

        ///////////////////////////////////////////////////////////////////////////////////////////
        ImageLoader imageLoader;
        NetworkImageView imageView = listItem.findViewById(R.id.history_img);
        imageView.setImageResource(R.drawable.default_info_image);
        String url = currentItem.getUrl();
        if(url.equals("")){
            imageView.setDefaultImageResId(R.drawable.default_info_image);
        }

        imageLoader = CustomVolleyRequest.getInstance(context)
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.default_store_icon, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);
        ///////////////////////////////////////////////////////////////////////////////////////////

        TextView title = (TextView) listItem.findViewById(R.id.history_title);
        title.setText(currentItem.getName());

        TextView price = listItem.findViewById(R.id.history_price);
        price.setText(Double.toString(currentItem.getPrice()));

        TextView date = listItem.findViewById(R.id.history_date);
        date.setText(currentItem.getDate());

        return listItem;
    }
}
