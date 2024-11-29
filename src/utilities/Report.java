/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.time.LocalDate;

/**
 *
 * @author laiba
 */
public class Report {
    private int reportId;
    private String branchCode;
    private String rangeTag;
    private LocalDate startDate;
    private LocalDate endDate;
    private double sales;
    private int remainingStock;
    private double profit;

    public Report(int reportId, String branchCode, String rangeTag, LocalDate startDate, LocalDate endDate, double sales, int remainingStock, double profit) {
        this.reportId = reportId;
        this.branchCode = branchCode;
        this.rangeTag = rangeTag;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sales = sales;
        this.remainingStock = remainingStock;
        this.profit = profit;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getRangeTag() {
        return rangeTag;
    }

    public void setRangeTag(String rangeTag) {
        this.rangeTag = rangeTag;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public int getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(int remainingStock) {
        this.remainingStock = remainingStock;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
    
    
}
