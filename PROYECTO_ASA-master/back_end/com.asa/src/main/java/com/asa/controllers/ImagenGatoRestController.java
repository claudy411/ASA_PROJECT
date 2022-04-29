package com.asa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.asa.model.entity.ImagenGato;
import com.asa.model.services.IImagenGatoService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa")
public class ImagenGatoRestController {
	@Autowired
	private IImagenGatoService imgService;
	
	@GetMapping("/imagenes_gatos/{id}")	
	public ResponseEntity<?> verPorId(@PathVariable Long id) {
		
		ImagenGato img= null;
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
		
		return new ResponseEntity<ImagenGato>(img, HttpStatus.OK);
	}
	
	@PostMapping("/imagenes_gatos")	
	public ResponseEntity<?> crearRegistro(@Valid @RequestBody ImagenGato nueva, BindingResult result) {
		
		ImagenGato img_nueva= null;
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

			response.put("mensaje", "Error al insertar la imagen del gato en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "La imagen del gato se ha insertado con exito");
		response.put("imagen", img_nueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	public ResponseEntity<?> verImagenesByGatoId(@PathVariable Long id_gato){
		
		List<ImagenGato> img= null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			img.add(imgService.verImagenById(id_gato));
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		if(img.isEmpty()) {
			response.put("mensaje",
					"El gato con ID: ".concat(id_gato.toString().concat(" no tiene imagenes en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<ImagenGato>>(img, HttpStatus.OK);
	}
}
