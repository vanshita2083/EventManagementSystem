package Controller;

import com.event_managment.model.Participant;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ParticipantController {

    public boolean saveParticipantToDB(Participant participant) {
        String sql = "INSERT INTO Participant (participant_id, name, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, participant.getParticipantId());
            stmt.setString(2, participant.getName());
            stmt.setString(3, participant.getEmail());
            stmt.setString(4, participant.getPhone());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error saving participant to DB");
            e.printStackTrace();
            return false;
        }
    }

    public Participant findByIdFromDB(int id) {
        String sql = "SELECT * FROM Participant WHERE participant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

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
            System.out.println("Error finding participant in DB");
            e.printStackTrace();
        }

        return null;
    }

    public Participant searchParticipantInDB(String name) {
        String sql = "SELECT * FROM Participant WHERE name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);

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
            System.out.println("Error searching participant in DB");
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteParticipantFromDB(int id) {
        String sql = "DELETE FROM Participant WHERE participant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error deleting participant from DB");
            e.printStackTrace();
            return false;
        }
    }
}