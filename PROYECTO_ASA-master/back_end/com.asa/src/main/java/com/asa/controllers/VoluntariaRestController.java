package com.asa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.asa.model.entity.Voluntaria;
import com.asa.model.services.IService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class VoluntariaRestController {
	
	@Autowired
	private IService<Voluntaria> voluntariaService;
	
	@GetMapping("/voluntarias")
	public List<Voluntaria> verVoluntarias() {

		return voluntariaService.findAll();

	}

	@GetMapping("/voluntarias/page/{page}")

	public Page<Voluntaria> verVoluntariasPorPAg(@PathVariable Integer page) {

		return voluntariaService.findAll(PageRequest.of(page, 4));

	}

	@GetMapping("/voluntarias/{id}")

	public ResponseEntity<?> verVoluntariaPorId(@PathVariable Long id) {

		Voluntaria voluntaria = null;
		Map<String, Object> response = new HashMap<>();

		try {

			voluntaria = voluntariaService.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (voluntaria == null) {

			response.put("mensaje", "La voluntaria ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Voluntaria>(voluntaria, HttpStatus.OK);

	}

	@PostMapping("/voluntarias")

	public ResponseEntity<?> insertarVoluntaria(@Valid @RequestBody Voluntaria voluntaria, BindingResult result) {

		Voluntaria voluntariaNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			voluntariaNew = voluntariaService.save(voluntaria);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar la voluntaria en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La voluntaria se ha creado con exito!");
		response.put("voluntaria", voluntariaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/voluntarias/{id}")

	public ResponseEntity<?> actualizarVoluntaria(@Valid @RequestBody Voluntaria voluntaria, BindingResult result, @PathVariable Long id) {

		Voluntaria voluntariaActual = voluntariaService.findById(id);
		Voluntaria voluntariaUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}

		if (voluntariaActual == null) {
			
			response.put("mensaje", "Error: no se pudo editar, la voluntaria ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}

		try {
			
			voluntariaActual.setApellido1(voluntaria.getApellido1());
			voluntariaActual.setApellido2(voluntaria.getApellido2());
			voluntariaActual.setNombre(voluntaria.getNombre());
			voluntariaActual.setEmail(voluntaria.getEmail());
			voluntariaActual.setTelefono(voluntaria.getTelefono());
			voluntariaActual.setCiudad(voluntaria.getCiudad());
			
			voluntariaUpdated = voluntariaService.save(voluntariaActual);		
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar la voluntaria en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		response.put("mensaje", "La voluntaria se ha actualizado con exito!");
		response.put("voluntaria", voluntariaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/voluntarias/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Voluntaria voluntaria = voluntariaService.findById(id);
			

			voluntariaService.delete(id);
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la voluntaria en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La voluntaria se ha eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}


}
