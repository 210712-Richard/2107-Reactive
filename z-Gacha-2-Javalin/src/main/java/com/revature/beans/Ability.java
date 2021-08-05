package com.revature.beans;

import java.io.Serializable;

public class Ability implements Serializable {
	
	private String abilityName;
	
	public Ability() {
		super();
	}

	public Ability(String abilityName) {
		this.abilityName = abilityName;
	}

	@Override
	public String toString() {
		return "Ability [abilityName=" + abilityName + "]";
	}

	public String getAbilityName() {
		return abilityName;
	}

	public void setAbilityName(String abilityName) {
		this.abilityName = abilityName;
	}

	
}
