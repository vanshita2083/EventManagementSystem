package main;
import java.util.ArrayList;
public class Organiser {

    private int organiserId;
    private String name;
    private String contactInfo;
    private ArrayList<Event> events;

    public Organiser() {
    	events = new ArrayList<>();
    }

    public Organiser(int organiserId, String name, String contactInfo) {
        this.organiserId = organiserId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.events = new ArrayList<>();
    }

    public int getOrganiserId() {
        return organiserId;
    }

    public void setOrganizerId(int organiserId) {
        this.organiserId = organiserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public String toString() {
        return "Organizer ID: " + organiserId +
                ", Name: " + name +
                ", Contact: " + contactInfo;
    }
}
