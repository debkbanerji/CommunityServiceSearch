package com.example.css;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class EventListAdapter extends ArrayAdapter<HashMap> {

    private List events;

    public EventListAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        events = objects;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(com.example.css.R.layout.feed_item, parent, false);
        }

        Object event = events.get(position);

        if (event instanceof Event) {
            TextView titleText = (TextView) convertView.findViewById(com.example.css.R.id.titleText);
            titleText.setText(((Event) event).getName());

            TextView descriptionText = (TextView) convertView.findViewById(com.example.css.R.id.detailText);
            descriptionText.setText(((Event) event).getDescription());

            TextView dateText = (TextView) convertView.findViewById(com.example.css.R.id.dateText);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
            String strDate = formatter.format(new Date(((Event) event).getDate()));
            formatter = new SimpleDateFormat("hh:mm");
            String strTime = formatter.format(new Date(((Event) event).getDate()));
            dateText.setText("At " + strTime + " on " + strDate);

            TextView addressText = (TextView) convertView.findViewById(com.example.css.R.id.addressText);
            addressText.setText("Location: " + ((Event) event).getAddress());
            final String strAddress = ((Event) event).getAddress();
            TextView creatorText = (TextView) convertView.findViewById(com.example.css.R.id.creatorText);
            creatorText.setText("Organized by " + ((Event) event).getCreator());

            //code to open in maps
            addressText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strUri = "geo:0,0?q=" + strAddress.replaceAll(" ", "+");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUri));
                    getContext().startActivity(intent);
                }
            });
        }
        return convertView;
    }
}
