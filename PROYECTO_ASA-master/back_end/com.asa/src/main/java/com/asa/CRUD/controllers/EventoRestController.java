package com.asa.CRUD.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asa.CRUD.dto.EventoDto;
import com.asa.CRUD.dto.LocalizacionDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.Evento;
import com.asa.CRUD.model.services.interfaces.IEventoService;
import com.asa.CRUD.model.services.interfaces.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/eventos")
public class EventoRestController {

	@Autowired
	private IEventoService service;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IUploadFileService uploadService;

	@GetMapping
	public ResponseEntity<List<EventoDto>> ver() throws Exception {

		List<EventoDto> lista = service.findAll().stream().map(datosBBDD -> mapper.map(datosBBDD, EventoDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<EventoDto>>(lista, HttpStatus.OK);

	}

	@GetMapping("/localizacion/{id}")
	public ResponseEntity<List<LocalizacionDto>> verLocalizacionPorID(@PathVariable("id") Long id) throws Exception {

		List<LocalizacionDto> lista = service.buscarPorLocalizacion(id).stream()
				.map(datosBBDD -> mapper.map(datosBBDD, LocalizacionDto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<LocalizacionDto>>(lista, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<EventoDto> verPorId(@PathVariable("id") Long id) throws Exception {

		Evento tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		EventoDto dtoResponse = mapper.map(tabla, EventoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<EventoDto> insertar(@RequestBody EventoDto datosDelFront) throws Exception {

		Evento delFront = mapper.map(datosDelFront, Evento.class);

		Evento objetoTabla = service.save(delFront);
		EventoDto dtoResponse = mapper.map(objetoTabla, EventoDto.class);

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
	public ResponseEntity<EventoDto> actualizar(@Valid @RequestBody Evento datosDelFront) throws Exception {

		Evento delFront = mapper.map(datosDelFront, Evento.class);
		Evento consultado = service.findById(delFront.getId());

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + delFront.getId());

		Evento tabla = service.modificar(delFront);
		EventoDto dtoResponse = mapper.map(tabla, EventoDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {

		Evento consultado = service.findById(id);

		if (consultado == null)
			throw new ModelNotFoundException("ID NO ECONTRADO: " + id);
		uploadService.eliminar(consultado.getFoto(), "eventos");
		service.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id)
			throws Exception {
		Map<String, Object> response = new HashMap<>();

		Evento evento = service.findById(id);

		if (!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo,"eventos");
				
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = evento.getFoto();

			uploadService.eliminar(nombreFotoAnterior,"eventos");

			evento.setFoto(nombreArchivo);

			service.save(evento);

			response.put("evento", evento);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/uploads/eventos/{id}")
	@ResponseBody
	public ResponseEntity<Resource> verFoto(@PathVariable("id") Long id) {

		Resource recurso = null;
		String nombreFoto = service.verFoto(id);

		try {
			recurso = uploadService.cargar(nombreFoto,"eventos");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

}
