package com.example.kuducredittracker.Resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        ImageView imageView = (ImageView) listItem.findViewById(R.id.m_store_img);
        imageView.setImageResource(R.drawable.default_info_image);

        TextView title = (TextView) listItem.findViewById(R.id.m_store_title);
        title.setText(currentItem.getName());

        RatingBar ratingBar = listItem.findViewById(R.id.item_rating_bar);
        ratingBar.setRating(3);

        return listItem;
    }


    /*
                <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" >
                <RatingBar
                    android:id="@+id/myRatingBar"
                    style="?android:attr/ratingBarStyleSmall"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:numStars="5" />
            </LinearLayout>

                final RatingBar minimumRating = (RatingBar)findViewById(R.id.myRatingBar);
    minimumRating.setOnTouchListener(new OnTouchListener()
    {
        public boolean onTouch(View view, MotionEvent event)
        {
            float touchPositionX = event.getX();
            float width = minimumRating.getWidth();
            float starsf = (touchPositionX / width) * 5.0f;
            int stars = (int)starsf + 1;
            minimumRating.setRating(stars);
            return true;
        }
    });
     */

}
