package com.revature.services;

import java.time.LocalDate;
import java.time.Period;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.GachaObject;
import com.revature.beans.User;
import com.revature.data.UserDAO;

public class UserService {
	private Logger log = LogManager.getLogger(UserService.class);
	public UserDAO ud = new UserDAO();
	
	public User login(String name) {
		User u = ud.getUser(name);
		return u;
	}
	
	public void doCheckIn(User user) {
		user.setLastCheckIn(LocalDate.now());
		user.setCurrency(user.getCurrency() + GachaObject.DAILY_BONUS);
		ud.writeToFile();
	}
	
	public void register(String username, String email, LocalDate birthday) {
		User u = new User();
		u.setCurrency(1000l);
		u.setUsername(username);
		u.setEmail(email);
		u.setBirthday(birthday);
		ud.addUser(u);
		ud.writeToFile();
	}
	
	public boolean hasCheckedIn(User user) {
		if(LocalDate.now().isAfter(user.getLastCheckIn())) {
			return false;
		}
		return true;
	}

	public boolean checkAvailability(String newName) {
		return ud.getUsers()
				.stream()
				.noneMatch((u)->u.getUsername().equals(newName));
//		for(User u: ud.getUsers()) {
//			if(u.getUsername().equals(newName))
//				return false;
//		}
//		return true;
	}

	public boolean checkBirthday(LocalDate birth) {
		LocalDate now = LocalDate.now();
		LocalDate sixteenYearsAgo = now.minus(Period.of(16, 0, 0));
		log.debug(sixteenYearsAgo);
		sixteenYearsAgo = sixteenYearsAgo.plus(Period.of(0, 0, 1));
		log.debug(sixteenYearsAgo);
		return birth.isBefore(sixteenYearsAgo);
	}

}
