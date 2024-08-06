package kw.kng.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kw.kng.dto.CategoryDto;
import kw.kng.dto.ExpenseDto;
import kw.kng.entity.CategoryEntity;
import kw.kng.entity.Expense;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repository.CategoryRepository;
import kw.kng.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService
{
	@Autowired
	private ExpenseRepository erepo;
	
	@Autowired
	private UserSetupService uss;
	
	@Autowired
	private CategoryRepository crepo;

	@Override
	public List<ExpenseDto> getAllExpenses(Pageable page)
	{
		//return erepo.findAll(page);
		List<Expense> listOfExpenses = erepo.findByUserSetupId(uss.getLoggedInUser().getId(),  page).toList();
		return listOfExpenses.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
	}
	
	@Override
	public ExpenseDto getExpenseById(String expenseId) 
	{
		Expense existingExpense = getExpenseEntity(expenseId);
		return mapToDto(existingExpense);
		//return e.get();
	}
	
	//Internal method created via refactoring
	public Expense getExpenseEntity(String expenseId)
	{
//		Optional<Expense> e=erepo.findById(id);
			Optional<Expense> e=erepo.findByUserSetupIdAndExpenseId(uss.getLoggedInUser().getId(), expenseId);
			if(!e.isPresent()) 
			{
				throw new ResourceNotFoundException("[FileName = ExpenseServiceImpl.class] Expense is not found for the id= " + expenseId);
			}
			Expense existingExpense = e.get();
			return existingExpense;
	}
	
	
	@Override
	public void deleteExpenseById(String expenseId)
	{
		Expense e=getExpenseEntity(expenseId);
		erepo.delete(e);
	}
	
	@Override
	public ExpenseDto saveExpenseDetails(ExpenseDto expense)
	{
		// check the existance of category
		Optional<CategoryEntity> optionalCategory = crepo.findByUserIdAndCategoryId(uss.getLoggedInUser().getId(), expense.getCategoryId());
		if(!optionalCategory.isPresent())
		{
			throw new ResourceNotFoundException("Category not found for the id "+ expense.getCategoryId());
		}
		expense.setExpenseId(UUID.randomUUID().toString());
		
		// map to entity object
		Expense newExpense = mapToEntity(expense);
		
		//save to the database
		newExpense.setCategory(optionalCategory.get());
		newExpense.setUserSetup(uss.getLoggedInUser());
		newExpense=erepo.save(newExpense);
		
		// map to response object
		return mapToDto(newExpense);
		
		//	expense.setUserSetup(uss.getLoggedInUser());
		// return erepo.save(expense);
	}
	
	//MAP -> Expense -> ExpenseDto
	private ExpenseDto mapToDto(Expense newExpense)
	{
		return ExpenseDto.builder()
							.expenseId(newExpense.getExpenseId())
							.name(newExpense.getName())
							.description(newExpense.getDescription())
							.amount(newExpense.getAmount())
							.date(newExpense.getDate())
							.createdAt(newExpense.getCreatedAt())
							.updatedAt(newExpense.getUpdatedAt())
							.categoryDto(mapToCategoryDto(newExpense.getCategory()))
							.build();
	}
	
	// MAP -> CategoryEntity  -> CategoryDto
	private CategoryDto mapToCategoryDto(CategoryEntity category)
	{
		return CategoryDto.builder()
				.name(category.getName())
				.categoryId(category.getCategoryId())
				.createdAt(category.getCreatedAt())
				.updatedAt(category.getUpdatedAt())
				.categoryIcon(category.getCategoryIcon())
				.description(category.getDescription())
				.build();
	}

	//MAP -> ExpenseDto -> Expense
	private Expense mapToEntity(ExpenseDto expenseDto)
	{
		return Expense.builder()
						.expenseId(expenseDto.getExpenseId())
						.name(expenseDto.getName())
						.description(expenseDto.getDescription())
						.date(expenseDto.getDate())
						.amount(expenseDto.getAmount())
						.build();
	}
	
	@Override
	public ExpenseDto updateExpenseDetails(String expenseId, ExpenseDto expenseDto)
	{
		Expense ee= getExpenseEntity(expenseId);
		if (expenseDto.getCategoryId() != null) 
		{
			Optional<CategoryEntity> optionalCategory = crepo.findByUserIdAndCategoryId(uss.getLoggedInUser().getId(), expenseDto.getCategoryId());
			if (!optionalCategory.isPresent()) 
			{
				throw new ResourceNotFoundException("Category not found for the id "+expenseDto.getCategoryId());
			}
			ee.setCategory(optionalCategory.get());
		}
		ee.setName(expenseDto.getName() != null ? expenseDto.getName() : ee.getName());
		ee.setDescription(expenseDto.getDescription() != null ? expenseDto.getDescription(): ee.getDescription());
		//ee.setCategory(expense.getCategoryDto() != null ? expense.getCategoryDto() : ee.getCategory());
		ee.setAmount(expenseDto.getAmount() != null ? expenseDto.getAmount() : ee.getAmount());
		ee.setDate(expenseDto.getDate() != null ? expenseDto.getDate(): ee.getDate());
		ee=erepo.save(ee);
		return mapToDto(ee); // MAP -> ExpenseDto -> Entity
	}
	
	@Override
	public List<ExpenseDto> readByCategory(String category, Pageable page)
	{
		Optional<CategoryEntity> optionalCategory = crepo.findByNameAndUserId(category, uss.getLoggedInUser().getId());
		if(!optionalCategory.isPresent())
		{
			throw new ResourceNotFoundException("Category not found for the name: "+category);
		}
		
		List<Expense> listy = erepo.findByUserSetupIdAndCategoryId(uss.getLoggedInUser().getId(),optionalCategory.get().getId(),page).toList();
		return listy.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
	}

	@Override
	public List<ExpenseDto> readByName(String name,Pageable page)
	{
		List<Expense> listy = erepo.findByUserSetupIdAndNameContaining(uss.getLoggedInUser().getId(), name, page).toList();
		return listy.stream().map(e ->mapToDto(e)).collect(Collectors.toList());
	}
	
	@Override
	public List<ExpenseDto> readByDate(Date startDate,Date endDate, Pageable page)
	{
		if(startDate == null)
		{
			startDate= new Date(0);
		}
		if(endDate == null)
		{
			endDate= new Date(System.currentTimeMillis());
		}
		List<Expense> listy= erepo.findByUserSetupIdAndDateBetween(uss.getLoggedInUser().getId(),startDate, endDate, page).toList();
		return listy.stream().map(e ->mapToDto(e)).collect(Collectors.toList());
	}
	
	
	
	
	
}


/*
 
  In Java, Optional is a container object used to contain not-null objects.
  Optional object is used to represent null with absent value. 
  This class has various utility methods to facilitate code to handle values as ‘available’ or ‘not available’ instead of checking null values. In the context of Spring Boot and more broadly, the Spring framework, Optional can play a significant role in making the code cleaner and more expressive, especially when dealing with operations that may or may not return a value.
  
  
*/