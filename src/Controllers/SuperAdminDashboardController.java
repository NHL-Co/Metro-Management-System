package Controllers;

import Models.BranchModel;
import Models.EmployeeModel;
import Views.SuperAdminView;
import Views.ViewBranchManagersView;

public class SuperAdminDashboardController {
    private BranchModel branchModel;
    private EmployeeModel empModel;
    private SuperAdminView spaView;
    private ViewBranchManagersView viewBranchManagersView;

    public SuperAdminDashboardController(EmployeeModel empModel) {
        this.branchModel = new BranchModel();
        this.empModel = empModel;

        this.viewBranchManagersView = new ViewBranchManagersView(empModel);
        viewBranchManagersView.setVisible(false);

        this.spaView = new SuperAdminView();
        addListeners();
    }

    private void addListeners() {
        spaView.getCreateBranchBtn().addActionListener(e -> {
            new CreateBranchController(empModel, branchModel);
            spaView.dispose();
        });
        spaView.getAddBMBtn().addActionListener(e -> {
            new AddBranchManagerController(empModel);
            spaView.dispose();
        });
        spaView.getViewBMBtn().addActionListener(e -> {
            viewBranchManagersView.setVisible(true);
            spaView.dispose();
        });
    }
    public void seeReports()
    {
        //Show reports screen
    }
}
