package com.kazi.retailer_reward.controller;

import com.kazi.retailer_reward.entity.Expense;
import com.kazi.retailer_reward.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping("/expense/addExpense")
    public Expense saveExpense(@RequestBody @Valid @NotNull
                                       Expense expense){
        return service.saveExpense(expense);
    }

    @PostMapping("/expense/addExpenses")
    public List<Expense> saveExpenses(@RequestBody @Valid @NotNull
                                            List<Expense> expenses){
        return service.saveExpenses(expenses);
    }

    @RequestMapping(
            value = "/reward/total",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllRewardPoints(@RequestParam Integer userId){
        return ResponseEntity.ok(service.getTotalRewardForUser(userId));

    }

    @RequestMapping(
            value = "/reward/month",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllRewardPointsByMonth(@RequestParam Integer userId,
                                                       @RequestParam String month){
        return ResponseEntity.ok(service.getTotalRewardForUserForMont(userId, month));

    }
}
