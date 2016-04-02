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

        final EditText name = (EditText) rootView.findViewById(R.id.eventName);
        final EditText description = (EditText) rootView.findViewById(R.id.eventDescription);
        final EditText address = (EditText) rootView.findViewById(R.id.eventAddress);
        final EditText date = (EditText) rootView.findViewById(R.id.datePicker);



        Button createButton = ((Button) rootView.findViewById(R.id.createButton));
        createButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                System.out.println("swag");
               firebase.child("SWAG").setValue("yo");
            }
        });

        return rootView;
    }

}
