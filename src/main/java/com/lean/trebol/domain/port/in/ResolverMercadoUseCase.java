package com.lean.trebol.domain.port.in;

import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de resolución de un mercado.
 */
public interface ResolverMercadoUseCase {

    /**
     * Define el resultado ganador de un mercado y dispara la resolución de sus apuestas.
     * 
     * @param mercadoId ID del mercado.
     * @param seleccionGanadora El resultado oficial (ej: "Local").
     */
    void ejecutar(UUID mercadoId, String seleccionGanadora);
}
