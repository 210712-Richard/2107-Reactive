package com.revature.dto;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.data.TupleValue;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.core.type.TupleType;
import com.revature.beans.Ability;
import com.revature.beans.Attributes;
import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;

@Table("gacha")
public class HistoricalCatDTO {

	private static final TupleType STATS_TUPLE = DataTypes.tupleOf(DataTypes.INT, DataTypes.INT, DataTypes.INT);
	
	@PrimaryKeyColumn(
			name="rarity",
			ordinal=0, // the position in the primary key that it is
			type = PrimaryKeyType.PARTITIONED)
	private Rarity rarity;
	@PrimaryKeyColumn(
			name="name",
			ordinal=1,
			type = PrimaryKeyType.CLUSTERED,
			ordering = Ordering.DESCENDING)
	private String name;
	private String type;
	@Column("stats")
	private TupleValue stats;
	private Ability ability;
	private String pictureUrl;
	
	public HistoricalCatDTO() {
		super();
	}
	public HistoricalCatDTO(GachaObject gacha) {
		super();
		this.name = gacha.getName();
		this.rarity = gacha.getRarity();
		this.type = gacha.getType();
		this.ability = gacha.getAbility();
		this.stats = STATS_TUPLE
				.newValue(gacha.getStats().getAttack(), gacha.getStats().getDefense(), gacha.getStats().getHealth());
		this.pictureUrl = gacha.getPictureUrl();
		
	}
	
	public HistoricalCat getHistoricalCat() {
		HistoricalCat h = new HistoricalCat();
		h.setName(this.name);
		h.setAbility(this.ability);
		h.setPictureUrl(this.pictureUrl);
		h.setRarity(this.rarity);
		h.setType(this.type);
		Attributes a = new Attributes(this.stats.getInt(0), this.stats.getInt(1), this.stats.getInt(2));
		h.setStats(a);
		return h;
	}
	
	public Rarity getRarity() {
		return rarity;
	}
	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
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
	public TupleValue getStats() {
		return stats;
	}
	public void setStats(TupleValue stats) {
		this.stats = stats;
	}
	public Ability getAbility() {
		return ability;
	}
	public void setAbility(Ability ability) {
		this.ability = ability;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ability == null) ? 0 : ability.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pictureUrl == null) ? 0 : pictureUrl.hashCode());
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
		HistoricalCatDTO other = (HistoricalCatDTO) obj;
		if (ability != other.ability)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pictureUrl == null) {
			if (other.pictureUrl != null)
				return false;
		} else if (!pictureUrl.equals(other.pictureUrl))
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
		return "HistoricalCatDTO [rarity=" + rarity + ", name=" + name + ", type=" + type + ", stats=" + stats
				+ ", ability=" + ability + ", pictureUrl=" + pictureUrl + "]";
	}
}
