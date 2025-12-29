package com.krakedev.veterinaria.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.veterinaria.entity.Mascota;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/mascotas")
public class MascotaController {
    private List<Mascota> mascotas = new ArrayList<>();
    public MascotaController(){
        mascotas.add(new Mascota(1,"PEpe","Golden Retriever", 2, "Albert", LocalDate.now()));
        mascotas.add(new Mascota(2,"Jose","Husky", 1, "Adrian", LocalDate.now()));
    }
    @GetMapping
    public List<Mascota> listarMascotas(){
        return mascotas;
    }
    @GetMapping("/{id}")
    public Mascota obtenerMascotaPorID(@PathVariable int id){
        Optional<Mascota> mascotaObtenida = mascotas.stream().filter(m -> m.getId() == id).findFirst();
        return mascotaObtenida.orElse(null);
    }
    @PostMapping("/crear")
    public Mascota crearMascota(@RequestBody Mascota mascota) {
        mascotas.add(mascota);
        return mascota;
    }
    @DeleteMapping("/{id}")
    public void eliminarMascota(@PathVariable int id){
        mascotas.removeIf(m -> m.getId() == id);
    }
    
}
