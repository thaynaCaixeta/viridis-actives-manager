package br.com.viridis.industrialactives.dto;

import br.com.viridis.industrialactives.entity.ServiceOrder;

public class ServiceOrderDTO {

	private String title;

	private String description;

	public ServiceOrderDTO() {

	}

	public ServiceOrderDTO(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public ServiceOrderDTO(ServiceOrder serviceOrder) {
		this.title = serviceOrder.getTitle();
		this.description = serviceOrder.getDescription();
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public static ServiceOrderDTO convert(ServiceOrder serviceOrder) {
		return new ServiceOrderDTO(serviceOrder);
	}
}
