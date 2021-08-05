package com.revature.data;

import java.util.List;

import com.revature.beans.User;

public interface UserDAO {

	void addUser(User u);

	List<User> getUsers();

	User getUser(String username);

	void updateUser(User user);

}