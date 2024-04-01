package kw.kng.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import kw.kng.binders.ErrorObject;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest req)
	{
		ErrorObject e = new ErrorObject();
		
		e.setStatusCode(HttpStatus.NOT_FOUND.value());
		e.setMessage(ex.getMessage());
		e.setTimestamp(new Date());
		
		return new ResponseEntity<ErrorObject>(e, HttpStatus.NOT_FOUND);
	}
		

	/*
	 Scenarios:
	 ---------
	 Eg: If an existing email saved into the system. Every email has to be uniques soe we use ItemAlreadyExistsException ExceptionHandler.
	 Status Code:409
	 */
	@ExceptionHandler(ItemAlreadyExistsException.class)// eg: If an existing email saved
	public ResponseEntity<ErrorObject> handleItemAlreadyExistsException(ItemAlreadyExistsException ex, WebRequest req)
	{
		ErrorObject e = new ErrorObject();
		
		e.setStatusCode(HttpStatus.CONFLICT.value());
		e.setMessage(ex.getMessage());
		e.setTimestamp(new Date());
		
		return new ResponseEntity<ErrorObject>(e, HttpStatus.CONFLICT);
	}
	
	
	/*
	 Scenarios:
	 ---------
	 Eg: GET, {{url}}/expenses/100s
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class) 
	public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest req)
	{
		ErrorObject e = new ErrorObject();
		
		e.setStatusCode(HttpStatus.BAD_REQUEST.value());
		e.setMessage(ex.getMessage());
		e.setTimestamp(new Date());
		
		return new ResponseEntity<ErrorObject>(e, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest req)
	{
		ErrorObject e = new ErrorObject();
		
		e.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		e.setMessage(ex.getMessage());
		e.setTimestamp(new Date());
		
		return new ResponseEntity<ErrorObject>(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}



	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("timestamp", new Date());
		body.put("statusCode", HttpStatus.BAD_REQUEST);
		
		List<String> errors=ex.getBindingResult()
					.getFieldErrors()
					.stream()
					.map(x ->x.getDefaultMessage())
					.collect(Collectors.toList());
		/*
		  When BAD_REQUEST occurs at POSTMAN. We need to take only `default message` part of the exception.
		  To do so we have override and customize in this method. the above line will fetch using stream and store 
		  it in a List<String> and pass it to body.
		 */			
		body.put("message",errors);
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}
	
	

	
	
	
	
	
	
}


/*

//--------------------------------------------------------------------------------------------------------------------------
 				//What is @ControllerAdvice ??
//--------------------------------------------------------------------------------------------------------------------------
 				 				
 1. @ControllerAdvice :
 -----------------------
 It is an annotation used in Spring Boot and the broader Spring Framework to handle exceptions 
 across the whole application in one global handling component. It can also be used to perform global, 
 cross-cutting tasks on the control-flow of a Spring application, such as adding attributes to the model or binding 
 certain objects to the view layer across all @Controller classes.

Here's what @ControllerAdvice allows you to do:

1.1. Global Exception Handling:
You can define methods within a class annotated with @ControllerAdvice to handle exceptions thrown by methods 
annotated with @RequestMapping (and similar annotations) across multiple controllers. Such methods can be annotated with @ExceptionHandler.

1.2. Binding Enhancements:
It allows you to apply common model attributes to all controllers within its scope.
This is useful for data that you need to make available globally, like user preferences or settings.

1.3. Input Validation:
You can use it to apply global, cross-cutting pre-processing logic, 
such as adding or manipulating request attributes before they reach the controller.

1.4. Global Response Body Advice: 
By implementing ResponseBodyAdvice in a @ControllerAdvice class, 
you can modify the response of all @RestController methods in your application.

// --------------------------------------------------------------------------------------------------------------------------

				//Break down of code:

//--------------------------------------------------------------------------------------------------------------------------
 				 
				 
2. Let's break down the key components:

2.1 @ControllerAdvice:
 This annotation marks the class as a controller advice, enabling it to handle exceptions thrown by controllers across the entire application.

2.2 @ExceptionHandler(ExpenseNotFoundException.class):
 This specifies that the annotated method should handle exceptions of type ExpenseNotFoundException. 
 You can list multiple exceptions as well if needed.

2.3 The method handleExpenseNotFoundException is designed to handle exceptions of the type ExpenseNotFoundException.
It accepts the exception instance and a WebRequest object, which can be used to provide request-specific context or parameters if needed.

2.4 Inside the method, an ErrorObject instance is created and populated with details about the exception, such as the HTTP status code (HttpStatus.NOT_FOUND.value()), 
a custom message retrieved from the exception (ex.getMessage()), and the current timestamp.

Finally, a ResponseEntity<ErrorObject> is returned with the ErrorObject and the HTTP status NOT_FOUND, providing a structured and informative response to the client.
This approach to exception handling with @ControllerAdvice and @ExceptionHandler is beneficial for several reasons:

a) Centralization: It allows for centralizing exception handling logic in one place rather than spreading it across individual controllers.

b) Consistency: It ensures a consistent response structure for errors, which is particularly useful for APIs.

c) Separation of Concerns: It keeps your business logic and controller methods cleaner by abstracting the error handling away.

d) Flexibility: It provides flexibility to handle different types of exceptions differently, enabling more detailed and specific responses based on the exception type.

This is a great way to manage exception handling in a Spring Boot application, enhancing maintainability, readability, and client-side error handling.
 
 
3. We extend ths GlobalExceptionHandler with ResponseEntityExceptionHandler. With ResponseEntityExceptionHandler
we can customize the exceptions.
 
4.To override a method from ResponseEntityExceptionHandler. we use short cut keys
Shortcut Keys:
`ALT+SHIFT+S` -> Select teh method to override -> and then select `V` 

So our main objective is to customize the exception by overriding the ResponseEntityExceptionHandler. And here we will use Map<Key,Value>
Our plan is that , whenever we get BAD_REQUEST from Postman we should display meanifull response in the response of postman. so we customize it through this.
We will take `default message` from the exception.

 */