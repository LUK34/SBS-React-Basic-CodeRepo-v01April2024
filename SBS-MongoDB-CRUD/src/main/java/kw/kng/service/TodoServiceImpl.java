package kw.kng.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kw.kng.exception.TodoCollectionException;
import kw.kng.model.TodoDTO;
import kw.kng.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService 
{
	@Autowired 
	private TodoRepository trepo;
	
	@Override
	public void createTodo(TodoDTO todo) throws ConstraintViolationException,TodoCollectionException
	{
		Optional<TodoDTO> todoOptional = trepo.findByTodo(todo.getTodo());
		if(todoOptional.isPresent())
		{
			throw new TodoCollectionException( TodoCollectionException.TodoAlreadyExists() );
		}
		else
		{
			todo.setCreatedAt(new Date( System.currentTimeMillis() ) );
			trepo.save(todo);
		}
	}
	
	@Override
	public List<TodoDTO> getAllTodos() 
	{
		List<TodoDTO> todos = trepo.findAll();
		if (todos.size() > 0)
		{
			return todos;
		}
		else
		{
			return new ArrayList<TodoDTO>(); //If emtpy create and return a new empty array list.
		}
	}
	
	@Override
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException
	{
		Optional<TodoDTO> optionalTodo = trepo.findById(id);
		if (!optionalTodo.isPresent()) 
		{
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		}
		else
		{
			return optionalTodo.get();
		}
	}
	
	@Override
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException
	{
		Optional<TodoDTO> todoWithId = trepo.findById(id);
        Optional<TodoDTO> todoWithSameName = trepo.findByTodo(todo.getTodo());
        if(todoWithId.isPresent())
        {
            if(todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)) //True  && False = False
            {

                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }
            TodoDTO todoToUpdate=todoWithId.get();
            
            todoToUpdate.setTodo(todo.getTodo());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setCompleted(todo.getCompleted());
            todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            trepo.save(todoToUpdate);
        }
        else
        {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
	}
	
	@Override
	public void deleteTodoById(String id) throws TodoCollectionException
	{
		Optional<TodoDTO> todoOptional = trepo.findById(id);
        if(!todoOptional.isPresent())
        {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
        else
        {
        	trepo.deleteById(id);
        }
		
	}
	
	
	
	
	
}
