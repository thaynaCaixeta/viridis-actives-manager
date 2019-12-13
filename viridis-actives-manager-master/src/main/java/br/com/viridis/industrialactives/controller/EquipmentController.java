package br.com.viridis.industrialactives.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.viridis.industrialactives.dto.EquipmentDTO;
import br.com.viridis.industrialactives.entity.Equipment;
import br.com.viridis.industrialactives.service.EquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/equipment")
@Api(value = "Equipamento API")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;

	
	@ApiOperation(value = "Cadastra um novo equipamento", response = ResponseEntity.class, notes = "Método responsável por buscar um equipamento específico através"
			+ "do ID fornecido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Equipamento registrado com sucesso!", response = String.class),
			@ApiResponse(code = 400, message = "Erro -> Equipamento já cadastrado", response = ResponseEntity.class)})
	@Transactional
	@PostMapping("/new")
	public ResponseEntity<String> createEquipment(@Valid @RequestBody EquipmentDTO equipmentDTO) {
		if (equipmentService.existsBySerieNumber(equipmentDTO.getSerieNumber())) {
			return new ResponseEntity("Erro -> Equipamento já cadastrado", HttpStatus.BAD_REQUEST);
		}
		this.save(equipmentDTO);
		return ResponseEntity.ok().body("Equipamento registrado com sucesso!");
	}

	@ApiOperation(value = "Listar Equipamentos", response = EquipmentDTO.class, notes = "Método responsável por listar todos os equipamentos cadastrados na base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Confirma a operação", response = EquipmentDTO.class), })
	@GetMapping("/get")
	@ResponseBody
	public List<EquipmentDTO> getEquipments() {
		List<Equipment> equipments = this.equipmentService.findAll();
		return Equipment.convertList(equipments);
	}
	@ApiOperation(value = "Busca um equipamento específico através", response = EquipmentDTO.class, notes = "Método responsável por buscar um equipamento específico através"
			+ "do ID fornecido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o objeto encontrado", response = EquipmentDTO.class),
			@ApiResponse(code = 400, message = "Erro ->Equipamento não encontrado", response = EquipmentDTO.class)})
	@GetMapping("/get/{id}")
	public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable String id) {
		Optional<Equipment> equipment = this.equipmentService.findByIdEquipment(id);
		if (!equipment.isPresent()) {
			return new ResponseEntity("Erro ->Equipamento não encontrado", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(EquipmentDTO.convert(equipment.get()));
	}

	private void save(@RequestBody @Valid EquipmentDTO equipmentDTO) {
		Equipment equipment = Equipment.convert(equipmentDTO);
		equipmentService.save(equipment);
	}

}
