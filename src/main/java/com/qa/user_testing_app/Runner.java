package com.qa.user_testing_app;

import java.util.Scanner;

import com.qa.user_testing_app.controller.UserController;
import com.qa.user_testing_app.domain.UserRepository;
import com.qa.user_testing_app.utils.ConnectionUtilities;
import com.qa.user_testing_app.utils.InputUtilities;

public class Runner {
	
	public static void main(String[] args) {
		String userSql = "CREATE TABLE IF NOT EXISTS `user` ("
				+ "   `id` BIGINT AUTO_INCREMENT,"
				+ "   `forename` VARCHAR(128) NOT NULL,"
				+ "   `surname` VARCHAR(128) NOT NULL,"
				+ "   `age` TINYINT,"
				+ "   PRIMARY KEY (`id`)"
				+ ");"
				+ ""
				+ "INSERT INTO `user` (`forename`, `surname`, `age`) "
				+ "VALUES ('Fred', 'Smith', 32), ('Aiden', 'Walker', 27), ('Sarah', 'Hills', 2);";
		ConnectionUtilities.initialiseDatabase(userSql);
		
		InputUtilities utils = new InputUtilities(new Scanner(System.in));
		App app = new App(utils, new UserController(utils, new UserRepository()));
		app.run();
	}
	
	

}
