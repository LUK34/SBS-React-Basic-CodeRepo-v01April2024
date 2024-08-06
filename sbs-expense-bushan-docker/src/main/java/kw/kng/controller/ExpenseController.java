package kw.kng.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.dto.CategoryDto;
import kw.kng.dto.ExpenseDto;
import kw.kng.entity.Expense;
import kw.kng.io.CategoryResponse;
import kw.kng.io.ExpenseRequest;
import kw.kng.io.ExpenseResponse;
import kw.kng.service.ExpenseService;

@RestController
@CrossOrigin
public class ExpenseController 
{
	@Autowired
	private ExpenseService es;
	
	/*
	 
	 //First way of versioning the application
	@GetMapping("/") 
	public String test()
	{
		return "testing versioning";
	}
	
	*/
	
	//Link: {{url}}/expenses?page=0&size=3&sort=amount,desc
	@GetMapping("/expenses")
	public List<ExpenseResponse> getAllExpenses(Pageable page)
	{
		/*
		 	int n=1; calculateFactorial(1);
		 */		
		List<ExpenseDto> listOfExpenses = es.getAllExpenses(page);
		return listOfExpenses.stream().map(expenseDto -> mapToExpenseResponse(expenseDto)).collect(Collectors.toList());
	}
	

	
	// PathVariable approach example
	@GetMapping("/expenses/{id}")
	public ExpenseResponse getExpenseById(@PathVariable("id") String expenseId) 
	{
		ExpenseDto expenseDto = es.getExpenseById(expenseId);
		return mapToResponse(expenseDto);
		//return "the expense id is (@PathVariable example)= "+id;
		
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses")
	public void deleteExpenseById(@RequestParam("id") String id)
	{
		es.deleteExpenseById(id);
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/expenses")
	public ExpenseResponse saveExpenseDetails(@Valid @RequestBody ExpenseRequest expenseRequest) 
	{
		ExpenseDto expenseDTO = mapToDTO(expenseRequest);
		expenseDTO =es.saveExpenseDetails(expenseDTO);
		//return mapToResponse(expenseDTO);
		return mapToExpenseResponse(expenseDTO);
	}
	
	private ExpenseResponse mapToExpenseResponse(ExpenseDto expenseDTO)
	{
		return ExpenseResponse.builder()
						.expenseId(expenseDTO.getExpenseId())
						.name(expenseDTO.getName())
						.description(expenseDTO.getDescription())
						.amount(expenseDTO.getAmount())
						.date(expenseDTO.getDate())
						.categoryResponse(mapToCategoryResponse(expenseDTO.getCategoryDto()))
						.createdAt(expenseDTO.getCreatedAt())
						.updatedAt(expenseDTO.getUpdatedAt())
						.build();
	}
	
	private CategoryResponse mapToCategoryResponse(CategoryDto categoryDto) 
	{
		return CategoryResponse.builder()
							.categoryId(categoryDto.getCategoryId())
							.name(categoryDto.getName())
							.description(categoryDto.getDescription())
							.categoryIcon(categoryDto.getCategoryIcon())
							.createdAt(categoryDto.getCreatedAt())
							.updatedAt(categoryDto.getUpdatedAt())
							.build();
	}

	private ExpenseDto mapToDTO(ExpenseRequest expenseRequest)
	{
		return ExpenseDto.builder()
						.name(expenseRequest.getName())
						.description(expenseRequest.getDescription())
						.amount(expenseRequest.getAmount())
						.date(expenseRequest.getDate())
						.categoryId(expenseRequest.getCategoryId())
						.build();
	}
	
	private ExpenseResponse mapToResponse(ExpenseDto expenseDto)
	{
		return ExpenseResponse.builder()
				.expenseId(expenseDto.getExpenseId())
				.name(expenseDto.getName())
				.description(expenseDto.getDescription())
				.amount(expenseDto.getAmount())
				.date(expenseDto.getDate())
				.categoryResponse(mapToCategoryResponse(expenseDto.getCategoryDto()))
				.createdAt(expenseDto.getCreatedAt())
				.updatedAt(expenseDto.getUpdatedAt())
				.build();
	}
	
	
	@PutMapping("/expenses/{id}")
	public ExpenseResponse updateExpenseDetails(@RequestBody ExpenseRequest expenseRequest ,@PathVariable String id)
	{
		ExpenseDto updatedExpense = mapToDTO(expenseRequest);
		updatedExpense= es.updateExpenseDetails(id, updatedExpense); 
		return mapToResponse(updatedExpense);
	}
	

	@GetMapping("/expenses/category")
	public List<ExpenseResponse> getAllExpenseByCategory(@RequestParam String category, Pageable page)
	{
		List<ExpenseDto> listy = es.readByCategory(category, page);
		return listy.stream().map(ex -> mapToResponse(ex)).collect(Collectors.toList());
	}
	
	@GetMapping("/expenses/name")
	public List<ExpenseResponse> getAllExpenseByName(@RequestParam String keyword, Pageable page)
	{
		List<ExpenseDto> listy= es.readByName(keyword, page);
		return listy.stream().map(ex -> mapToResponse(ex)).collect(Collectors.toList());
	}
	
	@GetMapping("/expenses/date")
	public List<ExpenseResponse> getAllExpenseByDate(@RequestParam(required=false) Date startDate, 
											 @RequestParam(required=false) Date endDate,
											 Pageable page)
	{
		List<ExpenseDto> listy= es.readByDate(startDate, endDate, page);
		return listy.stream().map(ex -> mapToResponse(ex)).collect(Collectors.toList());
	}
	
	//The below code is a recursive function that helps in INTERNAL_SERVER_ERROR Exception
	public int calculateFactorial(int number)
	{
		return number * calculateFactorial(number -1);
	}
	


}


/*
 ------------------------------------------------------------------------------
 							PathVariable and RequestParam
 ------------------------------------------------------------------------------
  

--------------------------------------
@PathVariable:
--------------------------------------

This is used to extract data from the URI path itself. It's typically used for RESTful web services, 
where the URL contains a path parameter representing a specific resource or a set of resources.

Advantages
--------------------
1.1 Cleaner URLs:
 Suitable for RESTful services where URLs represent resources, making them more readable and meaningful.

1.2 Direct mapping to domain objects:
 Can be directly mapped to domain objects or identifiers, facilitating operations like CRUD directly from the URI.

1.3 Type conversion:
 Automatically handles type conversion to the method parameter type.

Disadvantages
---------------------
1.1 Limited to URI path:
 Only suitable for values that are part of the URL path.

1.2 Complexity with multiple variables:
 Handling URLs with multiple @PathVariable parameters can get complicated, especially if they're optional.

1.3 Less flexibility:
 Not as flexible for optional parameters or scenarios where parameters might change frequently.

--------------------------------------
@RequestParam:
--------------------------------------
Description
-------------------
@RequestParam is used to access query parameters, form parameters, and even multipart files from the request. It's widely used in traditional web applications handling form submissions.

Advantages
-------------------
1.1 Flexibility:
Ideal for handling optional parameters with default values, making it very flexible for web forms and queries.

1.2 Easy handling of multiple parameters:
Simplifies the processing of multiple and non-URI data, including complex form structures.

1.3 Compatibility:
Works well with traditional web forms and applications, where data is often passed as query parameters.

Disadvantages
-------------------
1.1 Messier URLs: 
Can lead to long and complex URLs with many parameters, especially if not properly structured.
1.2 Not ideal for RESTful URI patterns:
 Using query parameters for resource identification does not align well with REST principles.
1.3 Overhead:
 Handling large numbers of @RequestParam annotations can clutter method signatures and make them harder to read.

SUMMARY
---------------------
In summary, @PathVariable is best suited for RESTful services where URL paths denote specific resources,
offering cleaner URLs and direct mapping capabilities. On the other hand, @RequestParam is more flexible for traditional web 
applications that rely heavily on forms and queries, allowing for easy handling of optional and numerous parameters. 
The choice between the two should be guided by the specific requirements of your application and the design of your API endpoints.
  
  
  
  
  
 @PathVariable example
 ------------------------
 CODE:
 	@GetMapping("/expenses/{id}")
	public String getExpenseById(@PathVariable("id")Long id) 
	{
		return "the expense id is (@PathVariable example)= "+id;
		
	}
 URL: http://localhost:8080/api/v1/expenses/2
 Output: the expense id is (@PathVariable example)=  2 
 
 
  @RequestParam example
 ------------------------
 CODE:
 @DeleteMapping("/expenses")
	public String deleteExpenseById(@RequestParam("id")Long id)
	{
		return "Delete the expense object by its id(@RequestParam example)= "+id;
	}
 URL: http://localhost:8080/api/v1/expenses?id=2
 Output: Delete the expense object by its id(@RequestParam example)= 2
	
	

* Only for DeleteMapping and PostMapping we put Status code. Remaining we dont need to add the status code,
 If we want we can add.The reason why we are not adding is because the status code will be shown at postman application.
	
  
 List of pages url: Pagination and Sorting
  
 URL: http://localhost:8080/api/v1/expenses?size=1
 URL: http://localhost:8080/api/v1/expenses?size=2&page=1
 URL: http://localhost:8080/api/v1/expenses?sort=amount    (will display JSON in asc order by default)
 URL: http://localhost:8080/api/v1/expenses?sort=name
 URL: http://localhost:8080/api/v1/expenses?sort=name,desc	(will display JSON in DESC order by default)
 URL: http://localhost:8080/api/v1/expenses?page=0&size=2&sort=amount,desc		(Pagination and sorting in descending order)
 
 */
