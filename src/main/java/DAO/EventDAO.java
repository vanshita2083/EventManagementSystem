package DAO;

import com.event_managment.model.Event;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public boolean saveEvent(Event event) {
        String sql = "INSERT INTO Event (event_id, event_name, location, event_date, max_participants) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, event.getEventId());
            stmt.setString(2, event.getName());
            stmt.setString(3, event.getLocation());
            stmt.setString(4, event.getDate());
            stmt.setInt(5, event.getMaxParticipants());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error saving event");
            e.printStackTrace();
            return false;
        }
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Event";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getString("location"),
                        rs.getString("event_date"),
                        rs.getInt("max_participants")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error fetching events");
            e.printStackTrace();
        }

        return events;
    }

    public Event getEventById(int eventId) {
        String sql = "SELECT * FROM Event WHERE event_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eventId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Event(
                            rs.getInt("event_id"),
                            rs.getString("event_name"),
                            rs.getString("location"),
                            rs.getString("event_date"),
                            rs.getInt("max_participants")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error finding event");
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteEvent(int eventId) {
        String sql = "DELETE FROM Event WHERE event_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eventId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleting event");
            e.printStackTrace();
            return false;
        }
    }
}