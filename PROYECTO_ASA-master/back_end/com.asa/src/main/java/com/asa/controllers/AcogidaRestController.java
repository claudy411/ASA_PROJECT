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

import com.asa.model.entity.Acogida;
import com.asa.model.services.IService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class AcogidaRestController {

	@Autowired
	private IService<Acogida> acogidaService;

	@GetMapping("/acogidas")
	public List<Acogida> ver() {

		return acogidaService.findAll();

	}

	@GetMapping("/acogidas/page/{page}")

	public Page<Acogida> verPorPag(@PathVariable Integer page) {

		return acogidaService.findAll(PageRequest.of(page, 4));

	}

	@GetMapping("/acogidas/{id}")

	public ResponseEntity<?> verPorId(@PathVariable Long id) {

		Acogida acogida = null;
		Map<String, Object> response = new HashMap<>();

		try {

			acogida = acogidaService.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (acogida == null) {

			response.put("mensaje",
					"El ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Acogida>(acogida, HttpStatus.OK);

	}

	@PostMapping("/acogidas")

	public ResponseEntity<?> insertar(@Valid @RequestBody Acogida acogida, BindingResult result) {

		Acogida acogidaNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			acogidaNew = acogidaService.save(acogida);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar la acogida en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La acogida se ha creado con exito!");
		response.put("acogida", acogidaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/acogidas/{id}")

	public ResponseEntity<?> actualizar(@Valid @RequestBody Acogida acogida, BindingResult result,
			@PathVariable Long id) {

		Acogida actual = acogidaService.findById(id);
		Acogida updated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (actual == null) {

			response.put("mensaje", "Error: no se pudo editar, la acogida ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		try {

			actual.setApellido1(acogida.getApellido1());
			actual.setApellido2(acogida.getApellido2());
			actual.setNombre(acogida.getNombre());
			actual.setEmail(acogida.getEmail());
			actual.setTelefono(acogida.getTelefono());
			actual.setCiudad(acogida.getCiudad());
			updated = acogidaService.save(actual);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al actualizar la acogida en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La acogida se ha actualizado con exito!");
		response.put("acogida", updated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/acogidas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)

	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {

			Acogida acogida = acogidaService.findById(id);

			acogidaService.delete(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la acogida en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "La acogida se ha eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
