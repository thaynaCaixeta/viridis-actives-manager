package br.com.viridis.industrialactives.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.viridis.industrialactives.entity.Maintenance;
import br.com.viridis.industrialactives.form.MaintenanceForm;

public interface MaintenanceService {

	Boolean existsByDateAndEquipmentoNumber(String data, String serieNumber);

	void save(Maintenance newMaintenance);

	List<Maintenance> findAll();

	Optional<Maintenance> findById(String id);

	public boolean updateMaintenance(String id, @Valid MaintenanceForm maintenanceForm);
	
}
