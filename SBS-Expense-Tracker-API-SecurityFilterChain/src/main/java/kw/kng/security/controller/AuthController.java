package kw.kng.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.binders.LoginModel;
import kw.kng.binders.UserModel;
import kw.kng.entity.UserSetup;
import kw.kng.service.UserSetupService;

@RestController
public class AuthController 
{
	@Autowired
	private UserSetupService userSetupService;
	
	@Autowired
	private AuthenticationManager authManager; //From Spring Security. Refer the packages from the above

	@PostMapping("/login")
	public ResponseEntity<HttpStatus> login(@RequestBody LoginModel loginModel) 
	{
		Authentication a= authManager.authenticate( new  UsernamePasswordAuthenticationToken ( loginModel.getEmail(), loginModel.getPassword() ) );
		SecurityContextHolder.getContext().setAuthentication(a);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	/*
	@PostMapping("/login")
	public ResponseEntity<String> login() 
	{
		return new ResponseEntity<String>("User is logged in", HttpStatus.OK);
	}
	*/
	@PostMapping("/register")
	public ResponseEntity<UserSetup> save(@Valid @RequestBody UserModel user) {
		return new ResponseEntity<UserSetup>(userSetupService.createUser(user), HttpStatus.CREATED);
	}

}
