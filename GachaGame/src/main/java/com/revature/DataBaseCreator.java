package com.revature;

import java.time.LocalDate;

import com.revature.beans.Ability;
import com.revature.beans.Attributes;
import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.GachaDao;
import com.revature.data.GachaDaoImpl;
import com.revature.data.UserDao;
import com.revature.data.UserDaoImpl;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDao ud = new UserDaoImpl();
	private static GachaDao gd = new GachaDaoImpl();
	
	public static void dropTables() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS User;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("DROP TABLE IF EXISTS Gacha;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("DROP TABLE IF EXISTS Owned_Gacha;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}
	
	public static void createTables() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS User (")
				.append("username text PRIMARY KEY, type text, currency bigint, ")
				.append("birthday date, lastCheckIn date, email text, inventory list<uuid> );");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS Gacha (")
				.append("rarity text, level int, id bigint, ability text,")
				.append(" name text, stats tuple<int, int, int>, pictureUrl text, primary key (rarity, name)); ");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS Owned_Gacha (")
				.append("rarity text, level int, id uuid, ability text,")
				.append(" name text, stats tuple<int, int, int>, pictureUrl text, primary key (id, rarity)); ");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}
	
	public static void populateUserTable() {
		User u = new User("Richard","richard.orr@revature.com", LocalDate.of(1960, 8, 30), 2000l);
		u.setType(UserType.GAME_MASTER);
		ud.addUser(u);
		ud.addUser(new User("Michael", "michael@michael.michael", LocalDate.of(1700, 5, 6), 1000l));
		ud.addUser(new User("Jaclyn", "jaclyn@jaclyn.jaclyn", LocalDate.of(1660, 4, 2), 1000l));
		ud.addUser(new User("Joshua", "one@josh.alltime", LocalDate.of(1984, 1, 25), 1000l));
		ud.addUser(new User("Stephen", "stephen@steven.steve", LocalDate.of(1880, 7, 23), 1000l));
	}

	public static void populateGachaTable() {
		GachaObject g = new HistoricalCat("Shakespeare", Rarity.ALLEY, "Poet");
		g.setStats(new Attributes(3, 1, 10));
		g.setAbility(Ability.POETRY);
		gd.addGacha(g);
		
		g = new HistoricalCat("Felix", Rarity.ALLEY, "Black");
		g.setStats(new Attributes(1, 1, 30));
		g.setAbility(Ability.HUNTER);
		gd.addGacha(g);
		
		g = new HistoricalCat("Tony", Rarity.FERAL, "Tiger");
		g.setStats(new Attributes(10, 10, 100));
		g.setAbility(Ability.FLAKES);
		gd.addGacha(g);
		
		g = new HistoricalCat("Napoleon", Rarity.ARISTOCAT, "Munchkin");
		g.setStats(new Attributes(9, 6, 80));
		g.setAbility(Ability.INVADE_RUSSIA_IN_THE_WINTER);
		gd.addGacha(g);
		
		g = new HistoricalCat("Grumpy", Rarity.HOUSE, "Grump");
		g.setStats(new Attributes(4, 6, 50));
		g.setAbility(Ability.HUNTER);
		gd.addGacha(g);
		
		g = new HistoricalCat("Peasant", Rarity.ALLEY, "Mongrel");
		g.setStats(new Attributes(1, 3, 10));
		g.setAbility(Ability.FARM);
		gd.addGacha(g);
		
		g = new HistoricalCat("Tom", Rarity.HOUSE, "Tomcat");
		g.setStats(new Attributes(4, 4, 80));
		g.setAbility(Ability.HUNTER);
		gd.addGacha(g);
		
	}
}
