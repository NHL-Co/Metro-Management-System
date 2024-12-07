/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EmployeeModel;
import Models.SuperAdmin;

import Views.DEODashboardView;
import Views.LogInOptionsView;
import utilities.Employee;
import utilities.MessageDialog;

public class LogInOptionsController {
    
    private static LogInOptionsView logInOptionsView = new LogInOptionsView();
    private static SuperAdmin spaModel;
    private static EmployeeModel empModel;
    
    public LogInOptionsController() {
        spaModel = new SuperAdmin();
        spaModel.seedAdmin("admin-user", "245362");
        empModel = new EmployeeModel();

        //Set action listener
        logInOptionsView.getLoginButton().addActionListener(e -> {
            if(logInOptionsView.getSelectedRole().equals("Super Admin")){
                superAdminLogin();
            }
            else if (logInOptionsView.getSelectedRole().isEmpty()) {
                MessageDialog.showFail("Please Select your Role!");
            }
            else{
                employeeLogin();
            }
        });

    }
    
    public static void superAdminLogin() {
        String usernameInput = logInOptionsView.getUsername();
        String pwdInput = logInOptionsView.getPassword();

        // authenticate super admin
        if(spaModel.adminLogin(usernameInput, pwdInput)) {
            MessageDialog.showSuccess("Successfully Logged In!");
            logInOptionsView.dispose();
            new SuperAdminDashboardController(usernameInput, empModel);
        }
        else {
             MessageDialog.showFail("Incorrect email/password!");
        }
    }
    
    public static void employeeLogin() {
        String emailInput = logInOptionsView.getUsername();
        String pwdInput = logInOptionsView.getPassword();
        String roleType = logInOptionsView.getSelectedRole();
        Employee emp;

        if((emp = empModel.employeeLogin(emailInput, pwdInput)) != null)
        {
            MessageDialog.showSuccess("Successfully Logged In!");
            //logInOptionsView.dispose();

            if(roleType.equals("B")) // Branch Manager
            {
                //new BranchMgrDashboardController(emp, empModel);
            }
            else if(roleType.equals("D")) // Data Entry Operator
            {
                new DEODashboardView();
            }
            else if(roleType.equals("C")) // Cashier
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
