package br.com.viridis.industrialactives.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.viridis.industrialactives.dto.ServiceOrderDTO;
import br.com.viridis.industrialactives.form.MaintenanceForm;

@Entity
@Table(name = "maintenance")
public class Maintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMaintenance;

	@DateTimeFormat
	@NotNull
	@Column
	private Date date;

	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	private ServiceOrder service;

	@NotNull
	private String equipmentNumberSerie;

	@NotNull
	@Column
	private String operatorName;

	public Maintenance() {

	}

	public Maintenance(MaintenanceForm maintenanceForm) throws ParseException {
		this.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(maintenanceForm.getDate()));
		ServiceOrder service = new ServiceOrder(
				new ServiceOrderDTO(maintenanceForm.getServiceTitle(), maintenanceForm.getServiceDescription()));
		this.setService(service);
		setEquipmentNumberSerie(maintenanceForm.getEquipmentSerieNumber());
		setOperatorName(maintenanceForm.getOperatorName());

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ServiceOrder getService() {
		return service;
	}

	public void setService(ServiceOrder service) {
		this.service = service;
	}

	public String getEquipmentNumberSerie() {
		return equipmentNumberSerie;
	}

	public void setEquipmentNumberSerie(String equipmentNumberSerie) {
		this.equipmentNumberSerie = equipmentNumberSerie;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Long getId_maintenance() {
		return idMaintenance;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public static Maintenance convert(@Valid MaintenanceForm maintenanceForm) {
		try {
			return new Maintenance(maintenanceForm);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
