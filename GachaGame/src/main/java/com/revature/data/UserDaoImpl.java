package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class UserDaoImpl implements UserDao {
	private CqlSession session = CassandraUtil.getInstance().getSession();

	@Override
	public void addUser(User u) {
		// SimpleStatement leaves us vulnerable to CQL injection and also it's hard to read and write.
//		StringBuilder sb = new StringBuilder("Insert into user ")
//				.append("(username, type, email, currency, birthday, lastCheckIn)")
//				.append(" values ('"+u.getUsername()+"', '"+u.getType()+"', '"
//				+u.getEmail()+"', "+u.getCurrency()+", '"+u.getBirthday()
//				+"', '"+u.getLastCheckIn()+"');");
//		System.out.println(sb.toString());
//		SimpleStatement s = new SimpleStatementBuilder(sb.toString())
//				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		
		// Bound Statement will guard against CQL injection and is just a lot nicer
		String query = "Insert into user (username, type, email, currency, birthday, lastCheckIn) values (?, ?, ?, ?, ?, ?);";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getUsername(), u.getType().toString(), u.getEmail(), u.getCurrency(), u.getBirthday(), u.getLastCheckIn());
		session.execute(bound);
	}

	@Override
	public List<User> getUsers() {
		String query = "Select username, type, email, currency, birthday, lastCheckIn from user";
		// This query will not be particularly efficient as it needs to query every partition.
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		ResultSet rs = session.execute(s);
		List<User> users = new ArrayList<>();
		rs.forEach(row -> {
			User u = new User();
			u.setUsername(row.getString("username"));
			u.setEmail(row.getString("email"));
			u.setCurrency(row.getLong("currency"));
			u.setType(UserType.valueOf(row.getString("type")));
			u.setBirthday(row.getLocalDate("birthday"));
			u.setLastCheckIn(row.getLocalDate("lastcheckin"));
			
			users.add(u);
		});
		return users;
	}

	@Override
	public User getUser(String username) {

		String query = "Select username, type, email, currency, birthday, lastCheckIn from user where username=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(username);
		// ResultSet is the values returned by my query.
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			// if there is no return values
			return null;
		}
		User u = new User();
		u.setUsername(row.getString("username"));
		u.setEmail(row.getString("email"));
		u.setCurrency(row.getLong("currency"));
		u.setType(UserType.valueOf(row.getString("type")));
		u.setBirthday(row.getLocalDate("birthday"));
		u.setLastCheckIn(row.getLocalDate("lastcheckin"));
//		row = rs.one();
//		if(row != null) {
//			throw new RuntimeException("More than one user with same username");
//		}
		return u;
	}

	@Override
	public void updateUser(User u) {
		String query = "Update user set type = ?, email = ?, currency = ?, birthday = ?, lastCheckIn = ?, inventory = ? where username = ?;";
		List<UUID> inventory = u.getInventory().stream().map(g -> g.getId()).collect(Collectors.toList());
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getType().toString(), u.getEmail(), u.getCurrency(), u.getBirthday(), u.getLastCheckIn(), inventory, u.getUsername());
		session.execute(bound);
	}

	@Override
	public List<UUID> getUserInventory(String username) {
		String query = "Select inventory from user where username=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(username);
		// ResultSet is the values returned by my query.
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			// if there is no return values
			return null;
		}
		List<UUID> inventory = row.getList("inventory", UUID.class);
		return inventory;
	}

}
