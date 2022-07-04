package com.asa.CRUD.model.services.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	public Resource cargar(String nombreFoto,String carpeta) throws MalformedURLException;
	
	public String copiar(MultipartFile archivo,String carpeta) throws IOException;
	
	public boolean eliminar (String nombreFoto,String carpeta);
	
	public Path getPath(String nombreFoto,String carpeta);

	
}
