package com.revature.services;

import com.revature.beans.GachaObject;
import com.revature.beans.Rarity;

public interface GachaService {

	// create a new gacha for the pool
	GachaObject createGacha(GachaObject gacha);
	// edit an existing gacha in the pool
	void updateGacha(GachaObject gacha);
	GachaObject getGacha(Rarity rarity, String gachaName);
}