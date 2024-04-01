package kw.kng.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import kw.kng.binders.UserModel;
import kw.kng.entity.UserSetup;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.service.UserSetupService;

@Controller
public class UserSetupController 
{
	@Autowired
	private UserSetupService us;
	
	/*
	@PostMapping("/register")
	public ResponseEntity<UserSetup> save(@Valid @RequestBody UserModel user)
	{
		return new ResponseEntity<UserSetup>(us.createUser(user), HttpStatus.CREATED);
	}
	*/
	
	@GetMapping("/profile")
	public ResponseEntity<UserSetup> getUserSetupDetails()
	{
		return new ResponseEntity<UserSetup>(us.readUserDetails(), HttpStatus.OK);
	}
	
	@PutMapping("/profile")
	public ResponseEntity<UserSetup> updateUserSetupDetails(@RequestBody UserModel user)
	{
		UserSetup muser= us.updateUserDetails(user);
		return new ResponseEntity<UserSetup>(muser, HttpStatus.OK);
	}

	
	@DeleteMapping("/selfdelete")
	public ResponseEntity<HttpStatus> deleteUserSetupDetails() throws ResourceNotFoundException
	{
		us.deleteUserDetailsById();
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
} 

/*
 1. Remeber we are using Binding class UserModel to update the contents to UserSetup entity.
 
 
 
 
 */

