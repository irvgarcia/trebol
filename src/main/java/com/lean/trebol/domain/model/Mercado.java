package com.lean.trebol.domain.model;

import com.lean.trebol.domain.model.enums.EstadoMercado;

import java.util.UUID;

public class Mercado {

    private UUID id;

    private UUID evento_id;

    private String nombre;

    private EstadoMercado estado;

    public Mercado() {
    }

    public Mercado(UUID evento_id, String nombre, EstadoMercado estado) {
        if (evento_id == null) throw new IllegalArgumentException("El ID de evento es obligatorio");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre del mercado es obligatorio");
        if (estado == null) throw new IllegalArgumentException("El estado es obligatorio");

        this.id = UUID.randomUUID();
        this.evento_id = evento_id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public UUID getId() {
        return id;
    }

    public UUID getEvento_id() {
        return evento_id;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoMercado getEstado() {
        return estado;
    }
}
