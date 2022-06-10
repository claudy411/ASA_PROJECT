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

import com.asa.CRUD.dto.MascotaDto;
import com.asa.CRUD.exceptions.ModelNotFoundException;
import com.asa.CRUD.model.entity.Mascota;
import com.asa.CRUD.model.services.interfaces.IMascotaService;
import com.asa.CRUD.model.services.interfaces.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/asa/mascotas")
public class MascotaRestController {

	@Autowired
	private IMascotaService service;

	@Autowired
	private IUploadFileService uploadService;
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<MascotaDto>> ver() throws Exception {

		List<MascotaDto> lista = service.findAll().stream().map(datosBBDD -> mapper.map(datosBBDD, MascotaDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MascotaDto>>(lista, HttpStatus.OK);

	}

//	@GetMapping("/page/{page}") // ojo aqui no va el dto
//	public Page<Mascota> verPorPag(@PathVariable Integer page) {
//
//		return service.findAll(PageRequest.of(page, 4));
//
//	}
//
	@GetMapping("/{id}")
	public ResponseEntity<MascotaDto> verPorId(@PathVariable("id") Long id) throws Exception {

		Mascota tabla = service.findById(id);

		if (tabla == null)
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);

		MascotaDto dtoResponse = mapper.map(tabla, MascotaDto.class);

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);

	}
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<MascotaDto>> verPorTipo(@PathVariable("tipo") String tipo) throws Exception {

		List<MascotaDto> lista = service.buscarPorTipo(tipo).stream().map(datosBBDD -> mapper.map(datosBBDD, MascotaDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MascotaDto>>(lista, HttpStatus.OK);

	}
	
	@GetMapping("/residencia/{id}")
	public ResponseEntity<List<MascotaDto>> verPorResidencia(@PathVariable("id") Long id) throws Exception {

		List<MascotaDto> lista = service.buscarPorResidencia(id).stream().map(datosBBDD -> mapper.map(datosBBDD, MascotaDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MascotaDto>>(lista, HttpStatus.OK);

	}
	
	@GetMapping("/situacion/{situacion}")
	public ResponseEntity<List<MascotaDto>> verPorSituacion(@PathVariable("situacion") String situacion) throws Exception {

		List<MascotaDto> lista = service.buscarPorSituacion(situacion).stream().map(datosBBDD -> mapper.map(datosBBDD, MascotaDto.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MascotaDto>>(lista, HttpStatus.OK);

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

//	@PreAuthorize("hasRole('ADMIN')")
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

		uploadService.eliminar(consultado.getFotoPerfil(),"mascotas");
		service.delete(id);
		

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,@RequestParam("id") Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		Mascota mascota = service.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo,"mascotas");
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = mascota.getFotoPerfil();
			
			uploadService.eliminar(nombreFotoAnterior,"mascotas");
						
			mascota.setFotoPerfil(nombreArchivo);
			
			service.save(mascota);
			
			response.put("mascota", mascota);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/mascotas/{id}")
	@ResponseBody
	public ResponseEntity<Resource> verFoto(@PathVariable("id") Long id){

		Resource recurso = null;
		String nombreFoto= service.verFoto(id);
		
		try {
			recurso = uploadService.cargar(nombreFoto,"mascotas");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
//	public ResponseEntity<ByteArrayResource> verFoto(@PathVariable String nombreFoto){
//
//	
//		if(nombreFoto!=null && !nombreFoto.isEmpty()) {
//			
//			Path fileName= uploadService.getPath(nombreFoto);
//			try {
//				byte[] buffer= Files.readAllBytes(fileName);
//				ByteArrayResource recurso= new ByteArrayResource(buffer);
//				return ResponseEntity.ok().contentLength(buffer.length)
//						.contentType(MediaType.parseMediaType("image/png"))
//						.body(recurso);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		return ResponseEntity.badRequest().build();
//	}

}
