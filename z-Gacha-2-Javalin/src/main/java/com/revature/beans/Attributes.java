package com.revature.beans;

import java.io.Serializable;

public class Attributes implements Serializable {
	private Integer attack;
	private Integer defense;
	private Integer health;
	public Attributes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attributes(Integer attack, Integer defense, Integer health) {
		super();
		this.attack = attack;
		this.defense = defense;
		this.health = health;
	}
	public Integer getAttack() {
		return attack;
	}
	public void setAttack(Integer attack) {
		this.attack = attack;
	}
	public Integer getDefense() {
		return defense;
	}
	public void setDefense(Integer defense) {
		this.defense = defense;
	}
	public Integer getHealth() {
		return health;
	}
	public void setHealth(Integer health) {
		this.health = health;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attack == null) ? 0 : attack.hashCode());
		result = prime * result + ((defense == null) ? 0 : defense.hashCode());
		result = prime * result + ((health == null) ? 0 : health.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attributes other = (Attributes) obj;
		if (attack == null) {
			if (other.attack != null)
				return false;
		} else if (!attack.equals(other.attack))
			return false;
		if (defense == null) {
			if (other.defense != null)
				return false;
		} else if (!defense.equals(other.defense))
			return false;
		if (health == null) {
			if (other.health != null)
				return false;
		} else if (!health.equals(other.health))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Attributes [attack=" + attack + ", defense=" + defense + ", health=" + health + "]";
	}
}
