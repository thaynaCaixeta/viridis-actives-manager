package br.com.viridis.industrialactives.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.viridis.industrialactives.dto.ServiceOrderDTO;

@Entity
@Table(name = "serviceOrder")
public class ServiceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idService;

	@NotNull
	@Size(min = 5)
	@Column
	private String title;

	@NotNull
	@Size(min = 10)
	@Column
	private String description;

	public ServiceOrder() {

	}

	public ServiceOrder(ServiceOrderDTO serviceOrderDTO) {
		this.title = serviceOrderDTO.getTitle();
		this.description = serviceOrderDTO.getDescription();
	}

	public Long getIdServiceOrder() {
		return idService;
	}

	public void setIdServiceOrder(Long idService) {
		this.idService = idService;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static ServiceOrder convert(ServiceOrderDTO serviceOrderDto) {
		return new ServiceOrder(serviceOrderDto);
	}
}
