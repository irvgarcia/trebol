package com.lean.trebol.domain.model;

import com.lean.trebol.domain.model.enums.Seleccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Cuota {

    private UUID id;

    private UUID mercado_id;

    private Seleccion seleccion;

    private BigDecimal valor;

    private LocalDateTime vigente_desde;

    public Cuota() {
    }

    public Cuota(UUID mercado_id, Seleccion seleccion, BigDecimal valor) {
        if (mercado_id == null) throw new IllegalArgumentException("El ID de mercado es obligatorio");
        if (seleccion == null) throw new IllegalArgumentException("La selección es obligatoria");
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("El valor de la cuota debe ser mayor a cero");

        this.id = UUID.randomUUID();
        this.mercado_id = mercado_id;
        this.seleccion = seleccion;
        this.valor = valor;
        this.vigente_desde = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getMercado_id() {
        return mercado_id;
    }

    public Seleccion getSeleccion() {
        return seleccion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getVigente_desde() {
        return vigente_desde;
    }
}
