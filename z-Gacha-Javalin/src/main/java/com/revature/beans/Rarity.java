package com.revature.beans;

import java.io.Serializable;

public enum Rarity implements Serializable {
	ALLEY(60), HOUSE(90), ARISTOCAT(99), FERAL(100);
	
	private Integer chance;
	
	Rarity(Integer chance) {
		this.chance = chance;
	}
	
	public Integer getChance() {
		return this.chance;
	}
	
	public static Rarity getRarity(Integer i) {
		if(i < ALLEY.getChance()) {
			return ALLEY;
		}
		
		if(i < HOUSE.getChance()) {
			return HOUSE;
		}
		
		if(i < ARISTOCAT.getChance()) {
			return ARISTOCAT;
		}
		
		if(i < FERAL.getChance()) {
			return FERAL;
		}
		throw new RuntimeException("Incorrect Input to getRarity(): "+i);
	}
}
