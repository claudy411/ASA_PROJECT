package com.asa.CRUD.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asa.CRUD.dto.AdopcionDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.Adopcion;
import com.asa.CRUD.model.services.interfaces.IAdopcionService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/adopciones")
public class AdopcionRestController {
	
	@Autowired
	private IAdopcionService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<AdopcionDto>> ver() throws Exception {

		List<AdopcionDto> lista = service.findAll().stream().map(datosBBDD -> mapper.map(datosBBDD, AdopcionDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<AdopcionDto>>(lista, HttpStatus.OK);

	}

//	@GetMapping("/page/{page}") // ojo aqui no va el dto
//	public Page<Acogida> verPorPag(@PathVariable Integer page) {
//
//		return service.findAll(PageRequest.of(page, 4));
//
//	}

	@GetMapping("/{id}")
	public ResponseEntity<AdopcionDto> verPorId(@PathVariable("id") Long id) throws Exception {

		Adopcion tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		AdopcionDto dtoResponse = mapper.map(tabla, AdopcionDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}


	@PostMapping
	public ResponseEntity<AdopcionDto> insertar(@Valid @RequestBody AdopcionDto datosDelFront) throws Exception {

		Adopcion delFront = mapper.map(datosDelFront, Adopcion.class);
		Adopcion objetoTabla = service.save(delFront);
		AdopcionDto dtoResponse = mapper.map(objetoTabla, AdopcionDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);

	}

	// la madurez del señor Richardson

//	@PostMapping
//	public ResponseEntity<Void> insertar(@Valid @RequestBody AcogidaDto datosDelFront) throws Exception {
//		
//		Acogida objeto = mapper.map(datosDelFront, Acogida.class);
//		Acogida objetoTabla= service.save(objeto);
//		AcogidaDto dtoResponse= mapper.map(objetoTabla, AcogidaDto.class);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dtoResponse.getId()).toUri();
//		return ResponseEntity.created(location).build();
//		
//	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<AdopcionDto> actualizar(@Valid @RequestBody Adopcion datosDelFront) throws Exception {

		Adopcion delFront = mapper.map(datosDelFront, Adopcion.class);
		Adopcion consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		Adopcion tabla = service.modificar(delFront);
		AdopcionDto dtoResponse = mapper.map(tabla, AdopcionDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		Adopcion consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
