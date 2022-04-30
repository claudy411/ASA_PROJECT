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

import com.asa.model.entity.Gato;
import com.asa.model.services.IService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class GatoRestController {
	
	@Autowired
	private IService<Gato> gatoService;
/*
	@Autowired
	private IUploadFileService uploadService;*/

	/**
	 * 
	 * @return devuelve todos los perros de la base de datos con toda la info
	 */
	@GetMapping("/gatos")
	public List<Gato> verTodos() {

		return gatoService.findAll();

	}

	/**
	 * 
	 * @param pagina
	 * @return 4 registros por pagina
	 */
	@GetMapping("/gatos/pagina/{pagina}")
	public Page<Gato> verTodos(@PathVariable Integer pagina) {

		return gatoService.findAll(PageRequest.of(pagina, 4));
	}

	/**
	 * 
	 * @param id gato
	 * @return los datos del gato cuyo id se pasa por parametro y un estado http
	 *         ok, en caso de que haya algun fallo, muestra mensaje y estado http
	 *         correspondiente
	 */
	@GetMapping("/gatos/{id}")
	public ResponseEntity<?> verPorId(@PathVariable Long id) {

		Gato gato = null;
		Map<String, Object> response = new HashMap<>();

		try {

			gato = gatoService.findById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (gato == null) {

			response.put("mensaje",
					"El gato con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Gato>(gato, HttpStatus.OK);

	}

	/**
	 * 
	 * @param gato  a insertar
	 * @param result
	 * @return en caso de exito, un mensaje de exito y los datos del gato añadido
	 *         en caso de fallo, el mensaje correspondiente al error
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/gatos")
	public ResponseEntity<?> crearRegistro(@Valid @RequestBody Gato nuevo, BindingResult result) {

		Gato gato_nuevo = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			gato_nuevo = gatoService.save(nuevo);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar el gato en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El gato se ha insertado con exito");
		response.put("gato", gato_nuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	/**
	 * 
	 * @param los    nuevos datos del gato a actualizar
	 * @param result
	 * @param id     del gato que se quiere actualizar
	 * @return en caso de exito, mensaje y los nuevos datos en caso se fracaso
	 *         mensaje correspondiente
	 */
	@PutMapping("/gatos/{id}")
	public ResponseEntity<?> actualizarRegistro(@Valid @RequestBody Gato gato, BindingResult result,
			@PathVariable Long id) {

		Gato gato_existente = gatoService.findById(id);
		Gato gato_actualizar = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (gato_existente == null) {

			response.put("mensaje", "No se pudo actualizar el gato con ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			gato_existente.setNombre(gato.getNombre());
			gato_existente.setDescripcion(gato.getDescripcion());
			gato_existente.setfEntrada(gato.getfEntrada());
			gato_existente.setfNacimiento(gato.getfNacimiento());
			gato_existente.setRaza(gato.getRaza());
			gato_existente.setSexo(gato.getSexo());
			gato_existente.setSituacion(gato.getSituacion());
			gato_existente.setSize(gato.getSize());
			// falta para los campos de las tablas relacionadas

			gato_actualizar = gatoService.save(gato_existente);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al actualizar el gato en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "Los datos del gato se han actualizado con exito!");
		response.put("gato", gato_actualizar);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/gatos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> eliminar(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {

			Gato gato= gatoService.findById(id);
			
			gatoService.delete(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar el gato en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "Los datos del gato se han eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}


}
