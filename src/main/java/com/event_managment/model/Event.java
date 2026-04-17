package com.event_managment.model;

public class Event {

    private int eventId;
    private String name;
    private String location;
    private String date;
    private int maxParticipants;

    public Event() {
    }

    public Event(int eventId, String name, String location, String date, int maxParticipants) {
        this.eventId = eventId;
        this.name = name;
        this.location = location;
        this.date = date;
        this.maxParticipants = maxParticipants;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    @Override
    public String toString() {
        return "Event ID: " + eventId +
                ", Name: " + name +
                ", Location: " + location +
                ", Date: " + date +
                ", Max Participants: " + maxParticipants;
    }
}