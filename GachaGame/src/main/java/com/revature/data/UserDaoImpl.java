package com.revature.data;

import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.beans.User;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class UserDaoImpl implements UserDao {
	private CqlSession session = CassandraUtil.getInstance().getSession();

	@Override
	public void addUser(User u) {
		StringBuilder sb = new StringBuilder("Insert into user ")
				.append("(username, type, email, currency, birthday, lastCheckIn)")
				.append(" values ('"+u.getUsername()+"', '"+u.getType()+"', '"
				+u.getEmail()+"', "+u.getCurrency()+", '"+u.getBirthday()
				+"', '"+u.getLastCheckIn()+"');");
		System.out.println(sb.toString());
		SimpleStatement s = new SimpleStatementBuilder(sb.toString())
				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		session.execute(s);
		
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

}
