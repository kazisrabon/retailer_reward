package com.kazi.retailer_reward.repository;

import com.kazi.retailer_reward.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
    List<Expense> getAllExpensesByUserId(Integer userId);
    List<Expense> getAllExpensesByUserIdAndMonth(Integer userId, String month);
}
