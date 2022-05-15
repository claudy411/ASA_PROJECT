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

import com.asa.dto.ImgMascotaDto;
import com.asa.exceptions.ModelNotFoundException;
import com.asa.model.entity.ImagenMascota;
import com.asa.model.services.IImagenMascotaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/img-mascotas")
public class ImagenMascotaRestController {

	@Autowired
	private IImagenMascotaService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<ImgMascotaDto>> ver() throws Exception {

		List<ImgMascotaDto> lista = service.findAll().stream()
				.map(datosBBDD -> mapper.map(datosBBDD, ImgMascotaDto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<ImgMascotaDto>>(lista, HttpStatus.OK);

	}

//	@GetMapping("/page/{page}") // ojo aqui no va el dto
//	public Page<ImagenMascota> verPorPag(@PathVariable Integer page) {
//
//		return service.findAll(PageRequest.of(page, 4));
//
//	}

	@GetMapping("/{id}")
	public ResponseEntity<ImgMascotaDto> verPorId(@PathVariable Long id) throws Exception {

		ImagenMascota tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		ImgMascotaDto dtoResponse = mapper.map(tabla, ImgMascotaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ImgMascotaDto> insertar(@Valid @RequestBody ImgMascotaDto datosDelFront) throws Exception {

		ImagenMascota delFront = mapper.map(datosDelFront, ImagenMascota.class);
		ImagenMascota objetoTabla = service.save(delFront);
		ImgMascotaDto dtoResponse = mapper.map(objetoTabla, ImgMascotaDto.class);

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
	public ResponseEntity<ImgMascotaDto> actualizar(@Valid @RequestBody ImagenMascota datosDelFront) throws Exception {

		ImagenMascota delFront = mapper.map(datosDelFront, ImagenMascota.class);
		ImagenMascota consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		ImagenMascota tabla = service.modificar(delFront);
		ImgMascotaDto dtoResponse = mapper.map(tabla, ImgMascotaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		ImagenMascota consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);

		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
