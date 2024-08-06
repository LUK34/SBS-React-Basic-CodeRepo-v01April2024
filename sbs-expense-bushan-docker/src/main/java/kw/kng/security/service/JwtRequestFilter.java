package kw.kng.security.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import kw.kng.utils.JwtTokenUtil;

public class JwtRequestFilter extends OncePerRequestFilter 
{

	@Autowired
	private JwtTokenUtil jwtTokenUtil; //we are accesing the method through a functional program created inside this class.
	
	@Autowired
	private CustomUserDetailsServiceImpl cuds;
	
	/* ==========================================================  JWT TOKEN Filtration Method START    ========================================================== */
	
	@Override //we are overriding the doFilterInternal method from OncePerRequestFilter Interface
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
			final String requestTokenHeader = request.getHeader("Authorization");
			String jwtToken = null;
			String userName= null;
			
			/*
			  When it comes to the control flow of the  spring security in this application.
			   At the very first time the control flow  will go to the authentication filter. The authentication filter is nothing but a series of filter.
			   
			 */
			
			
			 // ----------------------------------------  1. Taking token content from Bearer (After 7th Character) START ---------------------------------------- 
			if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) // Bearer has 6 + 1 white space character
			{
					jwtToken= requestTokenHeader.substring(7); 
					// We start extracting jwtToken contents after the 7th Character. and pass the token to `getUsernameFromToken` method
					try 
					{
						userName= jwtTokenUtil.getUsernameFromToken(jwtToken);
					}catch(IllegalArgumentException e){
						throw new RuntimeException("Unable to get JWT token.");
					}catch(ExpiredJwtException e) {
						throw new RuntimeException("JWT Token has expired.");
					}
			}
			// ----------------------------------------  1. Taking token content from Bearer (After 7th Character) END ---------------------------------------- 
			
			
			
			//----------------------------------------  2. Once we get the token we need to  validate the token START ---------------------------------------- 
			if( userName != null  && SecurityContextHolder.getContext().getAuthentication() == null)
			{
					UserDetails ud= cuds.loadUserByUsername(userName); //here deep inside the method we are actually passing the email as user name.
					if(jwtTokenUtil.validateToken(jwtToken, ud))
					{
						UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
						authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
			}
			//---------------------------------------- 2.  Once we get the token we need to  validate the token END ---------------------------------------- 
			
			/*
			  When it comes to the control flow of the  spring security in this application.
			   At the very first time the control flow  will go to the authentication filter. The authentication filter is nothing but a series of filter.   
			 */
			
			//---------------------------------------- 3. After 1 and 2 we need to continue with the filter START ---------------------------------------- 
			
				filterChain.doFilter(request, response);
			
			//---------------------------------------- 3. After 1 and 2 we need to continue with the filter END ---------------------------------------- 
			
			

	}
	/* ==========================================================  JWT TOKEN Filtration Method END    ========================================================== */
}
