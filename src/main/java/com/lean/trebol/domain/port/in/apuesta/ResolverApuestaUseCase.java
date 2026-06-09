package com.lean.trebol.domain.port.in.apuesta;

import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de resolución de una apuesta.
 */
public interface ResolverApuestaUseCase {

    /**
     * Ejecuta la lógica para resolver una apuesta basándose en un resultado oficial.
     * 
     * @param apuestaId ID de la apuesta a resolver.
     * @param seleccionGanadora El resultado o selección que resultó ganadora en el mercado.
     */
    void ejecutar(UUID apuestaId, String seleccionGanadora);
}
