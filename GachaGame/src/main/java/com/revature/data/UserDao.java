package com.revature.data;

import java.util.List;

import com.revature.beans.User;

public interface UserDao {

	void addUser(User u);

	List<User> getUsers();

	User getUser(String username);

	void updateUser(User user);

}