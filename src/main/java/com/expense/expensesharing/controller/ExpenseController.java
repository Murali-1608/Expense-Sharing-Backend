package com.expense.expensesharing.controller;

import com.expense.expensesharing.model.Expense;
import com.expense.expensesharing.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService expenseService = new ExpenseService();

    @PostMapping("/expense")
    public String addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
        return "Expense added successfully";
    }

    @GetMapping("/balances")
    public Map<String, Map<String, Double>> getBalances() {
        return expenseService.getBalances();
    }

    @GetMapping("/balances/{userId}")
    public Map<String, Double> getUserBalance(@PathVariable String userId) {
        return expenseService.getBalanceForUser(userId);
    }

    // SIMPLIFIED BALANCES
    @GetMapping("/balances/simplified")
    public Map<String, Map<String, Double>> getSimplifiedBalances() {
        return expenseService.getSimplifiedBalances();
    }
}
