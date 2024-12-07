package Views;

import Controllers.LogInOptionsController;
import utilities.ColorPalette;
import utilities.Styling;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        superAdminButton.setBackground(Color.white);
        branchManagerButton.setBackground(Color.white);
        cashierButton.setBackground(Color.WHITE);
        dataEntryOperatorButton.setBackground(Color.WHITE);

        ButtonGroup group = new ButtonGroup();
        group.add(superAdminButton);
        group.add(branchManagerButton);
        group.add(cashierButton);
        group.add(dataEntryOperatorButton);

        // Apply enhanced styling to the radio buttons
        superAdminButton.setFont(new Font("Arial", Font.PLAIN, 15));
        branchManagerButton.setFont(new Font("Arial", Font.PLAIN, 15));
        cashierButton.setFont(new Font("Arial", Font.PLAIN, 15));
        dataEntryOperatorButton.setFont(new Font("Arial", Font.PLAIN, 15));

        superAdminButton.setFocusable(false);
        branchManagerButton.setFocusable(false);
        cashierButton.setFocusable(false);
        dataEntryOperatorButton.setFocusable(false);

        // Add radio buttons to the panel
        radioButtonPanel.add(superAdminButton);
        radioButtonPanel.add(branchManagerButton);
        radioButtonPanel.add(cashierButton);
        radioButtonPanel.add(dataEntryOperatorButton);

        loginForm.add(radioButtonPanel);
        // Username and Password Fields with modern borders and larger sizes
        JPanel usernamePanel = new JPanel(new GridBagLayout());
        usernamePanel.setOpaque(false); // Ensure the panel itself is transparent
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel enterUsernamelbl = new JLabel("Username:  ");
        Styling.setLabelHeading(enterUsernamelbl); // Larger font for label
        enterName = new JTextField();
        Styling.setTextField(enterName);

        // Username Label Constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        usernamePanel.add(enterUsernamelbl, gbc);

        // Username TextField Constraints
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernamePanel.add(enterName, gbc);

        loginForm.add(usernamePanel);

        JPanel passwordPanel = new JPanel(new GridBagLayout());
        passwordPanel.setOpaque(false); // Ensure the panel itself is transparent
        JLabel enterPasswordlbl = new JLabel("Password:  ");
        Styling.setLabelHeading(enterPasswordlbl); // Larger font for label
        enterPassword = new JPasswordField();
        Styling.setPasswordField(enterPassword);

        // Password Label Constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        passwordPanel.add(enterPasswordlbl, gbc);

        // Password TextField Constraints
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordPanel.add(enterPassword, gbc);

        loginForm.add(passwordPanel);

        // Submit Button with Fancy Hover Effect
        loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(350, 60)); // Bigger and longer button
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Default Blue color
        Styling.setButton(loginButton);

        loginForm.add(Box.createRigidArea(new Dimension(0, 30)));
        loginForm.add(loginButton);

        // Add components to main frame
        add(groceryImage, BorderLayout.WEST);
        add(loginForm, BorderLayout.CENTER);

        setVisible(true);
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