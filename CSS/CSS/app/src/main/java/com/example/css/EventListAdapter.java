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
import android.widget.ImageView;
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
        //System.out.println("getView");

        if (event instanceof HashMap) {
//            HashMap<String, Object> m = (HashMap) article;
//            TextView titleText = (TextView) convertView.findViewById(com.example.css.R.id.titleText);
//            //titleText.setText((String) (m.get("title"))+(m.get("seen")).toString()); //debug statement
//            titleText.setText((String) (m.get("test")));
//            TextView summaryText = (TextView) convertView.findViewById(com.example.css.R.id.summaryText);
//            summaryText.setText((String) (m.get("summary")));
//            TextView timeText = (TextView) convertView.findViewById(com.example.css.R.id.timeText);
//            Date resultDate = new Date((long) (m.get("postTime")));
//            String stringDate = resultDate.toString();
//            String[] splitDate = stringDate.split(" ");
//            stringDate = "Shared: " + splitDate[0] + " " + splitDate[1] + " " + splitDate[2] + ", " + splitDate[3] + " ";
//            timeText.setText(stringDate);
            //System.out.println("IS HASHMAP");
        }
        if (event instanceof Event) {
            // System.out.println("IS EVENT");
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
                    System.out.println(strUri);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUri));
                    getContext().startActivity(intent);
                }
            });
        }
        return convertView;
    }
}
