package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.GachaObject;
import com.revature.beans.Rarity;
import com.revature.data.GachaDao;

@Service
public class GachaServiceImpl implements GachaService {
	@Autowired
	private GachaDao gachaDao;
	// perform operations on Gachas
	
	// create a new gacha for the pool
	@Override
	public GachaObject createGacha(GachaObject gacha) {
		gachaDao.addGacha(gacha);
		return gacha;
	}
	// edit an existing gacha in the pool
	public void updateGacha(GachaObject gacha) {
		gachaDao.updateGacha(gacha);
	}
	@Override
	public GachaObject getGacha(Rarity rarity, String gachaName) {
		return gachaDao.getGachaByRarityAndName(rarity, gachaName);
	}
	@Override
	public List<GachaObject> getGachas() {
		return gachaDao.getGachas();
	}
}
