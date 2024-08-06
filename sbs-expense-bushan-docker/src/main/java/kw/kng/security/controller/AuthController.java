package kw.kng.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.binders.JwtResponse;
import kw.kng.binders.LoginModel;
import kw.kng.binders.UserModel;
import kw.kng.entity.UserSetup;
import kw.kng.security.service.CustomUserDetailsServiceImpl;
import kw.kng.service.UserSetupService;
import kw.kng.utils.JwtTokenUtil;

@RestController
public class AuthController 
{
	@Autowired
	private UserSetupService userSetupService;
	
	@Autowired
	private CustomUserDetailsServiceImpl cuds; //Custom security service
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authManager; //From Spring Security. Refer the packages from the above

	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginModel loginModel)  throws Exception
	{
		authenticate(loginModel.getEmail(), loginModel.getPassword());
		//we need to generate JWT
		final UserDetails uds= cuds.loadUserByUsername(loginModel.getEmail());
		
		final String token = jwtTokenUtil.generateToken(uds);
		
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
	}
	
	
	private void authenticate(String email, String password) throws Exception
	{
		try
		{
			 authManager.authenticate( new  UsernamePasswordAuthenticationToken ( email, password ) );	
		}
		catch(DisabledException e){	
			throw new Exception("User disabled"); }
		catch(BadCredentialsException e)	{ 
			throw new Exception("Bad Credentials");	}
	}

	
	
	@PostMapping("/register")
	public ResponseEntity<UserSetup> save(@Valid @RequestBody UserModel user) {
		return new ResponseEntity<UserSetup>(userSetupService.createUser(user), HttpStatus.CREATED);
	}

}
