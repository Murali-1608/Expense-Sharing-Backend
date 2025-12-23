package com.expense.expensesharing.model;

import com.expense.expensesharing.util.SplitType;
import java.util.List;

public class Expense {

    private String expenseId;
    private String groupId;
    private double totalAmount;
    private String paidBy;
    private SplitType splitType;
    private List<Split> splits;

    public Expense() {}

    public Expense(String expenseId, String groupId, double totalAmount,
                   String paidBy, SplitType splitType, List<Split> splits) {
        this.expenseId = expenseId;
        this.groupId = groupId;
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.splitType = splitType;
        this.splits = splits;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }
}
