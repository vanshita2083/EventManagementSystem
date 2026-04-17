package View;

import javax.swing.*;
import java.awt.*;
import Controller.ParticipantController;
import com.event_managment.model.Participant;

public class ParticipantForm extends JFrame {

	private ParticipantController participantController = new ParticipantController();
	
    private JTextField txtParticipantId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JButton btnAdd;
    private JButton btnClear;

    public ParticipantForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Participant Form");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Participant ID:"));
        txtParticipantId = new JTextField();
        panel.add(txtParticipantId);

        panel.add(new JLabel("Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        panel.add(txtPhone);

        btnAdd = new JButton("Add Participant");
        btnAdd.addActionListener(e -> addParticipant());
        btnClear = new JButton("Clear");

        panel.add(btnAdd);
        panel.add(btnClear);

        add(panel);

        btnClear.addActionListener(e -> clearFields());
    }

    private void addParticipant() {
        try {
            int id = Integer.parseInt(txtParticipantId.getText());
            String name = txtName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();

            Participant participant = new Participant(id, name, email, phone);

            boolean success = participantController.saveParticipantToDB(participant);

            if (success) {
                JOptionPane.showMessageDialog(this, "Participant added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add participant!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
    
    private void clearFields() {
        txtParticipantId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
    }
}