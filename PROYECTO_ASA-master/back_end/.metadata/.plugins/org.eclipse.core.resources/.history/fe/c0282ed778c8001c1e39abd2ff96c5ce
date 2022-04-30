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

import com.asa.model.entity.Adoptante;
import com.asa.model.services.IService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class AdoptanteRestController {

	@Autowired
	private IService<Adoptante> adoptanteService;

	@GetMapping("/adoptantes")
	public List<Adoptante> verAdoptantes() {

		return adoptanteService.findAll();

	}

	@GetMapping("/adoptantes/page/{page}")

	public Page<Adoptante> verAdoptantesPorPag(@PathVariable Integer page) {

		return adoptanteService.findAll(PageRequest.of(page, 4));

	}

	@GetMapping("/adoptantes/{id}")

	public ResponseEntity<?> verAdoptantePorId(@PathVariable Long id) {

		Adoptante adoptante = null;
		Map<String, Object> response = new HashMap<>();

		try {

			adoptante = adoptanteService.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (adoptante == null) {

			response.put("mensaje", "El adoptante ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Adoptante>(adoptante, HttpStatus.OK);

	}

	@PostMapping("/adoptantes")

	public ResponseEntity<?> insertarAdoptante(@Valid @RequestBody Adoptante adoptante, BindingResult result) {

		Adoptante adoptanteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			adoptanteNew = adoptanteService.save(adoptante);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar el adoptante en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El adoptante se ha creado con exito!");
		response.put("adoptante", adoptanteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/adoptantes/{id}")

	public ResponseEntity<?> actualizarAdoptante(@Valid @RequestBody Adoptante adoptante, BindingResult result, @PathVariable Long id) {

		Adoptante adoptanteActual = adoptanteService.findById(id);
		Adoptante adoptanteUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}

		if (adoptanteActual == null) {
			
			response.put("mensaje", "Error: no se pudo editar, el adoptante ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}

		try {
			
			adoptanteActual.setApellido1(adoptante.getApellido1());
			adoptanteActual.setApellido2(adoptante.getApellido2());
			adoptanteActual.setNombre(adoptante.getNombre());
			adoptanteActual.setEmail(adoptante.getEmail());
			adoptanteActual.setTelefono(adoptante.getTelefono());
			adoptanteActual.setCiudad(adoptante.getCiudad());
			adoptanteUpdated = adoptanteService.save(adoptanteActual);		
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar el adoptante en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		response.put("mensaje", "El adoptante se ha actualizado con exito!");
		response.put("adoptante", adoptanteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/adoptantes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Adoptante adoptante = adoptanteService.findById(id);
			

			adoptanteService.delete(id);
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar el adoptante en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El adoptante se ha eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}


}

