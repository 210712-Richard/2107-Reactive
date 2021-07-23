package com.revature.services;

import java.time.LocalDate;

import com.revature.beans.GachaObject;
import com.revature.beans.User;

public interface UserService {

	User login(String name);

	void doCheckIn(User user);

	User register(String username, String email, LocalDate birthday);

	boolean hasCheckedIn(User user);

	boolean checkAvailability(String newName);

	boolean checkBirthday(LocalDate birth);

	// level up a gacha that a user owns
	void levelGacha(User user, GachaObject predator, GachaObject food);

	// summon a gacha
	GachaObject summon(User summoner);

}