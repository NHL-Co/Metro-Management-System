package Controllers;

import Models.BranchModel;
import Models.EmployeeModel;
import Views.SuperAdminView;
import Views.ViewEmployeesView;

public class SuperAdminDashboardController {
    private BranchModel branchModel;
    private EmployeeModel empModel;
    private SuperAdminView spaView;

    public SuperAdminDashboardController(EmployeeModel empModel) {
        this.branchModel = new BranchModel();
        this.empModel = empModel;

        this.spaView = new SuperAdminView();
        addListeners();
    }

    private void addListeners() {
        spaView.getCreateBranchBtn().addActionListener(e -> {
            new CreateBranchController(empModel, branchModel);
            spaView.dispose();
        });
        spaView.getAddBMBtn().addActionListener(e -> {
            new AddEmployeeController(null, empModel, 'B');
            spaView.dispose();
        });
        spaView.getViewBMBtn().addActionListener(e -> {
            new ViewEmployeesView(null, empModel, 'B');
            spaView.dispose();
        });
    }
    public void seeReports()
    {
        //Show reports screen
    }
}
