package com.lean.trebol.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Dinero {

    public static final Dinero CERO = new Dinero(BigDecimal.ZERO);

    private static final int ESCALA = 4;
    private static final RoundingMode REDONDEO = RoundingMode.HALF_UP;

    private final BigDecimal valor;

    public Dinero(BigDecimal valor) {
        Objects.requireNonNull(valor, "El valor monetario no puede ser null");
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Un monto monetario no puede ser negativo: " + valor
            );
        }
        this.valor = valor.setScale(ESCALA, REDONDEO);
    }

    public Dinero(String valor) {
        this(new BigDecimal(valor));
    }

    public Dinero sumar(Dinero otro) {
        return new Dinero(this.valor.add(otro.valor));
    }

    public Dinero restar(Dinero otro) {
        return new Dinero(this.valor.subtract(otro.valor));
    }

    public Dinero multiplicar(BigDecimal factor) {
        return new Dinero(this.valor.multiply(factor).setScale(ESCALA, REDONDEO));
    }

    public boolean esMayorOIgualQue(Dinero otro) {
        return this.valor.compareTo(otro.valor) >= 0;
    }

    public boolean esCero() {
        return this.valor.compareTo(BigDecimal.ZERO) == 0;
    }

    public BigDecimal valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dinero)) return false;
        Dinero dinero = (Dinero) o;
        return valor.equals(dinero.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return "$" + valor.toPlainString();
    }
}
