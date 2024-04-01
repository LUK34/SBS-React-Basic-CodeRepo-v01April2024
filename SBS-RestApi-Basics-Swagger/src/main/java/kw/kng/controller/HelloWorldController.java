package kw.kng.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.entity.BasicHelloWorld;
import kw.kng.service.HelloWorldService;

@RestController //This is basically a combination of @Controller and @ResponseBody
//@RequestMapping("/api/v1")
public class HelloWorldController 
{
	//localhost:8080/api/v1/helloworld
	@Autowired
	private HelloWorldService hws;
	
	@Value("${app.name}")
	private String appName;
	
	@Value("${app.version}")
	private String appVersion;
	
	
	@GetMapping("/version")
	public String getAppDetails()
	{
		return appName+ " - "+appVersion;
	}
	
	
	//TYPE: POST, URL: localhost:8080/helloworld
	/* 
		@RequestMapping(value = "/helloworld", method = RequestMethod.GET)
		@ResponseBody
	 */
	@GetMapping("/helloworld")
	public ResponseEntity<List<BasicHelloWorld>> getHelloWorld(@RequestParam Integer pageNumber, @RequestParam Integer pageSize)
	{
		return new ResponseEntity<List<BasicHelloWorld>>(hws.getHelloWorld(pageNumber, pageSize ), HttpStatus.OK);
	}

	//TYPE: GET, URL: localhost:8080/helloworld/1
	@GetMapping("/helloworld/{id}")
	public ResponseEntity<BasicHelloWorld> getHelloWorld(@PathVariable("id") Long id)
	{
		return new ResponseEntity<BasicHelloWorld>(hws.getBasicHelloWorldRecordById(id), HttpStatus.OK );
	}
	
	//TYPE: POST, URL: localhost:8080/helloworld
	@PostMapping("/helloworld")
	public ResponseEntity<BasicHelloWorld> saveHelloWorld(@Valid @RequestBody BasicHelloWorld bwh)
	{
		return new ResponseEntity<>(hws.saveBasicHelloWorld(bwh) , HttpStatus.CREATED);
	}
	
	//TYPE: PUT, URL: localhost:8080/helloworld/1
	@PutMapping("/helloworld/{id}")
	public  ResponseEntity<BasicHelloWorld> updateHelloWorld(@PathVariable Long id, @RequestBody BasicHelloWorld basicHelloWorld)
	{
		return new ResponseEntity<>(hws.updateBasicHelloWorldRecordById(id, basicHelloWorld), HttpStatus.OK ); 
	}
	
	
	//TYPE: DELETE, URL: localhost:8080/helloworld?id=34
	@DeleteMapping("/helloworld")
	public ResponseEntity<HttpStatus> deleteHelloWorld(@RequestParam("id") Long id)
	{
		   try 
		   {
	            hws.deleteBasicHelloWorldById(id);
	            return ResponseEntity.noContent().build();//return 204 NO_CONTENT
	        }
		   catch (RuntimeException ex)
		   {
	            return ResponseEntity.notFound().build(); //return 404 NOT_FOUND 
	        }
	}
	
	@GetMapping("/helloworld/filterByName")
	public ResponseEntity<List<BasicHelloWorld>> getBasicHelloWorldByName(@RequestParam String name)
	{
		return new ResponseEntity<List<BasicHelloWorld>> (hws.getBasicHelloWorldByName(name) , HttpStatus.OK);
	}
	
	
	@GetMapping("/helloworld/filterByNameAndLocation")
	public ResponseEntity<List<BasicHelloWorld>> getBasicHelloWorldByNameAndLocation(@RequestParam String name, @RequestParam String location)
	{
		return new ResponseEntity<List<BasicHelloWorld>> (hws.getBasicHelloWorldByNameAndLocation(name, location) , HttpStatus.OK);
	}
	
	
	@GetMapping("/helloworld/filterByNameKeyword")
	public ResponseEntity<List<BasicHelloWorld>> getBasicHelloWorldByNameByKeyword(@RequestParam String name)
	{
		return new ResponseEntity<List<BasicHelloWorld>> (hws.getBasicHelloWorldByNameKeyword(name) , HttpStatus.OK);
	}
	
	
	@GetMapping("/helloworld/{name}/{location}")
	public ResponseEntity<List<BasicHelloWorld>> getBasicHelloWorldByNameOrLocation(@PathVariable String name,@PathVariable String location)
	{
		return new ResponseEntity<List<BasicHelloWorld>> (hws.getBasicHelloWorldByNameOrLocation(name, location) , HttpStatus.OK);
	}
	
	
	@DeleteMapping("/helloworld/delete/{name}")
	public ResponseEntity<String> delereBasicHelloWorldByName(@PathVariable String name)
	{
		return new ResponseEntity<String> (hws.deleteBasicHelloWorldByName(name)  + " Records with name:  " + name + " deleted.",  HttpStatus.OK);
	}
}

/*
 
1.  @RequestMapping(value = "/helloworld", method = RequestMethod.GET)

Description:
-----------------
This annotation is used to map HTTP GET requests to a specific handler method or class in your controller. 
value = "/helloworld" specifies the URL path that this method will handle. So, when a GET request is made to /helloworld, 
the method annotated with this will be invoked. method = RequestMethod.GET explicitly states that this should only respond to GET requests.
 
 2. @ResponseBody

Description:
-----------------
This annotation is used at the method level. It indicates that the return value of the method should be used as the body of the response sent back to the client. 
Spring will automatically convert this return value into the appropriate format (like JSON or XML) based on the content type specified in the request or through other configurations.
 
 3. @GetMapping("/helloworld")

Description:
-----------------
This is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET). It simplifies the previous example
 by only needing the path value. It's specifically designed to handle GET requests, making the code more readable and concise.
  Like the first example, it maps a GET request for /helloworld to the annotated method.
 
 4. @GetMapping("/helloworld/{id}")

Description:
-----------------
This is another use of the @GetMapping annotation but with a dynamic part in the path, indicated by {id}. 
This means it will match any GET request that follows the pattern /helloworld/someValue, where someValue can be any value representing an ID.
This is useful for fetching specific resources, like an employee's details in this context.
 
5.  public String getEmployee(@PathVariable("id") Long id)

Description:
-----------------
This is a method signature. The method is designed to respond to the GET request mapped by @GetMapping("/helloworld/{id}").
The @PathVariable("id") Long id part captures the dynamic portion of the URL ({id}) and passes it to the method as a variable. 
The value is automatically converted to the specified type (Long in this case) and can be used inside the method to perform actions like fetching an employee's details by ID.
 
 
 */


/*
 {
    "name":"Luke Rajan",
    "age":27,
    "location":"India",
    "email":"lrm@gmail.com",
    "department":"CSE"
}
 */


