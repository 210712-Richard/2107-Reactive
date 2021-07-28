package com.revature.beans;

import java.util.UUID;

public class HistoricalCat implements GachaObject {
	private static final long serialVersionUID = 8127818726714331011L;
	private UUID id;
	private Rarity rarity;
	private Integer level;
	private String name;
	private String type;
	private Attributes stats;
	private Ability ability;
	public HistoricalCat() {
		super();
		this.level = 0;
	}
	public HistoricalCat(String name, Rarity rarity, String type) {
		this();
		this.name = name;
		this.rarity = rarity;
		this.type = type;
	}
	@Override
	public void levelUp() {
		this.level++;
		this.stats.setAttack((int) Math.ceil(this.stats.getAttack() * 1.2));
		this.stats.setDefense((int) Math.ceil(this.stats.getDefense() * 1.2));
		this.stats.setHealth((int) Math.ceil(this.stats.getHealth() * 1.2));
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Rarity getRarity() {
		return rarity;
	}
	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Attributes getStats() {
		return stats;
	}
	public void setStats(Attributes stats) {
		this.stats = stats;
	}
	public Ability getAbility() {
		return ability;
	}
	public void setAbility(Ability ability) {
		this.ability = ability;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ability == null) ? 0 : ability.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		HistoricalCat other = (HistoricalCat) obj;
		if (ability == null) {
			if (other.ability != null)
				return false;
		} else if (!ability.equals(other.ability))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rarity != other.rarity)
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "HistoricalCat [id=" + id + ", rarity=" + rarity + ", level=" + level + ", name=" + name + ", type="
				+ type + ", stats=" + stats + ", ability=" + ability + "]";
	}
}
