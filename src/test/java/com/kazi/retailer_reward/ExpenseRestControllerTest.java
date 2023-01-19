package com.kazi.retailer_reward;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazi.retailer_reward.controller.ExpenseController;
import com.kazi.retailer_reward.dao.ExpenseDAO;
import com.kazi.retailer_reward.entity.Expense;
import com.kazi.retailer_reward.repository.ExpenseRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (ExpenseController.class)
public class ExpenseRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    ExpenseRepo expenseRepo;

    @MockBean
    ExpenseDAO expenseDAO;

    @Mock
    Logger logger;

    @Mock
    List<Expense> mockList;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testInsertionInExpense() throws Exception{
        Expense expense = new Expense(1, 1, 120, "January");

        Mockito.when(logger.isInfoEnabled()).thenReturn(false);
        Mockito.when(expenseRepo.save(expense)).thenReturn(expense);

        String json = mapper.writeValueAsString(expense);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult requestResult = mockMvc.perform(post("/expense/addExpense").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

        String result = requestResult.getResponse().getContentAsString();

        Assert.notNull(result, "result is not null");
    }

    @Test
    void testGetAllPoints() throws Exception{
        Expense expense1 = new Expense(1, 1, 120, "January");
        Expense expense2 = new Expense(1, 2, 120, "January");
        mockList = mock(List.class);
        mockList.add(expense1);
        mockList.add(expense2);
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(expense1);

        Mockito.when(expenseRepo.getAllExpensesByUserId(1)).thenReturn(expenseList);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/reward/total?userId={id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.point").value(90));
    }

    @Test
    void testGetAllPointsByMonth() throws Exception{
        Expense expense1 = new Expense(1, 1, 120, "January");
        Expense expense2 = new Expense(2, 1, 120, "February");
        Expense expense3 = new Expense(3, 2, 120, "February");

        mockList = mock(List.class);
        mockList.add(expense1);
        mockList.add(expense2);
        mockList.add(expense3);

        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(expense1);

        Mockito.when(expenseRepo.getAllExpensesByUserIdAndMonth(1, "January")).thenReturn(expenseList);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/reward/total?userId={id}&month={month}", 1, "January")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.point").value(90));
    }
}
