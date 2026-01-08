package com.krakedev.veterinaria.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet", nullable = false)
    private Long id;
    @Column(name= "name", nullable = false)
    private String nombre;
    @Column(name = "species", nullable = false)
    private String especie;
    @Column(name = "age", nullable = false)
    private int edad;
    @Column(name = "owner_name")
    private String nombreDueno;
    @Column(name = "register_date")
    private LocalDate fechaRegistro;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EstadoMascota estadoMascota;
}
