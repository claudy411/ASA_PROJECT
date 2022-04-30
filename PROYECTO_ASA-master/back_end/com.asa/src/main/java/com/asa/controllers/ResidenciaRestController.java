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


import com.asa.model.entity.Residencia;
import com.asa.model.services.IService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class ResidenciaRestController {
	
	@Autowired
	private IService<Residencia> resiService;
	
	@GetMapping("/residencias")
	public List<Residencia> verResidencias(){
		
		return resiService.findAll();
	}
	
	@GetMapping("/residencias/page/{page}")
	public Page<Residencia> verResidenciasPorPaginas(@PathVariable Integer page){
		
		return resiService.findAll(PageRequest.of(page, 4));
	}
	
	@GetMapping("/residencias/{id}")
	public ResponseEntity<?> verResidenciasPorId(@PathVariable Long id) {
		
		Residencia resi=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			resi= resiService.findById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		if(resi==null) {
			response.put("mensaje", "La residencia con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		
		return new ResponseEntity<Residencia>(resi,HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/residencias")
	public ResponseEntity<?> insertarResidencia(@Valid @RequestBody Residencia residencia, BindingResult result) {

		Residencia resi_nueva = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			resi_nueva = resiService.save(residencia);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar la residencia en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La residencia se ha creado con exito!");
		response.put("residencia", resi_nueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/residencias/{id}")

	public ResponseEntity<?> actualizarResidencia(@Valid @RequestBody Residencia residencia, BindingResult result, @PathVariable Long id) {

		Residencia resiActual = resiService.findById(id);
		Residencia resiUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}

		if (resiActual == null) {
			
			response.put("mensaje", "Error: no se pudo editar, la residencia ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		}

		try {
					
			resiActual.setNombre(residencia.getNombre());
			resiActual.setPrecio(residencia.getPrecio());
			resiActual.setPropietario(residencia.getPropietario());
			resiActual.setTelefono(residencia.getTelefono());
			resiActual.setCalle(residencia.getCalle());
			resiActual.setNumero(residencia.getNumero());
			resiActual.setCp(residencia.getCp());
			resiActual.setLocalidad(residencia.getLocalidad());
			
			resiUpdated = resiService.save(resiActual);	
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar la residencia en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		response.put("mensaje", "La residencia se ha actualizado con exito!");
		response.put("residencia", resiUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/residencias/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Residencia residencia = resiService.findById(id);
			
			resiService.delete(id);
			
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la residencia en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "La residencia se ha eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

	
}
