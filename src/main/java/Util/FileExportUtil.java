package Util;

import com.event_managment.model.Participant;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class FileExportUtil {

    public static void exportParticipantsToCSV(int eventId, List<Participant> participants) {
        String fileName = "event_" + eventId + "_participants.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("participant_id,name,email,phone");

            for (Participant participant : participants) {
                writer.println(
                        participant.getParticipantId() + "," +
                        participant.getName() + "," +
                        participant.getEmail() + "," +
                        participant.getPhone()
                );
            }

            System.out.println("CSV file exported successfully: " + fileName);
        } catch (Exception e) {
            System.out.println("Error exporting CSV file");
            e.printStackTrace();
        }
    }

    public static void exportParticipantsToJSON(int eventId, List<Participant> participants) {
        String fileName = "event_" + eventId + "_participants.json";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("[");

            for (int i = 0; i < participants.size(); i++) {
                Participant participant = participants.get(i);

                writer.println("  {");
                writer.println("    \"participantId\": " + participant.getParticipantId() + ",");
                writer.println("    \"name\": \"" + participant.getName() + "\",");
                writer.println("    \"email\": \"" + participant.getEmail() + "\",");
                writer.println("    \"phone\": \"" + participant.getPhone() + "\"");
                writer.print("  }");

                if (i < participants.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }

            writer.println("]");

            System.out.println("JSON file exported successfully: " + fileName);
        } catch (Exception e) {
            System.out.println("Error exporting JSON file");
            e.printStackTrace();
        }
    }
}