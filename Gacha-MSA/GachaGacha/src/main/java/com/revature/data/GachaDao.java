package com.revature.data;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Rarity;
import com.revature.dto.HistoricalCatDTO;

@Repository
public interface GachaDao extends CassandraRepository<HistoricalCatDTO, String>{
	List<HistoricalCatDTO> findByRarity(Rarity rarity);
	HistoricalCatDTO findByRarityAndName(Rarity rarity, String gachaName);
}