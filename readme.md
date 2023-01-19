# Read Me First

This project is the for the online assessment of an interview. The task is:

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
dollar spent between $50 and $100 in each transaction.
(e.g., a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three-month period, calculate the reward points earned for
each customer per month and total.

* Solve using Spring Boot
* Create a RESTful endpoint
* Make up a data set to best demonstrate your solution
* Check solution into GitHub

# Getting Started

To run this application goto the main directory of this project and run

`mvn spring-boot:run`

This will start the application into `localhost:9323` port.
This project is also configured for MySQL Database. And I use `Expense_Tbl` to store and retrieve information

## Functionalities
### APIs 
Base path is: `localhost:9323/api/v1`
1. Save expense/s: `localhost:9323/api/v1/expense/addExpense` and `localhost:9323/api/v1/expense/addExpenses` 
2. Get all reward points for a user: `localhost:9323/api/v1/reward/total?userId=xyz`
3. Get all reward points for a user for a specific month: `localhost:9323/api/v1/reward/month?userId=xyz&month=pqr`

### Tests
I have written three test cases:
1. to check if save is working properly
2. to check if we are getting reward points for each user combined or separate month
3. to check if the calculated reward point is correct
