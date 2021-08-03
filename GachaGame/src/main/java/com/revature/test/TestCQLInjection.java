package com.revature.test;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.revature.util.CassandraUtil;

public class TestCQLInjection {
	public static CqlSession session = CassandraUtil.getInstance().getSession();
	public static void main(String[] args) {
		String name = "Richard'/*";
		String password = "*/and password>'";
		
		String secret = getUserBound(name, password);
		System.out.println(secret);
		System.exit(0);
	}
	private static String getUserInjection(String name, String password) {
		String query = "select secret from test where name='"+name+"' AND password = '"+password+"' allow filtering;";
		SimpleStatement s = SimpleStatement.builder(query).build();
		ResultSet rows = session.execute(s);
		System.out.println("Executed statement");
		Row row = rows.one();
		if(row == null) {
			return null;
		}
		return row.getString("secret");
	}
	
	private static String getUserBound(String name, String password) {
		String query = "select secret from test where name=? AND password = ? allow filtering;";
		SimpleStatement s = SimpleStatement.builder(query).build();
		BoundStatement b = session.prepare(s).bind(name, password);
		ResultSet rows = session.execute(b);
		System.out.println("Executed statement");
		Row row = rows.one();
		if(row == null) {
			return null;
		}
		return row.getString("secret");
	}
}
