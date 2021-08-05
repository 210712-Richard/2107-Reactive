package com.revature.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.UserType;

public class UserDAO {
	// DAO = Database Access Object
	// This is a class that is dedicated to accessing data from persistence.
	private static String filename = "users.dat";
	private static List<User> users;
	
	static {
		DataSerializer<User> ds = new DataSerializer<User>();
		users = ds.readObjectsFromFile(filename);
		
		// Helper for myself. If no users exist in the users.dat file (first startup) than I should create a few
		if(users == null) {
			users = new ArrayList<User>();
			users.add(new User(users.size(), "Alby", "alby@alby.alby", LocalDate.of(1900, 1, 1), 1000l));
			users.add(new User(users.size(), "William", "will@will.will", LocalDate.of(1950, 5, 1), 3000l));
			users.add(new User(users.size(), "Jaclyn", "jaclyn@jaclyn.jaclyn", LocalDate.of(2021, 1, 1), 2000l));
			User u = new User(users.size(), "richard", "richard.orr@revature.com", LocalDate.of(1900, 1, 1), 1000l);
			u.setType(UserType.GAME_MASTER);
			users.add(u);
			ds.writeObjectsToFile(users, filename);
		}
	}
	public void addUser(User u) {
		u.setId(users.size());
		users.add(u);
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public User getUser(String username) {
		
//		for(User user : users) {
//			if(user.getUsername().equals(username)) {
//				return user;
//			}
//		}
//		
//		return null;
		return users.stream()
			.filter((u) -> u.getUsername().equals(username))
			.findFirst()
			.orElse(null);
	}
	
	public void updateUser(User user) {
		// due to us holding the entire list in memory, we will actually automatically update the user
		// in the list anytime we change the fields of the user object.
		// I'll leave this method as a placeholder for our Week 3 Database integration.
	}
	
	public void writeToFile() {
		new DataSerializer<User>().writeObjectsToFile(users, filename);
	}

}
