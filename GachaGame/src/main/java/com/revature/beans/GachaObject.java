package com.revature.beans;

import java.io.Serializable;

public interface GachaObject extends Serializable{
	Integer MAXIMUM_LEVEL = 10;
	Long DAILY_BONUS = 500l;
	Long SUMMON_COST = 100l;
	
	void levelUp();
	
	Long getId();
	void setId(Long id);
	
	String getName();
	void setName(String name);
	
	Rarity getRarity();
	void setRarity(Rarity rarity);
	
	Integer getLevel();
	void setLevel(Integer level);
}
