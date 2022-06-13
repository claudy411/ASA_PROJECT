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

import com.asa.CRUD.dto.LocalizacionDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.Localizacion;
import com.asa.CRUD.model.services.interfaces.ILocalizacionService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/localizaciones")
public class LocalizacionRestController {

	@Autowired
	private ILocalizacionService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO') or hasRole('PUBLIC')")
	public ResponseEntity<List<LocalizacionDto>> ver() throws Exception {

		List<LocalizacionDto> lista = service.findAll().stream()
				.map(datosBBDD -> mapper.map(datosBBDD, LocalizacionDto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<LocalizacionDto>>(lista, HttpStatus.OK);

	}

//	@GetMapping("/page/{page}") // ojo aqui no va el dto
//	public Page<Localizacion> verPorPag(@PathVariable Integer page) {
//
//		return service.findAll(PageRequest.of(page, 4));
//
//	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VOLUNTARIO') or hasRole('PUBLIC')")
	public ResponseEntity<LocalizacionDto> verPorId(@PathVariable("id") Long id) throws Exception {

		Localizacion tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		LocalizacionDto dtoResponse = mapper.map(tabla, LocalizacionDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<LocalizacionDto> insertar(@Valid @RequestBody LocalizacionDto datosDelFront)
			throws Exception {

		Localizacion delFront = mapper.map(datosDelFront, Localizacion.class);
		Localizacion objetoTabla = service.save(delFront);
		LocalizacionDto dtoResponse = mapper.map(objetoTabla, LocalizacionDto.class);

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
	public ResponseEntity<LocalizacionDto> actualizar(@Valid @RequestBody Localizacion datosDelFront) throws Exception {

		Localizacion delFront = mapper.map(datosDelFront, Localizacion.class);
		Localizacion consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		Localizacion tabla = service.modificar(delFront);
		LocalizacionDto dtoResponse = mapper.map(tabla, LocalizacionDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		Localizacion consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
