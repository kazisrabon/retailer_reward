package com.kazi.retailer_reward.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "Expense_Tbl")
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_id_seq")
    @SequenceGenerator(name = "expense_id_seq", allocationSize = 1)
    @Column(name = "expenseId", nullable = false)
    private Integer expenseId;
    private Integer userId;
    private Integer amount;
    private String month;
}
