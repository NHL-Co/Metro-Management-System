package Controllers;

import Models.EmployeeModel;
import Views.AddBranchManagerView;
import utilities.Employee;
import utilities.InputValidation;
import utilities.MessageDialog;

public class AddBranchManagerController {
    private AddBranchManagerView addBranchManagerView;
    private EmployeeModel empModel;

    public AddBranchManagerController(EmployeeModel empModel){
        this.empModel = empModel;
        this.addBranchManagerView = new AddBranchManagerView(empModel);

        addListeners();
    }
    private void addListeners() {
        addBranchManagerView.getSaveButton().addActionListener(e -> {
            createBranchMgr(); // Method that adds a new branch manager
            addBranchManagerView.getNameField().setText("");  // Clear the input fields
            addBranchManagerView.getEmail().setText("");
            addBranchManagerView.getSalary().setText("");
        });
    }
    public void createBranchMgr()
    {
        String name = addBranchManagerView.getNameField().getText(), email = addBranchManagerView.getEmail().getText(), password = "123456", branchcode = addBranchManagerView.getSelectedBranchCode(), emptype = "B";
        double salary = Double.parseDouble(addBranchManagerView.getSalary().getText());
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
}
