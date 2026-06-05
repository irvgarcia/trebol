package com.lean.trebol.domain.model;

import com.lean.trebol.domain.model.enums.EstadoApuesta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Apuesta {

    private UUID id;

    private UUID usuario_id;

    private UUID mercado_id;

    private UUID cuota_id;

    private BigDecimal monto;

    private BigDecimal cuota_snapshot;

    private EstadoApuesta estado;

    private LocalDateTime fecha;

    public Apuesta() {
    }

    public Apuesta(UUID usuario_id, UUID mercado_id, UUID cuota_id, BigDecimal monto, BigDecimal cuota_snapshot, EstadoApuesta estado) {
        if (usuario_id == null) throw new IllegalArgumentException("El ID de usuario es obligatorio");
        if (mercado_id == null) throw new IllegalArgumentException("El ID de mercado es obligatorio");
        if (cuota_id == null) throw new IllegalArgumentException("El ID de cuota es obligatorio");
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("El monto debe ser mayor a cero");
        if (cuota_snapshot == null || cuota_snapshot.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("La cuota snapshot debe ser mayor a cero");
        if (estado == null) throw new IllegalArgumentException("El estado es obligatorio");

        this.id = UUID.randomUUID();
        this.usuario_id = usuario_id;
        this.mercado_id = mercado_id;
        this.cuota_id = cuota_id;
        this.monto = monto;
        this.cuota_snapshot = cuota_snapshot;
        this.estado = EstadoApuesta.ACTIVA;
        this.fecha = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getUsuario_id() {
        return usuario_id;
    }

    public UUID getMercado_id() {
        return mercado_id;
    }

    public UUID getCuota_id() {
        return cuota_id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public BigDecimal getCuota_snapshot() {
        return cuota_snapshot;
    }

    public EstadoApuesta getEstado() {
        return estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
