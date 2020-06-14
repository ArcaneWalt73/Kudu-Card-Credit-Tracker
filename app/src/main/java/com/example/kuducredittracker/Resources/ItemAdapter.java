package com.example.kuducredittracker.Resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.kuducredittracker.MarketPlace.Item;
import com.example.kuducredittracker.MarketPlace.Store;
import com.example.kuducredittracker.R;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {
    
    private Context context;
    ArrayList<Item> items;
    
    public ItemAdapter(@NonNull Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
        {
            listItem = LayoutInflater.from(context).inflate(R.layout.market_place_items, parent, false);
        }

        Item currentItem = items.get(position);

        //ImageView imageView = (ImageView) listItem.findViewById(R.id.m_store_img);

        ///////////////////////////////////////////////////////////////////////////////////////////
        ImageLoader imageLoader;
        NetworkImageView imageView = listItem.findViewById(R.id.m_store_img);
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

        TextView title = (TextView) listItem.findViewById(R.id.m_store_title);
        title.setText(currentItem.getName());

        RatingBar ratingBar = listItem.findViewById(R.id.item_rating_bar);
        ratingBar.setRating(currentItem.getRating());


        return listItem;
    }
}
