package br.com.viridis.industrialactives.dto;

import br.com.viridis.industrialactives.entity.Equipment;

public class EquipmentDTO {

	private String name;

	private String serieNumber;

	public EquipmentDTO() {
	}
	
	public EquipmentDTO(String name, String serieNumber) {
		this.name = name;
		this.serieNumber = serieNumber;
	}
	
	public EquipmentDTO(Equipment equipment) {
		this.name = equipment.getName();
		this.serieNumber = equipment.getSeriesNumber();
	}

	public String getName() {
		return name;
	}

	public String getSerieNumber() {
		return serieNumber;
	}

	public static EquipmentDTO convert(Equipment equipment) {
		return new EquipmentDTO(equipment);
	}

}
