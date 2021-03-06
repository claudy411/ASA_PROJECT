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

import com.asa.CRUD.dto.EncargadoDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.EncargadoLocalizacion;
import com.asa.CRUD.model.services.interfaces.IEncargadoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/encargados")
public class EncargadoLocalizacionRestController {

	@Autowired
	private IEncargadoService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<List<EncargadoDto>> ver() throws Exception {

		List<EncargadoDto> lista = service.findAll().stream()
				.map(datosBBDD -> mapper.map(datosBBDD, EncargadoDto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<EncargadoDto>>(lista, HttpStatus.OK);

	}

	@GetMapping("/localizacion/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<List<EncargadoDto>> verPorLocalizacion(@PathVariable("id") Long id) throws Exception {
		
		List<EncargadoDto> lista = service.buscarPorLocalizacion(id).stream()
				.map(datosBBDD -> mapper.map(datosBBDD, EncargadoDto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<EncargadoDto>>(lista, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO')")
	public ResponseEntity<EncargadoDto> verPorId(@PathVariable("id") Long id) throws Exception {

		EncargadoLocalizacion tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		EncargadoDto dtoResponse = mapper.map(tabla, EncargadoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<EncargadoDto> insertar(@Valid @RequestBody EncargadoDto datosDelFront) throws Exception {

		EncargadoLocalizacion delFront = mapper.map(datosDelFront, EncargadoLocalizacion.class);
		EncargadoLocalizacion objetoTabla = service.save(delFront);
		EncargadoDto dtoResponse = mapper.map(objetoTabla, EncargadoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);

	}

	// la madurez del se??or Richardson

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
	public ResponseEntity<EncargadoDto> actualizar(@Valid @RequestBody EncargadoLocalizacion datosDelFront)
			throws Exception {

		EncargadoLocalizacion delFront = mapper.map(datosDelFront, EncargadoLocalizacion.class);
		EncargadoLocalizacion consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		EncargadoLocalizacion tabla = service.modificar(delFront);
		EncargadoDto dtoResponse = mapper.map(tabla, EncargadoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		EncargadoLocalizacion consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
