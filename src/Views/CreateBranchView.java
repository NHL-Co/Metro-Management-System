package Views;

import Controllers.SuperAdminDashboardController;
import Models.EmployeeModel;
import utilities.ColorPalette;
import utilities.Styling;

import javax.swing.*;
import java.awt.*;

public class CreateBranchView extends JFrame {
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private JTextField branchCodeField;
    private JComboBox<String> cityComboBox;
    private JTextField addressField;
    private JTextField phoneField;
    private EmployeeModel empModel;
    private static JButton saveButton;

    public CreateBranchView(EmployeeModel empModel) {
        this.empModel = empModel;

        setTitle("Create Branch");
        ImageIcon icon = new ImageIcon("src/Images/MetroLogo.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(d.width, d.height);
        setBackground(ColorPalette.LIGHT_GREY);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        init();
    }

    public void init() {
        setLayout(new BorderLayout());

        // Add Dashboard
        JPanel dashboardPanel = new JPanel();
        Styling.setDashboard(this, dashboardPanel, d, "Super Admin - Create Branch", "Super Admin");
        add(dashboardPanel, BorderLayout.NORTH);

        // Create Branch Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Branch Code
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Branch Code:"), gbc);
        gbc.gridx = 1;
        branchCodeField = new JTextField(20);
        Styling.setTextField(branchCodeField);
        formPanel.add(branchCodeField, gbc);

        // City
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("City:"), gbc);
        gbc.gridx = 1;
        cityComboBox = new JComboBox<>(getPakistaniCities());
        Styling.setComboBox(cityComboBox);
        formPanel.add(cityComboBox, gbc);

        // Address
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        addressField = new JTextField(20);
        Styling.setTextField(addressField);
        formPanel.add(addressField, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        Styling.setTextField(phoneField);
        formPanel.add(phoneField, gbc);

        // Button Panel (above footer)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        saveButton = new JButton("Save");
        Styling.setButton(saveButton);

        JButton cancelButton = new JButton("Cancel");
        Styling.setButton(cancelButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Add action for cancel button
        cancelButton.addActionListener(e -> {
            new SuperAdminDashboardController(empModel);
            dispose();
        });

        // Footer Panel
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setPreferredSize(new Dimension(d.width, 50));
        footerPanel.add(Styling.footer(this), BorderLayout.SOUTH);

        // Main Bottom Panel (button panel + footer)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(footerPanel, BorderLayout.SOUTH);

        // Add formPanel and bottomPanel to main layout
        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


    private String[] getPakistaniCities() {
        return new String[]{"Karachi", "Lahore", "Islamabad", "Rawalpindi", "Peshawar", "Quetta", "Faisalabad", "Multan", "Sialkot", "Hyderabad"};
    }

    // Getters for form fields
    public String getBranchCode() {
        return branchCodeField.getText();
    }

    public String getSelectedCity() {
        return (String) cityComboBox.getSelectedItem();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getPhone() {
        return phoneField.getText();
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
