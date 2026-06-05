package com.lean.trebol.domain.model;

import com.lean.trebol.domain.model.enums.EstadoEvento;

import java.time.LocalDateTime;
import java.util.UUID;

public class Evento {

    private UUID id;

    private String nombre;

    private LocalDateTime fecha_inicio;

    private EstadoEvento estado;

    public Evento() {
    }

    public Evento(String nombre, LocalDateTime fecha_inicio, EstadoEvento estado) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre del evento es obligatorio");
        if (fecha_inicio == null) throw new IllegalArgumentException("La fecha de inicio es obligatoria");
        if (estado == null) throw new IllegalArgumentException("El estado es obligatorio");

        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.estado = estado;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFecha_inicio() {
        return fecha_inicio;
    }

    public EstadoEvento getEstado() {
        return estado;
    }
}
