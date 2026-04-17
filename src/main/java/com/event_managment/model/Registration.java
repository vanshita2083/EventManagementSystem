package com.event_managment.model;

public class Registration {

    private int registrationId;
    private String registrationDate;
    private Participant participant;
    private Event event;

    public Registration() {
    }

    public Registration(int registrationId, String registrationDate, Participant participant, Event event) {
        this.registrationId = registrationId;
        this.registrationDate = registrationDate;
        this.participant = participant;
        this.event = event;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Registration ID: " + registrationId +
                ", Date: " + registrationDate +
                ", Participant: " + participant.getName() +
                ", Event: " + event.getName();
    }
}