package com.revature.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.GachaObject;
import com.revature.beans.Rarity;
import com.revature.beans.User;
import com.revature.data.GachaDao;
import com.revature.data.OwnedGachaDao;
import com.revature.data.OwnedGachaDaoImpl;
import com.revature.data.UserDao;

@Service
public class UserServiceImpl implements UserService {
	private Logger log = LogManager.getLogger(UserServiceImpl.class);
	@Autowired
	public UserDao ud;
	@Autowired
	public GachaDao gachaDao;
	@Autowired
	public OwnedGachaDao ownedGachaDao;
	
	private Random r = new Random();
	
	@Override
	public User login(String name) {
		User u = ud.getUser(name);
		// TODO: Make this more reactive
		List<UUID> inventoryIds = ud.getUserInventory(name);
		
		List<GachaObject> inventory = inventoryIds.stream()
				.map(id -> ownedGachaDao.getGachaById(id))
				.collect(Collectors.toList());
		u.setInventory(inventory);
		return u;
	}
	
	@Override
	public void doCheckIn(User user) {
		user.setLastCheckIn(LocalDate.now());
		user.setCurrency(user.getCurrency() + GachaObject.DAILY_BONUS);
		ud.updateUser(user);
	}
	
	@Override
	public User register(String username, String email, LocalDate birthday) {
		User u = new User();
		u.setCurrency(1000l);
		u.setUsername(username);
		u.setEmail(email);
		u.setBirthday(birthday);
		ud.addUser(u);
		return u;
	}
	
	@Override
	public boolean hasCheckedIn(User user) {
		if(LocalDate.now().isAfter(user.getLastCheckIn())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean checkAvailability(String newName) {
		User u = ud.getUser(newName);
		return u==null ? true : false;
	}

	@Override
	public boolean checkBirthday(LocalDate birth) {
		LocalDate now = LocalDate.now();
		LocalDate sixteenYearsAgo = now.minus(Period.of(16, 0, 0));
		log.debug(sixteenYearsAgo);
		sixteenYearsAgo = sixteenYearsAgo.plus(Period.of(0, 0, 1));
		log.debug(sixteenYearsAgo);
		return birth.isBefore(sixteenYearsAgo);
	}
	
	// level up a gacha that a user owns
	@Override
	public void levelGacha(User user, GachaObject predator, GachaObject food) {
		// make sure the predator isn't max level
		if(GachaObject.MAXIMUM_LEVEL.equals(predator.getLevel())){
			return;
		}
		predator.levelUp();
		// verify the food exists
		// verify the food isn't the predator
		user.getInventory().remove(food);
		
		// delete a cat
		ownedGachaDao.deleteGacha(food);
		// save a cat
		ownedGachaDao.updateGacha(predator);
		// save a user
		log.debug(user);
		ud.updateUser(user);
	}
	
	// summon a gacha
	@Override
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
		summonedObject.setId(ownedGachaDao.addGacha(summonedObject));
		
		// 4. Update the user's currency
		summoner.setCurrency(summoner.getCurrency() - GachaObject.SUMMON_COST);
		
		summoner.getInventory().add(summonedObject);
		ud.updateUser(summoner);
		// 6. Saving
		return summonedObject;
	}

	@Override
	public void updateUser(User user) {
		ud.updateUser(user);
	}

}
