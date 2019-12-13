package br.com.viridis.industrialactives.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viridis.industrialactives.entity.Maintenance;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
	
	Boolean existsByDate(Date date);
	
	Optional<Maintenance> findByIdMaintenance(Long id);


}
