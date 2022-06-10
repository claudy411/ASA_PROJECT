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

import com.asa.CRUD.dto.AcogidaDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.Acogida;
import com.asa.CRUD.model.services.interfaces.IAcogidaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/acogidas")
public class AcogidaRestController {

	@Autowired
	private IAcogidaService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<AcogidaDto>> ver() throws Exception {

		List<AcogidaDto> lista = service.findAll().stream().map(datosBBDD -> mapper.map(datosBBDD, AcogidaDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<AcogidaDto>>(lista, HttpStatus.OK);

	}



	@GetMapping("/{id}")
	public ResponseEntity<AcogidaDto> verPorId(@PathVariable("id") Long id) throws Exception {

		Acogida tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		AcogidaDto dtoResponse = mapper.map(tabla, AcogidaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<AcogidaDto> insertar(@Valid @RequestBody AcogidaDto datosDelFront) throws Exception {

		Acogida delFront = mapper.map(datosDelFront, Acogida.class);
		Acogida objetoTabla = service.save(delFront);
		AcogidaDto dtoResponse = mapper.map(objetoTabla, AcogidaDto.class);

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
	public ResponseEntity<AcogidaDto> actualizar(@Valid @RequestBody Acogida datosDelFront) throws Exception {

		Acogida delFront = mapper.map(datosDelFront, Acogida.class);
		Acogida consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		Acogida tabla = service.modificar(delFront);
		AcogidaDto dtoResponse = mapper.map(tabla, AcogidaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		Acogida consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
