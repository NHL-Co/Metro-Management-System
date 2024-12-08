package Views;

import Controllers.SuperAdminDashboardController;
import Models.BranchModel;
import Models.EmployeeModel;
import utilities.ColorPalette;
import utilities.Styling;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddBranchManagerView extends JFrame {
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private JTextField nameField;
    private JTextField emailField;
    private JComboBox<String> branchCodeComboBox;
    private JTextField salaryField;
    private JButton saveButton;
    private JButton cancelButton;
    private BranchModel branchModel;
    private EmployeeModel empModel;

    public AddBranchManagerView(EmployeeModel empModel) {
        this.empModel = empModel;
        this.branchModel = new BranchModel();

        setTitle("Add Branch Manager");
        ImageIcon icon = new ImageIcon("src/Images/MetroLogo.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(d.width, d.height);
        setBackground(ColorPalette.LIGHT_GREY);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        init(branchModel);
    }

    private void init(BranchModel branchModel) {
        setLayout(new BorderLayout());

        // Dashboard
        JPanel dashboardPanel = new JPanel();
        Styling.setDashboard(this, dashboardPanel, d, "Super Admin - Add Branch Manager");
        add(dashboardPanel, BorderLayout.NORTH);
        add(createAddManagerPanel(branchModel));

        setVisible(true);
    }

    private JPanel createAddManagerPanel(BranchModel branchModel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        Styling.setTextField(nameField);
        formPanel.add(nameField, gbc);

        // Email Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        Styling.setTextField(emailField);
        formPanel.add(emailField, gbc);

        // Branch Code ComboBox
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Branch Code:"), gbc);
        gbc.gridx = 1;
        branchCodeComboBox = new JComboBox<>(getBranchCodes(branchModel));
        Styling.setComboBox(branchCodeComboBox);
        formPanel.add(branchCodeComboBox, gbc);

        // Salary Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1;
        salaryField = new JTextField(20);
        Styling.setTextField(salaryField);
        formPanel.add(salaryField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        saveButton = new JButton("Save");
        Styling.setButton(saveButton);

        cancelButton = new JButton("Cancel");
        Styling.setButton(cancelButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        cancelButton.addActionListener(e -> {
            new SuperAdminDashboardController(empModel);
            dispose();
        });

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    private String[] getBranchCodes(BranchModel branchModel) {
        List<String> branchCodes = branchModel.getBranchCodes();
        return branchCodes.toArray(new String[0]);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getEmail() {
        return emailField;
    }

    public String getSelectedBranchCode() {
        return (String) branchCodeComboBox.getSelectedItem();
    }

    public JTextField getSalary() {
        return salaryField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
