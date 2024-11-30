/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BranchModel;
import Models.EmployeeModel;
import javax.swing.JOptionPane;
import utilities.Branch;
import utilities.Employee;
import utilities.InputValidation;
import utilities.MessageDialog;
import utilities.InputValidation;

/**
 *
 * @author laiba
 */
public class SuperAdminDashboardController {
    private String adminUsername;
    private BranchModel branchModel;
    private EmployeeModel empModel;
    // SuperAdminDashboard spaView;
    // CreateBranchForm createBranchView;
    // CreateBranchMgrForm createBranchMgrView;
    // SuperAdminReports reportsView;

    public SuperAdminDashboardController(String adminUsername, EmployeeModel empModel) {
        this.adminUsername = adminUsername;
        this.branchModel = new BranchModel();
        this.empModel = empModel;
        // init views and
        // set all action listeners
    }
    
    public void createBranch()
    {
        String branchCode = "", city = "", address = "", phone = "";
        int nEmp = 0;
        if(InputValidation.validateBranchCode(branchCode) && InputValidation.validatePhone(phone))
        {
            Branch branch = new Branch(branchCode, city, address, phone, nEmp);
            if(branchModel.addBranch(branch))
            {
                MessageDialog.showSuccess("Branch created successfully!");
            }
            else
            {
                MessageDialog.showFail("Could not create branch!");
            }
        }
        
    }
    
    public void createBranchMgr()
    {
        String name = "", email = "", password = "123456", branchcode = "", emptype = "B";
        double salary = 0;
        if(InputValidation.validateEmail(email) && InputValidation.validateEmail(email))
        {
            Employee branchMgr = new Employee(0, name, email, password, branchcode, salary, emptype);
            if(empModel.addEmployee(branchMgr))
            {
                MessageDialog.showSuccess("Branch manager created successfully!");
            }
            else
            {
                MessageDialog.showFail("Could not create branch manager!");
            }
        }
        
    }
    
    public void seeReports()
    {
        //Show reports screen
    }
   
}
