package Controller;

import com.event_managment.model.Event;
import com.event_managment.model.Participant;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class EventController {

    public boolean saveEventToDB(Event event) {
        String sql = "INSERT INTO Event (event_id, event_name, location, event_date, max_participants) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, event.getEventId());
            stmt.setString(2, event.getName());
            stmt.setString(3, event.getLocation());
            stmt.setString(4, event.getDate());
            stmt.setString(4, event.getDate());
            stmt.setInt(5, event.getMaxParticipants());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error saving event to DB");
            e.printStackTrace();
            return false;
        }
    }

    public List<Event> getEventsFromDB() {
        List<Event> events = new LinkedList<>();
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
            System.out.println("Error loading events from DB");
            e.printStackTrace();
        }

        return events;
    }

    public Event findEventByIdFromDB(int id) {
        String sql = "SELECT * FROM Event WHERE event_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

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
            System.out.println("Error finding event in DB");
            e.printStackTrace();
        }

        return null;
    }

    public Event searchEventInDB(String name) {
        String sql = "SELECT * FROM Event WHERE event_name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);

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
            System.out.println("Error searching event in DB");
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteEventFromDB(int id) {
        String sql = "DELETE FROM Event WHERE event_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error deleting event from DB");
            e.printStackTrace();
            return false;
        }
    }

    public List<Participant> getParticipantsForEventFromDB(int eventId) {
        List<Participant> participants = new LinkedList<>();

        String sql = """
                SELECT p.participant_id, p.name, p.email, p.phone
                FROM Registration r
                JOIN Participant p ON p.participant_id = r.participant_id
                WHERE r.event_id = ?
                ORDER BY p.participant_id
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eventId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    participants.add(new Participant(
                            rs.getInt("participant_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone")
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading participants for event from DB");
            e.printStackTrace();
        }

        return participants;
    }
}