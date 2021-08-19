package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.GachaObject;
import com.revature.beans.Rarity;
import com.revature.data.GachaDao;
import com.revature.dto.HistoricalCatDTO;

@Service
public class GachaServiceImpl implements GachaService {
	private GachaDao gachaDao;

	@Autowired
	public GachaServiceImpl(GachaDao gachaDao) {
		super();
		this.gachaDao = gachaDao;
	}
	// perform operations on Gachas
	
	// create a new gacha for the pool
	@Override
	public GachaObject createGacha(GachaObject gacha) {
		gachaDao.save(new HistoricalCatDTO(gacha));
		return gacha;
	}
	// edit an existing gacha in the pool
	public void updateGacha(GachaObject gacha) {
		gachaDao.save(new HistoricalCatDTO(gacha));
	}
	@Override
	public GachaObject getGacha(Rarity rarity, String gachaName) {
		return gachaDao.findByRarityAndName(rarity, gachaName).getHistoricalCat();
		//return gachaDao.getGachaByRarityAndName(rarity, gachaName);
	}
	@Override
	public List<GachaObject> getGachas() {
		return gachaDao.findAll().stream().map(dto -> dto.getHistoricalCat()).collect(Collectors.toList());
	}
}
