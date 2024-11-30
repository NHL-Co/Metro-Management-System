/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BillModel;
import Models.ProductModel;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import utilities.Bill;
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
    private Map<Product, Integer> products_qty;
    // view for making bill by selecting products

    public GenerateBillController(ProductModel prodModel, BillModel billModel) 
    {
        bill = new Bill(0, LocalDate.now(), null, true, 0, 0, 0);
        products_qty = new HashMap<>();
        this.prodModel = prodModel;
        this.billModel = billModel;
        // init view and set action listeners
    }
    
    
    public void addProductToBill()
    {
        // get selected row from view: selected row number + 1 = product id
        // get qty by entering a number
        int id = 1;
        int qty = 1;
        Product product = prodModel.getProduct(id);
        products_qty.put(product, qty);
    }
    
    public void confirmBill()
    {
        // get boolean cash/card from view
        boolean cash = true;
        double totalAmount = calculateTotalAmount();
        double tax = calculateTax(totalAmount);
        double netAmount = totalAmount + tax;
        bill.setTotalAmount(totalAmount);
        bill.setTax(tax);
        bill.setNetAmount(netAmount);
        bill.setProducts_qty(products_qty);
        bill.setCash(cash);
        billModel.addBill(bill);
        
        // call show bill on screen view
        
    }
    
    public double calculateTotalAmount()
    {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : bill.getProducts_qty().entrySet()) 
        {
            int qty = entry.getValue();
            double price = entry.getKey().getPriceByUnit();
            total += (price * qty);
        }
        
        return total;
    }
    
    public double calculateTax(double totalAmount)
    {
        return totalAmount * GlobalValues.getTax();
    }
    
    
}
