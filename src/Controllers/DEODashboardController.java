/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BillModel;
import Models.EmployeeModel;
import Models.ProductModel;
import Models.VendorModel;
import utilities.Employee;
import utilities.InputValidation;
import utilities.MessageDialog;
import utilities.Product;
import utilities.Vendor;

/**
 *
 * @author laiba
 */
public class DEODashboardController {
    private VendorModel vendorModel;
    private ProductModel prodModel;
    private EmployeeModel empModel;
    private Employee deo;
    // deo dashboard view, add vendor view, add product view
    
    public DEODashboardController(Employee deo, EmployeeModel empModel) {
        this.deo = deo;
        this.empModel = empModel;
        this.prodModel = new ProductModel();
        this.vendorModel = new VendorModel();
        if(deo.getPassword().equals("123456"))
        {
            // show changePwd form
        }
    }
    
    public void changePwd()
    {
        String oldPwd = "", newPwd = "";
        if(oldPwd.equals(deo.getPassword()))
        {
            deo.setPassword(newPwd);
            if(empModel.updateEmployee(deo))
            {
                MessageDialog.showSuccess("Password changed successfully!");
            }
            else
            {
                MessageDialog.showFail("Could not change password!");
            }
        }
    }
    
    public void addVendor()
    {
        String name = "", cnic = "", phoneNumber = "";
        
        if(InputValidation.validateCNIC(cnic) && InputValidation.validatePhone(phoneNumber))
        {
            Vendor vendor = new Vendor(0, name, cnic, phoneNumber);
            if(vendorModel.addVendor(vendor))
            {
                MessageDialog.showSuccess("Vendor added successfully!");
            }
            else
            {
                MessageDialog.showFail("Could not add vendor!");
            }
        }
    }
    
    public void addProduct()
    {
        int vendorId = 0;
        String name = "", category = "";
        double originalPrice = 0, salePrice = 0, priceByUnit = 0, priceByCart = 0;
        Product product = new Product(0, vendorId, name, category, originalPrice, salePrice, priceByUnit, priceByCart);
        if(prodModel.addProduct(product))
        {
            MessageDialog.showSuccess("Product added successfully!");
        }
        else
        {
            MessageDialog.showFail("Could not add product!");
        }
    }
}
