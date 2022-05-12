package com.asa.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.asa.dto.MascotaDto;
import com.asa.exceptions.ModelNotFoundException;
import com.asa.model.entity.Mascota;
import com.asa.model.services.IMascotaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/mascotas")
public class MascotaRestController {

	@Autowired
	private IMascotaService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<MascotaDto>> ver() throws Exception {

		List<MascotaDto> lista = service.findAll().stream().map(datosBBDD -> mapper.map(datosBBDD, MascotaDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MascotaDto>>(lista, HttpStatus.OK);

	}

	@GetMapping("/page/{page}") // ojo aqui no va el dto
	public Page<Mascota> verPorPag(@PathVariable Integer page) {

		return service.findAll(PageRequest.of(page, 4));

	}

	@GetMapping("/{id}")
	public ResponseEntity<MascotaDto> verPorId(@PathVariable Long id) throws Exception {

		Mascota tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		MascotaDto dtoResponse = mapper.map(tabla, MascotaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<MascotaDto> insertar(@Valid @RequestBody MascotaDto datosDelFront) throws Exception {

		Mascota delFront = mapper.map(datosDelFront, Mascota.class);
		Mascota objetoTabla = service.save(delFront);
		MascotaDto dtoResponse = mapper.map(objetoTabla, MascotaDto.class);

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
	public ResponseEntity<MascotaDto> actualizar(@Valid @RequestBody Mascota datosDelFront) throws Exception {

		Mascota delFront = mapper.map(datosDelFront, Mascota.class);
		Mascota consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		Mascota tabla = service.modificar(delFront);
		MascotaDto dtoResponse = mapper.map(tabla, MascotaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		Mascota consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
