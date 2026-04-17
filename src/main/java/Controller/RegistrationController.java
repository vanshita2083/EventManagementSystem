package Controller;

import com.event_managment.model.Event;
import com.event_managment.model.Participant;
import com.event_managment.model.Registration;
import Exception.EventFullException;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistrationController {

    public boolean registerToDB(int registrationId, String registrationDate, int eventId, int participantId) throws EventFullException {
        String insertSql = "INSERT INTO Registration (registration_id, registration_date, event_id, participant_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {

            Event event = getEventById(conn, eventId);
            if (event == null) {
                throw new Exception("Event not found!");
            }

            Participant participant = getParticipantById(conn, participantId);
            if (participant == null) {
                throw new Exception("Participant not found!");
            }

            if (isAlreadyRegistered(conn, eventId, participantId)) {
                throw new Exception("Participant already registered for this event!");
            }

            if (getRegistrationCount(conn, eventId) >= event.getMaxParticipants()) {
                throw new EventFullException("Event is full!");
            }

            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setInt(1, registrationId);
                stmt.setString(2, registrationDate);
                stmt.setInt(3, eventId);
                stmt.setInt(4, participantId);

                return stmt.executeUpdate() > 0;
            }

        } catch (EventFullException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Error registering participant to DB");
            e.printStackTrace();
            return false;
        }
    }

    public List<Registration> getAllRegistrations() {
        List<Registration> list = new ArrayList<>();

        String sql = """
                SELECT r.registration_id, r.registration_date,
                       e.event_id, e.event_name, e.location, e.event_date, e.max_participants,
                       p.participant_id, p.name, p.email, p.phone
                FROM Registration r
                JOIN Event e ON r.event_id = e.event_id
                JOIN Participant p ON r.participant_id = p.participant_id
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getString("location"),
                        rs.getString("event_date"),
                        rs.getInt("max_participants")
                );

                Participant participant = new Participant(
                        rs.getInt("participant_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );

                Registration registration = new Registration(
                        rs.getInt("registration_id"),
                        rs.getString("registration_date"),
                        participant,
                        event
                );

                list.add(registration);
            }

        } catch (Exception e) {
            System.out.println("Error loading registrations from DB");
            e.printStackTrace();
        }

        return list;
    }

    private Event getEventById(Connection conn, int id) throws Exception {
        String sql = "SELECT * FROM Event WHERE event_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
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
        }

        return null;
    }

    private Participant getParticipantById(Connection conn, int id) throws Exception {
        String sql = "SELECT * FROM Participant WHERE participant_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Participant(
                            rs.getInt("participant_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone")
                    );
                }
            }
        }

        return null;
    }

    private int getRegistrationCount(Connection conn, int eventId) throws Exception {
        String sql = "SELECT COUNT(*) FROM Registration WHERE event_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }

    private boolean isAlreadyRegistered(Connection conn, int eventId, int participantId) throws Exception {
        String sql = "SELECT COUNT(*) FROM Registration WHERE event_id = ? AND participant_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            ps.setInt(2, participantId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }

        return false;
    }
}