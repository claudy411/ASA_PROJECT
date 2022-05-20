package com.asa.CRUD.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asa.CRUD.dto.ImgMascotaDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.ImagenMascota;
import com.asa.CRUD.model.entity.Mascota;
import com.asa.CRUD.model.services.IImagenMascotaService;
import com.asa.CRUD.model.services.IMascotaService;
import com.asa.CRUD.model.services.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/img-mascotas")
public class ImagenMascotaRestController {

	@Autowired
	private IImagenMascotaService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IUploadFileService uploadService;
	
	@Autowired
	private IMascotaService mascotaService;
	
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
	public ResponseEntity<ImgMascotaDto> verPorId(@PathVariable("id") Long id) throws Exception {

		ImagenMascota tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		ImgMascotaDto dtoResponse = mapper.map(tabla, ImgMascotaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> insertar(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) throws Exception {
		
		Map<String, Object> response = new HashMap<>();
		
		Mascota mascota = mascotaService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen ");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			Path ruta=uploadService.getPath(nombreArchivo);
			ImagenMascota img= new ImagenMascota();
			
			img.setUrl(ruta.toString());
			img.setMascota(mascota);
			
			
			service.save(img);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//		ImagenMascota delFront = mapper.map(datosDelFront, ImagenMascota.class);
//		ImagenMascota objetoTabla = service.save(delFront);
//		ImgMascotaDto dtoResponse = mapper.map(objetoTabla, ImgMascotaDto.class);
//
//		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);

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
