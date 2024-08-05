package kw.kng.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import kw.kng.dto.ExpenseDto;
import kw.kng.entity.Expense;

public interface ExpenseService
{
	
	
	public List<ExpenseDto> getAllExpenses(Pageable page);
	public ExpenseDto getExpenseById(String expenseId);
	public void deleteExpenseById(String expenseId);
	public ExpenseDto saveExpenseDetails(ExpenseDto expenseDto);
	public ExpenseDto updateExpenseDetails(String expenseId, ExpenseDto expense);
	public List<ExpenseDto> readByCategory(String category, Pageable page);
	public List<ExpenseDto> readByName(String name,Pageable page);
	public List<ExpenseDto> readByDate(Date startDate,Date endDate, Pageable page);

	
}



