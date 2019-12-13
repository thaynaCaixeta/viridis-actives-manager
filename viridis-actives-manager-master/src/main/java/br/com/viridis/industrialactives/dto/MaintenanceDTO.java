package br.com.viridis.industrialactives.dto;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import br.com.viridis.industrialactives.entity.Maintenance;

public class MaintenanceDTO {

	private String date;

	private String serviceName;

	private String equipmentSerieNumber;

	private String operatorName;

	public MaintenanceDTO() {

	}

	public MaintenanceDTO(Maintenance maintenance) {
		this.date = new SimpleDateFormat("dd/MM/yyyy").format(maintenance.getDate());
		this.serviceName = maintenance.getService().getTitle();
		this.equipmentSerieNumber = maintenance.getEquipmentNumberSerie();
		this.operatorName = maintenance.getOperatorName();
	}

	public String getDate() {
		return date;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getEquipmentSerieNumber() {
		return equipmentSerieNumber;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public static List<MaintenanceDTO> convertList(List<Maintenance> maintenances) {
		return maintenances.stream().map(MaintenanceDTO::new).collect(Collectors.toList());
	}

	public static MaintenanceDTO convert(Maintenance maintenance) {
		return new MaintenanceDTO(maintenance);
	}

}
