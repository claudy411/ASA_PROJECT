package com.asa.controllers;

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

import com.asa.dto.PadrinoDto;
import com.asa.exceptions.ModelNotFoundException;
import com.asa.model.entity.Padrino;
import com.asa.model.services.IAcogidaService;
import com.asa.model.services.IPadrinoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/padrinos")
public class PadrinoRestController {

	@Autowired
	private IPadrinoService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<PadrinoDto>> ver() throws Exception {

		List<PadrinoDto> lista = service.findAll().stream().map(datosBBDD -> mapper.map(datosBBDD, PadrinoDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<PadrinoDto>>(lista, HttpStatus.OK);

	}

//	@GetMapping("/page/{page}") // ojo aqui no va el dto
//	public Page<Acogida> verPorPag(@PathVariable Integer page) {
//
//		return service.findAll(PageRequest.of(page, 4));
//
//	}

	@GetMapping("/{id}")
	public ResponseEntity<PadrinoDto> verPorId(@PathVariable("id") Long id) throws Exception {

		Padrino tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		PadrinoDto dtoResponse = mapper.map(tabla, PadrinoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PadrinoDto> insertar(@Valid @RequestBody PadrinoDto datosDelFront) throws Exception {

		Padrino delFront = mapper.map(datosDelFront, Padrino.class);
		Padrino objetoTabla = service.save(delFront);
		PadrinoDto dtoResponse = mapper.map(objetoTabla, PadrinoDto.class);

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
	public ResponseEntity<PadrinoDto> actualizar(@Valid @RequestBody Padrino datosDelFront) throws Exception {

		Padrino delFront = mapper.map(datosDelFront, Padrino.class);
		Padrino consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		Padrino tabla = service.modificar(delFront);
		PadrinoDto dtoResponse = mapper.map(tabla, PadrinoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		Padrino consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
