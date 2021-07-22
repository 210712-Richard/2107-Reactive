package com.revature.services;

import com.revature.beans.GachaObject;
import com.revature.data.GachaDAO;

public class GachaService {
	private GachaDAO gachaDao = new GachaDAO();
	// perform operations on Gachas
	
	// create a new gacha for the pool
	public GachaObject createGacha(GachaObject gacha) {
		gachaDao.addGacha(gacha);
		gachaDao.writeToFile();
		return gacha;
	}
	// edit an existing gacha in the pool
	
	// remove a gacha from the pool

}
