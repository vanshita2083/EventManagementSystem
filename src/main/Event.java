package main;
import java.util.ArrayList;
	public class Event {

	    private int eventId;
	    private String name;
	    private String location;
	    private String date;
	    private int maxParticipants;
	    private ArrayList<Registration> registrations;
	    
	    public Event() {
	    	registrations = new ArrayList<>();
	    }

	    public Event(int eventId, String name, String location, String date, int maxParticipants) {
	        this.eventId = eventId;
	        this.name = name;
	        this.location = location;
	        this.date = date;
	        this.maxParticipants = maxParticipants;
	        this.registrations = new ArrayList<>();
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
	    public ArrayList<Registration> getRegistrations() {
	        return registrations;
	    }

	    public void setRegistrations(ArrayList<Registration> registrations) {
	        this.registrations = registrations;
	    }
	    
	    
	    public String toString() {
	        return "Event ID: " + eventId +
	                ", Name: " + name +
	                ", Location: " + location +
	                ", Date: " + date +
	                ", Max Participants: " + maxParticipants;
	        
	    }
	}


