package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;

import com.example.css.Event;
import com.example.css.EventListAdapter;
import com.example.css.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendingEventsFragments extends Fragment {
    public ArrayList eventList;
    public View rootView;
    public EventListAdapter[] eventAdapter;
    public ListView feedListView;
    public Firebase firebase;

    public AttendingEventsFragments() {
    }

    private void updateAdapter() {
        eventAdapter[0] = new EventListAdapter(rootView.getContext(), R.layout.feed_item, eventList);
        feedListView.setAdapter(eventAdapter[0]);
    }
    private void updateMap(DataSnapshot dataSnapshot) {
        HashMap<String, String> map = (HashMap) dataSnapshot.getValue();

        String name = map.get("name");
        String address = map.get("address");
        String description = map.get("description");
        String creator = map.get("creator");
        ArrayList<String> usernames = new ArrayList<String>(); //is being left blank
        long date = 0;
        try {
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
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_attending_events_fragments, container, false);
        eventList = new ArrayList(0);
        eventAdapter = new EventListAdapter[1];
        eventAdapter[0] = new EventListAdapter(rootView.getContext(), R.layout.feed_item, eventList);

        feedListView = (ListView) rootView.findViewById(R.id.eventListView);
        feedListView.setAdapter(eventAdapter[0]);
        firebase = new Firebase("https://cssquare.firebaseio.com/");

        firebase.child("eventList").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateMap(dataSnapshot);
                updateAdapter();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                updateMap(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                updateMap(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                updateMap(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return rootView;
    }
    /*
    @Override
    public void onStart() {
        updateAdapter();
    }
    /*
    @Override
    public void onResume() {
        updateAdapter();
    }
    */
}
