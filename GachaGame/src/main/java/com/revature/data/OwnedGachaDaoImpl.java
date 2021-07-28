package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.datastax.oss.driver.api.core.data.TupleValue;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.revature.beans.Ability;
import com.revature.beans.Attributes;
import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class OwnedGachaDaoImpl implements OwnedGachaDao {
	private CqlSession session = CassandraUtil.getInstance().getSession();

	@Override
	public UUID addGacha(GachaObject gacha) {
		String query = "Insert into owned_gacha (id, level, rarity, stats, name, ability) values (?, ?, ?, ?, ?, ?);";
		TupleValue stats = DataTypes.tupleOf(DataTypes.INT, DataTypes.INT, DataTypes.INT)
				.newValue(gacha.getStats().getAttack(), gacha.getStats().getDefense(), gacha.getStats().getHealth());
		UUID id = UUID.randomUUID();
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s).bind(id, gacha.getLevel(), gacha.getRarity().toString(),
				stats, gacha.getName(), gacha.getAbility().toString());
		session.execute(bound);
		return id;
	}

	@Override
	public List<GachaObject> getGachas() {
		List<GachaObject> gachas = new ArrayList<GachaObject>();
		String query = "Select id, level, rarity, stats, name, ability from owned_gacha";
		ResultSet rs = session.execute(new SimpleStatementBuilder(query).build());

		rs.forEach(row -> {
			GachaObject g = new HistoricalCat();
			g.setId(row.getUuid("id"));
			g.setLevel(row.getInt("level"));
			g.setAbility(Ability.valueOf(row.getString("ability")));
			g.setRarity(Rarity.valueOf(row.getString("rarity")));
			g.setStats(new Attributes(row.getTupleValue("stats").get(0, Integer.class),
					row.getTupleValue("stats").get(1, Integer.class),
					row.getTupleValue("stats").get(2, Integer.class)));
			g.setName(row.getString("name"));
			gachas.add(g);
		});

		return gachas;
	}

	@Override
	public GachaObject getGachaByName(String name) {
		String query = "Select id, level, rarity, stats, name, ability from owned_gacha where name = ?";
		BoundStatement bound = session.prepare(new SimpleStatementBuilder(query).build()).bind(name);
		ResultSet rs = session.execute(bound);

		Row row = rs.one();
		if (row == null) {
			return null;
		}
		GachaObject g = new HistoricalCat();
		g.setId(row.getUuid("id"));
		g.setLevel(row.getInt("level"));
		g.setAbility(Ability.valueOf(row.getString("ability")));
		g.setRarity(Rarity.valueOf(row.getString("rarity")));
		g.setStats(new Attributes(row.getTupleValue("stats").get(0, Integer.class),
				row.getTupleValue("stats").get(1, Integer.class), row.getTupleValue("stats").get(2, Integer.class)));
		g.setName(row.getString("name"));

		return g;
	}

	@Override
	public List<GachaObject> getGachasByRarity(Rarity rarity) {
		List<GachaObject> gachas = new ArrayList<GachaObject>();
		String query = "Select id, level, rarity, stats, name, ability from owned_gacha where rarity = ?";
		BoundStatement bound = session.prepare(new SimpleStatementBuilder(query).build()).bind(rarity.toString());
		ResultSet rs = session.execute(bound);

		rs.forEach(row -> {
			GachaObject g = new HistoricalCat();
			g.setId(row.getUuid("id"));
			g.setLevel(row.getInt("level"));
			g.setAbility(Ability.valueOf(row.getString("ability")));
			g.setRarity(Rarity.valueOf(row.getString("rarity")));
			g.setStats(new Attributes(row.getTupleValue("stats").get(0, Integer.class),
					row.getTupleValue("stats").get(1, Integer.class),
					row.getTupleValue("stats").get(2, Integer.class)));
			g.setName(row.getString("name"));
			gachas.add(g);
		});

		return gachas;
	}

	@Override
	public void updateGacha(GachaObject gacha) {
		String query = "updated owned_gacha set level = ?, stats = ?, name = ?, ability = ? where id = ? and rarity = ?;";
		TupleValue stats = DataTypes.tupleOf(DataTypes.INT, DataTypes.INT, DataTypes.INT)
				.newValue(gacha.getStats().getAttack(), gacha.getStats().getDefense(), gacha.getStats().getHealth());

		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s).bind(gacha.getLevel(), stats, gacha.getName(),
				gacha.getAbility().toString(), gacha.getId(), gacha.getRarity().toString());
		session.execute(bound);
	}

	@Override
	public GachaObject getGachaById(UUID id) {
		String query = "Select id, level, rarity, stats, name, ability from owned_gacha where id = ?";
		BoundStatement bound = session.prepare(new SimpleStatementBuilder(query).build()).bind(id);
		ResultSet rs = session.execute(bound);

		Row row = rs.one();
		if (row == null) {
			return null;
		}
		GachaObject g = new HistoricalCat();
		g.setId(row.getUuid("id"));
		g.setLevel(row.getInt("level"));
		g.setAbility(Ability.valueOf(row.getString("ability")));
		g.setRarity(Rarity.valueOf(row.getString("rarity")));
		g.setStats(new Attributes(row.getTupleValue("stats").get(0, Integer.class),
				row.getTupleValue("stats").get(1, Integer.class), row.getTupleValue("stats").get(2, Integer.class)));
		g.setName(row.getString("name"));

		return g;
	}

}
