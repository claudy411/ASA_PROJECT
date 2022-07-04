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

import com.asa.CRUD.dto.VoluntariaDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.Voluntaria;
import com.asa.CRUD.model.services.interfaces.IVoluntariaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/voluntarios")
public class VoluntariaRestController {

	@Autowired
	private IVoluntariaService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<List<VoluntariaDto>> ver() throws Exception {

		List<VoluntariaDto> lista = service.findAll().stream()
				.map(datosBBDD -> mapper.map(datosBBDD, VoluntariaDto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<VoluntariaDto>>(lista, HttpStatus.OK);

	}

//	@GetMapping("/page/{page}") // ojo aqui no va el dto
//	public Page<Voluntaria> verPorPag(@PathVariable Integer page) {
//
//		return service.findAll(PageRequest.of(page, 4));
//
//	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<VoluntariaDto> verPorId(@PathVariable("id") Long id) throws Exception {

		Voluntaria tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		VoluntariaDto dtoResponse = mapper.map(tabla, VoluntariaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}


	@PostMapping
	public ResponseEntity<VoluntariaDto> insertar(@Valid @RequestBody VoluntariaDto datosDelFront) throws Exception {

		Voluntaria delFront = mapper.map(datosDelFront, Voluntaria.class);
		Voluntaria objetoTabla = service.save(delFront);
		VoluntariaDto dtoResponse = mapper.map(objetoTabla, VoluntariaDto.class);

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
	public ResponseEntity<VoluntariaDto> actualizar(@Valid @RequestBody Voluntaria datosDelFront) throws Exception {

		Voluntaria delFront = mapper.map(datosDelFront, Voluntaria.class);
		Voluntaria consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		Voluntaria tabla = service.modificar(delFront);
		VoluntariaDto dtoResponse = mapper.map(tabla, VoluntariaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		Voluntaria consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
