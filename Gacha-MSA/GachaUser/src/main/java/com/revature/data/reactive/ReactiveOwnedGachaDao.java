package com.revature.data.reactive;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.OwnedHistoricalCatDTO;

import reactor.core.publisher.Mono;

@Repository
public interface ReactiveOwnedGachaDao extends ReactiveCassandraRepository<OwnedHistoricalCatDTO, UUID>{
	Mono<OwnedHistoricalCatDTO> findByUuid(UUID id);
}
