package Controllers;

import Models.EmployeeModel;
import Models.SuperAdmin;

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
            new SuperAdminDashboardController(empModel);
        }
        else {
             MessageDialog.showFail("Incorrect email/password!");
        }
    }
    
    public static void employeeLogin() {
        String emailInput = logInOptionsView.getUsername();
        String pwdInput = logInOptionsView.getPassword();
        String role = logInOptionsView.getSelectedRole();
        Employee emp = empModel.employeeLogin(emailInput, pwdInput);

        if(emp != null && emp.getEmpType().equals(role))
        {
            switch (role) {
                case "B" -> {
                    MessageDialog.showSuccess("Successfully Logged In!");
                    new BranchMgrDashboardController(emp, empModel);
                    logInOptionsView.dispose();
                }
                case "D" -> {
                    MessageDialog.showSuccess("Successfully Logged In!");
                    new DEODashboardController(emp, empModel);
                    logInOptionsView.dispose();
                }
                case "C" -> {
                    MessageDialog.showSuccess("Successfully Logged In!");
                    new CashierDashboardController(emp, empModel);
                    logInOptionsView.dispose();
                }
            }
        }
        else
        {
            MessageDialog.showFail("No user found. Please enter correct credentials.");
        }
    }
}