/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EmployeeModel;
import utilities.Employee;
import utilities.InputValidation;
import utilities.MessageDialog;

/**
 *
 * @author laiba
 */
public class BranchMgrDashboardController {
    private Employee branchMgr;
    private EmployeeModel empModel;
    // branch mgr view

    public BranchMgrDashboardController(Employee branchMgr, EmployeeModel empModel) {
        this.branchMgr = branchMgr;
        this.empModel = empModel;
        if(branchMgr.getPassword().equals("123456"))
        {
            // show changePwd form
        }
    }
    
    public void changePwd()
    {
        String oldPwd = "", newPwd = "";
        if(oldPwd.equals(branchMgr.getPassword()))
        {
            branchMgr.setPassword(newPwd);
            if(empModel.updateEmployee(branchMgr))
            {
            MessageDialog.showSuccess("Password changed successfully!");
            }
            else
            {
                MessageDialog.showFail("Could not change password!");
            }
        }
    }
    
    public void addCashier()
    {
        String name = "", email = "", password = "123456", branchcode = "", emptype = "C";
        double salary = 0;
        
        if(InputValidation.validateEmail(email) && InputValidation.validateSalary(salary))
        {
            Employee cashier = new Employee(0, name, email, password, branchcode, salary, emptype);
            if(empModel.addEmployee(cashier))
            {
                MessageDialog.showSuccess("Cashier created successfully!");
            }
            else
            {
                MessageDialog.showFail("Could not create Cashier!");
            }
        }
        
    }
    
    public void addDEO()
    {
        String name = "", email = "", password = "123456", branchcode = "", emptype = "D";
        double salary = 0;
        
        if(InputValidation.validateEmail(email) && InputValidation.validateSalary(salary))
        {
            Employee deo = new Employee(0, name, email, password, branchcode, salary, emptype);
            if(empModel.addEmployee(deo))
            {
                MessageDialog.showSuccess("Data Entry Operator created successfully!");
            }
            else
            {
                MessageDialog.showFail("Could not create Data Entry Operator!");
            }
        }
    }
    
    
    
    
    
}
