package com.bridgeit.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeit.model.User;
import com.bridgeit.service.IUserService;
import com.bridgeit.util.EmailAlreadyExistException;
import com.bridgeit.util.Response;

@RestController
public class UserController {
	
	@Autowired
	private IUserService userservice;
   
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user,HttpServletRequest request)throws EmailAlreadyExistException
	{
		System.out.println(user.getEmail()); 
		if(userservice.isEmailIdPresent(user.getEmail()))
			{
				 	System.out.println("email already exists");	
				 	return new  ResponseEntity<>("User Already  Registered ",HttpStatus.CONFLICT);
			}
		
				if(userservice.addUser(user,request)==null)
				{
					return new ResponseEntity<>("Registration failed",HttpStatus.CONFLICT);
				}
				else {
					return new  ResponseEntity<>("User Registered Successfully",HttpStatus.OK);
				}
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody User user,HttpServletRequest request, HttpServletResponse response ){		
		System.out.println("comes under login method");
		Response res=new Response();
		user.setEnabled(false);
		String token=userservice.validateUser(user);
		
		if(token!=null)
		{
			System.out.println("logged In successsfully");
			System.out.println("your token generated is" +token);
			
			response.setHeader("Authorization", token);
			response.setStatus(200);
			res.setMsg("login Successful");	
			return new ResponseEntity<String>("Login succesful",HttpStatus.OK);
		}
		
		
		return new ResponseEntity<String>("Invalid username or password",HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value="/tokenvalue/{token}",method=RequestMethod.GET)
	public ResponseEntity<?> token(@PathVariable("token") String token){
		System.out.println("user clicks the link");
		return new ResponseEntity<>(token,HttpStatus.OK);
	}
	
	
}