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

import com.asa.model.entity.Localizacion;
import com.asa.model.services.IService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class LocalizacionRestController {
	
	@Autowired
	private IService<Localizacion> locService;
	
	@GetMapping("/localizaciones")
	public List<Localizacion> verLocalizaciones() {

		return locService.findAll();

	}

	@GetMapping("/localizaciones/page/{page}")

	public Page<Localizacion> verLocalizacionesPag(@PathVariable Integer page) {

		return locService.findAll(PageRequest.of(page, 4));

	}

	@GetMapping("/localizaciones/{id}")

	public ResponseEntity<?> localizacionPorId(@PathVariable Long id) {

		Localizacion loc = null;
		Map<String, Object> response = new HashMap<>();

		try {

			loc = locService.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (loc == null) {

			response.put("mensaje", "La localizacion ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Localizacion>(loc, HttpStatus.OK);

	}

	@PostMapping("/localizaciones")

	public ResponseEntity<?> create(@Valid @RequestBody Localizacion localizacion, BindingResult result) {

		Localizacion locNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			locNew = locService.save(localizacion);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar la localizacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La localizacion se ha creado con exito!");
		response.put("localizacion", locNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/localizaciones/{id}")

	public ResponseEntity<?> update(@Valid @RequestBody Localizacion localizacion, BindingResult result, @PathVariable Long id) {

		Localizacion locActual = locService.findById(id);
		Localizacion locUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}

		if (locActual == null) {
			
			response.put("mensaje", "Error: no se pudo editar, la localizacion ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}

		try {
			
			locActual.setNombre(localizacion.getNombre());
			locActual.setCalle(localizacion.getCalle());
			locActual.setNumero(localizacion.getNumero());
			locActual.setCp(localizacion.getCp());
			locActual.setLocalidad(localizacion.getLocalidad());
//			locActual.setEncargado(localizacion.getEncargado());
			
			locUpdated = locService.save(locActual);			
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar la localizacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		response.put("mensaje", "La localizacion se ha actualizado con exito!");
		response.put("localizacion", locUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/localizaciones/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Localizacion loc = locService.findById(id);
			
			locService.delete(id);
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la localizacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La localizacion se ha eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

}
