package main;

public class Participant {

    private int participantId;
    private String name;
    private String email;
    private String phoneNumber;

    public Participant() {
    }

    public Participant(int participantId, String name, String email, String phoneNumber) {
        this.participantId = participantId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return "Participant ID: " + participantId +
                ", Name: " + name +
                ", Email: " + email +
                ", Phone: " + phoneNumber;
    }
}