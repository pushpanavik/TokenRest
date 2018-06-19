package com.bridgeit.interceptor;
import java.util.regex.Pattern;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Validation extends HandlerInterceptorAdapter{
	
	private static String emailValidation = "[a-z0-9+_.-]+@{1}[a-z](.+){1}[a-z]";
	
	
		public static String userValidation(String email) {
			
			if(!Pattern.matches(emailValidation, email)) {
			String	msg="enter email correct";
			System.out.println("Invalid user email");
				
			}
			return email;
		}	
				
		}
		
				