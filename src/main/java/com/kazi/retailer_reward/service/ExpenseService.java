package com.kazi.retailer_reward.service;

import com.kazi.retailer_reward.entity.Expense;
import com.kazi.retailer_reward.entity.RewardReturn;
import com.kazi.retailer_reward.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo repo;

    public Expense saveExpense(Expense expense){
        return repo.save(expense);
    }

    public List<Expense> saveExpenses(List<Expense> expenses){
        return repo.saveAll(expenses);
    }

    public RewardReturn getTotalRewardForUser(Integer userId){
        return new RewardReturn(
                "This is the total reward point",
                200,
                pointcalculation(repo.getAllExpensesByUserId(userId))+0,
                "All");
    }

    public RewardReturn getTotalRewardForUserForMont(Integer userId, String month){
        return new RewardReturn(
                "This is the monthly total reward point",
                200,
                pointcalculation(repo.getAllExpensesByUserIdAndMonth(userId, month))+0,
                month+"");
    }

    private Integer pointcalculation(List<Expense> expenseList) {
        int totalPoint = 0, point;
        for (Expense expense: expenseList) {
            point = 0;
            if (expense.getAmount() > 50){
                if (expense.getAmount() > 100){
                    point += 50;
                    point += ((expense.getAmount() - 100)*2);
                }
                else {
                    point += (expense.getAmount() - 50);
                }
            }
            totalPoint += point;
        }
        return totalPoint;
    }
}
