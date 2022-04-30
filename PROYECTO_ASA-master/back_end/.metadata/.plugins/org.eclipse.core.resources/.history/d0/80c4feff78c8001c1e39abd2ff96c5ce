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

import com.asa.model.entity.Evento;
import com.asa.model.services.IService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class EventoRestController {

	@Autowired
	private IService<Evento> eventoService;
	
	@GetMapping("/eventos")
	public List<Evento> verEventos() {

		return eventoService.findAll();

	}

	@GetMapping("/eventos/page/{page}")

	public Page<Evento> verEventosPag(@PathVariable Integer page) {

		return eventoService.findAll(PageRequest.of(page, 4));

	}

	@GetMapping("/eventos/{id}")

	public ResponseEntity<?> verEventoPorId(@PathVariable Long id) {

		Evento evento = null;
		Map<String, Object> response = new HashMap<>();

		try {

			evento = eventoService.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (evento == null) {

			response.put("mensaje", "El evento ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Evento>(evento, HttpStatus.OK);

	}

	@PostMapping("/eventos")

	public ResponseEntity<?> insertarEvento(@Valid @RequestBody Evento evento, BindingResult result) {

		Evento eventoNuevo = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			eventoNuevo = eventoService.save(evento);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar el evento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El evento se ha creado con exito!");
		response.put("evento", eventoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/eventos/{id}")

	public ResponseEntity<?> actualizarEvento(@Valid @RequestBody Evento evento, BindingResult result, @PathVariable Long id) {

		Evento eventoActual = eventoService.findById(id);
		Evento eventoUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}

		if (eventoActual == null) {
			
			response.put("mensaje", "Error: no se pudo editar, el evento ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}

		try {
			
			eventoActual.setDescripcion(evento.getDescripcion());
			eventoActual.setFecha(evento.getFecha());
//			eventoActual.setLocalizacion(evento.getLocalizacion());
			eventoActual.setNombre(evento.getNombre());
			eventoUpdated = eventoService.save(eventoActual);			
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar el evento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		response.put("mensaje", "El evento se ha actualizado con exito!");
		response.put("evento", eventoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/eventos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	
	public ResponseEntity<?> eliminarEvento(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Evento evento = eventoService.findById(id);

			eventoService.delete(id);
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar el evento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El evento se ha eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
}
