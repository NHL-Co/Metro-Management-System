package Views;

import utilities.ColorPalette;
import utilities.RoundedButton;
import utilities.Styling;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DEODashboardView extends JFrame {

    private RoundedButton vendorButton;
    private RoundedButton productButton;
    private JButton addVendorButton;
    private JButton saveProductButton;

    private JTextField vendorNameField;
    private JTextField vendorCnicField;
    private JTextField vendorPhoneField;

    private JTextField productNameField;
    private JTextField productOriginalPriceField;
    private JTextField productSalePriceField;
    private JTextField productUnitPriceField;
    private JTextField productCartonPriceField;
    private String[] categories;
    private JComboBox<String> vendorComboBox;
    private JComboBox<String> productCategoryComboBox;

    private JPanel contentPanel;
    private JPanel vendorPanel;
    private JPanel productPanel;

    private Dimension d = new Dimension();

    public DEODashboardView() {
        setTitle("Data Entry Operator Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon icon = new ImageIcon("src/Images/MetroLogo.png");
        setIconImage(icon.getImage());
        setSize(d.width, d.height);
        setBackground(ColorPalette.LIGHT_GREY);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());


        initializeUI();

        setVisible(true);
    }

    private JPanel mainPanel;

    private void initializeUI() {
        // Create and add the header panel
        JPanel headerPanel = new JPanel();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Styling.setDashboard(headerPanel, d, "DEO Dashboard");
        add(headerPanel, BorderLayout.NORTH);

        JLabel dashboardHeading = new JLabel("Data Entry Operator Dashboard");
        dashboardHeading.setForeground(ColorPalette.YELLOW);
        dashboardHeading.setFont(new Font("Franklin Gothic Book", Font.BOLD, 27)); // Apply styling
        dashboardHeading.setHorizontalAlignment(SwingConstants.CENTER);
        dashboardHeading.setVerticalAlignment(SwingConstants.CENTER);
        vendorComboBox = new JComboBox<>(); // Initialize combo box
        // Main panel to switch between centerPanel and contentPanel
        mainPanel = new JPanel(new CardLayout());
        JPanel centerPanel = createCenterPanel();
        contentPanel = new JPanel(new CardLayout());

        createVendorPanel();
        createProductPanel();

        contentPanel.add(vendorPanel, "VendorPanel");
        contentPanel.add(productPanel, "ProductPanel");

        // Add centerPanel and contentPanel to mainPanel
        mainPanel.add(centerPanel, "CenterPanel");
        mainPanel.add(contentPanel, "ContentPanel");

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.white);

        // Create and style buttons
        vendorButton = new RoundedButton("Vendor Management", 30);
        productButton = new RoundedButton("Product Management", 30);

        // Add icons to buttons
        vendorButton.setIcon(new ImageIcon("src/Images/vendor.png"));
        productButton.setIcon(new ImageIcon("src/Images/cart.png"));

        // Style the buttons using the Styling class
        Styling.setBigButton(vendorButton);
        Styling.setBigButton(productButton);

        // Center the buttons and labels using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Controlled spacing (5px for top/bottom, 10px for left/right)

        // Add vendor button and text
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(vendorButton, gbc);


        // Add product button and text
        gbc.gridx = 1; gbc.gridy = 0;
        centerPanel.add(productButton, gbc);

        gbc.gridx = 1; gbc.gridy = 1;

        return centerPanel;
    }


    private void createVendorPanel() {
        vendorPanel = new JPanel(new GridBagLayout());
        // Customizing the TitledBorder
        TitledBorder vendorBorder = BorderFactory.createTitledBorder("VENDOR MANAGEMENT");
        vendorBorder.setTitleFont(new Font("Franklin Gothic Book", Font.BOLD, 22)); // Set a larger font size
        vendorBorder.setTitleColor(ColorPalette.BLUE); // Set the color to blue for a fancy effect
        vendorBorder.setTitlePosition(TitledBorder.TOP); // Center the title at the top

        vendorPanel.setBorder(vendorBorder);
        vendorPanel.setBackground(Color.WHITE);  // Set the background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Vendor Name
        gbc.gridx = 0; gbc.gridy = 0;
        vendorPanel.add(new JLabel("Vendor Name:"), gbc);

        gbc.gridx = 1;
        vendorNameField = new JTextField(20);
        Styling.setTextField(vendorNameField);
        vendorPanel.add(vendorNameField, gbc);

        // Vendor CNIC
        gbc.gridx = 0; gbc.gridy = 1;
        vendorPanel.add(new JLabel("Vendor CNIC:"), gbc);

        gbc.gridx = 1;
        vendorCnicField = new JTextField(20);
        Styling.setTextField(vendorCnicField);
        vendorPanel.add(vendorCnicField, gbc);

        // Vendor Phone Number
        gbc.gridx = 0; gbc.gridy = 2;
        vendorPanel.add(new JLabel("Vendor Phone Number:"), gbc);

        gbc.gridx = 1;
        vendorPhoneField = new JTextField(20);
        Styling.setTextField(vendorPhoneField);
        vendorPanel.add(vendorPhoneField, gbc);

        // Add Vendor Button (aligned better)
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        addVendorButton = new JButton("Add Vendor");
        Styling.setButton(addVendorButton);
        vendorPanel.add(addVendorButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        Styling.setButton(backButton);
        backButton.addActionListener(e -> showMainPanel());

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        vendorPanel.add(backButton, gbc);
    }


 // Declare productCategoryComboBox

    private void createProductPanel() {
        productPanel = new JPanel(new GridBagLayout());

        // Customizing the TitledBorder
        TitledBorder productBorder = BorderFactory.createTitledBorder("PRODUCT MANAGEMENT");
        productBorder.setTitleFont(new Font("Franklin Gothic Book", Font.BOLD, 22)); // Set a larger font size
        productBorder.setTitleColor(ColorPalette.BLUE); // Set the color to blue for a fancy effect
        productBorder.setTitlePosition(TitledBorder.TOP); // Center the title at the top

        productPanel.setBorder(productBorder);
        productPanel.setBackground(Color.white);  // Set the background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Product Name
        gbc.gridx = 0; gbc.gridy = 0;
        productPanel.add(new JLabel("Product Name:"), gbc);

        gbc.gridx = 1;
        productNameField = new JTextField(20);
        productPanel.add(productNameField, gbc);

        // Product Category (ComboBox)
        gbc.gridx = 0; gbc.gridy = 1;
        productPanel.add(new JLabel("Product Category:"), gbc);

        gbc.gridx = 1;
        productCategoryComboBox = new JComboBox<>(); // Initialize the JComboBox
        populateCategoryComboBox(); // Populate the combo box with categories
        productCategoryComboBox.setBackground(Color.WHITE);  // Set background color
        productCategoryComboBox.setForeground(Color.BLACK);  // Set text color
        productCategoryComboBox.setFont(new Font("Arial", Font.PLAIN, 14));  // Set font

        // Set the same preferred size for both combo boxes
        setComboBoxPreferredSize(productCategoryComboBox);

        productPanel.add(productCategoryComboBox, gbc);

        // Vendor Selection (ComboBox)
        gbc.gridx = 0; gbc.gridy = 2;
        productPanel.add(new JLabel("Select Vendor:"), gbc);

        gbc.gridx = 1;
        vendorComboBox = new JComboBox<>();
        vendorComboBox.setBackground(Color.WHITE);  // Set background color
        vendorComboBox.setForeground(Color.BLACK);  // Set text color
        vendorComboBox.setFont(new Font("Arial", Font.PLAIN, 14));  // Set font

        // Set the same preferred size for both combo boxes
        setComboBoxPreferredSize(vendorComboBox);

        productPanel.add(vendorComboBox, gbc);

        // Product Price Fields
        gbc.gridx = 0; gbc.gridy = 3;
        productPanel.add(new JLabel("Original Price:"), gbc);
        gbc.gridx = 1;
        productOriginalPriceField = new JTextField(20);
        productPanel.add(productOriginalPriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        productPanel.add(new JLabel("Sale Price:"), gbc);
        gbc.gridx = 1;
        productSalePriceField = new JTextField(20);
        productPanel.add(productSalePriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        productPanel.add(new JLabel("Unit Price:"), gbc);
        gbc.gridx = 1;
        productUnitPriceField = new JTextField(20);
        productPanel.add(productUnitPriceField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        productPanel.add(new JLabel("Carton Price:"), gbc);
        gbc.gridx = 1;
        productCartonPriceField = new JTextField(20);
        productPanel.add(productCartonPriceField, gbc);

        // Save Product Button
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        saveProductButton = new JButton("Save Product");
        Styling.setButton(saveProductButton);
        productPanel.add(saveProductButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        Styling.setButton(backButton);
        backButton.addActionListener(e -> showMainPanel());

        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        productPanel.add(backButton, gbc);
    }

    private void populateCategoryComboBox() {
        // List of categories typically found in a department store
          categories = new String[]{
                  "Fruits", "Vegetables", "Appliances", "Poultry", "Meat", "Dairy", "Bakery",
                  "Beverages", "Snacks", "Household Items", "Personal Care", "Health & Wellness",
                  "Clothing", "Footwear", "Toys & Games", "Electronics", "Furniture", "Books",
                  "Stationery", "Cosmetics", "Cleaning Supplies", "Automotive", "Gardening",
                  "Pet Supplies", "Baby Products", "Sports & Fitness", "Camping & Hiking",
                  "Jewelry", "Tools & Hardware", "Office Supplies", "Grocery", "Seasonal Decorations"
          };

        // Add categories to the combo box
        for (String category : categories) {
            productCategoryComboBox.addItem(category);
        }
    }

    // Helper method to set the same preferred size for both combo boxes
    private void setComboBoxPreferredSize(JComboBox<String> comboBox) {
        comboBox.setPreferredSize(new Dimension(200, 30)); // Set the same size for both combo boxes
    }


    private void showMainPanel() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "CenterPanel");
    }

    public void showPanel(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "ContentPanel");

        CardLayout contentLayout = (CardLayout) contentPanel.getLayout();
        contentLayout.show(contentPanel, panelName);
    }

    // Getters for the components

    public JButton getVendorButton() {
        return vendorButton;
    }

    public JButton getProductButton() {
        return productButton;
    }

    public JButton getAddVendorButton() {
        return addVendorButton;
    }

    public JButton getSaveProductButton() {
        return saveProductButton;
    }

    public JTextField getVendorNameField() {
        return vendorNameField;
    }

    public JTextField getVendorCnicField() {
        return vendorCnicField;
    }

    public JTextField getVendorPhoneField() {
        return vendorPhoneField;
    }

    public JTextField getProductNameField() {
        return productNameField;
    }

    public String getProductCategoryField() {
        int index = productCategoryComboBox.getSelectedIndex();
        return categories[index];
    }

    public JTextField getProductOriginalPriceField() {
        return productOriginalPriceField;
    }

    public JTextField getProductSalePriceField() {
        return productSalePriceField;
    }

    public JTextField getProductUnitPriceField() {
        return productUnitPriceField;
    }

    public JTextField getProductCartonPriceField() {
        return productCartonPriceField;
    }

    public JComboBox<String> getVendorComboBox() {
        return vendorComboBox;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
