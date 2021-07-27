package com.revature.services;

import com.revature.beans.GachaObject;
import com.revature.data.GachaDao;
import com.revature.data.GachaDaoImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

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
	
	// remove a gacha from the pool

}
