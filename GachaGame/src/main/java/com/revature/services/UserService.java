package com.revature.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.GachaObject;
import com.revature.beans.Rarity;
import com.revature.beans.User;
import com.revature.data.GachaDAO;
import com.revature.data.UserDAO;

public class UserService {
	private Logger log = LogManager.getLogger(UserService.class);
	public UserDAO ud = new UserDAO();
	public GachaDAO gachaDao = new GachaDAO();
	private Random r = new Random();
	
	public User login(String name) {
		User u = ud.getUser(name);
		return u;
	}
	
	public void doCheckIn(User user) {
		user.setLastCheckIn(LocalDate.now());
		user.setCurrency(user.getCurrency() + GachaObject.DAILY_BONUS);
		ud.writeToFile();
	}
	
	public User register(String username, String email, LocalDate birthday) {
		User u = new User();
		u.setCurrency(1000l);
		u.setUsername(username);
		u.setEmail(email);
		u.setBirthday(birthday);
		ud.addUser(u);
		ud.writeToFile();
		return u;
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
	
	// level up a gacha that a user owns
	public void levelGacha(User user, GachaObject predator, GachaObject food) {
		predator.levelUp();
		user.getInventory().remove(food);
		ud.writeToFile();
	}
	
	// summon a gacha
	public GachaObject summon(User summoner) {
		GachaObject summonedObject = null;
		
		// 1. Verify user has enough currency
		if(summoner.getCurrency() < GachaObject.SUMMON_COST) {
			return null;
		}
		// 2. Determine what rarity the user gets
		Integer chance = r.nextInt(100);
		// 3. Obtain the object
		List<GachaObject> rarityObjects = gachaDao.getGachasByRarity(Rarity.getRarity(chance));
		Collections.shuffle(rarityObjects);
		summonedObject = rarityObjects.get(0);
		// 4. Update the user's currency
		summoner.setCurrency(summoner.getCurrency() - GachaObject.SUMMON_COST);
		// 5. Add the object to the inventory
		summonedObject.setId((long) summoner.getInventory().size());
		summoner.getInventory().add(summonedObject);
		// 6. Saving
		ud.writeToFile();
		return summonedObject;
	}

}
