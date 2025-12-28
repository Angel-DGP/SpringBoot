package com.krakedev.veterinaria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.veterinaria.entity.Mascota;

@RestController
@RequestMapping("api/mascotas")
public class MascotaController {
    private List<Mascota> mascotas = new ArrayList<>();
    public MascotaController(){
        mascotas.add(new Mascota(1,"PEpe","Golden Retriever", 2, "Albert"));
        mascotas.add(new Mascota(2,"Jose","Husky", 1, "Adrian"));
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
}
