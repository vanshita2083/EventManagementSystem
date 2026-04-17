package DAO;

import com.event_managment.model.Participant;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    public boolean saveParticipant(Participant participant) {
        String sql = "INSERT INTO Participant (participant_id, name, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, participant.getParticipantId());
            stmt.setString(2, participant.getName());
            stmt.setString(3, participant.getEmail());
            stmt.setString(4, participant.getPhone());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error saving participant");
            e.printStackTrace();
            return false;
        }
    }

    public List<Participant> getAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        String sql = "SELECT * FROM Participant";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                participants.add(new Participant(
                        rs.getInt("participant_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error fetching participants");
            e.printStackTrace();
        }

        return participants;
    }

    public Participant getParticipantById(int participantId) {
        String sql = "SELECT * FROM Participant WHERE participant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, participantId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Participant(
                            rs.getInt("participant_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error finding participant");
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteParticipant(int participantId) {
        String sql = "DELETE FROM Participant WHERE participant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, participantId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleting participant");
            e.printStackTrace();
            return false;
        }
    }
}