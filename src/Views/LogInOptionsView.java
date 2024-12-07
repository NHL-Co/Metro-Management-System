package Views;

import utilities.ColorPalette;
import utilities.Styling;
import javax.swing.*;
import java.awt.*;

public class LogInOptionsView extends JFrame {
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private static JTextField enterName;
    private static JPasswordField enterPassword;
    private static JRadioButton superAdminButton;
    private static JRadioButton branchManagerButton;
    private static JRadioButton cashierButton;
    private static JRadioButton dataEntryOperatorButton;
    private static JButton loginButton;

    public LogInOptionsView() {
        setTitle("Login");
        ImageIcon icon = new ImageIcon("src/Images/MetroLogo.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(d.width, d.height); // Fullscreen window
        setBackground(ColorPalette.LIGHT_GREY);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        init();
    }

    private void init() {
        // Main layout
        setLayout(new BorderLayout());

        // Grocery Image
        JLabel groceryImage = new JLabel(new ImageIcon(new ImageIcon("src/Images/GroceryShopping.jpg")
                .getImage()
                .getScaledInstance(d.width / 2, d.height, Image.SCALE_SMOOTH)));

        // Login Form Container
        JPanel loginForm = new JPanel();
        loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));
        loginForm.setOpaque(false); // Transparent background for the panel
        loginForm.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50)); // Adds padding around the form

        // Logo at the top
        JLabel logo = new JLabel(new ImageIcon(new ImageIcon("src/Images/MetroLogo.png")
                .getImage()
                .getScaledInstance(d.width / 4, d.height / 4, Image.SCALE_SMOOTH)));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the logo
        loginForm.add(logo);

        // Login Header
        JLabel loginlbl = new JLabel("LOGIN");
        loginlbl.setFont(new Font("Arial", Font.BOLD, 36)); // Increased size for a modern look
        loginlbl.setForeground(ColorPalette.BLUE); // Color to match button
        loginlbl.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the header
        loginForm.add(Box.createRigidArea(new Dimension(0, 40))); // Added padding after login header
        loginForm.add(loginlbl);

        // Role Selection Section (Aligned radio buttons)
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // FlowLayout to align buttons horizontally
        radioButtonPanel.setOpaque(false); // Transparent background

        superAdminButton = new JRadioButton("Super Admin");
        branchManagerButton = new JRadioButton("Branch Manager");
        cashierButton = new JRadioButton("Cashier");
        dataEntryOperatorButton = new JRadioButton("Data-Entry Operator");

        ButtonGroup group = new ButtonGroup();
        group.add(superAdminButton);
        group.add(branchManagerButton);
        group.add(cashierButton);
        group.add(dataEntryOperatorButton);

        // Apply enhanced styling to the radio buttons
        customizeRadioButton(superAdminButton);
        customizeRadioButton(branchManagerButton);
        customizeRadioButton(cashierButton);
        customizeRadioButton(dataEntryOperatorButton);

        // Add radio buttons to the panel
        radioButtonPanel.add(superAdminButton);
        radioButtonPanel.add(branchManagerButton);
        radioButtonPanel.add(cashierButton);
        radioButtonPanel.add(dataEntryOperatorButton);

        loginForm.add(radioButtonPanel);

        // Use GridBagLayout for Username and Password Fields to ensure close alignment
        JPanel credentialsPanel = new JPanel(new GridBagLayout());
        credentialsPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Adjust padding between components

        // Username
        JLabel enterUsernamelbl = new JLabel("Email:  ");
        Styling.setLabelHeading(enterUsernamelbl); // Larger font for label
        enterName = new JTextField();
        Styling.setTextField(enterName);

        // Password
        JLabel enterPasswordlbl = new JLabel("Password:  ");
        Styling.setLabelHeading(enterPasswordlbl); // Larger font for label
        enterPassword = new JPasswordField();
        Styling.setPasswordField(enterPassword);

        // Username label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        credentialsPanel.add(enterUsernamelbl, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        credentialsPanel.add(enterName, gbc);

        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        credentialsPanel.add(enterPasswordlbl, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        credentialsPanel.add(enterPassword, gbc);

        loginForm.add(credentialsPanel);

        // Submit Button with Fancy Hover Effect
        loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(350, 60)); // Bigger and longer button
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Default Blue color
        Styling.setButton(loginButton);

        loginForm.add(Box.createRigidArea(new Dimension(0, 30))); // Adjust the gap before the button
        loginForm.add(loginButton);

        // Add components to main frame
        add(groceryImage, BorderLayout.WEST);
        add(loginForm, BorderLayout.CENTER);

        setVisible(true);
    }

    // Method to customize radio buttons
    private void customizeRadioButton(JRadioButton radioButton) {
        // Set the font, size, and border for radio buttons
        radioButton.setFont(new Font("Bell MT", Font.PLAIN, 18));
        radioButton.setForeground(ColorPalette.BLUE);  // A darker blue for the text

        // Custom border and background
        radioButton.setBorder(BorderFactory.createLineBorder(ColorPalette.BLUE, 2));
        radioButton.setBackground(Color.white);
        radioButton.setContentAreaFilled(false);
        radioButton.setFocusPainted(false);
        radioButton.setOpaque(true);

        // Add hover effect
        radioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                radioButton.setBackground(new Color(230, 230, 255)); // Light blue hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                radioButton.setBackground(Color.white); // Reset background
            }
        });

        // Change the background when selected
        radioButton.addActionListener(e -> {
            if (radioButton.isSelected()) {
                radioButton.setBackground(new Color(130, 230, 255)); // Change to a light blue when selected
            } else {
                radioButton.setBackground(Color.white); // Reset to white when not selected
            }
        });
    }




    // Getter for username
    public String getUsername() {
        return enterName.getText();
    }

    // Getter for password
    public String getPassword() {
        return new String(enterPassword.getPassword());
    }

    // Getter for login button
    public JButton getLoginButton(){
        return loginButton;
    }

    // Getter for selected role
    public String getSelectedRole() {
        if (superAdminButton.isSelected()) {
            return "Super Admin";
        } else if (branchManagerButton.isSelected()) {
            return "B"; // Branch Manager - type "B"
        } else if (cashierButton.isSelected()) {
            return "C"; // Cashier - type "C"
        } else if (dataEntryOperatorButton.isSelected()) {
            return "D"; // Data Entry Operator - type "D"
        }
        return "";
    }
}