package com.krakedev.veterinaria.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.veterinaria.entity.EstadoMascota;
import com.krakedev.veterinaria.entity.Mascota;
import com.krakedev.veterinaria.repository.MascotaRepository;
import com.krakedev.veterinaria.service.MascotaService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService{

    private final MascotaRepository mascotaRepository;

    @Override
    public Mascota registrarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> buscarPorNombre(String nombre) {
        Optional<Mascota> mascotaExistente = mascotaRepository.findByNombre(nombre);
        return mascotaExistente;
    }

    @Override
    public Optional<Mascota> buscarPorId(Long id) {
        Optional<Mascota> mascotaExistente = mascotaRepository.findById(id);
        return mascotaExistente;
    }

    @Override
    @SneakyThrows
    public Mascota actualizarMascota(Long id, Mascota mascota) {
       Mascota mascotaExistente = mascotaRepository.findById(id).orElseThrow(()-> new Exception("No se encontro una mascota con este ID: " + id));
        mascotaExistente.setEdad(mascota.getEdad());
        mascotaExistente.setEspecie(mascota.getEspecie());
        mascotaExistente.setFechaRegistro(mascota.getFechaRegistro());
        mascotaExistente.setNombre(mascota.getNombre());
        mascotaExistente.setNombreDueno(mascota.getNombreDueno());
       return mascotaRepository.save(mascotaExistente);
    }

    @Override
    @SneakyThrows
    public void eliminarMascota(Long id) {
        Mascota mascotaExistente = mascotaRepository.findById(id).orElseThrow(()-> new Exception("No se encontro una mascota con este ID: " + id));
        mascotaRepository.delete(mascotaExistente);
    }

    @Override
    @SneakyThrows
    public Mascota cambiarEstadoMascota(Long idMascota, EstadoMascota nuevoEstado) {
        Mascota mascotaExistente = mascotaRepository.findById(idMascota).orElseThrow(() -> new Exception("Mascota con ID: " + idMascota + " no encontrado"));
        mascotaExistente.setEstadoMascota(nuevoEstado);
        return mascotaRepository.save(mascotaExistente);
    }

    @Override
    public List<Mascota> obtenerPorEstadoMascota(EstadoMascota estadoMascota) {
        return mascotaRepository.findByEstadoMascota(estadoMascota);
    }


}
