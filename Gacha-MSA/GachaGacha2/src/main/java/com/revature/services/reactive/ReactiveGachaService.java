package com.revature.services.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;
import com.revature.data.reactive.ReactiveGachaDao;
import com.revature.dto.HistoricalCatDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveGachaService {
	private ReactiveGachaDao gachaDao;

	@Autowired
	public ReactiveGachaService(ReactiveGachaDao gachaDao) {
		super();
		this.gachaDao = gachaDao;
	}
	// perform operations on Gachas
	
	// create a new gacha for the pool
	public Mono<HistoricalCat> createGacha(HistoricalCat gacha) {
		return gachaDao.save(new HistoricalCatDTO(gacha)).map((dto) -> dto.getHistoricalCat());
	}
	// edit an existing gacha in the pool
	public Mono<HistoricalCat> updateGacha(HistoricalCat gacha) {
		return gachaDao.save(new HistoricalCatDTO(gacha)).map((dto) -> dto.getHistoricalCat());
	}
	public Mono<HistoricalCat> getGacha(Rarity rarity, String gachaName) {
		return gachaDao.findByRarityAndName(rarity, gachaName).map(dto -> dto.getHistoricalCat());
	}
	public Flux<HistoricalCat> getGachas() {
		return gachaDao.findAll().map(dto -> dto.getHistoricalCat());
	}
	
	public Flux<HistoricalCat> getGacha(Rarity rarity) {
		return gachaDao.findByRarity(rarity).map(dto -> dto.getHistoricalCat());
	}
}
