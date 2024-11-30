/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EmployeeModel;
import Models.SuperAdmin;
import javax.swing.JOptionPane;
import utilities.Employee;
import utilities.MessageDialog;

/**
 *
 * @author laiba
 */
public class LogInOptionsController {

    
    //LogInOptionsView logInOptionsView
    private SuperAdmin spaModel;
    private EmployeeModel empModel;
    
    public LogInOptionsController() {
        spaModel = new SuperAdmin();
        spaModel.seedAdmin("admin-user", "245362");
        empModel = new EmployeeModel();
        // logInOptionsView.setAdminLoginBtnActionListener(e -> superAdminLogin());
        // logInOptionsView.setEmployeeLoginBtnActionListener(e -> employeeLogin());
        
    }
    
    public void superAdminLogin()
    {
        String usernameInput = "", pwdInput = ""; // get from view
        if(spaModel.adminLogin(usernameInput, pwdInput))
        {
            MessageDialog.showSuccess("SuccessfullyLoggedIn!");
            //logInOptionsView.dispose();
            //new SuperAdminDashboardController(usernameInput, empModel);
        }
        else
        {
             MessageDialog.showFail("Incorrect email/password!");
        }
    }
    
    public void employeeLogin()
    {
        String emailInput = "laiba@gmail.com", pwdInput = "123456"; // get from view
        Employee emp;
        if((emp = empModel.employeeLogin(emailInput, pwdInput)) != null)
        {
            MessageDialog.showSuccess("SuccessfullyLoggedIn!");
            //logInOptionsView.dispose();
            String empType = emp.getEmpType();
            if(empType.equals("B")) // Branch Manager
            {
                //new BranchMgrDashboardController(emp, empModel);
            }
            else if(empType.equals("D")) // Data Entry Operator
            {
                //new DEODashboardController(emp, empModel);
            }
            else if(empType.equals("C")) // Cashier
            {
                //new CashierDashboardController(emp, empModel);
            }
        }
        else
        {
            MessageDialog.showFail("Incorrect email/password!");
        }
    }
    
    
}
