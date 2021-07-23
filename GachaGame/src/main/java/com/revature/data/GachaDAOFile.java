package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.beans.Ability;
import com.revature.beans.Attributes;
import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;
import com.revature.factory.Log;

@Log
public class GachaDAOFile implements GachaDAO {
	private static String filename = "gacha.dat";
	private static List<GachaObject> gachas;
	
	static {
		DataSerializer<GachaObject> ds = new DataSerializer<GachaObject>();
		gachas = ds.readObjectsFromFile(filename);
		
		// Helper for myself. If no users exist in the users.dat file (first startup) than I should create a few
		if(gachas == null) {
			gachas = new ArrayList<GachaObject>();
			HistoricalCat cat = new HistoricalCat("Tony", Rarity.FERAL, "Tiger");
			cat.setStats(new Attributes(10, 10, 100));
			cat.setAbility(new Ability("They're GRRRRREAT!"));
			cat.setId((long) gachas.size());
			gachas.add(cat);
			
			cat = new HistoricalCat("Felix", Rarity.ALLEY, "Black");
			cat.setStats(new Attributes(1, 1, 30));
			cat.setAbility(new Ability("Mouse Hunter"));
			cat.setId((long) gachas.size());
			gachas.add(cat);
			
			cat = new HistoricalCat("Napoleon", Rarity.ARISTOCAT, "Munchkin");
			cat.setStats(new Attributes(9, 6, 80));
			cat.setAbility(new Ability("Divide and Conquer"));
			cat.setId((long) gachas.size());
			gachas.add(cat);
			
			cat = new HistoricalCat("Grumpy", Rarity.HOUSE, "Grump");
			cat.setStats(new Attributes(4, 6, 50));
			cat.setAbility(new Ability("Grump"));
			cat.setId((long) gachas.size());
			gachas.add(cat);
			
			cat = new HistoricalCat("Tom", Rarity.HOUSE, "Tomcat");
			cat.setStats(new Attributes(4, 4, 80));
			cat.setAbility(new Ability("ACME"));
			cat.setId((long) gachas.size());
			gachas.add(cat);
			
			cat = new HistoricalCat("Shakespeare", Rarity.ALLEY, "Poet");
			cat.setStats(new Attributes(3, 1, 10));
			cat.setAbility(new Ability("Sonnet"));
			cat.setId((long) gachas.size());
			gachas.add(cat);
			
			cat = new HistoricalCat("Peasant", Rarity.ALLEY, "Mutt");
			cat.setStats(new Attributes(1, 3, 10));
			cat.setAbility(new Ability("Farm"));
			cat.setId((long) gachas.size());
			gachas.add(cat);
			
			ds.writeObjectsToFile(gachas, filename);
		}
	}
	@Override
	public void addGacha(GachaObject gacha) {
		gacha.setId((long) gachas.size());
		gachas.add(gacha);
	}
	
	@Override
	public List<GachaObject> getGachas(){
		return gachas;
	}
	
	@Override
	public GachaObject getGacha(Long id) {
		return gachas.stream()
				.filter((g) -> g.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public GachaObject getGachaByName(String name) {
		return gachas.stream()
			.filter((g) -> g.getName().equals(name))
			.findFirst()
			.orElse(null);
	}
	
	@Override
	public List<GachaObject> getGachasByRarity(Rarity rarity) {
		return gachas.stream()
			.filter((g) -> g.getRarity().equals(rarity))
			.collect(Collectors.toList());
	}
	
	@Override
	public void updateGacha(GachaObject gacha) {
		// due to us holding the entire list in memory, we will actually automatically update the user
		// in the list anytime we change the fields of the user object.
		// I'll leave this method as a placeholder for our Week 3 Database integration.
	}
	
	public void writeToFile() {
		new DataSerializer<GachaObject>().writeObjectsToFile(gachas, filename);
	}
}
