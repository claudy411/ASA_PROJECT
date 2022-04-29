package com.asa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asa.model.entity.ImagenPerro;
import com.asa.model.entity.Perro;
import com.asa.model.services.IImagenPerroService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class ImagenPerroRestController {

	
	@Autowired
	private IImagenPerroService imgService;
	
	@GetMapping("/imagenes_perros/{id}")	
	public ResponseEntity<?> verPorId(@PathVariable Long id) {
		
		ImagenPerro img= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			img=imgService.verImagenById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		if(img==null) {
			response.put("mensaje",
					"La imagen con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ImagenPerro>(img, HttpStatus.OK);
	}
	
	@PostMapping("/imagenes_perros")	
	public ResponseEntity<?> crearRegistro(@Valid @RequestBody ImagenPerro nueva, BindingResult result) {
		
		ImagenPerro img_nueva= null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			img_nueva= imgService.incluirImagen(img_nueva);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al insertar la imagen del perro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "La imagen del perro se ha insertado con exito");
		response.put("imagen", img_nueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	public ResponseEntity<?> verImagenesByPerroId(@PathVariable Long id_perro){
		
		List<ImagenPerro> img= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			img.add(imgService.verImagenById(id_perro));
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		if(img.isEmpty()) {
			response.put("mensaje",
					"El perro con ID: ".concat(id_perro.toString().concat(" no tiene imagenes en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<ImagenPerro>>(img, HttpStatus.OK);
	}
}
