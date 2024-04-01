package kw.kng.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kw.kng.security.service.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringBootWebSecurityConfiguration 
{
	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
        return http.build();
    }
    
    
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception 
    {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailsService)
                   .passwordEncoder(passwordEncoder)
                   .and()
                   .build();
    }
    
    
/*
    @Bean
    public InMemoryUserDetailsManager userDetailsService()
    {
        
    	UserDetails user1 = User.withUsername("hello")
                                				.password(passwordEncoder().encode("1234"))
                                				.authorities("admin")
                                				.build();
        UserDetails user2 = User.withUsername("world")
                                				.password(passwordEncoder().encode("1234"))
                                				.authorities("user")
                                				.build();
        
        return new InMemoryUserDetailsManager(user1, user2);
    }
*/
    
    
    
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        // This is not secure. Use a more secure password encoder in production.
    	//        return NoOpPasswordEncoder.getInstance();
    	return new BCryptPasswordEncoder(); //Pasword =1234 for all users but in db it is encrypted.
    }
    
    
}



/*
 1. Ok so we are commenting InMemoryUserDetailsManager. Creating a service package in security layer and defining a CustomUserDetailsServiceImpl
 method. This method implement UserDetailsService . We have to override the username method and replace username with email.
 Because we will be login postman thorugh Email and password. After that specify the authManager ( This spring security code for SpringFilterChain method).
 What thid does is . Under postman->Authorization->Select dropdown as Basic Auth->Enter the username( Here u have to eneter the email) and Password.
 If the details are present in database, then the user can successfully log in via postman. Once logged in the user can access the functionalities easily.


*/