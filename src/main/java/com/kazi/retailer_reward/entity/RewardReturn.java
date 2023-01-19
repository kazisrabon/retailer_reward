package com.kazi.retailer_reward.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardReturn {
    private String status;
    private Integer code;
    private Integer point;
    private String month;

}
