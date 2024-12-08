package Views;

import Controllers.SuperAdminDashboardController;
import utilities.Employee;
import Models.EmployeeModel;
import utilities.ColorPalette;
import utilities.Styling;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class ViewBranchManagersView extends JFrame {
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private JTextField searchField;
    public JTable managerTable;
    private DefaultTableModel tableModel;
    private EmployeeModel empModel;

    public ViewBranchManagersView(EmployeeModel empModel) {
        this.empModel = empModel;

        setTitle("Add Branch Manager");
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

    private void init() {
        setLayout(new BorderLayout());

        // Dashboard
        JPanel dashboardPanel = new JPanel();
        Styling.setDashboard(this, dashboardPanel, d, "Super Admin - View Branch Managers");
        add(dashboardPanel, BorderLayout.NORTH);

        add(createViewManagersPanel());

        // Back Button
        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.WHITE);
        backPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton backButton = new JButton("Back");
        Styling.setButton(backButton);

        backButton.addActionListener(e -> {
            new SuperAdminDashboardController(empModel); // Navigate back to SuperAdminView
            dispose(); // Close the current frame
        });

        backPanel.add(backButton);
        add(backPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createViewManagersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Search Field
        searchField = new JTextField(10);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ColorPalette.BLUE, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        searchField.setFont(Styling.bodyFont);
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.BLACK);
        searchField.setCaretColor(Color.pink);

        JLabel searchlbl = new JLabel("Search: ");
        Styling.setLabelHeading(searchlbl);

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchlbl, BorderLayout.LINE_START);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 400, 20, 400));
        panel.add(searchPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Name", "Email", "Branch Code", "Salary", "Actions"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        managerTable = new JTable(tableModel);

        // Set custom styling for the table
        managerTable.setBackground(Color.WHITE);
        managerTable.setFont(Styling.bodyFont);
        managerTable.setRowHeight(30);
        managerTable.getTableHeader().setFont(Styling.headingFont);
        managerTable.getTableHeader().setBackground(ColorPalette.BLUE);
        managerTable.getTableHeader().setForeground(Color.WHITE);
        managerTable.setGridColor(ColorPalette.LIGHT_GREY);
        managerTable.setIntercellSpacing(new Dimension(10, 10));
        managerTable.setSelectionBackground(new Color(230, 230, 255));

        // Shrink the width of the columns
        TableColumnModel columnModel = managerTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); // ID
        columnModel.getColumn(1).setPreferredWidth(150); // Name
        columnModel.getColumn(2).setPreferredWidth(200); // Email
        columnModel.getColumn(3).setPreferredWidth(100); // Branch Code
        columnModel.getColumn(4).setPreferredWidth(100); // Salary
        columnModel.getColumn(5).setPreferredWidth(100); // Delete button

        managerTable.getColumn("Actions").setCellRenderer((TableCellRenderer) new ButtonRenderer());
        managerTable.getColumn("Actions").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(managerTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadBranchManagers();

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });

        return panel;
    }

    public void loadBranchManagers() {
        List<Employee> branchManagers = empModel.getBranchManagers();
        for (Employee emp : branchManagers) {
            Object[] row = {emp.getEmpNo(), emp.getName(), emp.getEmail(), emp.getBranchCode(), emp.getSalary(), "Delete"};
            tableModel.addRow(row);
        }
    }

    //search bar
    private void filterTable() {
        String filterText = searchField.getText().toLowerCase();

        tableModel.setRowCount(0);

        List<Employee> branchManagers = empModel.getBranchManagers();

        for (Employee emp : branchManagers) {
            boolean matches = emp.getName().toLowerCase().contains(filterText) ||
                    emp.getEmail().toLowerCase().contains(filterText) ||
                    emp.getBranchCode().toLowerCase().contains(filterText) ||
                    String.valueOf(emp.getSalary()).toLowerCase().contains(filterText);

            if (matches) {
                Object[] row = {emp.getEmpNo(), emp.getName(), emp.getEmail(), emp.getBranchCode(), emp.getSalary(), "Delete"};
                tableModel.addRow(row);
            }
        }
    }

    //add button in last column
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Delete");
            setBackground(ColorPalette.BLUE);
            setForeground(Color.WHITE);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    //deletes employee
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setBackground(ColorPalette.BLUE);
            button.setForeground(Color.WHITE);
            button.setOpaque(true);
            button.addActionListener(e -> {
                row = managerTable.getSelectedRow();
                String empId = tableModel.getValueAt(row, 0).toString();
                empModel.deleteEmployee(Integer.parseInt(empId));

                // Remove the row from the table
                tableModel.removeRow(row);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }
    }
}
