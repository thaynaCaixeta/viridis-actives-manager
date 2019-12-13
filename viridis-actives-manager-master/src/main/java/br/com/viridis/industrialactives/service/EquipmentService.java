package br.com.viridis.industrialactives.service;

import java.util.List;
import java.util.Optional;

import br.com.viridis.industrialactives.entity.Equipment;

public interface EquipmentService {
	
	Boolean existsBySerieNumber(String serieNumber);
	
	List<Equipment> findAll();
	
	void save(Equipment equipment);

	Optional<Equipment> findBySerieNumber(String serieNumber);
	
	Optional<Equipment> findByIdEquipment(String id);

}
