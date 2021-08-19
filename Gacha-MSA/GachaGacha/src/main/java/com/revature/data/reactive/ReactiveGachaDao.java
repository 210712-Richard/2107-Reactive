package com.revature.data.reactive;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Rarity;
import com.revature.dto.HistoricalCatDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveGachaDao extends ReactiveCassandraRepository<HistoricalCatDTO, String>{
	Flux<HistoricalCatDTO> findByRarity(Rarity rarity);
	Mono<HistoricalCatDTO> findByRarityAndName(Rarity rarity, String gachaName);
}