package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.example.css.Event;
import com.example.css.EventListAdapter;
import com.example.css.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendingEventsFragments extends Fragment {


    public AttendingEventsFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_attending_events_fragments, container, false);

        final ArrayList eventList = new ArrayList(0);
//        eventList.add(new HashMap<>(0));

        final EventListAdapter[] eventAdapter = new EventListAdapter[1];
        eventAdapter[0] = new EventListAdapter(rootView.getContext(), R.layout.feed_item, eventList);

        final ListView feedListView = (ListView) rootView.findViewById(R.id.eventListView);
        feedListView.setAdapter(eventAdapter[0]);

//        Switch sortSwitch = (Switch) rootView.findViewById(R.id.sortSwitch);

//        //set the switch to ON
//        sortSwitch.setChecked(true);
//        //attach a listener to check for changes in state
//        sortSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    System.out.println("Switch is currently ON");
//                } else {
//                    System.out.println("Switch is currently OFF");
//                }
//            }
//        });
//        // Inflate the layout for this fragment

        final Firebase firebase = new Firebase("https://cssquare.firebaseio.com/");

        firebase.child("eventList").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Event addEvent = new Event(dataSnapshot.getValue());
//                Event addEvent = new Event("Test","Me","somedescription","someaddress",1,new ArrayList<String>(0));

                HashMap<String, String> map = (HashMap) dataSnapshot.getValue();

                String name = map.get("name");
                String address = map.get("address");
                String description = map.get("description");
                String creator = map.get("creator");
                ArrayList<String> usernames = new ArrayList<String>(); //is being left blank
                long date = 0;
                try {
//                    date = Long.parseLong(map.get("date"));
                    date = (Long) (Object) map.get("date");
                } catch (NumberFormatException nfe) {
                    date = System.currentTimeMillis();
                }
                Event addEvent = new Event(name, creator, description, address, date, usernames);
                eventList.add(addEvent);
                Collections.sort(eventList, new Comparator() {
                    @Override
                    public int compare(Object lhs, Object rhs) {
                        if (((Event) lhs).getDate() < ((Event) rhs).getDate()) {
                            return -1;
                        } else if (((Event) lhs).getDate() > ((Event) rhs).getDate()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });
                eventAdapter[0] = new EventListAdapter(rootView.getContext(), R.layout.feed_item, eventList);
                feedListView.setAdapter(eventAdapter[0]);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return rootView;
    }

}
