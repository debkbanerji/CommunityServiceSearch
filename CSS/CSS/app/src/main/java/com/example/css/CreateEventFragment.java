package com.example.css;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.firebase.client.Firebase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment {


    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_event, container, false);

        // Inflate the layout for this fragment
        final Firebase firebase = new Firebase("https://cssquare.firebaseio.com/");

        final EditText enteredName = (EditText) rootView.findViewById(R.id.eventName);
        final EditText enteredDescription = (EditText) rootView.findViewById(R.id.eventDescription);
        final EditText enteredAddress = (EditText) rootView.findViewById(R.id.eventAddress);
//        final EditText enteredDate = (EditText) rootView.findViewById(R.id.datePicker);
        final EditText enteredOrganizer = (EditText) rootView.findViewById(R.id.eventOrganizer);
        final DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.datePicker2);
        final TimePicker timePicker = (TimePicker) rootView.findViewById(R.id.timePicker);
        enteredDescription.setMovementMethod(new ScrollingMovementMethod());
        timePicker.setIs24HourView(true);


        ImageButton createButton = ((ImageButton) rootView.findViewById(R.id.createButton));
        createButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String name = enteredName.getText().toString();
                String description = enteredDescription.getText().toString();
                String address = enteredAddress.getText().toString();
//                String stringDate = enteredDate.getText().toString();
                String organizer = enteredOrganizer.getText().toString();
                enteredName.setText("");
//                enteredDate.setText("");
                enteredDescription.setText("");
                enteredAddress.setText("");
                enteredOrganizer.setText("");
                long date = 0;
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy/hh/mm");
//                    Date d = formatter.parse(stringDate);
                    String createdDate = "" + month + "/" + day + "/" + year + "/" + hour + "/" + minute;

                    Date d = formatter.parse(createdDate);
                    date = d.getTime();
                } catch (ParseException e) {
                    date = System.currentTimeMillis();
                }
                String stringDate = String.valueOf(date);
                Event event = new Event(name, organizer, description, address, date, new ArrayList<String>());
                firebase.child("eventList").child(stringDate).setValue(event);
            }
        });

        return rootView;
    }

}
