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

import com.asa.model.entity.Mascota;
import com.asa.model.services.IService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class MascotaRestController {
	
	@Autowired
	private IService<Mascota> mascotaService;
	
	@GetMapping("/mascotas")
	public List<Mascota> verMascotas() {

		return mascotaService.findAll();

	}

	@GetMapping("/mascotas/page/{page}")

	public Page<Mascota> verMascotasPag(@PathVariable Integer page) {

		return mascotaService.findAll(PageRequest.of(page, 4));

	}

	@GetMapping("/mascotas/{id}")

	public ResponseEntity<?> verMascotaPorId(@PathVariable Long id) {

		Mascota mascota = null;
		Map<String, Object> response = new HashMap<>();

		try {

			mascota = mascotaService.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (mascota == null) {

			response.put("mensaje", "La mascota ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);

	}

	@PostMapping("/mascotas")

	public ResponseEntity<?> insertarMascota(@Valid @RequestBody Mascota mascota, BindingResult result) {

		Mascota mascotaNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			mascotaNew = mascotaService.save(mascota);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar la mascota en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La mascota se ha creado con exito!");
		response.put("mascota", mascotaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/mascotas/{id}")

	public ResponseEntity<?> actualizarMascota(@Valid @RequestBody Mascota mascota, BindingResult result, @PathVariable Long id) {

		Mascota mascotaActual = mascotaService.findById(id);
		Mascota mascotaUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}

		if (mascotaActual == null) {
			
			response.put("mensaje", "Error: no se pudo editar, la mascota ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}

		try {
			
			mascotaActual.setNombre(mascota.getNombre());
			mascotaActual.setfNacimiento(mascota.getfEntrada());
			mascotaActual.setfEntrada(mascota.getfEntrada());
			mascotaActual.setRaza(mascota.getRaza());
			mascotaActual.setSexo(mascota.getSexo());
			mascotaActual.setCaracter(mascota.getCaracter());
			mascotaActual.setSituacion(mascota.getSituacion());
			mascotaActual.setTipo(mascota.getTipo());
			mascotaUpdated = mascotaService.save(mascotaActual);			
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar la mascota en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		response.put("mensaje", "La mascota se ha actualizado con exito!");
		response.put("mascota", mascotaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/mascotas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	
	public ResponseEntity<?> eliminarMascota(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Mascota evento = mascotaService.findById(id);

			mascotaService.delete(id);
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la mascota en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El evento se ha eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

}
