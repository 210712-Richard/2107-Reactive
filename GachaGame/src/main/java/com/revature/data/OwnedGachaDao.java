package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.GachaObject;
import com.revature.beans.Rarity;

public interface OwnedGachaDao {	
	GachaObject getGachaById(UUID id);
	
	UUID addGacha(GachaObject gacha);

	List<GachaObject> getGachas();
	
	GachaObject getGachaByName(String name);

	List<GachaObject> getGachasByRarity(Rarity rarity);

	void updateGacha(GachaObject gacha);
}
