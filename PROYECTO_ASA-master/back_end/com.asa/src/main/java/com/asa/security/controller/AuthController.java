package com.asa.security.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asa.security.dto.JwtDto;
import com.asa.security.dto.LoginUsuario;
import com.asa.security.dto.NuevoUsuario;
import com.asa.security.enumerados.RolNombre;
import com.asa.security.jwt.JwtProvider;
import com.asa.security.model.entity.Rol;
import com.asa.security.model.entity.Usuario;
import com.asa.security.model.service.RolService;
import com.asa.security.model.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
    	
    	Map<String, Object> response = new HashMap<>();
    	
        if(bindingResult.hasErrors()) {
        	response.put("mensaje", "Campos mal puestos!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        	
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
        	response.put("mensaje","ese nombre ya existe");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
           
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
        	response.put("mensaje","ese email ya existe");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
           
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        if(nuevoUsuario.getRoles().contains("public")) {
        	 roles.add(rolService.getByRolNombre(RolNombre.ROLE_PUBLIC).get());
        }else {
        	 roles.add(rolService.getByRolNombre(RolNombre.ROLE_VOLUNTARIO).get());
             
             if(nuevoUsuario.getRoles().contains("admin")) {
             	roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
             }
        }
       
       
        
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        response.put("mensaje","usuario guardado");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
    	
    	Map<String, Object> response = new HashMap<>();
    	
        if(bindingResult.hasErrors()) {
        	response.put("mensaje","campos mal puestos");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
          
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
