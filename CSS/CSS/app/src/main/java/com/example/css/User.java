package com.example.css;
import java.util.ArrayList;
public class User {
    private String name;
    private ArrayList<String> eventNames; //all events you've gone/going to
    private ArrayList friends;

    public User(String name, ArrayList<String> eventNames, ArrayList friends) {
        this.name = name;
        this.eventNames = eventNames;
        this.friends = friends;
    }
    //--setters and getters----
    public String getName() {
        return name;
    }
    public ArrayList<String> getEventNames() {
        return eventNames;
    }
    public ArrayList getFriends() {
        return friends;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addEvent(Event event) {
        eventNames.add(event.getName());
    }
    public void addEvent(String eventName) {
        eventNames.add(eventName);
    }
    public void setFriends(ArrayList friends) {
        this.friends = friends;
    }
    //----------------
    public boolean containsEvent(Event... event) {
        boolean result = true;
        for (Event e : event) {
            if (!eventNames.contains(e.getName())) {
                result = false;
            }
        }
        return result;
    }
    public boolean containsEvent(String... eventName) {
        boolean result = true;
        for (String s : eventName) {
            if (!eventNames.contains(s)) {
                result = false;
            }
        }
        return result;
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        return (obj.hashCode() == hashCode());
    }

}
