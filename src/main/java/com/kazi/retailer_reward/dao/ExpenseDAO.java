package com.kazi.retailer_reward.dao;

import com.kazi.retailer_reward.entity.Expense;
import com.kazi.retailer_reward.repository.ExpenseRepo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("expenseRepo")
public abstract class ExpenseDAO implements ExpenseRepo {

    @Override
    public List<Expense> getAllExpensesByUserId(Integer userId) {
        return findAll().stream().filter(expense -> expense.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<Expense> getAllExpensesByUserIdAndMonth(Integer userId, String month) {
        return findAll().stream()
                .filter(expense -> expense.getUserId().equals(userId)&&expense.getMonth().equals(month))
                .collect(Collectors.toList());
    }
}
