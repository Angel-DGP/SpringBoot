package com.krakedev.veterinaria.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.veterinaria.entity.Mascota;
import com.krakedev.veterinaria.service.MascotaService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/mascotas")
@RequiredArgsConstructor
public class MascotaController {
    private final MascotaService mascotaService;

    @GetMapping()
    public ResponseEntity<List<Mascota>> listarMascota(){
        List<Mascota> mascotas = mascotaService.listarMascotas();
        return ResponseEntity.ok(mascotas);
    }/*
    @GetMapping
    public List<Mascota> listarMascotas(){
        return mascotas;
    }
    @GetMapping("/{id}")
    public Mascota obtenerMascotaPorID(@PathVariable Long id){
        Optional<Mascota> mascotaObtenida = mascotas.stream().filter(m -> m.getId() == id).findFirst();
        return mascotaObtenida.orElse(null);
    }*/
    @PostMapping("/crear")
    public ResponseEntity<?> crearMascota(@RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.registrarMascota(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
    }/* 
    @DeleteMapping("/{id}")
    public void eliminarMascota(@PathVariable int id){
        mascotas.removeIf(m -> m.getId() == id);    
    }*/
    
}
