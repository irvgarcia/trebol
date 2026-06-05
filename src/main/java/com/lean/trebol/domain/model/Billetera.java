package com.lean.trebol.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Billetera {

    private UUID id;

    private UUID usuario_id;

    private BigDecimal saldo_disponible;

    private BigDecimal saldo_retenido;

    public Billetera() {
    }

//    public Billetera(UUID usuario_id) {
//        this(usuario_id, BigDecimal.ZERO, BigDecimal.ZERO);
//    }

    public Billetera(UUID usuario_id, BigDecimal saldo_disponible, BigDecimal saldo_retenido) {
        if (usuario_id == null) throw new IllegalArgumentException("El ID de usuario es obligatorio");
        if (saldo_disponible == null || saldo_disponible.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("El saldo disponible no puede ser negativo");
        if (saldo_retenido == null || saldo_retenido.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("El saldo retenido no puede ser negativo");

        this.id = UUID.randomUUID();
        this.usuario_id = usuario_id;
        this.saldo_disponible = saldo_disponible;
        this.saldo_retenido = saldo_retenido;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUsuario_id() {
        return usuario_id;
    }

    public BigDecimal getSaldo_disponible() {
        return saldo_disponible;
    }

    public BigDecimal getSaldo_retenido() {
        return saldo_retenido;
    }
}
