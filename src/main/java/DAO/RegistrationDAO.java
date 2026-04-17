package DAO;

import com.event_managment.model.Event;
import com.event_managment.model.Participant;
import com.event_managment.model.Registration;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {

    public boolean saveRegistration(Registration registration) {
        String sql = "INSERT INTO Registration (registration_id, registration_date, event_id, participant_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, registration.getRegistrationId());
            stmt.setString(2, registration.getRegistrationDate());
            stmt.setInt(3, registration.getEvent().getEventId());
            stmt.setInt(4, registration.getParticipant().getParticipantId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error saving registration");
            e.printStackTrace();
            return false;
        }
    }

    public List<Registration> getAllRegistrations() {
        List<Registration> registrations = new ArrayList<>();

        String sql = """
                SELECT r.registration_id, r.registration_date,
                       e.event_id, e.event_name, e.location, e.event_date, e.max_participants,
                       p.participant_id, p.name, p.email, p.phone
                FROM Registration r
                JOIN Event e ON r.event_id = e.event_id
                JOIN Participant p ON r.participant_id = p.participant_id
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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

                registrations.add(new Registration(
                        rs.getInt("registration_id"),
                        rs.getString("registration_date"),
                        participant,
                        event
                ));
            }
        } catch (Exception e) {
            System.out.println("Error fetching registrations");
            e.printStackTrace();
        }

        return registrations;
    }

    public boolean deleteRegistration(int registrationId) {
        String sql = "DELETE FROM Registration WHERE registration_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, registrationId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleting registration");
            e.printStackTrace();
            return false;
        }
    }
}