package kw.kng.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kw.kng.entity.UserSetup;
import kw.kng.repository.UserSetupRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService 
{
	@Autowired
	private UserSetupRepository urepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
	{
		UserSetup existingUser = urepo.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found the for the email: " + email) );
		return new org.springframework.security.core.userdetails.User( existingUser.getEmail(), existingUser.getPassword(),  new ArrayList<>() );
	}

}

/*
 
 1. Remember we did not define an interface called UserDetailsService. This UseDetailService is already present in Springboot.
 So we implement UserDetailsService.
 
 2. In this UserDetailsService we will overload the  `UserDetails loadUserByUsername(String username )` 
 and we will replace username with email, to validate email
 
 */