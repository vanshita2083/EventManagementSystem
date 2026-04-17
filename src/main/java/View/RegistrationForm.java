package View;

import javax.swing.*;
import java.awt.*;
import Controller.RegistrationController;
import Exception.EventFullException;

public class RegistrationForm extends JFrame {

	private RegistrationController registrationController = new RegistrationController();
	
    private JTextField txtRegistrationId;
    private JTextField txtRegistrationDate;
    private JTextField txtEventId;
    private JTextField txtParticipantId;
    private JButton btnRegister;
    private JButton btnClear;

    public RegistrationForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Registration Form");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Registration ID:"));
        txtRegistrationId = new JTextField();
        panel.add(txtRegistrationId);

        panel.add(new JLabel("Registration Date (YYYY-MM-DD):"));
        txtRegistrationDate = new JTextField();
        panel.add(txtRegistrationDate);

        panel.add(new JLabel("Event ID:"));
        txtEventId = new JTextField();
        panel.add(txtEventId);

        panel.add(new JLabel("Participant ID:"));
        txtParticipantId = new JTextField();
        panel.add(txtParticipantId);

        btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> registerParticipant());
        btnClear = new JButton("Clear");

        panel.add(btnRegister);
        panel.add(btnClear);

        add(panel);

        btnClear.addActionListener(e -> clearFields());
    }
    private void registerParticipant() {
        try {
            int registrationId = Integer.parseInt(txtRegistrationId.getText());
            String registrationDate = txtRegistrationDate.getText();
            int eventId = Integer.parseInt(txtEventId.getText());
            int participantId = Integer.parseInt(txtParticipantId.getText());

            boolean success = registrationController.registerToDB(
                    registrationId,
                    registrationDate,
                    eventId,
                    participantId
            );

            if (success) {
                JOptionPane.showMessageDialog(this, "Registration added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed!");
            }

        } catch (EventFullException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void clearFields() {
        txtRegistrationId.setText("");
        txtRegistrationDate.setText("");
        txtEventId.setText("");
        txtParticipantId.setText("");
    }
}