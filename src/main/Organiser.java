package main;

public class Organiser {

    private int organiserId;
    private String name;
    private String contactInfo;

    public Organiser() {
    }

    public Organiser(int organiserId, String name, String contactInfo) {
        this.organiserId = organiserId;
        this.name = name;
        this.contactInfo = contactInfo;
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

    public String toString() {
        return "Organizer ID: " + organiserId +
                ", Name: " + name +
                ", Contact: " + contactInfo;
    }
}
