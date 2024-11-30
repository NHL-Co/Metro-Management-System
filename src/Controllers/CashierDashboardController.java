/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BillModel;
import Models.EmployeeModel;
import Models.ProductModel;
import utilities.Employee;
import utilities.MessageDialog;
import utilities.Product;

/**
 *
 * @author laiba
 */
public class CashierDashboardController {
    private Employee cashier;
    private EmployeeModel empModel;
    // cashier view
    private ProductModel prodModel;
    private BillModel billModel;

    public CashierDashboardController(Employee cashier, EmployeeModel empModel) {
        this.cashier = cashier;
        this.empModel = empModel;
        this.prodModel = new ProductModel();
        this.billModel = new BillModel();
        if(cashier.getPassword().equals("123456"))
        {
            // show changePwd form
        }
    }
    
    public void changePwd()
    {
        String oldPwd = "", newPwd = "";
        if(oldPwd.equals(cashier.getPassword()))
        {
            cashier.setPassword(newPwd);
            if(empModel.updateEmployee(cashier))
            {
                MessageDialog.showSuccess("Password changed successfully!");
            }
            else
            {
                MessageDialog.showFail("Could not change password!");
            }
        }
    }
    
    
    public void generateBill()
    {
        // call createBill model
    }
    
    
    
    
}
