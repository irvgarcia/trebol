package com.lean.trebol.domain.model;

import com.lean.trebol.domain.model.enums.EstadoApuesta;
import com.lean.trebol.domain.valueobject.Dinero;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Apuesta {

    private final UUID id;
    private final UUID usuarioId;
    private final UUID mercadoId;           // FK: ¿en qué apostaste?
    private final UUID cuotaId;             // FK: ¿a qué cuota (trazabilidad)?
    private final String seleccionElegida;  // copia de Cuota.seleccion al momento de apostar
    private final Dinero monto;
    private final BigDecimal cuotaSnapshot; // valor congelado — nunca cambia
    private EstadoApuesta estado;
    private final LocalDateTime fecha;

    private Apuesta(UUID id, UUID usuarioId, UUID mercadoId, UUID cuotaId,
                    String seleccionElegida, Dinero monto, BigDecimal cuotaSnapshot,
                    EstadoApuesta estado, LocalDateTime fecha) {
        this.id = Objects.requireNonNull(id);
        this.usuarioId = Objects.requireNonNull(usuarioId, "usuarioId requerido");
        this.mercadoId = Objects.requireNonNull(mercadoId, "mercadoId requerido");
        this.cuotaId = Objects.requireNonNull(cuotaId, "cuotaId requerido");
        this.seleccionElegida = Objects.requireNonNull(seleccionElegida, "seleccionElegida requerida");
        this.monto = Objects.requireNonNull(monto);
        this.cuotaSnapshot = validarCuota(cuotaSnapshot);
        this.estado = Objects.requireNonNull(estado);
        this.fecha = Objects.requireNonNull(fecha);
    }

    public static Apuesta crear(UUID usuarioId, UUID mercadoId, Cuota cuota, Dinero monto) {
        Objects.requireNonNull(cuota, "La cuota no puede ser null");
        return new Apuesta(
                UUID.randomUUID(),
                usuarioId,
                mercadoId,
                cuota.getId(),
                cuota.getSeleccion(),       // copia de la selección
                monto,
                cuota.getValor(),           // ← congelado aquí, en este instante
                EstadoApuesta.ACTIVA,
                LocalDateTime.now()
        );
    }

    public static Apuesta reconstituir(UUID id, UUID usuarioId, UUID mercadoId, UUID cuotaId, String seleccionElegida,
                                       Dinero monto, BigDecimal cuotaSnapshot,
                                       EstadoApuesta estado, LocalDateTime fecha) {
        return new Apuesta(id, usuarioId, mercadoId, cuotaId, seleccionElegida, monto, cuotaSnapshot, estado, fecha);
    }

    public Dinero calcularGananciaBruta() {
        return monto.multiplicar(cuotaSnapshot);
    }

    public Dinero calcularGananciaNeta() {
        BigDecimal beneficioFactor = cuotaSnapshot.subtract(BigDecimal.ONE);
        return monto.multiplicar(beneficioFactor);
    }

    public void resolver(String seleccionGanadora) {
        if (estado != EstadoApuesta.ACTIVA) {
            throw new IllegalStateException(
                    "Solo se puede resolver una apuesta ACTIVA. Estado: " + estado
            );
        }
        Objects.requireNonNull(seleccionGanadora, "La selección ganadora no puede ser null");

        if (this.seleccionElegida.equalsIgnoreCase(seleccionGanadora.trim())) {
            this.estado = EstadoApuesta.GANADA;
        } else {
            this.estado = EstadoApuesta.PERDIDA;
        }
    }

    public void cancelar() {
        if (estado != EstadoApuesta.ACTIVA) {
            throw new IllegalStateException("Solo se puede cancelar una apuesta ACTIVA. Estado: " + estado);
        }
        this.estado = EstadoApuesta.CANCELADA;
    }

    public boolean isGanada() { return estado == EstadoApuesta.GANADA; }
    public boolean isCancelada() { return estado == EstadoApuesta.CANCELADA; }
    public boolean isActiva() { return estado == EstadoApuesta.ACTIVA; }

    // ── Getters ────────────────────────────────────────────────────────────────

    public UUID getId() { return id; }
    public UUID getUsuarioId() { return usuarioId; }
    public UUID getMercadoId() { return mercadoId; }
    public UUID getCuotaId() { return cuotaId; }
    public String getSeleccionElegida() { return seleccionElegida; }
    public Dinero getMonto() { return monto; }
    public BigDecimal getCuotaSnapshot() { return cuotaSnapshot; }
    public EstadoApuesta getEstado() { return estado; }
    public LocalDateTime getFecha() { return fecha; }

    // ── Validación ─────────────────────────────────────────────────────────────

    private BigDecimal validarCuota(BigDecimal cuota) {
        Objects.requireNonNull(cuota, "El cuotaSnapshot no puede ser null");
        if (cuota.compareTo(new BigDecimal("1.01")) < 0) {
            throw new IllegalArgumentException(
                    "cuotaSnapshot inválido: " + cuota + ". Debe ser >= 1.01"
            );
        }
        return cuota.setScale(4, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apuesta)) return false;
        return id.equals(((Apuesta) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Apuesta{id=" + id +
                ", monto=" + monto +
                ", cuotaSnapshot=" + cuotaSnapshot +
                ", seleccion='" + seleccionElegida + "'" +
                ", estado=" + estado + "}";
    }
}
