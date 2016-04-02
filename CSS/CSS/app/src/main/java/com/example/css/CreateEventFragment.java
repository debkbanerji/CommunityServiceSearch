package com.example.css;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        final EditText enteredDate = (EditText) rootView.findViewById(R.id.datePicker);


        Button createButton = ((Button) rootView.findViewById(R.id.createButton));
        createButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
//                System.out.println("swag");
                String name = enteredName.getText().toString();
                String description = enteredDescription.getText().toString();
                String address = enteredAddress.getText().toString();
                String stringDate = enteredDate.getText().toString();
                enteredName.setText("");
                enteredDate.setText("");
                enteredDescription.setText("");
                enteredAddress.setText("");
                long date = 0;
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                    Date d = formatter.parse(stringDate);
                    date = d.getTime();
                } catch(ParseException e) {
                    date = System.currentTimeMillis();
                }

                firebase.child("SWAG").setValue(name);
            }
        });

        return rootView;
    }

}
