package com.revature.services;

import com.revature.beans.GachaObject;
import com.revature.data.GachaDAO;
import com.revature.data.GachaDAOFile;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

@Log
public class GachaServiceImpl implements GachaService {
	private GachaDAO gachaDao = (GachaDAO) BeanFactory.getFactory().get(GachaDAO.class, GachaDAOFile.class);
	// perform operations on Gachas
	
	// create a new gacha for the pool
	@Override
	public GachaObject createGacha(GachaObject gacha) {
		gachaDao.addGacha(gacha);
		(new GachaDAOFile()).writeToFile();
		return gacha;
	}
	// edit an existing gacha in the pool
	
	// remove a gacha from the pool

}
