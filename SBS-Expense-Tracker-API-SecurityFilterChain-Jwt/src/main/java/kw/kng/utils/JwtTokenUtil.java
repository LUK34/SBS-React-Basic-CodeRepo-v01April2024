package kw.kng.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtTokenUtil 
{

	private static final long JWT_TOKEN_VALIDITY=5 * 60 * 60;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateToken(UserDetails uds) 
	{
		Map<String, Object>  claims= new HashMap<>();
	
		return  Jwts.builder()
						 .setClaims(claims)
						 .setSubject(uds.getUsername())
						 .setIssuedAt(new Date(System.currentTimeMillis() ) )
						 .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000) )
						 .signWith(SignatureAlgorithm.HS512, secret)
						 .compact(); // compact will return a string
	}
	
	//Get the User name from  getClaimFromToken() functional program
	public String getUsernameFromToken(String jwtToken) 
	{
		return getClaimFromToken(jwtToken, Claims::getSubject);
	}
	
	//Get the Issued Date from  getClaimFromToken() functional program
	public Date getIssuedDateFromToken(String jwtToken)
	{
		return getClaimFromToken(jwtToken, Claims::getIssuedAt);
	}
	
	//Get the Expiration Date from  getClaimFromToken() functional program
	public Date getExpirationDateFromToken(String jwtToken)
	{
		return getClaimFromToken(jwtToken, Claims::getExpiration);
	}
	
	
	// Java 8 Functional Programming- Functional programming to parse the JWT token contents by using secret key parsed form properties file
	// On parsing the secret key we will get various parts of the token such as (Subject, IssuedAt, Expiration).
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) //Claims is imported from the package. Refer the import above.
	{
		final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claimResolver.apply(claims);
	}

	// True/False to validate the token
	public boolean validateToken(String jwtToken, UserDetails ud) 
	{
		final String username= getUsernameFromToken(jwtToken);
		return username.equals( ud.getUsername() ) && !isTokenExpired(jwtToken);
		//Here it will check whether the jwttoken assigned to the username has expired or not. Expiration time is 5hrs(converted to milliseconds).
		//T & T= T,  T&&F=F 
	}

	//True/False to validate if token is expired or not
	private boolean isTokenExpired(String jwtToken)
	{
		final Date expiration = getExpirationDateFromToken(jwtToken);
		return expiration.before(new Date()); 
		
		//Here it will check whether the expiration date equals to current date.
	}
	

}

/*
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  																					// KT for I_iots
   //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  
 1. JWT_TOKEN_VALIDITY: A static constant representing the validity time of the JWT in seconds. In this case, it's set to 5 hours (5 * 60 * 60).
 
 2.The generateToken method takes a UserDetails object as a parameter, which contains information about the authenticated user for whom the JWT is being generated.
 
3. Inside the method, a new empty HashMap is created to hold the JWT claims. Claims are pieces of information asserted about the subject (user).

4. It then uses the Jwts.builder() method to start building the JWT. The builder pattern is used here for constructing a complex object step by step.
		4.1 .setClaims(claims): Sets the claims for the token. In this case, an empty map is used, but typically, you could add custom claims here.
		4.2 .setSubject(uds.getUsername()): Sets the subject of the token, usually the user identifier. Here, it's the username of the UserDetails object.
		4.3 .setIssuedAt(new Date(System.currentTimeMillis())): Sets the issuance time of the token to the current time.
		4.4 .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)): Sets the expiration time of the token.
		 		JWT_TOKEN_VALIDITY is multiplied by 1000 to convert it from seconds to milliseconds.
		4.5 .signWith(SignatureAlgorithm.HS512, secret): Specifies the signing algorithm (HS512 in this case) and the secret key to be used for signing the JWT.
		 		This is crucial for the verification process at the receiving end.
		4.6 .compact(): Finalizes the token construction and returns the compact URL-safe JWT string.
 
 5. Functional Programming: You pass a function into a method and will return a function.
 
 6.
 
 6.1 generateToken(UserDetails uds):
This method remains unchanged from the previous explanation.  It generates a JWT based on the provided UserDetails object, which contains user information like username.
The method sets the JWT's subject, issued at time, expiration time, and signs it with a specified secret and algorithm.

6.2 getUsernameFromToken(String jwtToken):
This method extracts the username from a JWT. It utilizes the getClaimFromToken helper method to do so, 
specifically fetching the subject claim, which, by convention, contains the username of the authenticated user.

6.3
private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver):
This is a private generic method designed to extract a specific claim from a given token. It uses Java 8's functional programming capabilities, accepting a JWT token and a Function that takes Claims and returns a type T.
The method first parses the token to extract all claims using Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody(). This parsing step requires the signing key (secret) that was used to sign the JWT to validate the signature.
The claimResolver function is then applied to the claims object to extract the specific claim needed. This approach makes the method very flexible, allowing any claim to be extracted from the token by passing the appropriate lambda expression.
 
 */










