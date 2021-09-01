package com.revature.dto;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.data.TupleValue;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.core.type.TupleType;
import com.revature.beans.Ability;
import com.revature.beans.Attributes;
import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;

@Table("owned_gacha")
public class OwnedHistoricalCatDTO {
	private static final TupleType STATS_TUPLE = DataTypes.tupleOf(DataTypes.INT, DataTypes.INT, DataTypes.INT);

	@PrimaryKeyColumn(
			name="id",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED
			)
	private UUID uuid;
	@PrimaryKeyColumn(
			name="rarity",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED,
			ordering=Ordering.DESCENDING)
	private Rarity rarity;
	private Integer level;
	private String name;
	private String type;
	private TupleValue stats;
	private Ability ability;
	private String pictureUrl;
	
	public OwnedHistoricalCatDTO() {
	}
	public OwnedHistoricalCatDTO(HistoricalCat cat) {
		this.uuid = cat.getId();
		this.ability = cat.getAbility();
		this.level = cat.getLevel();
		this.name = cat.getName();
		this.pictureUrl = cat.getPictureUrl();
		this.type = cat.getType();
		this.rarity = cat.getRarity();

		this.stats = STATS_TUPLE
				.newValue(cat.getStats().getAttack(), cat.getStats().getDefense(), cat.getStats().getHealth()); 
	}
	
	public HistoricalCat getCat() {
		HistoricalCat h = new HistoricalCat();
		h.setName(this.name);
		h.setAbility(this.ability);
		h.setPictureUrl(this.pictureUrl);
		h.setRarity(this.rarity);
		h.setType(this.type);
		Attributes a = new Attributes(this.stats.getInt(0), this.stats.getInt(1), this.stats.getInt(2));
		h.setStats(a);
		h.setId(this.uuid);
		h.setLevel(this.level);
		return h;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID id) {
		this.uuid = id;
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
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pictureUrl == null) ? 0 : pictureUrl.hashCode());
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		OwnedHistoricalCatDTO other = (OwnedHistoricalCatDTO) obj;
		if (ability != other.ability)
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
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OwnedHistoricalCatDTO [uuid=" + uuid + ", rarity=" + rarity + ", level=" + level + ", name=" + name
				+ ", type=" + type + ", stats=" + stats + ", ability=" + ability + ", pictureUrl=" + pictureUrl + "]";
	}
	
}
