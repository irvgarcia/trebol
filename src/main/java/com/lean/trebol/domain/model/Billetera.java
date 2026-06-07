package com.lean.trebol.domain.model;

import com.lean.trebol.domain.exception.SaldoInsuficienteException;
import com.lean.trebol.domain.valueobject.Dinero;

import java.util.UUID;
import java.util.Objects;

public class Billetera {

    private final UUID id;
    private final UUID usuarioId;
    private Dinero saldoDisponible;
    private Dinero saldoRetenido;

    private Billetera(UUID id, UUID usuarioId,
                      Dinero saldoDisponible, Dinero saldoRetenido) {
        this.id = Objects.requireNonNull(id);
        this.usuarioId = Objects.requireNonNull(usuarioId);
        this.saldoDisponible = Objects.requireNonNull(saldoDisponible);
        this.saldoRetenido = Objects.requireNonNull(saldoRetenido);
    }

    public static Billetera crear(UUID usuarioId) {
        return new Billetera(UUID.randomUUID(), usuarioId, Dinero.CERO, Dinero.CERO);
    }

    public static Billetera reconstituir(UUID id, UUID usuarioId, Dinero saldoDisponible, Dinero saldoRetenido) {
        return new Billetera(id, usuarioId, saldoDisponible, saldoRetenido);
    }

    public void depositar(Dinero monto) {
        validarMontoPositivo(monto, "depositar");
        this.saldoDisponible = saldoDisponible.sumar(monto);
    }

    public void retirar(Dinero monto) {
        validarMontoPositivo(monto, "retirar");
        if (!saldoDisponible.esMayorOIgualQue(monto)) {
            throw new SaldoInsuficienteException(
                    "Saldo disponible insuficiente para retirar. " +
                            "Disponible: " + saldoDisponible + ", solicitado: " + monto
            );
        }
        this.saldoDisponible = saldoDisponible.restar(monto);
    }

    public void retener(Dinero monto) {
        validarMontoPositivo(monto, "retener");
        if (!saldoDisponible.esMayorOIgualQue(monto)) {
            throw new SaldoInsuficienteException(
                    "Saldo disponible insuficiente para apostar. " +
                            "Disponible: " + saldoDisponible + ", apuesta: " + monto
            );
        }
        this.saldoDisponible = saldoDisponible.restar(monto);
        this.saldoRetenido = saldoRetenido.sumar(monto);
    }

    public void liberar(Dinero monto) {
        validarMontoPositivo(monto, "liberar");
        this.saldoRetenido = saldoRetenido.restar(monto);
        this.saldoDisponible = saldoDisponible.sumar(monto);
    }

    public void acreditarGanancia(Dinero montoApostado, Dinero gananciaNeta) {
        // Primero liberar lo retenido
        this.saldoRetenido = saldoRetenido.restar(montoApostado);
        // Luego acreditar: monto original + ganancia neta
        this.saldoDisponible = saldoDisponible.sumar(montoApostado).sumar(gananciaNeta);
    }

    public Dinero saldoTotal() {
        return saldoDisponible.sumar(saldoRetenido);
    }

    private void validarMontoPositivo(Dinero monto, String operacion) {
        if (monto.esCero()) {
            throw new IllegalArgumentException(
                    "El monto para '" + operacion + "' debe ser mayor que cero"
            );
        }
    }

    // ── Getters ────────────────────────────────────────────────────────────────

    public UUID getId() { return id; }
    public UUID getUsuarioId() { return usuarioId; }
    public Dinero getSaldoDisponible() { return saldoDisponible; }
    public Dinero getSaldoRetenido() { return saldoRetenido; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Billetera)) return false;
        return id.equals(((Billetera) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Billetera{id=" + id +
                ", disponible=" + saldoDisponible +
                ", retenido=" + saldoRetenido + "}";
    }
}
