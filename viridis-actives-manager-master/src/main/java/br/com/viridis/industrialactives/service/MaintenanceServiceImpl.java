package br.com.viridis.industrialactives.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viridis.industrialactives.entity.Maintenance;
import br.com.viridis.industrialactives.form.MaintenanceForm;
import br.com.viridis.industrialactives.repository.EquipmentRepository;
import br.com.viridis.industrialactives.repository.MaintenanceRepository;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

	private static final Logger log = LoggerFactory.getLogger(EquipmentServiceImpl.class);

	@Autowired
	private MaintenanceRepository maintenanceRepository;

	@Autowired
	private EquipmentRepository equipmentRepository;

	public Boolean existsByDateAndEquipmentoNumber(String date, String serieNumber) {
		Date formattedDate = null;
		try {
			formattedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (formattedDate != null) {
			boolean checkExistByData = maintenanceRepository.existsByDate(formattedDate);
			boolean checkExistsByEquipmentNumber = equipmentRepository.existsBySerieNumber(serieNumber);
			if (checkExistByData && checkExistsByEquipmentNumber)
				return true;
		}
		return false;
	}

	@Transactional
	@Override
	public void save(Maintenance newMaintenance) {
		this.maintenanceRepository.save(newMaintenance);
	}

	@Override
	public List<Maintenance> findAll() {
		return maintenanceRepository.findAll();
	}

	@Override
	public Optional<Maintenance> findById(String id) {
		return maintenanceRepository.findByIdMaintenance(Long.parseLong(id));
	}
	
	@Override
	@Transactional
	public boolean updateMaintenance(String id, @Valid MaintenanceForm maintenanceForm) {
		Optional<Maintenance> optional = findById(id);
		if(optional.isPresent()) {
			Maintenance maintenance = optional.get();
			 try {
				maintenance.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(maintenanceForm.getData()));
				maintenance.getService().setTitle(maintenanceForm.getServiceTitle());
				maintenance.getService().setDescription(maintenanceForm.getServiceDescription());
				maintenance.setEquipmentNumberSerie(maintenanceForm.getEquipmentSerieNumber());
				maintenance.setOperatorName(maintenanceForm.getOperatorName());
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
