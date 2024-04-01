package kw.kng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig 
{
		public ValidatingMongoEventListener validationMongoEventListener() //This is constructor is created from the imported package above
		{
			return new ValidatingMongoEventListener(validator()); //We pass the below bean object into this constructor
		}
		
		@Bean
		public LocalValidatorFactoryBean validator()
		{
			return new LocalValidatorFactoryBean();
		}
}
