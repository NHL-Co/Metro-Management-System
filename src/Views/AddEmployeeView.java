package Views;

import Controllers.SuperAdminDashboardController;
import Models.EmployeeModel;
import utilities.ColorPalette;
import utilities.Employee;
import utilities.Styling;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddEmployeeView extends JFrame {
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private JTextField nameField;
    private JTextField emailField;
    private JComboBox<String> branchCodeComboBox;
    private JTextField salaryField;
    private JButton saveButton;
    private JButton cancelButton;
    private Employee emp;
    private char empType;

    /**
     * Generic Add Employee View Constructor
     *
     * @param emp      Current employee (null if not logged in)
     * @param empModel EmployeeModel object
     * @param empType  Employee Type ('B' for Branch Manager, 'C' for Cashier, 'D' for Data Entry Operator)
     * @param branches List of branch codes to populate the branch dropdown
     */
    public AddEmployeeView(Employee emp, EmployeeModel empModel, char empType, List<String> branches) {
        this.emp = emp;
        this.empType = empType;

        setTitle(getWindowTitle());
        ImageIcon icon = new ImageIcon("src/Images/MetroLogo.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(d.width, d.height);
        setBackground(ColorPalette.LIGHT_GREY);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        init(branches);
    }

    private void init(List<String> branches) {
        setLayout(new BorderLayout());

        // Dashboard
        JPanel dashboardPanel = new JPanel();
        Styling.footer(this);
        if (emp == null) {
            Styling.setDashboard(this, dashboardPanel, d, getDashboardTitle(), "Super Admin");
        } else {
            Styling.setDashboard(this, dashboardPanel, d, getDashboardTitle(), emp.getName());
        }
        add(dashboardPanel, BorderLayout.NORTH);

        // Add Employee Panel
        JPanel addEmployeePanel = createAddEmployeePanel(branches);
        add(addEmployeePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createAddEmployeePanel(List<String> branches) {
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
        branchCodeComboBox = new JComboBox<>(branches.toArray(new String[0]));
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

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Get the appropriate window title based on empType
     */
    private String getWindowTitle() {
        switch (empType) {
            case 'C': return "Add Cashier";
            case 'D': return "Add Data Entry Operator";
            default: return "Add Branch Manager";
        }
    }

    /**
     * Get the appropriate dashboard title based on empType
     */
    private String getDashboardTitle() {
        switch (empType) {
            case 'C': return "Branch Manager - Add Cashier";
            case 'D': return "Branch Manager - Add Data Entry Operator";
            default: return "Super Admin - Add Branch Manager";
        }
    }

    // Getters for form fields
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

    public JButton getCancelButton() {
        return cancelButton;
    }
}
