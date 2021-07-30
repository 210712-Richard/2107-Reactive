package com.revature.data;

import java.util.ArrayList;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.datastax.oss.driver.api.core.data.TupleValue;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.core.type.TupleType;
import com.revature.beans.Ability;
import com.revature.beans.Attributes;
import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class GachaDaoImpl implements GachaDao {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static final TupleType STATS_TUPLE = DataTypes.tupleOf(DataTypes.INT, DataTypes.INT, DataTypes.INT);
	
	@Override
	public void addGacha(GachaObject gacha) {
		String query = "Insert into gacha (rarity, stats, name, ability, pictureUrl) values (?, ?, ?, ?, ?);";
		TupleValue stats = STATS_TUPLE
				.newValue(gacha.getStats().getAttack(), gacha.getStats().getDefense(), gacha.getStats().getHealth());

		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s).bind(gacha.getRarity().toString(), stats, gacha.getName(),
				gacha.getAbility().toString(), gacha.getPictureUrl());
		session.execute(bound);
	}

	@Override
	public List<GachaObject> getGachas() {
		List<GachaObject> gachas = new ArrayList<GachaObject>();
		String query = "Select rarity, stats, name, ability, pictureUrl from gacha";
		ResultSet rs = session.execute(new SimpleStatementBuilder(query).build());

		rs.forEach(row -> {
			GachaObject g = new HistoricalCat();
			g.setAbility(Ability.valueOf(row.getString("ability")));
			g.setRarity(Rarity.valueOf(row.getString("rarity")));
			TupleValue stats = row.getTupleValue("stats");
			g.setStats(new Attributes(stats.get(0, Integer.class),
					stats.get(1, Integer.class),
					stats.get(2, Integer.class)));
			g.setName(row.getString("name"));
			g.setPictureUrl(row.getString("pictureUrl"));
			gachas.add(g);
		});

		return gachas;
	}

	@Override
	public GachaObject getGachaByRarityAndName(Rarity rarity, String name) {
		String query = "Select stats, name, ability, pictureUrl from gacha where rarity = ? and name = ?";
		BoundStatement bound = session.prepare(new SimpleStatementBuilder(query).build()).bind(rarity.toString(), name);
		ResultSet rs = session.execute(bound);

		Row row = rs.one();
		if (row == null) {
			return null;
		}
		GachaObject g = new HistoricalCat();
		g.setAbility(Ability.valueOf(row.getString("ability")));
		g.setStats(new Attributes(row.getTupleValue("stats").get(0, Integer.class),
				row.getTupleValue("stats").get(1, Integer.class), row.getTupleValue("stats").get(2, Integer.class)));
		g.setRarity(rarity);
		g.setName(row.getString("name"));
		g.setPictureUrl(row.getString("pictureUrl"));

		return g;
	}

	@Override
	public List<GachaObject> getGachasByRarity(Rarity rarity) {
		List<GachaObject> gachas = new ArrayList<GachaObject>();
		String query = "Select rarity, stats, name, ability, pictureUrl from gacha where rarity = ?";
		BoundStatement bound = session.prepare(new SimpleStatementBuilder(query).build()).bind(rarity.toString());
		ResultSet rs = session.execute(bound);

		rs.forEach(row -> {
			GachaObject g = new HistoricalCat();
			g.setAbility(Ability.valueOf(row.getString("ability")));
			g.setRarity(Rarity.valueOf(row.getString("rarity")));
			g.setStats(new Attributes(row.getTupleValue("stats").get(0, Integer.class),
					row.getTupleValue("stats").get(1, Integer.class),
					row.getTupleValue("stats").get(2, Integer.class)));
			g.setName(row.getString("name"));
			g.setPictureUrl(row.getString("pictureUrl"));
			gachas.add(g);
		});

		return gachas;
	}

	@Override
	public void updateGacha(GachaObject gacha) {
		String query = "update gacha set stats=?, ability=?, pictureUrl= ? where rarity = ? and name = ?;";
		TupleValue stats = STATS_TUPLE
				.newValue(gacha.getStats().getAttack(), gacha.getStats().getDefense(), gacha.getStats().getHealth());

		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s).bind(
				stats,
				gacha.getAbility().toString(),
				gacha.getPictureUrl(),
				gacha.getRarity().toString(),
				gacha.getName());
		session.execute(bound);
	}

}
