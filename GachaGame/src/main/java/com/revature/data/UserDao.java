package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.User;

public interface UserDao {

	void addUser(User u);

	List<User> getUsers();

	User getUser(String username);

	void updateUser(User user);
	
	List<UUID> getUserInventory(String username);

}