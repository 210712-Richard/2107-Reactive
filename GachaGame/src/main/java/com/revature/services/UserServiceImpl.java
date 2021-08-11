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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;
import com.revature.beans.User;
import com.revature.data.GachaDao;
import com.revature.data.OwnedGachaDao;
import com.revature.data.UserDao;
import com.revature.dto.HistoricalCatDTO;
import com.revature.dto.OwnedHistoricalCatDTO;
import com.revature.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	private static Logger log = LogManager.getLogger(UserServiceImpl.class);
	
	private UserDao ud;
	private GachaDao gachaDao;
	private OwnedGachaDao ownedGachaDao;
	
	private Random r;
	
	@Autowired
	public UserServiceImpl(UserDao ud, GachaDao gachaDao, OwnedGachaDao ownedGachaDao) {
		super();
		this.ud = ud;
		this.gachaDao = gachaDao;
		this.ownedGachaDao = ownedGachaDao;
		this.r = new Random();
	}

	@Override
	public User login(String name) {
		UserDTO databaseUser = ud.findById(name).orElse(null);
		
		List<GachaObject> inventory = databaseUser.getInventory().stream()
				.map(id -> ownedGachaDao.findByUuid(id).get().getCat())
				.collect(Collectors.toList());
		User user = databaseUser.getUser();
		user.setInventory(inventory);
		return user;
	}
	
	@Override
	public void doCheckIn(User user) {
		UserDTO databaseUser = new UserDTO(user);
		databaseUser.setLastCheckIn(LocalDate.now());
		databaseUser.setCurrency(user.getCurrency() + GachaObject.DAILY_BONUS);
		ud.save(databaseUser);
	}
	
	@Override
	public User register(String username, String email, LocalDate birthday) {
		User u = new User();
		u.setCurrency(1000l);
		u.setUsername(username);
		u.setEmail(email);
		u.setBirthday(birthday);
		ud.save(new UserDTO(u));
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
		UserDTO u = ud.findById(newName).orElse(null);
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
	public void levelGacha(User user, HistoricalCat predator, HistoricalCat food) {
		// make sure the predator isn't max level
		if(GachaObject.MAXIMUM_LEVEL.equals(predator.getLevel())){
			return;
		}
		predator.levelUp();
		// verify the food exists
		// verify the food isn't the predator
		user.getInventory().remove(food);
		
		// delete a cat
		ownedGachaDao.delete(new OwnedHistoricalCatDTO(food));
		// save a cat
		ownedGachaDao.save(new OwnedHistoricalCatDTO(predator));
		// save a user
		log.debug(user);
		ud.save(new UserDTO(user));
	}
	
	// summon a gacha
	@Override
	public GachaObject summon(User summoner) {
		HistoricalCat summonedObject = null;
		
		// 1. Verify user has enough currency
		if(summoner.getCurrency() < GachaObject.SUMMON_COST) {
			return null;
		}
		// 2. Determine what rarity the user gets
		Integer chance = r.nextInt(100);
		// 3. Obtain the object
		List<HistoricalCatDTO> rarityObjects = gachaDao.findByRarity(Rarity.getRarity(chance));
		Collections.shuffle(rarityObjects);
		summonedObject = rarityObjects.get(0).getHistoricalCat();
		summonedObject.setId(UUID.randomUUID());
		ownedGachaDao.save(new OwnedHistoricalCatDTO(summonedObject));
		
		// 4. Update the user's currency
		summoner.setCurrency(summoner.getCurrency() - GachaObject.SUMMON_COST);
		
		summoner.getInventory().add(summonedObject);
		ud.save(new UserDTO(summoner));
		// 6. Saving
		return summonedObject;
	}

	@Override
	public void updateUser(User user) {
		ud.save(new UserDTO(user));
	}

}
