package gpacalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GpaApp extends JFrame {
    private JTextField nameField, rollField, semField;
    private JPanel sgpaPanel;
    private JButton calcButton;
    private JTextField[] sgpaFields;

    public GpaApp() {
        setTitle("Student GPA Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(300, 30));
        add(nameField);

        add(new JLabel("Roll No:"));
        rollField = new JTextField();
        rollField.setMaximumSize(new Dimension(300, 30));
        add(rollField);

        add(new JLabel("Number of Semesters (max 8):"));
        semField = new JTextField();
        semField.setMaximumSize(new Dimension(300, 30));
        add(semField);

        JButton genBtn = new JButton("Generate SGPA Fields");
        add(genBtn);

        sgpaPanel = new JPanel();
        sgpaPanel.setLayout(new BoxLayout(sgpaPanel, BoxLayout.Y_AXIS));
        add(sgpaPanel);

        calcButton = new JButton("Calculate YGPA & DGPA");
        calcButton.setEnabled(false);
        add(calcButton);

        genBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateSGPAFields();
            }
        });

        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateAndShow();
            }
        });

        setVisible(true);
    }

    private void generateSGPAFields() {
        sgpaPanel.removeAll();

        int semCount;
        try {
            semCount = Integer.parseInt(semField.getText());
            if (semCount < 1 || semCount > 8) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid semester count (1–8)");
            return;
        }

        sgpaFields = new JTextField[semCount];
        for (int i = 0; i < semCount; i++) {
            sgpaPanel.add(new JLabel("SGPA for Sem " + (i + 1) + ":"));
            sgpaFields[i] = new JTextField();
            sgpaFields[i].setMaximumSize(new Dimension(200, 30));
            sgpaPanel.add(sgpaFields[i]);
        }

        calcButton.setEnabled(true);
        sgpaPanel.revalidate();
        sgpaPanel.repaint();
    }

    private void calculateAndShow() {
        try {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            int sems = sgpaFields.length;

            if (name.isEmpty() || roll.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in name and roll number.");
                return;
            }

            Student s = new Student(name, roll, sems);
            for (int i = 0; i < sems; i++) {
                s.sgpa[i] = Double.parseDouble(sgpaFields[i].getText().trim());
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Name: ").append(name).append("\n");
            sb.append("Roll: ").append(roll).append("\n\n");

            for (String yg : s.calculateYGPAs()) {
                sb.append(yg).append("\n");
            }

            sb.append("\nFinal DGPA: ").append(String.format("%.2f", s.calculateDGPA()));

            JOptionPane.showMessageDialog(this, sb.toString(), "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check SGPA values.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GpaApp());
    }
}

