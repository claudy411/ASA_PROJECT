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

import com.asa.CRUD.dto.AdoptanteDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.Adoptante;
import com.asa.CRUD.model.services.interfaces.IAdoptanteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/adoptantes")
public class AdoptanteRestController {

	@Autowired
	private IAdoptanteService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<List<AdoptanteDto>> ver() throws Exception {

		List<AdoptanteDto> lista = service.findAll().stream().map(datosBBDD -> mapper.map(datosBBDD, AdoptanteDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<AdoptanteDto>>(lista, HttpStatus.OK);

	}

//	@GetMapping("/page/{page}") // ojo aqui no va el dto
//	public Page<Adoptante> verPorPag(@PathVariable Integer page) {
//
//		return service.findAll(PageRequest.of(page, 4));
//
//	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<AdoptanteDto> verPorId(@PathVariable("id") Long id) throws Exception {

		Adoptante tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		AdoptanteDto dtoResponse = mapper.map(tabla, AdoptanteDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<AdoptanteDto> insertar(@Valid @RequestBody AdoptanteDto datosDelFront) throws Exception {

		Adoptante delFront = mapper.map(datosDelFront, Adoptante.class);
		Adoptante objetoTabla = service.save(delFront);
		AdoptanteDto dtoResponse = mapper.map(objetoTabla, AdoptanteDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);

	}

	// la madurez del señor Richardson

//	@PostMapping
//	public ResponseEntity<Void> insertar(@Valid @RequestBody AcogidaDto datosDelFront) throws Exception {
//		
//		Acogida objeto = mapper.map(datosDelFront, Acogida.class);
//		Acogida objetoTabla= acogidaService.save(objeto);
//		AcogidaDto dtoResponse= mapper.map(objetoTabla, AcogidaDto.class);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dtoResponse.getId()).toUri();
//		return ResponseEntity.created(location).build();
//		
//	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<AdoptanteDto> actualizar(@Valid @RequestBody Adoptante datosDelFront) throws Exception {

		Adoptante delFront = mapper.map(datosDelFront, Adoptante.class);
		Adoptante consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		Adoptante tabla = service.modificar(delFront);
		AdoptanteDto dtoResponse = mapper.map(tabla, AdoptanteDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		Adoptante consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}

