package com.revature.data;

import java.util.List;

import com.revature.beans.GachaObject;
import com.revature.beans.Rarity;

public interface GachaDao {

	void addGacha(GachaObject gacha);

	List<GachaObject> getGachas();

	GachaObject getGacha(Long id);

	GachaObject getGachaByName(String name);

	List<GachaObject> getGachasByRarity(Rarity rarity);

	void updateGacha(GachaObject gacha);

}