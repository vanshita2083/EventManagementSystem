package main;

	public class Registration {

	    private int registrationId;
	    private String registrationDate;

	    public Registration() {
	    }

	    public Registration(int registrationId, String registrationDate) {
	        this.registrationId = registrationId;
	        this.registrationDate = registrationDate;
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

	    public String toString() {
	        return "Registration ID: " + registrationId +
	                ", Date: " + registrationDate;
	    }
	}
