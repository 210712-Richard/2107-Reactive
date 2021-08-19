package com.revature.services.reactive;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.User;
import com.revature.data.reactive.ReactiveOwnedGachaDao;
import com.revature.data.reactive.ReactiveUserDao;
import com.revature.dto.OwnedHistoricalCatDTO;
import com.revature.dto.UserDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class ReactiveUserService {
	private static Logger log = LogManager.getLogger(ReactiveUserService.class);

	private ReactiveUserDao ud;
	//private ReactiveGachaDao gachaDao;
	private ReactiveOwnedGachaDao ownedGachaDao;

	private Random r;

	@Autowired
	public ReactiveUserService(ReactiveUserDao ud,
			/* ReactiveGachaDao gachaDao, */ ReactiveOwnedGachaDao ownedGachaDao) {
		super();
		this.ud = ud;
		//this.gachaDao = gachaDao;
		this.ownedGachaDao = ownedGachaDao;
		this.r = new Random();
	}

	public Mono<User> login(String name) {
		
		Mono<User> userMono = ud.findById(name).map(user -> user.getUser());
		
//		Flux<UserDTO> userDto = Flux.from(ud.findById(name));
//		Flux<List<UUID>> ids = userDto.map(user -> user.getInventory());
//		Flux<UUID> monoFlux = ids.flatMap(list -> Flux.fromIterable(list));
//		Flux<OwnedHistoricalCatDTO> ownedCats = monoFlux.flatMap(uuid -> ownedGachaDao.findByUuid(uuid));
//		Flux<HistoricalCat> cats = ownedCats.map(owned -> owned.getCat());
//		Mono<List<HistoricalCat>> list = cats.collectList();
		
		Mono<List<HistoricalCat>> list = Flux.from(ud.findById(name))
				.map(user -> user.getInventory())
				.flatMap(l -> Flux.fromIterable(l))
				.flatMap(uuid -> ownedGachaDao.findByUuid(uuid))
				.map(owned -> owned.getCat())
				.collectList();
		
		Mono<Tuple2<List<HistoricalCat>,User>> bothThings = list.zipWith(userMono);
		Mono<User> returnUser = bothThings.map(tuple -> {
			User u = tuple.getT2();
			List<HistoricalCat> c = tuple.getT1();
			u.setInventory(c);
			return u;
		});
		
		
		return returnUser;
	}

	public void doCheckIn(User user) {
		UserDTO databaseUser = new UserDTO(user);
		databaseUser.setLastCheckIn(LocalDate.now());
		databaseUser.setCurrency(user.getCurrency() + GachaObject.DAILY_BONUS);
		ud.save(databaseUser);
	}

	public User register(String username, String email, LocalDate birthday) {
		User u = new User();
		u.setCurrency(1000l);
		u.setUsername(username);
		u.setEmail(email);
		u.setBirthday(birthday);
		ud.save(new UserDTO(u));
		return u;
	}

	public boolean hasCheckedIn(User user) {
		if (LocalDate.now().isAfter(user.getLastCheckIn())) {
			return false;
		}
		return true;
	}

	public boolean checkAvailability(String newName) {
		//UserDTO u = ud.findById(newName).orElse(null);
		return false;//u == null ? true : false;
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
	public void levelGacha(User user, HistoricalCat predator, HistoricalCat food) {
		// make sure the predator isn't max level
		if (GachaObject.MAXIMUM_LEVEL.equals(predator.getLevel())) {
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
	public GachaObject summon(User summoner) {
		HistoricalCat summonedObject = null;

		// 1. Verify user has enough currency
		if (summoner.getCurrency() < GachaObject.SUMMON_COST) {
			return null;
		}
		// 2. Determine what rarity the user gets
		Integer chance = r.nextInt(100);
		// 3. Obtain the object
//		List<HistoricalCatDTO> rarityObjects = gachaDao.findByRarity(Rarity.getRarity(chance));
//		Collections.shuffle(rarityObjects);
//		summonedObject = rarityObjects.get(0).getHistoricalCat();
//		summonedObject.setId(UUID.randomUUID());
//		ownedGachaDao.save(new OwnedHistoricalCatDTO(summonedObject));

		// 4. Update the user's currency
		summoner.setCurrency(summoner.getCurrency() - GachaObject.SUMMON_COST);

		summoner.getInventory().add(summonedObject);
		ud.save(new UserDTO(summoner));
		// 6. Saving
		return summonedObject;
	}

	public void updateUser(User user) {
		ud.save(new UserDTO(user));
	}

}
