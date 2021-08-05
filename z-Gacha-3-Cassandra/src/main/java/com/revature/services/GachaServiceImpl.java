package com.revature.services;

import java.util.List;

import com.revature.beans.GachaObject;
import com.revature.beans.Rarity;
import com.revature.data.GachaDao;
import com.revature.data.GachaDaoImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

import io.reactivex.rxjava3.core.Observable;

@Log
public class GachaServiceImpl implements GachaService {
	private GachaDao gachaDao = (GachaDao) BeanFactory.getFactory().get(GachaDao.class, GachaDaoImpl.class);
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
