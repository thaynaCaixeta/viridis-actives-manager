package br.com.viridis.industrialactives.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.viridis.industrialactives.dto.EquipmentDTO;
import br.com.viridis.industrialactives.dto.MaintenanceDTO;
import br.com.viridis.industrialactives.entity.Maintenance;
import br.com.viridis.industrialactives.form.MaintenanceForm;
import br.com.viridis.industrialactives.service.MaintenanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/maintenance")
@Api(value = "Manutenção API")
public class MaintenanceController {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@ApiOperation(value = "Listar Equipamentos", response = MaintenanceDTO.class, notes = "Método responsável por listar todos os equipamentos cadastrados na base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Confirma a operação", response = MaintenanceDTO.class)})
	@GetMapping("/get")
	public List<MaintenanceDTO> getAll(){
		List<Maintenance> maintenances = this.maintenanceService.findAll();
        return MaintenanceDTO.convertList(maintenances);
	}
	
	@ApiOperation(value = "Busca por uma ordem de manutenção específica", response = MaintenanceDTO.class, notes = "Método responsável por buscar uma ordem de manutenção específica através do ID fornecido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o objeto encontrado", response = ResponseEntity.class),
			@ApiResponse(code = 400, message = "Erro -> Serviço de manutenção não encontrado", response = MaintenanceDTO.class)})
	@GetMapping("/get/{id}")
	public ResponseEntity<MaintenanceDTO> get(@PathVariable String id) {
		Optional <Maintenance> maintenance = this.maintenanceService.findById(id);
		if(!maintenance.isPresent()) {
			return new ResponseEntity("Erro -> Serviço de manutenção não encontrado", HttpStatus.BAD_REQUEST);
		}
			return ResponseEntity.ok(MaintenanceDTO.convert(maintenance.get()));
	}
	
	@ApiOperation(value = "Cadastra uma nova ordem de manutenção", response = ResponseEntity.class, notes = "Método responsável por cadastrar uma nova ordem de manutenção")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Nova manutenção agendada com sucesso!", response = String.class),
			@ApiResponse(code = 400, message = "Erro -> Manutenção já programada", response = ResponseEntity.class)})
	
	@PostMapping("/new")
	public ResponseEntity<String> createMaintenance(@Valid @RequestBody MaintenanceForm maintenanceForm){
		if(maintenanceService.existsByDateAndEquipmentoNumber(maintenanceForm.getData(),
				maintenanceForm.getEquipmentSerieNumber())) {
			return new ResponseEntity("Erro -> Manutenção já programada", HttpStatus.BAD_REQUEST);
		}
		this.save(maintenanceForm);
		return ResponseEntity.ok().body("Nova manutenção agendada com sucesso!");
	}
	
	@ApiOperation(value = "Edita uma ordem de manutenção já cadastrada", response = MaintenanceForm.class, notes = "Método responsável por editar uma ordem de manutenção já cadastrada")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Serviço de manutenção atualizado com sucesso!", response = String.class),
			@ApiResponse(code = 400, message = "Erro -> Não foi possível atualizar os dados", response = ResponseEntity.class)})
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> updateMaintenance(@PathVariable String id, @Valid @RequestBody MaintenanceForm maintenanceForm){
		boolean check = maintenanceService.updateMaintenance(id, maintenanceForm);
		if(check) {
			return ResponseEntity.ok().body("Serviço de manutenção atualizado com sucesso!");
		}
		return new ResponseEntity("Erro -> Não foi possível atualizar os dados", HttpStatus.BAD_REQUEST);
	}
	
	private void save(@RequestBody @Valid MaintenanceForm maintenanceForm) {
		Maintenance newMaintenance = Maintenance.convert(maintenanceForm);
		maintenanceService.save(newMaintenance);
	}

}
