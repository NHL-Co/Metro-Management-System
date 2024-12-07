package Controllers;

import Models.EmployeeModel;
import Models.ProductModel;
import Models.VendorModel;
import Views.DEODashboardView;
import utilities.Employee;
import utilities.InputValidation;
import utilities.MessageDialog;
import utilities.Product;
import utilities.Vendor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DEODashboardController {
    private VendorModel vendorModel;
    private ProductModel prodModel;
    private EmployeeModel empModel;
    private Employee deo;
    private DEODashboardView view;

    public DEODashboardController(Employee deo, EmployeeModel empModel) {
        this.deo = deo;
        this.empModel = empModel;
        this.prodModel = new ProductModel();
        this.vendorModel = new VendorModel();
        this.view = new DEODashboardView();

        initializeListeners();
        populateVendorComboBox();
    }

    private void initializeListeners() {
        // Navigation button listeners to switch panels
        view.getVendorButton().addActionListener(e -> {
            view.showPanel("VendorPanel");
        });

        view.getProductButton().addActionListener(e -> {
            view.showPanel("ProductPanel");
        });

        // Add vendor button listener
        view.getAddVendorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVendor();
            }
        });

        // Save product button listener
        view.getSaveProductButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
    }

    private void showVendorPanel() {
        CardLayout cl = (CardLayout) view.getContentPanel().getLayout();
        cl.show(view.getContentPanel(), "VendorPanel");
    }

    private void showProductPanel() {
        CardLayout cl = (CardLayout) view.getContentPanel().getLayout();
        cl.show(view.getContentPanel(), "ProductPanel");
    }

    private void populateVendorComboBox() {
        // Fetch vendor names from the model and populate the combo box
        String[] vendorNames = vendorModel.getVendorNames();
        JComboBox<String> vendorComboBox = view.getVendorComboBox();
        for (String vendorName : vendorNames) {
            vendorComboBox.addItem(vendorName);
        }
    }

    public void addVendor() {
        String name = view.getVendorNameField().getText();
        String cnic = view.getVendorCnicField().getText();
        String phoneNumber = view.getVendorPhoneField().getText();

        if (InputValidation.validateCNIC(cnic) && InputValidation.validatePhone(phoneNumber)) {
            Vendor vendor = new Vendor(0, name, cnic, phoneNumber);
            if (vendorModel.addVendor(vendor)) {
                MessageDialog.showSuccess("Vendor added successfully!");
                view.getVendorComboBox().addItem(name); // Update vendor combo box
            } else {
                MessageDialog.showFail("Could not add vendor!");
            }
        } else {
            MessageDialog.showFail("Invalid CNIC or phone number!");
        }
    }

    public void addProduct() {
        try {
            String productName = view.getProductNameField().getText();
            String productCategory = view.getProductCategoryField();
            Product product = getProduct(productName, productCategory);

            String selectedVendorName = (String) view.getVendorComboBox().getSelectedItem(); // Get selected vendor name from the combo box

            if (prodModel.addProduct(product, selectedVendorName)) { // Pass the selected vendor name
                MessageDialog.showSuccess("Product added successfully!");
            } else {
                MessageDialog.showFail("Could not add product!");
            }
        } catch (NumberFormatException ex) {
            MessageDialog.showFail("Please enter valid numerical values for prices!");
        }
    }

    private Product getProduct(String productName, String productCategory) {
        double originalPrice = Double.parseDouble(view.getProductOriginalPriceField().getText());
        double salePrice = Double.parseDouble(view.getProductSalePriceField().getText());
        double unitPrice = Double.parseDouble(view.getProductUnitPriceField().getText());
        double cartonPrice = Double.parseDouble(view.getProductCartonPriceField().getText());

        // Get selected vendor ID based on combo box selection
        int vendorId = view.getVendorComboBox().getSelectedIndex() + 1;
        System.out.println(vendorId);

        Product product = new Product(0, vendorId, productName, productCategory,
                originalPrice, salePrice, unitPrice, cartonPrice);
        return product;
    }
}
