package com.lean.trebol.domain.model;

import com.lean.trebol.domain.model.enums.TipoTransaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {

    private UUID id;

    private UUID billetera_id;

    private TipoTransaccion tipo;

    private BigDecimal monto;

    private LocalDateTime fecha;

    public Transaccion() {
    }

    public Transaccion(UUID billetera_id, TipoTransaccion tipo, BigDecimal monto) {
        if (billetera_id == null) throw new IllegalArgumentException("El ID de billetera es obligatorio");
        if (tipo == null) throw new IllegalArgumentException("El tipo de transacción es obligatorio");
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("El monto debe ser mayor a cero");

        this.id = UUID.randomUUID();
        this.billetera_id = billetera_id;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getBilletera_id() {
        return billetera_id;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
