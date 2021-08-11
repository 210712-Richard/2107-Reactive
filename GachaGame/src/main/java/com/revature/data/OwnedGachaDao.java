package com.revature.data;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.dto.OwnedHistoricalCatDTO;

@Repository
public interface OwnedGachaDao extends CassandraRepository<OwnedHistoricalCatDTO, UUID>{
	Optional<OwnedHistoricalCatDTO> findByUuid(UUID id);
}
