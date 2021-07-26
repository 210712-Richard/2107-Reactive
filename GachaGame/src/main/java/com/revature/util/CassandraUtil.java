package com.revature.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

public class CassandraUtil {
	private static CassandraUtil instance = null;
	private static final Logger log = LogManager.getLogger(CassandraUtil.class);
	
	// the object that represents the actual connection to our database.
	private CqlSession session = null;
	
	private CassandraUtil() {
		log.trace("Establishing connection with Cassandra");
		DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
		try {
			this.session = CqlSession.builder().withConfigLoader(loader).withKeyspace("gacha").build();
		} catch(Exception e) {
			log.error("Method threw exception: "+e);
			for(StackTraceElement s : e.getStackTrace()) {
				log.warn(s);
			}
			throw e;
		}
	}
	
	public static synchronized CassandraUtil getInstance() {
		if(instance == null) {
			instance = new CassandraUtil();
		}
		return instance;
	}
	
	public CqlSession getSession() {
		return session;
	}
}
