package Controllers;

import Models.BranchModel;
import Models.EmployeeModel;
import Views.CreateBranchView;
import utilities.Branch;
import utilities.InputValidation;
import utilities.MessageDialog;

public class CreateBranchController {
    private CreateBranchView createBranchView;
    private EmployeeModel empModel;
    private BranchModel branchModel;


    public CreateBranchController(EmployeeModel empModel, BranchModel bModel){
        this.empModel = empModel;
        this.branchModel = bModel;
        this.createBranchView = new CreateBranchView(empModel);

        addListeners();

    }
    private void addListeners() {
        createBranchView.getSaveButton().addActionListener(e -> createBranch());
    }
    public void createBranch() {
        String branchCode = createBranchView.getBranchCode();
        String city = createBranchView.getSelectedCity();
        String address = createBranchView.getAddress();
        String phone = createBranchView.getPhone();

        if (InputValidation.validateBranchCode(branchCode) && InputValidation.validatePhone(phone)) {
            Branch branch = new Branch(branchCode, city, address, phone, 0);
            if (branchModel.addBranch(branch)) {
                MessageDialog.showSuccess("Branch created successfully!");
                new SuperAdminDashboardController(empModel);
                createBranchView.dispose();
            } else {
                MessageDialog.showFail("Could not create branch!");
            }
        } else {
            MessageDialog.showFail("Invalid input. Please check branch code or phone format.");
        }
    }

}
