package com.lean.trebol.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Cuota {

    private static final BigDecimal VALOR_MINIMO = new BigDecimal("1.01");

    private final UUID id;
    private final UUID mercadoId;
    private final String seleccion;
    private BigDecimal valor;               // mutable — las cuotas cambian
    private final LocalDateTime creadaEn;
    private LocalDateTime actualizadaEn;

    private Cuota(UUID id, UUID mercadoId, String seleccion,
                  BigDecimal valor, LocalDateTime creadaEn, LocalDateTime actualizadaEn) {
        this.id = Objects.requireNonNull(id);
        this.mercadoId = Objects.requireNonNull(mercadoId, "mercadoId requerido");
        this.seleccion = validarSeleccion(seleccion);
        this.valor = validarValor(valor);
        this.creadaEn = Objects.requireNonNull(creadaEn);
        this.actualizadaEn = Objects.requireNonNull(actualizadaEn);
    }

    public static Cuota crear(UUID mercadoId, String seleccion, BigDecimal valor) {
        LocalDateTime ahora = LocalDateTime.now();
        return new Cuota(UUID.randomUUID(), mercadoId, seleccion, valor, ahora, ahora);
    }

    public static Cuota reconstituir(UUID id, UUID mercadoId, String seleccion,
                                     BigDecimal valor, LocalDateTime creadaEn,
                                     LocalDateTime actualizadaEn) {
        return new Cuota(id, mercadoId, seleccion, valor, creadaEn, actualizadaEn);
    }

    public void actualizarValor(BigDecimal nuevoValor) {
        this.valor = validarValor(nuevoValor);
        this.actualizadaEn = LocalDateTime.now();
    }

    // ── Getters ────────────────────────────────────────────────────────────────

    public UUID getId() { return id; }
    public UUID getMercadoId() { return mercadoId; }
    public String getSeleccion() { return seleccion; }
    public BigDecimal getValor() { return valor; }
    public LocalDateTime getCreadaEn() { return creadaEn; }
    public LocalDateTime getActualizadaEn() { return actualizadaEn; }

    // ── Validaciones internas ──────────────────────────────────────────────────

    private BigDecimal validarValor(BigDecimal valor) {
        Objects.requireNonNull(valor, "El valor de la cuota no puede ser null");
        if (valor.compareTo(VALOR_MINIMO) < 0) {
            throw new IllegalArgumentException(
                    "El valor de la cuota debe ser >= " + VALOR_MINIMO + ". Recibido: " + valor
            );
        }
        return valor;
    }

    private String validarSeleccion(String seleccion) {
        if (seleccion == null || seleccion.isBlank()) {
            throw new IllegalArgumentException("La selección de la cuota no puede estar vacía");
        }
        return seleccion.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuota)) return false;
        return id.equals(((Cuota) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Cuota{seleccion='" + seleccion + "', valor=" + valor + "}";
    }
}
