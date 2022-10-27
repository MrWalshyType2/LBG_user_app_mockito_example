package com.qa.user_testing_app.controller;

import java.util.List;

import com.qa.user_testing_app.domain.User;
import com.qa.user_testing_app.domain.UserRepository;
import com.qa.user_testing_app.utils.InputUtilities;

public class UserController {

	private InputUtilities inputUtils;
	private UserRepository repo;
	
	public UserController(InputUtilities inputUtils, UserRepository repo) {
		this.inputUtils = inputUtils;
		this.repo = repo;
	}
	
	public User createUser() {
		String forename = inputUtils.getString("Forename: ");
		String surname = inputUtils.getString("Surname: ");
		int age = inputUtils.getInteger("Age: ");
		return repo.save(new User(forename, surname, age));
	}
	
	public List<User> readAll() {
		return repo.findAll();
	}
}
