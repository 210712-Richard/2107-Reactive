package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.revature.beans.GachaObject;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.UserDao;

public class UserServiceTest {
	private static UserServiceImpl service;
	private static User u;
	
	@BeforeAll // Specifies that this static method will be run before any tests
	public static void setUpClass() {
		u = new User(); // New users start with a date of 1/1/21
		u.setUsername("Test");
	}
	
	@BeforeEach // Specifies a method that is to be run before each test
	public void setUpTests() {
		service = new UserServiceImpl(); // create a new userService for every test to maximize isolation
		u.setLastCheckIn(LocalDate.of(2021, 1,1));
		u.setCurrency(500l);
		service.ud = Mockito.mock(UserDao.class);
	}
	
	@Test
	public void testRegister() {
		String username = "test";
		String email = "test@test.test";
		LocalDate date = LocalDate.of(2020, 2, 2);
		service.register(username, email, date);
		
		// An object that allows us to receive parameters from methods called on a Mockito mock object
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

		// ud.addUser() was called with our User as an argument.
		Mockito.verify(service.ud).addUser(captor.capture());

		// ud.writeToFile() was called.
		//Mockito.verify(service.ud).writeToFile();
		
		// A user is created with the given arguments
		// That user is of type Player
		// That user has a starting Currency of 1000
		User u = captor.getValue();
		assertEquals(1000l, u.getCurrency(), "Asserting starting currency is 1000");
		assertEquals(UserType.PLAYER, u.getType(), "Asserting it is a Player");
		assertEquals(username, u.getUsername(), "Asserting username is correct");
		assertEquals(email, u.getEmail(), "Asserting email is correct");
		assertEquals(date, u.getBirthday(), "Asserting birthday is correct");
	}
	
	@Test
	public void testDoCheckIn() {
		// We must make sure that 3 things happen:
		Long startingCurrency = u.getCurrency();
		assertNotEquals(LocalDate.now(), u.getLastCheckIn(), "Asserting that last check is NOT today before the call");
		service.doCheckIn(u);

		// 1. checkin-date is now today
		assertEquals(LocalDate.now(), u.getLastCheckIn(), "Checking to see if the date is today.");
		
		// 2. currency is now higher
		assertEquals(startingCurrency + GachaObject.DAILY_BONUS, u.getCurrency(), "Asserting that currency is correct");

		// 3. writeToFile was called.
		//Mockito.verify(service.ud).writeToFile();
		
	}

	@Test //Specifies that this a Unit test that JUnit should call.
	public void testHasCheckedInThrowsException() {
		// in most testing frameworks you check values by writing an assert statement.
		// An assert will throw an error if the values you expect are not present.
		assertThrows(NullPointerException.class, () -> { service.hasCheckedIn(null); });
	
	}
	@Test
	public void testHasCheckedInReturnsFalse() {
		Boolean hasCheckedIn = service.hasCheckedIn(u);
		assertFalse(hasCheckedIn);
		LocalDate yesterday = LocalDate.now().minus(Period.of(0, 0, 1));
		u.setLastCheckIn(yesterday);
		assertFalse(service.hasCheckedIn(u));
	}
	
	@Test
	public void testHasCheckedInReturnsTrue() {
		u.setLastCheckIn(LocalDate.now());
		assertTrue(service.hasCheckedIn(u));
	}
}
