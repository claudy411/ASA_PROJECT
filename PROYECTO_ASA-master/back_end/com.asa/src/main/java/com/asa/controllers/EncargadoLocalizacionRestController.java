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
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.asa.model.entity.EncargadoLocalizacion;
import com.asa.model.services.IService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class EncargadoLocalizacionRestController {
	
	@Autowired
	private IService<EncargadoLocalizacion> encargadoService;
	
	@GetMapping("/encargados")
	public List<EncargadoLocalizacion> verEncargados() {

		return encargadoService.findAll();

	}

	@GetMapping("/encargados/page/{page}")

	public Page<EncargadoLocalizacion> verEncargadosPorPAg(@PathVariable Integer page) {

		return encargadoService.findAll(PageRequest.of(page, 4));

	}

	@GetMapping("/encargados/{id}")

	public ResponseEntity<?> verEncargadoPorId(@PathVariable Long id) {

		EncargadoLocalizacion encargado = null;
		Map<String, Object> response = new HashMap<>();

		try {

			encargado = encargadoService.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (encargado == null) {

			response.put("mensaje", "El encargado ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<EncargadoLocalizacion>(encargado, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/encargados")

	public ResponseEntity<?> insertarEncargado(@Valid @RequestBody EncargadoLocalizacion encargado, BindingResult result) {

		EncargadoLocalizacion encargadoNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			encargadoNew = encargadoService.save(encargado);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar el encargado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El encargado se ha creado con exito!");
		response.put("encargado", encargadoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/encargados/{id}")

	public ResponseEntity<?> actualizarEncargado(@Valid @RequestBody EncargadoLocalizacion encargado, BindingResult result, @PathVariable Long id) {

		EncargadoLocalizacion encargadoActual = encargadoService.findById(id);
		EncargadoLocalizacion encargadoUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}

		if (encargadoActual == null) {
			
			response.put("mensaje", "Error: no se pudo editar, el encargado ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}

		try {
			
			encargadoActual.setApellido1(encargado.getApellido1());
			encargadoActual.setApellido2(encargado.getApellido2());
			encargadoActual.setNombre(encargado.getNombre());
			encargadoActual.setEmail(encargado.getEmail());
			encargadoActual.setTelefono(encargado.getTelefono());
			
			encargadoUpdated = encargadoService.save(encargadoActual);		
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar el encargado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		response.put("mensaje", "El encargado se ha actualizado con exito!");
		response.put("encargado", encargadoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/encargados/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			EncargadoLocalizacion cliente = encargadoService.findById(id);
			

			encargadoService.delete(id);
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar el encargado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El encargado se ha eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}


}
