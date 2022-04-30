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

import com.asa.model.entity.Perro;
import com.asa.model.entity.Residencia;
import com.asa.model.services.IService;
import com.asa.model.services.PerroServiceImpl;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class PerroRestController {

	@Autowired
	private IService<Perro> perroService;
	private PerroServiceImpl ps;
/*
	@Autowired
	private IUploadFileService uploadService;*/

	/**
	 * 
	 * @return devuelve todos los perros de la base de datos con toda la info
	 */
	@GetMapping("/perros")
	public List<Perro> verTodos() {

		return perroService.findAll();

	}

	/**
	 * 
	 * @param pagina
	 * @return 4 registros por pagina
	 */
	@GetMapping("/perros/page/{pagina}")
	public Page<Perro> verTodos(@PathVariable Integer pagina) {

		return perroService.findAll(PageRequest.of(pagina, 4));
	}

	/**
	 * 
	 * @param id perro
	 * @return los datos del perro cuyo id se pasa por parametro y un estado http
	 *         ok, en caso de que haya algun fallo, muestra mensaje y estado http
	 *         correspondiente
	 */
	@GetMapping("/perros/{id}")
	public ResponseEntity<?> verPorId(@PathVariable Long id) {

		Perro perro = null;
		Map<String, Object> response = new HashMap<>();

		try {

			perro = perroService.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (perro == null) {

			response.put("mensaje",
					"El perro con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Perro>(perro, HttpStatus.OK);

	}

	/**
	 * 
	 * @param perro  a insertar
	 * @param result
	 * @return en caso de exito, un mensaje de exito y los datos del perro añadido
	 *         en caso de fallo, el mensaje correspondiente al error
	 */
	@PostMapping("/perros")
	public ResponseEntity<?> crearRegistro(@Valid @RequestBody Perro nuevo, BindingResult result) {

		Perro perro_nuevo = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			perro_nuevo = perroService.save(nuevo);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar el perro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El perro se ha insertado con exito");
		response.put("perro", perro_nuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	/**
	 * 
	 * @param los    nuevos datos del perro a actualizar
	 * @param result
	 * @param id     del perro que se quiere actualizar
	 * @return en caso de exito, mensaje y los nuevos datos en caso se fracaso
	 *         mensaje correspondiente
	 */
	@PutMapping("/perros/{id}")
	public ResponseEntity<?> actualizarRegistro(@Valid @RequestBody Perro perro, BindingResult result,
			@PathVariable Long id) {

		Perro perro_existente = perroService.findById(id);
		Perro perro_actualizar = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (perro_existente == null) {

			response.put("mensaje", "No se pudo actualizar el perro con ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			perro_existente.setNombre(perro.getNombre());
			perro_existente.setDescripcion(perro.getDescripcion());
			perro_existente.setfEntrada(perro.getfEntrada());
			perro_existente.setfNacimiento(perro.getfNacimiento());
			perro_existente.setRaza(perro.getRaza());
			perro_existente.setSexo(perro.getSexo());
			perro_existente.setSituacion(perro.getSituacion());
			perro_existente.setSize(perro.getSize());
			perro_existente.setResidencia(perro.getResidencia());
			// falta para los campos de las tablas relacionadas

			perro_actualizar = perroService.save(perro_existente);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al actualizar el perro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "Los datos del perro se han actualizado con exito!");
		response.put("perro", perro_actualizar);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/perros/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> eliminar(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {

			Perro perro= perroService.findById(id);
			
			perroService.delete(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar el perro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "Los datos del perro se han eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/perros/residencias")
	public List<Residencia> findAllResidencias() {

		return ps.findAllResidencias();

	}
}
