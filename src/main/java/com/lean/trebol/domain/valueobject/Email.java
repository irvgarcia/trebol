package com.lean.trebol.domain.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Email {

    private static final Pattern PATRON = Pattern.compile(
            "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"
    );

    private final String valor;

    public Email(String valor) {
        Objects.requireNonNull(valor, "El email no puede ser null");
        String normalizado = valor.trim().toLowerCase();
        if (!PATRON.matcher(normalizado).matches()) {
            throw new IllegalArgumentException("Formato de email inválido: " + valor);
        }
        this.valor = normalizado;
    }

    public String valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        return valor.equals(((Email) o).valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
