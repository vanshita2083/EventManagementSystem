package View;

import javax.swing.*;
import java.awt.*;
import Controller.EventController;
import com.event_managment.model.Event;

public class EventForm extends JFrame {

	private EventController eventController = new EventController();
	
    private JTextField txtEventId;
    private JTextField txtName;
    private JTextField txtLocation;
    private JTextField txtDate;
    private JTextField txtMaxParticipants;
    private JButton btnAdd;
    private JButton btnClear;

    public EventForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Event Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Event ID:"));
        txtEventId = new JTextField();
        panel.add(txtEventId);

        panel.add(new JLabel("Event Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Location:"));
        txtLocation = new JTextField();
        panel.add(txtLocation);

        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        txtDate = new JTextField();
        panel.add(txtDate);

        panel.add(new JLabel("Max Participants:"));
        txtMaxParticipants = new JTextField();
        panel.add(txtMaxParticipants);

        btnAdd = new JButton("Add Event");
        btnAdd.addActionListener(e -> addEvent());
        btnClear = new JButton("Clear");

        panel.add(btnAdd);
        panel.add(btnClear);

        add(panel);

        btnClear.addActionListener(e -> clearFields());
    }
    private void addEvent() {
        try {
            int id = Integer.parseInt(txtEventId.getText());
            String name = txtName.getText();
            String location = txtLocation.getText();
            String date = txtDate.getText();
            int max = Integer.parseInt(txtMaxParticipants.getText());

            Event event = new Event(id, name, location, date, max);

            boolean success = eventController.saveEventToDB(event);

            if (success) {
                JOptionPane.showMessageDialog(this, "Event added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add event!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void clearFields() {
        txtEventId.setText("");
        txtName.setText("");
        txtLocation.setText("");
        txtDate.setText("");
        txtMaxParticipants.setText("");
    }
}