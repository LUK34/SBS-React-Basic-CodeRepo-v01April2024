package kw.kng.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.binders.VersionModel;

@RestController
public class HealthCheckController
{
	
	@GetMapping("/")
	public ResponseEntity<VersionModel> version()
	{
		VersionModel vm=new VersionModel();
		vm.setVersion("1.1");
		return new ResponseEntity<VersionModel>(vm, HttpStatus.OK);
	}

}

/*
 
 1. VersionModel is a binding class.
 
 2.  Refer the second video of versioning. We are not versioning using the context path logic which is by 
 using simple controller or properties file.
 
 
 */
