package com.revature;

import java.time.LocalDate;

import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.UserDao;
import com.revature.data.UserDaoImpl;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDao ud = new UserDaoImpl();
	
	public static void createUserTable() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS User;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS User (")
				.append("username text PRIMARY KEY, type text, currency bigint, ")
				.append("birthday date, lastCheckIn date, email text, inventory list<int> );");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}
	
	public static void populateUserTable() {
		User u = new User("Richard","richard.orr@revature.com", LocalDate.of(1970, 10, 30), 2000l);
		u.setType(UserType.GAME_MASTER);
		ud.addUser(u);
	}
}
