package kw.kng.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.exception.TodoCollectionException;
import kw.kng.model.TodoDTO;
import kw.kng.repository.TodoRepository;
import kw.kng.service.TodoService;

@RestController
public class TodoController
{
	@Autowired
	private TodoRepository trepo;
	
	@Autowired
	private TodoService ts;
	
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos()
	{
		List<TodoDTO> todos = ts.getAllTodos();
		return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
		try 
		{
			ts.createTodo(todo);
			return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
		}
		catch (ConstraintViolationException e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} 
		catch (TodoCollectionException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id)
	{
		try 
		{
			return new ResponseEntity<>(ts.getSingleTodo(id), HttpStatus.OK);
		}
		catch (TodoCollectionException e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateSingleTodo(@PathVariable("id") String id, @RequestBody TodoDTO todo)
	{
				try
				{
					ts.updateTodo(id,todo);
					return new ResponseEntity<>("Updated movie with id "+id+"", HttpStatus.OK);
				}
		    catch(ConstraintViolationException e)
		    {
		        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		    }
		    catch (TodoCollectionException e)
			{
		        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		    }
	}
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws TodoCollectionException
	{
		try
		{
            ts.deleteTodoById(id);
            return new ResponseEntity<>("Successfully deleted todo with id "+id, HttpStatus.OK);
        }
        catch (TodoCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }	
	}
	
	

}
