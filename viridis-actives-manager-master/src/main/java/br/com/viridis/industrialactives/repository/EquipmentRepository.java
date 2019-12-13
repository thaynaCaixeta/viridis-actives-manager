package br.com.viridis.industrialactives.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viridis.industrialactives.entity.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{

	Boolean existsBySerieNumber(String serieNumber);
	
	Optional<Equipment> findBySerieNumber(String serieNumber);
	
	Optional<Equipment> findByIdEquipment(Long id);

}
