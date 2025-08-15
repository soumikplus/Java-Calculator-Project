import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Student GPA App");
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with vertical layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);
        nameField.setMaximumSize(new Dimension(500,25));

        JLabel rollLabel = new JLabel("Roll No:");
        JTextField rollField = new JTextField(10);
        rollField.setMaximumSize(new Dimension(100,25));

        JButton submitButton = new JButton("Submit");
        JLabel resultLabel = new JLabel("");

        // Add action to button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String roll = rollField.getText();
                resultLabel.setText("Student: " + name + " | Roll: " + roll);
            }
        });

        // Add components to the panel, one per line
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(10)); // Space between fields

        panel.add(rollLabel);
        panel.add(rollField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(submitButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(resultLabel);

        // Add panel to frame
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
