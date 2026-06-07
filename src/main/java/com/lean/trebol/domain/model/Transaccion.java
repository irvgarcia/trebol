package com.lean.trebol.domain.model;

import com.lean.trebol.domain.model.enums.TipoTransaccion;
import com.lean.trebol.domain.valueobject.Dinero;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaccion {

    private final UUID id;
    private final UUID billeteraId;
    private final TipoTransaccion tipo;
    private final Dinero monto;
    private final String descripcion;
    private final LocalDateTime fecha;

    private final UUID referenciaId;

    private Transaccion(UUID id, UUID billeteraId, TipoTransaccion tipo, Dinero monto, String descripcion, UUID referenciaId, LocalDateTime fecha) {
        this.id = Objects.requireNonNull(id);
        this.billeteraId = Objects.requireNonNull(billeteraId, "billeteraId requerido");
        this.tipo = Objects.requireNonNull(tipo, "tipo requerido");
        this.monto = Objects.requireNonNull(monto);
        this.descripcion = descripcion != null ? descripcion : "";
        this.referenciaId = referenciaId;   // puede ser null si no hay referencia
        this.fecha = Objects.requireNonNull(fecha);
    }

    public static Transaccion deposito(UUID billeteraId, Dinero monto) {
        return new Transaccion(
                UUID.randomUUID(), billeteraId,
                TipoTransaccion.DEPOSITO, monto,
                "Depósito de saldo", null, LocalDateTime.now()
        );
    }

    public static Transaccion retiro(UUID billeteraId, Dinero monto) {
        return new Transaccion(
                UUID.randomUUID(), billeteraId,
                TipoTransaccion.RETIRO, monto,
                "Retiro de saldo", null, LocalDateTime.now()
        );
    }

    public static Transaccion porApuesta(UUID billeteraId, Dinero monto, UUID apuestaId) {
        return new Transaccion(
                UUID.randomUUID(), billeteraId,
                TipoTransaccion.APUESTA, monto,
                "Apuesta colocada", apuestaId, LocalDateTime.now()
        );
    }

    public static Transaccion pago(UUID billeteraId, Dinero monto, UUID apuestaId) {
        return new Transaccion(
                UUID.randomUUID(), billeteraId,
                TipoTransaccion.PAGO, monto,
                "Pago de ganancia", apuestaId, LocalDateTime.now()
        );
    }

    public static Transaccion devolucion(UUID billeteraId, Dinero monto, UUID apuestaId) {
        return new Transaccion(
                UUID.randomUUID(), billeteraId,
                TipoTransaccion.PAGO, monto,
                "Devolución por cancelación", apuestaId, LocalDateTime.now()
        );
    }

    public static Transaccion reconstituir(UUID id, UUID billeteraId, TipoTransaccion tipo, Dinero monto, String descripcion, UUID referenciaId, LocalDateTime fecha) {
        return new Transaccion(id, billeteraId, tipo, monto, descripcion, referenciaId, fecha);
    }

    // ── Getters (sin setters — inmutable) ──────────────────────────────────────

    public UUID getId() { return id; }
    public UUID getBilleteraId() { return billeteraId; }
    public TipoTransaccion getTipo() { return tipo; }
    public Dinero getMonto() { return monto; }
    public String getDescripcion() { return descripcion; }
    public UUID getReferenciaId() { return referenciaId; }
    public LocalDateTime getFecha() { return fecha; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaccion)) return false;
        return id.equals(((Transaccion) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Transaccion{tipo=" + tipo +
                ", monto=" + monto +
                ", fecha=" + fecha + "}";
    }
}
