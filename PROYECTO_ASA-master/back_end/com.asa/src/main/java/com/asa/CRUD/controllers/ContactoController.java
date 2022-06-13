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

import com.asa.CRUD.dto.ContactoDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.Contacto;
import com.asa.CRUD.model.services.interfaces.IContactoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/contacto")
public class ContactoController {
	

	@Autowired
	private IContactoService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<List<ContactoDto>> ver() throws Exception {

		List<ContactoDto> lista = service.findAll().stream().map(datosBBDD -> mapper.map(datosBBDD, ContactoDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ContactoDto>>(lista, HttpStatus.OK);

	}



	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<ContactoDto> verPorId(@PathVariable("id") Long id) throws Exception {

		Contacto tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		ContactoDto dtoResponse = mapper.map(tabla, ContactoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}


	@PostMapping
	public ResponseEntity<ContactoDto> insertar(@Valid @RequestBody ContactoDto datosDelFront) throws Exception {

		Contacto delFront = mapper.map(datosDelFront, Contacto.class);
		Contacto objetoTabla = service.save(delFront);
		ContactoDto dtoResponse = mapper.map(objetoTabla, ContactoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);

	}



	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<ContactoDto> actualizar(@Valid @RequestBody Contacto datosDelFront) throws Exception {

		Contacto delFront = mapper.map(datosDelFront, Contacto.class);
		Contacto consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		Contacto tabla = service.modificar(delFront);
		ContactoDto dtoResponse = mapper.map(tabla, ContactoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		Contacto consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}


}
