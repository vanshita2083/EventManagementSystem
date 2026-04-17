package com.event_managment.model;

public class Organizer {

    private int organizerId;
    private String name;
    private String email;

    public Organizer() {
    }

    public Organizer(int organizerId, String name, String email) {
        this.organizerId = organizerId;
        this.name = name;
        this.email = email;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Organizer ID: " + organizerId +
                ", Name: " + name +
                ", Email: " + email;
    }
}