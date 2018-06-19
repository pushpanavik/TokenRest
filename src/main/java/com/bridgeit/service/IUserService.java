package com.bridgeit.service;

import javax.servlet.http.HttpServletRequest;

import com.bridgeit.model.User;

public interface IUserService {
	public String addUser(User user, HttpServletRequest req);
	public String validateUser(User user);
	/*public Register getUserByEmaiId(String email);
	public Register getUserById(int id);*/
	boolean isEmailIdPresent(String string);
}

