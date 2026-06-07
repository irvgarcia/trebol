package com.lean.trebol.domain.model;

import com.lean.trebol.domain.exception.MercadoCerradoException;
import com.lean.trebol.domain.model.enums.EstadoMercado;

import java.util.Objects;
import java.util.UUID;

public class Mercado {

    private final UUID id;
    private final UUID eventoId;
    private final String nombre;
    private EstadoMercado estado;
    private String seleccionGanadora;   // null hasta que se resuelve

    private Mercado(UUID id, UUID eventoId, String nombre, EstadoMercado estado, String seleccionGanadora) {
        this.id = Objects.requireNonNull(id);
        this.eventoId = Objects.requireNonNull(eventoId, "eventoId requerido");
        this.nombre = validarNombre(nombre);
        this.estado = Objects.requireNonNull(estado);
        this.seleccionGanadora = seleccionGanadora;
    }

    // ── Factory methods ────────────────────────────────────────────────────────

    public static Mercado crear(UUID eventoId, String nombre) {
        return new Mercado(UUID.randomUUID(), eventoId, nombre, EstadoMercado.ABIERTO, null);
    }

    public static Mercado reconstituir(UUID id, UUID eventoId, String nombre, EstadoMercado estado, String seleccionGanadora) {
        return new Mercado(id, eventoId, nombre, estado, seleccionGanadora);
    }

    public void cerrar() {
        if (estado != EstadoMercado.ABIERTO) {
            throw new MercadoCerradoException("Solo se puede cerrar un mercado ABIERTO. Estado actual: " + estado);
        }
        this.estado = EstadoMercado.CERRADO;
    }

    public void resolver(String seleccionGanadora) {
        if (estado == EstadoMercado.RESUELTO) {
            throw new MercadoCerradoException("El mercado ya fue resuelto");
        }
        if (seleccionGanadora == null || seleccionGanadora.isBlank()) {
            throw new IllegalArgumentException("La selección ganadora no puede estar vacía");
        }
        this.estado = EstadoMercado.RESUELTO;
        this.seleccionGanadora = seleccionGanadora.trim();
    }

    public boolean estaAbierto() {
        return estado == EstadoMercado.ABIERTO;
    }

    public boolean estaResuelto() {
        return estado == EstadoMercado.RESUELTO;
    }

    // ── Getters ────────────────────────────────────────────────────────────────

    public UUID getId() { return id; }
    public UUID getEventoId() { return eventoId; }
    public String getNombre() { return nombre; }
    public EstadoMercado getEstado() { return estado; }
    public String getSeleccionGanadora() { return seleccionGanadora; }

    private String validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del mercado no puede estar vacío");
        }
        return nombre.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mercado)) return false;
        return id.equals(((Mercado) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Mercado{id=" + id + ", nombre='" + nombre + "', estado=" + estado + "}";
    }
}
