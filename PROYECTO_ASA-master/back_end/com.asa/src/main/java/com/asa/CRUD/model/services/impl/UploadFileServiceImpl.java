package com.asa.CRUD.model.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.asa.CRUD.model.services.interfaces.IUploadFileService;

@Service

public class UploadFileServiceImpl implements IUploadFileService {

	private final static String DIRECTORIO_UPLOAD = "uploads/";

	@Override
	public Resource cargar(String nombreFoto,String carpeta) throws MalformedURLException {
		Path rutaArchivo = this.getPath(nombreFoto,carpeta);
		Resource recurso = new UrlResource(rutaArchivo.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {

			rutaArchivo = Paths.get("src/main/resources/static/images/mascotas").resolve("not_profile.jpg")
					.toAbsolutePath();

			recurso = new UrlResource(rutaArchivo.toUri());

		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo,String carpeta) throws IOException {
		
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = getPath(nombreArchivo,carpeta);

		Files.copy(archivo.getInputStream(), rutaArchivo);

		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto,String carpeta) {
		if (nombreFoto != null && nombreFoto.length() > 0) {
			Path rutaFotoAnterior =  getPath(nombreFoto,carpeta);
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		return false;
	}

//	@Override
//	public Path getPath(String nombreFoto) {
//
//		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
//	}

	@Override
	public Path getPath(String nombreFoto, String carpeta) {
		// TODO Auto-generated method stub
		return Paths.get(DIRECTORIO_UPLOAD+carpeta).resolve(nombreFoto).toAbsolutePath();
	}


}
