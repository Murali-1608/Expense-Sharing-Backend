package com.expense.expensesharing.service;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {

    private final Map<String, Map<String, Double>> balances = new HashMap<>();
    public void addBalance(String debtor, String creditor, double amount) {
        if (debtor.equals(creditor)) return;

        balances.putIfAbsent(debtor, new HashMap<>());

        Map<String, Double> creditorMap = balances.get(debtor);
        creditorMap.put(
                creditor,
                creditorMap.getOrDefault(creditor, 0.0) + amount
        );
    }

    public Map<String, Map<String, Double>> getBalances() {
        return balances;
    }

    public Map<String, Double> getBalanceForUser(String userId) {
        return balances.getOrDefault(userId, new HashMap<>());
    }

    public Map<String, Map<String, Double>> getSimplifiedBalances() {

        Map<String, Double> netBalance = new HashMap<>();

        for (String debtor : balances.keySet()) {
            for (Map.Entry<String, Double> entry : balances.get(debtor).entrySet()) {
                String creditor = entry.getKey();
                double amount = entry.getValue();

                netBalance.put(debtor,
                        netBalance.getOrDefault(debtor, 0.0) - amount);

                netBalance.put(creditor,
                        netBalance.getOrDefault(creditor, 0.0) + amount);
            }
        }

        Map<String, Map<String, Double>> simplified = new HashMap<>();

        for (String from : netBalance.keySet()) {
            if (netBalance.get(from) < 0) {
                for (String to : netBalance.keySet()) {
                    if (netBalance.get(to) > 0) {

                        double settle =
                                Math.min(-netBalance.get(from), netBalance.get(to));

                        if (settle > 0) {
                            simplified.putIfAbsent(from, new HashMap<>());
                            simplified.get(from).put(to, settle);

                            netBalance.put(from, netBalance.get(from) + settle);
                            netBalance.put(to, netBalance.get(to) - settle);
                        }
                    }
                }
            }
        }
        return simplified;
    }
}
