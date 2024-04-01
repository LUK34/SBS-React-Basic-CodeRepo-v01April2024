package kw.kng.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kw.kng.binders.UserModel;
import kw.kng.entity.UserSetup;
import kw.kng.exceptions.ItemAlreadyExistsException;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repository.UserSetupRepository;

@Service
public class UserSetupServiceImpl implements UserSetupService {

	@Autowired
	private UserSetupRepository urepo;
	
	@Autowired
	private PasswordEncoder bcrypt;
	
	
	@Override
	public UserSetup createUser(UserModel uModel) 
	{
		if(urepo.existsByEmail(uModel.getEmail()))
		{
			throw new ItemAlreadyExistsException("User email already exists in system. Please enter new email: "+uModel.getEmail());
		}
		UserSetup newUser= new UserSetup();
		BeanUtils.copyProperties(uModel, newUser);
		newUser.setPassword(bcrypt.encode(newUser.getPassword()));
		return urepo.save(newUser);
	}
	
	@Override
	public UserSetup readUserDetails() throws ResourceNotFoundException
	{
		Long userId=getLoggedInUser().getId();
		return urepo.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User not found for id: "+userId) );
	}
	
	@Override
	public UserSetup updateUserDetails(UserModel userModel) throws ResourceNotFoundException
	{
		UserSetup existingUser=readUserDetails();
		existingUser.setName( userModel.getName()  != null  ? userModel.getName() : existingUser.getName() );
		existingUser.setEmail( userModel.getEmail()  != null  ? userModel.getEmail() : existingUser.getEmail() );
		existingUser.setPassword( userModel.getPassword()  != null  ? bcrypt.encode( userModel.getPassword() )  : existingUser.getPassword() );
		existingUser.setAge( userModel.getAge()  != null  ? userModel.getAge() : existingUser.getAge() );
		
		return urepo.save(existingUser);
	}
	
	
	@Override
	public  void deleteUserDetailsById()
	{
		UserSetup existingUser=readUserDetails();
		urepo.delete(existingUser);
	}
	
	@Override
	 public UserSetup getLoggedInUser()
	 {
		 
		Authentication a=SecurityContextHolder.getContext().getAuthentication();
		String email=a.getName(); //Here we overriden the method, removed name and placed email.
		
		return urepo.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found for the email"+email));
	 }
	
	

}
/*
 
1. What is BeanUtils.copyProperties ??

Ans:
BeanUtils.copyProperties is a utility method provided by the Apache Commons BeanUtils library.
This method is used to copy property values from one Java Bean object to another.
This is particularly useful when you want to transfer data between two objects that have similar fields or properties.

Here's a brief overview of how it works:

1.1 Source and Target Objects:
The method takes two parameters: a source object (uModel in your example) and a target object (user). 
It reads the properties of the source object and copies them to the target object.

1.2 Property Matching:
The properties are matched based on their names and data types.
For a property value to be copied from the source to the target, both objects must have a property with the same name
and a compatible data type.

1.3 Use Case:
It's commonly used in scenarios where you need to transfer data from an object of one class to an object of another class,
provided they have properties with matching names and types. This is often the case in layered applications, such as when transferring data 
from data transfer objects (DTOs) to domain objects or entities and vice versa.

1.4 Advantages:
This method simplifies code by automating the copying process, reducing the need for manually setting each property value 
and decreasing the likelihood of missing a property or introducing a bug.

1.5 Limitations:
However, it's important to be aware of its limitations.
For example, BeanUtils.copyProperties will not copy properties that don't have matching names or compatible types
between the source and target objects. It also doesn't handle deep copying of complex objects automatically;
it performs a shallow copy, which means it doesn't copy the objects referenced by the source object's properties but rather their references.
 */