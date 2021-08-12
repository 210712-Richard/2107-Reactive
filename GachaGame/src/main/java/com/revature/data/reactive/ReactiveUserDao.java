package com.revature.data.reactive;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.UserDTO;

@Repository
public interface ReactiveUserDao extends ReactiveCassandraRepository<UserDTO, String>{

}
