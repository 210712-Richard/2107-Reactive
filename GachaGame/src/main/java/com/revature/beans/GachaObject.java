package com.revature.beans;

import java.io.Serializable;
import java.util.UUID;

public interface GachaObject extends Serializable{
	Integer MAXIMUM_LEVEL = 10;
	Long DAILY_BONUS = 500l;
	Long SUMMON_COST = 100l;
	
	void levelUp();
	
	UUID getId();
	void setId(UUID id);
	
	String getName();
	void setName(String name);
	
	Rarity getRarity();
	void setRarity(Rarity rarity);
	
	Integer getLevel();
	void setLevel(Integer level);
	
	Attributes getStats();
	void setStats(Attributes stats);
	
	Ability getAbility();
	void setAbility(Ability ability);
}
