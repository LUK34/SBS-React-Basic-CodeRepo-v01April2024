package kw.kng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
					title="Expense Tracker REST API Documentation",
					description="Expens Tracker REST API Documentation",
					version="v05August2024",
					contact=@Contact(
										name="Luke Rajan Mathew",
										email="",
										url=""
					),
					license=@License(
										name="",
										url=""
					)
				),
	   externalDocs= @ExternalDocumentation(
											description="Expens Tracker REST API Documentation for Developers",
											url="https://www.javaguides.net/external-doc.html"
										)
)
public class SbsExpenseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbsExpenseTrackerApiApplication.class, args);
	}

}


/*
URL for SWAGGER docs: 
----------------------------------

#http://localhost:8080/swagger-ui.html
http://localhost:8080/swagger-ui.html

*/