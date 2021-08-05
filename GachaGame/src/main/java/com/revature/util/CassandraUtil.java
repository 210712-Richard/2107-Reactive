package com.revature.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

@Configuration
public class CassandraUtil {
	private static final Logger log = LogManager.getLogger(CassandraUtil.class);
	
	@Bean
	public CqlSession getSession() {
		CqlSession session = null;
		log.trace("Establishing connection with Cassandra");
		DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
		try {
			session = CqlSession.builder().withConfigLoader(loader).withKeyspace("gacha").build();
		} catch(Exception e) {
			log.error("Method threw exception: "+e);
			for(StackTraceElement s : e.getStackTrace()) {
				log.warn(s);
			}
			throw e;
		}
		return session;
	}
}
