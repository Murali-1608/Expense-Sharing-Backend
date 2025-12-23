package com.expense.expensesharing.service;

import com.expense.expensesharing.model.Expense;
import com.expense.expensesharing.model.Split;
import com.expense.expensesharing.util.SplitType;

import java.util.List;
import java.util.Map;

public class ExpenseService {

    private final BalanceService balanceService = new BalanceService();
    private void validateExpense(Expense expense) {

        if (expense.getTotalAmount() <= 0) {
            throw new IllegalArgumentException("Total amount must be greater than zero");
        }

        if (expense.getSplitType() == SplitType.EXACT) {
            double sum = expense.getSplits().stream()
                    .mapToDouble(Split::getAmount)
                    .sum();

            if (sum != expense.getTotalAmount()) {
                throw new IllegalArgumentException(
                        "Exact split amounts must sum to total amount");
            }
        }

        if (expense.getSplitType() == SplitType.PERCENTAGE) {
            double sum = expense.getSplits().stream()
                    .mapToDouble(Split::getAmount)
                    .sum();

            if (sum != 100) {
                throw new IllegalArgumentException(
                        "Percentage splits must sum to 100");
            }
        }
    }

    public void addExpense(Expense expense) {

        validateExpense(expense);

        String paidBy = expense.getPaidBy();
        double totalAmount = expense.getTotalAmount();
        List<Split> splits = expense.getSplits();

        if (expense.getSplitType() == SplitType.EQUAL) {
            double equalShare = totalAmount / splits.size();
            for (Split split : splits) {
                if (!split.getUserId().equals(paidBy)) {
                    balanceService.addBalance(
                            split.getUserId(),
                            paidBy,
                            equalShare
                    );
                }
            }
        }

        else if (expense.getSplitType() == SplitType.EXACT) {
            for (Split split : splits) {
                if (!split.getUserId().equals(paidBy)) {
                    balanceService.addBalance(
                            split.getUserId(),
                            paidBy,
                            split.getAmount()
                    );
                }
            }
        }

        else if (expense.getSplitType() == SplitType.PERCENTAGE) {
            for (Split split : splits) {
                double share = (split.getAmount() / 100.0) * totalAmount;
                if (!split.getUserId().equals(paidBy)) {
                    balanceService.addBalance(
                            split.getUserId(),
                            paidBy,
                            share
                    );
                }
            }
        }
    }

    public Map<String, Map<String, Double>> getBalances() {
        return balanceService.getBalances();
    }

    public Map<String, Double> getBalanceForUser(String userId) {
        return balanceService.getBalanceForUser(userId);
    }

    public Map<String, Map<String, Double>> getSimplifiedBalances() {
        return balanceService.getSimplifiedBalances();
    }
}
