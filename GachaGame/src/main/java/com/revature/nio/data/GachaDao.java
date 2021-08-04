package com.revature.nio.data;

import java.util.concurrent.Future;

import com.revature.beans.GachaObject;
import com.revature.beans.Rarity;

import io.reactivex.rxjava3.core.Observable;

public interface GachaDao {
	void addGacha(GachaObject gacha);
	Observable<GachaObject> getGachas();
	Future<GachaObject> getGachaByRarityAndName(Rarity rarity, String name);
	Observable<GachaObject> getGachasByRarity(Rarity rarity);
	void updateGacha(GachaObject gacha);
}
