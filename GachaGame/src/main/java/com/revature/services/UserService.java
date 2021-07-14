package com.revature.services;

import java.time.LocalDate;

import com.revature.beans.GachaObject;
import com.revature.beans.User;
import com.revature.data.UserDAO;

public class UserService {
	
	private UserDAO ud = new UserDAO();
	
	public User login(String name) {
		User u = ud.getUser(name);
		return u;
	}
	
	public void doCheckIn(User user) {
		user.setLastCheckIn(LocalDate.now());
		user.setCurrency(user.getCurrency() + GachaObject.DAILY_BONUS);
		ud.writeToFile();
	}
	
	public boolean hasCheckedIn(User user) {
		if(LocalDate.now().isAfter(user.getLastCheckIn())) {
			return false;
		}
		return true;
	}

}
