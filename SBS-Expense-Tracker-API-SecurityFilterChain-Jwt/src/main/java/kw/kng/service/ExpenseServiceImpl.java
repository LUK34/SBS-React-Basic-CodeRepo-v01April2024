package kw.kng.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kw.kng.entity.Expense;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService
{
	@Autowired
	private ExpenseRepository erepo;
	
	@Autowired
	private UserSetupService uss;

	@Override
	public Page<Expense> getAllExpenses(Pageable page)
	{
		//return erepo.findAll(page);
		return erepo.findByUserSetupId(uss.getLoggedInUser().getId(),  page);
		
	}
	
	@Override
	public Expense getExpenseById(Long id) {
	//	Optional<Expense> e=erepo.findById(id);
		Optional<Expense> e=erepo.findByUserSetupIdAndId(uss.getLoggedInUser().getId(), id);
		if(e.isPresent()) 
		{
			return e.get();
		}
		throw new ResourceNotFoundException("[FileName = ExpenseServiceImpl.class] Expense is not found for the id= " + id);
	}
	
	
	@Override
	public void deleteExpenseById(Long id)
	{
		Expense e=getExpenseById(id);
		erepo.delete(e);
	}
	
	@Override
	public Expense saveExpenseDetails(Expense expense)
	{
		expense.setUserSetup(uss.getLoggedInUser());
		return erepo.save(expense);
	}
	
	@Override
	public Expense updateExpenseDetails(Long id, Expense expense)
	{
		Expense ee= getExpenseById(id);
		ee.setName(expense.getName() != null ? expense.getName() : ee.getName());
		ee.setDescription(expense.getDescription() != null ? expense.getDescription(): ee.getDescription());
		ee.setCategory(expense.getCategory() != null ? expense.getCategory() : ee.getCategory());
		ee.setAmount(expense.getAmount() != null ? expense.getAmount() : ee.getAmount());
		ee.setDate(expense.getDate() != null ? expense.getDate(): ee.getDate());
		return erepo.save(ee);
	}
	
	@Override
	public List<Expense> readByCategory(String category, Pageable page)
	{
		return erepo.findByUserSetupIdAndCategory(uss.getLoggedInUser().getId(),category,page).toList();
	}

	@Override
	public List<Expense> readByName(String name,Pageable page)
	{
		return erepo.findByUserSetupIdAndNameContaining(uss.getLoggedInUser().getId(), name, page).toList();
	}
	
	@Override
	public List<Expense> readByDate(Date startDate,Date endDate, Pageable page)
	{
		if(startDate == null)
		{
			startDate= new Date(0);
		}
		if(endDate == null)
		{
			endDate= new Date(System.currentTimeMillis());
		}
		Page<Expense> pages= erepo.findByUserSetupIdAndDateBetween(uss.getLoggedInUser().getId(),startDate, endDate, page);
		return pages.toList();
	}
	
	
	
	
	
}


/*
 
  In Java, Optional is a container object used to contain not-null objects.
  Optional object is used to represent null with absent value. 
  This class has various utility methods to facilitate code to handle values as ‘available’ or ‘not available’ instead of checking null values. In the context of Spring Boot and more broadly, the Spring framework, Optional can play a significant role in making the code cleaner and more expressive, especially when dealing with operations that may or may not return a value.
  
  
*/