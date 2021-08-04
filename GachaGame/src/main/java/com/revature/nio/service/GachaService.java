package com.revature.nio.service;

import com.revature.beans.GachaObject;
import com.revature.nio.data.GachaDao;
import com.revature.nio.data.GachaDaoImpl;

import io.reactivex.rxjava3.core.Observable;

public class GachaService {
	private GachaDao gd = new GachaDaoImpl();
	
	public Observable<GachaObject> getGachas() {
		return gd.getGachas();
	}
	
}
