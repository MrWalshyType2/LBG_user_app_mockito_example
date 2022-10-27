package com.qa.user_testing_app.utils;

import java.util.Scanner;

public class InputUtilities {

	private Scanner sc;
	
	public InputUtilities(Scanner sc) {
		this.sc = sc;
	}
	
	public int getInteger(String prompt) {
		do {
			System.out.print(prompt);
			try {
				return Integer.valueOf(sc.nextLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter a valid number.");
				System.out.print(prompt);
			}
		} while (true);
	}
	
	public String getString(String prompt) {
		System.out.print(prompt);
		return sc.nextLine();
	}
}
