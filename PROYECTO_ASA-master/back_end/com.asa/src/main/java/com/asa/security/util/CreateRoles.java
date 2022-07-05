package com.asa.security.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.asa.security.enumerados.RolNombre;
import com.asa.security.model.entity.Rol;
import com.asa.security.model.service.RolService;

/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

@Component
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RolService rolService;

	@Override
	public void run(String... args) throws Exception {
		Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
		Rol rolVoluntario = new Rol(RolNombre.ROLE_VOLUNTARIO);
		Rol rolPublic= new Rol(RolNombre.ROLE_PUBLIC);
		rolService.save(rolAdmin);
		rolService.save(rolVoluntario);
		rolService.save(rolPublic);
		
		File directorio = new File("/uploads");
		File eventos= new File("/uploads/eventos");
		File mascotas= new File("/uploads/mascotas");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
               eventos.mkdir();
               mascotas.mkdir();
            } else {
                System.out.println("Error al crear directorio");
            }
        }
	}
}
