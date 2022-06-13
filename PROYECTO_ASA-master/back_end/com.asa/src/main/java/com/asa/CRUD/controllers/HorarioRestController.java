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

import com.asa.CRUD.dto.HorarioDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.Horario;
import com.asa.CRUD.model.services.interfaces.IHorarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/horarios")
public class HorarioRestController {

	@Autowired
	private IHorarioService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<List<HorarioDto>> ver() throws Exception {

		List<HorarioDto> lista = service.findAll().stream().map(datosBBDD -> mapper.map(datosBBDD, HorarioDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<HorarioDto>>(lista, HttpStatus.OK);

	}

//	@GetMapping("/page/{page}") // ojo aqui no va el dto
//	public Page<Horario> verPorPag(@PathVariable Integer page) {
//
//		return service.findAll(PageRequest.of(page, 4));
//
//	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<HorarioDto> verPorId(@PathVariable("id") String id) throws Exception {

		Horario tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		HorarioDto dtoResponse = mapper.map(tabla, HorarioDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<HorarioDto> insertar(@Valid @RequestBody HorarioDto datosDelFront) throws Exception {

		Horario delFront = mapper.map(datosDelFront, Horario.class);
		Horario objetoTabla = service.save(delFront);
		HorarioDto dtoResponse = mapper.map(objetoTabla, HorarioDto.class);

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


	@PutMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<HorarioDto> actualizar(@Valid @RequestBody Horario datosDelFront) throws Exception {

		Horario delFront = mapper.map(datosDelFront, Horario.class);
		Horario consultado = service.findById(delFront.getEvento());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getEvento());

		Horario tabla = service.modificar(delFront);
		HorarioDto dtoResponse = mapper.map(tabla, HorarioDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) throws Exception {

		Horario consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
