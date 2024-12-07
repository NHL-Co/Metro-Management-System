/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BillModel;
import Models.ProductBranchModel;
import Models.ProductModel;
import Views.GenerateBillView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import utilities.Bill;
import utilities.Employee;
import utilities.GlobalValues;
import utilities.Product;

/**
 *
 * @author laiba
 */
public class GenerateBillController {
    private Bill bill; 
    private ProductModel prodModel;
    private BillModel billModel;
    private ProductBranchModel pbmodel;
    private Map<Product, Integer> products_qty;
    private GenerateBillView view;
    private ArrayList<Product> products;
    private Employee emp;

    public GenerateBillController(ProductModel prodModel, BillModel billModel, Employee emp) 
    {
        bill = new Bill(emp.getBranchCode());
        products_qty = new HashMap<>();
        this.billModel = billModel;
        this.prodModel = prodModel;
        this.emp = emp;
        products = prodModel.getProducts();
        filterProducts();
        view = new GenerateBillView(products);
        addProductToBill();
        view.setConfirmBtnListener(e -> confirmBill());
    }
    
    public void filterProducts()
    {
        pbmodel = new ProductBranchModel();
        for(Product p : products)
        {
            if((pbmodel.getProductBranchQty(p.getProductId(), emp.getBranchCode())) == 0)
            {
                products.remove(p);
            }
        }
    }
    
    public void addProductToBill()
    {
        HashMap<Product, JPanel> productCards = view.getProductCards();

        for (Product product : productCards.keySet()) {
        JPanel btnPanel = productCards.get(product);

        // Add Unit buttons listeners
        JButton addUnitButton = (JButton) btnPanel.getComponent(0);
        addUnitButton.addActionListener(e -> {
            int currentQty = products_qty.getOrDefault(product, 0);
            products_qty.put(product, currentQty + 1);
            System.out.println("Added unit of: " + product.getName() + ". New quantity: " + products_qty.get(product));
            view.addProductToBill(product, currentQty + 1);
        });

        // Add Carton buttons listeners
        JButton addCartonButton = (JButton) btnPanel.getComponent(1);
        addCartonButton.addActionListener(e -> {
            int currentQty = products_qty.getOrDefault(product, 0);
            products_qty.put(product, currentQty);
            System.out.println("Added carton of: " + product.getName() + ". New quantity: " + products_qty.get(product));
            view.addProductCartonToBill(product, currentQty);
        });
        }
    }
    
    public void confirmBill()
    {
        boolean cash = view.getCashCheckBox().isSelected();
        double totalAmount = calculateTotalAmount();
        double tax = calculateTax(totalAmount);
        double netAmount = totalAmount + tax;
        bill.setDate(LocalDate.now());
        bill.setTotalAmount(totalAmount);
        bill.setTax(tax);
        bill.setNetAmount(netAmount);
        bill.setProducts_qty(products_qty);
        bill.setCash(cash);
        //billModel.addBill(bill);
        
        // call show bill on screen view
        //pbmodel.updateProductQuantities(bill);
        
        // after confirming jdialog showing the bill
        //reinitializeBill()
    }
    
    public void reinitializeBill()
    {
        bill = new Bill(emp.getBranchCode());
        view.clearBill();
    }
    
    public double calculateTotalAmount()
    {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : bill.getProducts_qty().entrySet()) 
        {
            int qty = entry.getValue();
            double price = entry.getKey().getSalePrice();
            total += (price * qty);
        }
        
        return total;
    }
    
    public double calculateTax(double totalAmount)
    {
        return totalAmount * GlobalValues.getTax();
    }
    
    
}
