package com.qa.user_testing_app;

import java.util.List;

import com.qa.user_testing_app.controller.UserController;
import com.qa.user_testing_app.domain.User;
import com.qa.user_testing_app.utils.InputUtilities;

public class App {

	private InputUtilities inputUtilities;
	private UserController controller;
	
	public App(InputUtilities inputUtilities, UserController userController) {
		this.inputUtilities = inputUtilities;
		this.controller = userController;
	}
	
	public void run() {
		boolean isRunning = true;
		
		do {
			printMenu();
			isRunning = executeInput(inputUtilities.getString("> "));
		} while (isRunning);
		System.out.println("Goodbye...");
	}

	private boolean executeInput(String input) {
		boolean output = true;
		switch (input.toUpperCase()) {
		case "C":
			User newUser = controller.createUser();
			if (newUser != null) {
				System.out.println("Created a new user");
				System.out.println(newUser);
				return true;
			} else {
				System.out.println("Something went wrong creating the user!");
			}
			break;
		case "R":
			List<User> users = controller.readAll();
			for (User user : users) {
				System.out.println(user);
			}
			break;
		case "E":
			output = false;
			break;
		default:
			System.out.println("Invalid input supplied, please try again.");
		}
		return output;
	}

	private void printMenu() {
		System.out.println("C) Create a new user");
		System.out.println("R) Display all users");
		System.out.println("E) Exit");
	}
}
