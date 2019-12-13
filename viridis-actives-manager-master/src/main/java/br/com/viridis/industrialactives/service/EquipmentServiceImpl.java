package br.com.viridis.industrialactives.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viridis.industrialactives.entity.Equipment;
import br.com.viridis.industrialactives.repository.EquipmentRepository;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	private static final Logger log = LoggerFactory.getLogger(EquipmentServiceImpl.class);

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Override
	public Boolean existsBySerieNumber(String serieNumber) {
		return this.equipmentRepository.existsBySerieNumber(serieNumber);
	}

	public List<Equipment> findAll() {
		List<Equipment> equipments = equipmentRepository.findAll();
		return equipments;
	}

	@Override
	public void save(Equipment equipment) {
		this.equipmentRepository.save(equipment);
	}

	@Override
	public Optional<Equipment> findBySerieNumber(String serieNumber) {
		return equipmentRepository.findBySerieNumber(serieNumber);
	}

	@Override
	public Optional<Equipment> findByIdEquipment(String id) {
		return equipmentRepository.findByIdEquipment(Long.parseLong(id));
	}
}
