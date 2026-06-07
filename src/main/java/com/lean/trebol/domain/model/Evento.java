package com.lean.trebol.domain.model;

import com.lean.trebol.domain.exception.EventoNoDisponibleException;
import com.lean.trebol.domain.model.enums.EstadoEvento;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Evento {

    private final UUID id;
    private String nombre;
    private final LocalDateTime fechaInicio;
    private EstadoEvento estado;
    private final LocalDateTime createdAt;

    private Evento(UUID id, String nombre, LocalDateTime fechaInicio, EstadoEvento estado, LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id);
        this.nombre = validarNombre(nombre);
        this.fechaInicio = Objects.requireNonNull(fechaInicio, "fechaInicio requerida");
        this.estado = Objects.requireNonNull(estado);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    // ── Factory methods ────────────────────────────────────────────────────────

    public static Evento crear(String nombre, LocalDateTime fechaInicio) {
        if (fechaInicio.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "No se puede crear un evento con fecha en el pasado: " + fechaInicio
            );
        }
        return new Evento(UUID.randomUUID(), nombre, fechaInicio, EstadoEvento.PROXIMO, LocalDateTime.now());
    }

    public static Evento reconstituir(UUID id, String nombre, LocalDateTime fechaInicio, EstadoEvento estado, LocalDateTime createdAt) {
        return new Evento(id, nombre, fechaInicio, estado, createdAt);
    }

    // ── Comportamiento de dominio ──────────────────────────────────────────────

    public void iniciar() {
        if (estado != EstadoEvento.PROXIMO) {
            throw new EventoNoDisponibleException(
                    "Solo se puede iniciar un evento PROXIMO. Estado actual: " + estado
            );
        }
        this.estado = EstadoEvento.EN_CURSO;
    }

    public void finalizar() {
        if (estado != EstadoEvento.EN_CURSO) {
            throw new EventoNoDisponibleException(
                    "Solo se puede finalizar un evento EN_CURSO. Estado actual: " + estado
            );
        }
        this.estado = EstadoEvento.FINALIZADO;
    }

    public void cancelar() {
        if (estado == EstadoEvento.FINALIZADO || estado == EstadoEvento.CANCELADO) {
            throw new EventoNoDisponibleException(
                    "No se puede cancelar un evento " + estado
            );
        }
        this.estado = EstadoEvento.CANCELADO;
    }

    /** Verifica si el evento permite nuevas apuestas. */
    public boolean aceptaApuestas() {
        return estado == EstadoEvento.PROXIMO &&
                fechaInicio.isAfter(LocalDateTime.now());
    }

    // ── Getters ────────────────────────────────────────────────────────────────

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public EstadoEvento getEstado() { return estado; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    private String validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del evento no puede estar vacío");
        }
        return nombre.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;
        return id.equals(((Evento) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Evento{id=" + id + ", nombre='" + nombre + "', estado=" + estado + "}";
    }
}
