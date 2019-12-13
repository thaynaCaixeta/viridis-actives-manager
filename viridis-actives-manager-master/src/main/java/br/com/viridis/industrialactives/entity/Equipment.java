package br.com.viridis.industrialactives.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.viridis.industrialactives.dto.EquipmentDTO;

@Entity
@Table(name = "equipment")
public class Equipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEquipment;

	@NotNull
	@Size(min = 5)
	@Column
	private String name;

	@NotNull
	@Size(min = 5, max = 8)
	@Column
	private String serieNumber;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Maintenance> maintences;

	public Equipment() {

	}

	public Equipment(EquipmentDTO equipmentDTO) {
		this.name = equipmentDTO.getName();
		this.serieNumber = equipmentDTO.getSerieNumber();
	}

	public Equipment(Long idEquipment, @NotNull @Size(min = 5) String name,
			@NotNull @Size(min = 5, max = 8) String serieNumber) {
		this.idEquipment = idEquipment;
		this.name = name;
		this.serieNumber = serieNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeriesNumber() {
		return serieNumber;
	}

	public void setSeriesNumber(String seriesNumber) {
		this.serieNumber = seriesNumber;
	}

	public List<Maintenance> getMaintences() {
		return maintences;
	}

	public void setMaintences(List<Maintenance> maintences) {
		this.maintences = maintences;
	}

	public Long getId_equipment() {
		return idEquipment;
	}

	public static Equipment convert(EquipmentDTO equipmentoDTO) {
		Equipment newEquipment = new Equipment(equipmentoDTO);
		return newEquipment;
	}

	public static List<EquipmentDTO> convertList(List<Equipment> equipments) {
		return equipments.stream().map(EquipmentDTO::new).collect(Collectors.toList());
	}

}
