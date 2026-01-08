package com.krakedev.veterinaria.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/mascotas")
@RequiredArgsConstructor
public class MascotaController {
    private final MascotaService mascotaService;

    @GetMapping()
    public ResponseEntity<List<Mascota>> listarMascota(){
        List<Mascota> mascotas = mascotaService.listarMascotas();
        return ResponseEntity.ok(mascotas);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> crearMascota(@RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.registrarMascota(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
    }
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre){
        Optional<Mascota> mascotaExistente = mascotaService.buscarPorNombre(nombre);
        return mascotaExistente.isPresent() ? ResponseEntity.ok(mascotaExistente.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada");
    }
    
    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Mascota> mascotaExistente = mascotaService.buscarPorId(id);
        return mascotaExistente.isPresent() ? ResponseEntity.ok(mascotaExistente.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada");
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        Mascota mascotaActualizada = new Mascota();
        try {
            mascotaActualizada.setEdad(mascota.getEdad());
            mascotaActualizada.setEspecie(mascota.getEspecie());
            mascotaActualizada.setFechaRegistro(mascota.getFechaRegistro());
            mascotaActualizada.setNombre(mascota.getNombre());
            mascotaActualizada.setNombreDueno(mascota.getNombreDueno());

            Mascota mascotaBBDD = mascotaService.actualizarMascota(id, mascotaActualizada);
            return ResponseEntity.ok(mascotaBBDD);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMascotaPorId(@PathVariable Long id){
        try{
            mascotaService.eliminarMascota(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}
