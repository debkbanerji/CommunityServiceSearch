package com.example.css;

import java.util.ArrayList;

public class Event {
    private String name;
    private String creator; //person who made event
    private String description;
    private String address;
    private long date;
    private ArrayList<String> usernames; //everyone attending the event

    public Event(String name, String creator, String description, String address, long date, ArrayList<String> usernames) {
        this.name = name;
        this.creator = creator;
        this.description = description;
        this.address = address;
        this.date = date;
        this.usernames = usernames;
    }

    //--setters and getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getDate() {
        return date;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public String getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User creator) {
        this.creator = creator.getName();
    }

    public void setCreator(String creatorName) {
        this.creator = creatorName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void addUsername(User user) {
        usernames.add(user.getName());
    }

    public void addUsername(String username) {
        usernames.add(username);
    }

    //----------------
    public boolean containsUser(User... user) {
        boolean result = true;
        for (User u : user) {
            if (!usernames.contains(u.getName())) {
                result = false;
            }
        }
        return result;
    }

    public boolean containsUser(String... username) {
        boolean result = true;
        for (String s : username) {
            if (!usernames.contains(s)) {
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
        if (!(obj instanceof Event)) {
            return false;
        }
        return (obj.hashCode() == hashCode());
    }
}