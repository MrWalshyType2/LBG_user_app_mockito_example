package com.qa.user_testing_app.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.user_testing_app.domain.User;
import com.qa.user_testing_app.domain.UserRepository;
import com.qa.user_testing_app.utils.InputUtilities;

// use @ExtendWith to extend the behaviour of the JUnit 5 runner
@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest {

	// @Mock indicates we want a fake object rather than the real thing
	// - we will stub the return values of the methods that they contain
	@Mock
	InputUtilities inputUtils;
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks // This will handle creating an instance of the controller with
	// the fake objects injected into the constructor
	UserController controller;
	
	@Test
	void validCreateUserTest() {
		// Arrange
		User expectedUser = new User(1, "Fred", "O", 30);
		User toSave = new User("Fred", "O", 30);
		
		// We need to stub what is returned by the inputUtils methods
		// and the userRepository's methods
		Mockito.when(inputUtils.getString("Forename: "))
		       .thenReturn(expectedUser.getForename());
		Mockito.when(inputUtils.getString("Surname: "))
	       .thenReturn(expectedUser.getSurname());
		Mockito.when(inputUtils.getInteger("Age: "))
	       .thenReturn(expectedUser.getAge());
		
		Mockito.when(userRepository.save(toSave)).thenReturn(expectedUser);
		
		// Act
		User actualUser = controller.createUser();
		
		// Assertions
		Assertions.assertEquals(expectedUser, actualUser);
		// - verifications that the stubbed method calls where in fact called
		Mockito.verify(inputUtils, Mockito.times(1)).getString("Forename: ");
		Mockito.verify(inputUtils).getString("Surname: ");
		Mockito.verify(inputUtils).getInteger("Age: ");
		
		Mockito.verify(userRepository).save(toSave);
	}
}
